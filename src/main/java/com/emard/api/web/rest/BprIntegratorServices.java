package com.emard.api.web.rest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.emard.api.domain.BankInfos;
import com.emard.api.domain.Bp1Com;
import com.emard.api.domain.Bp1His;
import com.emard.api.domain.Bp1Infos;
import com.emard.api.domain.Bp2Com;
import com.emard.api.domain.Bp2Infos;
import com.emard.api.domain.Bp3His;
import com.emard.api.domain.Bp3Infos;
import com.emard.api.domain.Bp4Infos;
import com.emard.api.domain.ChargeFile;
import com.emard.api.domain.CrpAtr;
import com.emard.api.domain.FilesInfos;
import com.emard.api.generator.BP1infosGenerator;
import com.emard.api.generator.BP2infosGenerator;
import com.emard.api.generator.BP3infosGenerator;
import com.emard.api.generator.BP4infosGenerator;
import com.emard.api.generator.CRPinfosGenerator;
import com.emard.api.repository.Bp2InfosRepository;
import com.emard.api.repository.FilesInfosRepository;
import com.emard.api.service.BankInfosService;
import com.emard.api.service.Bp1ComService;
import com.emard.api.service.Bp1HisService;
import com.emard.api.service.Bp1InfosService;
import com.emard.api.service.Bp2ComService;
import com.emard.api.service.Bp2InfosService;
import com.emard.api.service.Bp3HisService;
import com.emard.api.service.Bp3InfosService;
import com.emard.api.service.Bp4InfosService;
import com.emard.api.service.ChargeFileService;
import com.emard.api.service.CrpAtrService;
import com.emard.api.service.FilesInfosService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import javax.persistence.Convert;

import org.apache.commons.collections4.IterableUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import jdk.internal.jline.internal.Log;
/**
 *
 * @author Bouna
 */
@RestController
@RequestMapping("/api/bpr")
public class BprIntegratorServices {

    private final Logger log = LoggerFactory.getLogger(BprIntegratorServices.class);

    @Autowired
    Bp1ComService bp1ComService;

    @Autowired
    Bp1HisService bp1HisService;

    @Autowired
    Bp2ComService bp2ComService;

    @Autowired
    Bp3HisService bp3HisService;

    @Autowired
    ChargeFileService chargeFileService;

    @Autowired
    FilesInfosRepository fileinfosService;

    @Autowired
    BankInfosService bankInfoService;

    @Autowired
    CrpAtrService crpAtrService;

    @GetMapping(value = "/generated/file2Bp/")
    public ResponseEntity<String> importExcelFiles() throws IOException, ParseException {
        //Iterable<Long>itrbl = null
        //System.out.println("AVANT "+idFile+ "dateref = "+dateRef);
        Pageable pag = PageRequest.of(0, 100);
        Optional<FilesInfos> fileInfos = fileinfosService.findById(new Long(2));
        Optional<BankInfos> bankInfos = bankInfoService.findOne(1l);

        ByteArrayInputStream in = null;

        if (fileInfos != null) {
            String pathOutPutFile = fileInfos.get().getInputPath();

            File bpFile = new File(pathOutPutFile);
            File repArch = new File(pathOutPutFile + "\\archives");
            repArch.mkdir();
            String codeIdBank = bankInfos.get().getCodeId();
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
            //formatter = formatter.withLocale(Locale.getDefault());  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
            //LocalDate dateFormater = LocalDate.parse(dateRef, formatter);
            //System.out.println("formater="+dateFormater);
            File[] matchingFiles = bpFile.listFiles();
            Resource resource = null;
            Bp1Com bp1Com = new Bp1Com();
            Bp2Com bp2Com = new Bp2Com();
            Bp3His bp3His = new Bp3His();
            CrpAtr crpAtr = new CrpAtr();
            ChargeFile chargeFile = new ChargeFile();
            
            //File[] files = bpFile.listFiles();
            File dir = new File(pathOutPutFile);
            File[] liste = dir.listFiles();
            Iterable<ChargeFile> chargeFiles = chargeFileService.findAll(pag);
            if(!IterableUtils.isEmpty(chargeFiles)){
                for (ChargeFile chFile : chargeFiles) {
                    System.out.println("FILE ID " + chFile.getId());
                    chargeFileService.delete(chFile.getId());
                    System.out.println("AFTER DELETE CHARGEFILE");
            }
            }
            

            for (File item : liste) {
                String nomfic = "";
                if (item.isFile()) {
                    nomfic = item.getName();
                    System.out.format("Nom du fichier: %s%n", nomfic);
                    /* Initialisation*/
                    
                            
                   /* for (ChargeFile chFile : chargeFiles) {
                        if (chFile.getNomFic() != null && chFile.getNomFic().equals(item.getName())) {
                            System.out.println("FILE ID " + chFile.getId());
                            chargeFileService.delete(chFile.getId());
                            System.out.println("AFTER DELETE CHARGEFILE");
                        }
                    }*/

                    /*Fin Initialisation*/
                    Tableau(item.getAbsolutePath(), nomfic);
                    ChargeFile chf = new ChargeFile();
                    chf.setDateCharge(LocalDate.now());
                    chf.setNomFic(nomfic);
                    chargeFileService.save(chf);
                    System.out.println("AFTER SAVE " + chf.getNomFic());
                }
                /* else if(item.isDirectory())
        {
          System.out.format("Nom du répertoir: %s%n", item.getName()); 
        } */
            }
            /*File[] matches = bpFile.listFiles(new FilenameFilter()
            {
              public boolean accept(File dir, String name)
              {
                 return (name.startsWith("BP1_COM_") || name.startsWith("BP1_HIS_") 
                         || name.startsWith("BP2_COM_") || name.startsWith("BP3_HIS_")
                         || name.startsWith("CRP_ATR_"))
                         && (name.endsWith(".xlsx"));
              }
            });*/

        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE));
        return new ResponseEntity<String>("Chargement effectué : " + HttpStatus.OK.name(), HttpStatus.OK);
    }

    public static void copyFile(InputStream inStream, File bfile) {

        //InputStream inStream = null;
        OutputStream outStream = null;

        try {

            /*afile =new File("Afile.txt");
            bfile =new File("Bfile.txt");*/
            //inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            //System.out.println("File is copied successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String dateDebutPeriode(String dateRef) throws ParseException {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//yyyy-MM-dd
        //Date dt = sdf .parse(dateRef.substring(0,1)+"/"+dateRef.substring(2, 3)+"/"+dateRef.substring(4, 7));
        /*System.out.println("jj = "+dateRef.substring(6));
       System.out.println("mm = "+dateRef.substring(2,4));
       System.out.println("yyyy = "+dateRef.substring(0,4));*/
        Date dt = sdf.parse(dateRef);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        String firstDate = sdf.format(c.getTime());
        String[] tab = firstDate.split("-");
        //System.out.println("firstDate "+firstDate);
        //System.out.println("date deb ret = "+firstDate.substring(6)+"/"+firstDate.substring(2, 4)+"/"+firstDate.substring(0,4));
        //return firstDate.substring(4)+"/"+firstDate.substring(2, 4)+"/"+firstDate.substring(0,2);
        return tab[2] + "/" + tab[1] + "/" + tab[0];
    }

    public static String dateFinPeriode(String dateRef) throws ParseException {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//ddMMyyyy
        //System.out.println("jj = [{}]"+dateRef.substring(6));
        //System.out.println("mm = "+dateRef.substring(2,4));
        //System.out.println("yyyy = "+dateRef.substring(5));
        Date dt = sdf.parse(dateRef);
        //Date dt = sdf .parse(dateRef);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        //get last day of the month - add month, substract a day.
        /*c.add(Calendar.MONTH, 0);
       c.add(Calendar.DAY_OF_MONTH, 0);*/
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        String lastDate = sdf.format(c.getTime());
        //System.out.println("lastDate "+lastDate);
        //System.out.println("date fin ret = "+lastDate.substring(6)+"/"+lastDate.substring(2, 4)+"/"+lastDate.substring(0,4));
        String[] tab = lastDate.split("-");
        //return lastDate.substring(0,2)+"/"+lastDate.substring(2, 4)+"/"+lastDate.substring(4);
        return tab[2] + "/" + tab[1] + "/" + tab[0];
    }

    public void Tableau(String fileString, String fileName) {

        try {

            FileInputStream file = new FileInputStream(fileString);
            Pageable pag = PageRequest.of(0, 100);
            boolean isAlreadyCharge = false;
            Workbook workbook = WorkbookFactory.create(file);
            final Sheet sheet = workbook.getSheetAt(0);//workbook.getSheet(sheetName);
            int top = sheet.getFirstRowNum();
            int bottom = sheet.getLastRowNum();
            Row line = sheet.getRow(top);
            int start = line.getFirstCellNum();
            int end = line.getLastCellNum();
            int length = end - start;

            DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            CellStyle style;
            DataFormat format = workbook.createDataFormat();
            style = workbook.createCellStyle();
            style.setDataFormat(format.getFormat("0.0##############"));
            LocalDate dateArrete = LocalDate.now();
            System.out.println("start = " + start + " end = " + end + " length = " + length + " top =" + top + " bottom =" + bottom);
            while (length == 0) {
                top++;
                line = sheet.getRow(top);
                start = line.getFirstCellNum();
                end = line.getLastCellNum();
                length = end - start;
            }
            System.out.println("AFTER WHILE length = " + length);
            int hight = bottom - top;
            System.out.println("AFTER WHILE hight = " + hight);

             System.out.println("Nom "+fileName);
             if (fileName.startsWith("BP1_COM")){
                 dateArrete = chargeBP1Com(fileName, sheet, line, top, hight, dateformat);
             }else if (fileName.startsWith("BP2_COM")){
                 chargeBP2Com(fileName, sheet, line, top, hight, dateformat,dateArrete);
             }else if (fileName.startsWith("BP1_HIS")){
                 chargeBP1His(fileName, sheet, line, top, hight, dateformat,dateArrete);
             }else if (fileName.startsWith("BP3_HIS")){
                 chargeBP3His(fileName, sheet, line, top, hight, dateformat,dateArrete);
             }/*else if (fileName.startsWith("CRP_ATR")){
                 chargeCrpAtr(fileName, sheet, line, top, hight, dateformat,dateArrete);
             } */
            workbook.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LocalDate chargeBP1Com(String fileName, Sheet sheet, Row line, int top, int hight, DateTimeFormatter dateformat) {
        /**Initialisation de la table BP1 si fichier déjà chargé**/
        Pageable pag = PageRequest.of(0, 100000);
        LocalDate dateArrete = LocalDate.now();
        Iterable<Bp1Com> bp1Coms = bp1ComService.findAll(pag);
        for (Bp1Com bp1 : bp1Coms) {
            if (bp1.getNomFic() != null && bp1.getNomFic().equals(fileName)) {
                System.out.println("BP1 ID " + bp1.getId());
                bp1ComService.delete(bp1.getId());
                System.out.println("AFTER DELETE BP1COM ");
            }
        }

        for (int index = 0; index < hight; index++) {
            line = sheet.getRow(index + top + 1);
            int z = index + top + 1;
            System.out.println("index + top + 1 = " + z);
            //System.out.println("line = "+line);
            System.out.println("cellule " + z + " = " + line.getCell(4));
            System.out.println("devise " + z + " = " + line.getCell(1));
                             System.out.println("age "+line.getCell(0).getStringCellValue());
                 Bp1Com bp1Com = new Bp1Com();
              bp1Com.setAge(line.getCell(0)!=null ? line.getCell(0).getStringCellValue(): "");
              System.out.println("Dev "+line.getCell(1).getStringCellValue());
              bp1Com.setDev(line.getCell(1)!=null ? line.getCell(1).getStringCellValue(): "");
              System.out.println("Ncp "+line.getCell(2).getStringCellValue());
              bp1Com.setNcp(line.getCell(2)!=null ? line.getCell(2).getStringCellValue(): "");
              System.out.println("Inti "+line.getCell(3).getStringCellValue());
              bp1Com.setInti(line.getCell(3)!=null ? line.getCell(3).getStringCellValue(): "");
              System.out.println("Sde "+line.getCell(4).getNumericCellValue());
              Cell cellformat = line.getCell(4);
              String sde = Double.toString(cellformat.getNumericCellValue()).equals("0.0")?"0":Double.toString(cellformat.getNumericCellValue());
              System.out.println("Sdestr "+Double.valueOf(sde)+ "SDE "+Double.valueOf(sde).longValue());
              long sdeLong = Double.valueOf(sde).longValue();
              cellformat.setCellValue(sde);
              //cellformat.setCellStyle(style);
              bp1Com.setSde(Double.parseDouble(line.getCell(4)!=null ? String.valueOf(sdeLong) : "0"));
              bp1Com.setCha(line.getCell(5)!=null ? line.getCell(5).getStringCellValue(): "");
              bp1Com.setDou(LocalDate.parse(line.getCell(6)!=null ? (line.getCell(6).getStringCellValue()): "01/01/1999",dateformat));
              bp1Com.setDdd(LocalDate.parse(line.getCell(7)!=null ? (line.getCell(7).getStringCellValue()): "01/01/1999",dateformat));
              bp1Com.setDdc(LocalDate.parse(line.getCell(8)!=null ? (line.getCell(8).getStringCellValue()): "01/01/1999",dateformat));
              bp1Com.setDateArrete(LocalDate.parse(line.getCell(9)!=null ? (line.getCell(9).getStringCellValue()): "01/01/1999",dateformat));
              System.out.println("age "+line.getCell(0).getStringCellValue());
              System.out.println("Dev "+line.getCell(1).getStringCellValue());
              System.out.println("Ncp "+line.getCell(2).getStringCellValue());
              bp1Com.setNomFic(fileName);
              bp1ComService.save(bp1Com);
              System.out.println("AFTER SAVE bp1Com "+fileName);
              dateArrete = (bp1Com.getDateArrete()!=null ? bp1Com.getDateArrete() : dateArrete);

        }
        return dateArrete;
    }
    
    public void chargeBP2Com(String fileName, Sheet sheet, Row line, int top, int hight, DateTimeFormatter dateformat, LocalDate dateArrete) {
        /**Initialisation de la table BP2COM si fichier déjà chargé**/
        Pageable pag = PageRequest.of(0, 100000);
        Iterable<Bp2Com> bp2Coms = bp2ComService.findAll(pag);
        for (Bp2Com bp2Com : bp2Coms) {
            if (bp2Com.getNomFic() != null && bp2Com.getNomFic().equals(fileName)) {
                System.out.println("BP2COM ID " + bp2Com.getId());
                bp2ComService.delete(bp2Com.getId());
                System.out.println("AFTER DELETE BP2COM ");
            }
        }
        
        for (int index = 0; index < hight; index++) {
            line = sheet.getRow(index + top + 1);
            int z = index + top + 1;
            System.out.println("index + top + 1 = " + z);
            //System.out.println("line = "+line);
            System.out.println("cellule " + z + " = " + line.getCell(4));
            System.out.println("devise " + z + " = " + line.getCell(1));
                            Bp2Com bp2com = new Bp2Com();
              bp2com.setAge(line.getCell(0)!=null ? line.getCell(0).getStringCellValue(): "");
              bp2com.setDev(line.getCell(1)!=null ? line.getCell(1).getStringCellValue(): "");
              bp2com.setNcp(line.getCell(2)!=null ? line.getCell(2).getStringCellValue(): "");
              bp2com.setInti(line.getCell(3)!=null ? line.getCell(3).getStringCellValue(): "");
              Cell cellformat = line.getCell(4);
              String sde = Double.toString(cellformat.getNumericCellValue()).equals("0.0")?"0":Double.toString(cellformat.getNumericCellValue());
              System.out.println("Sdestr "+Double.valueOf(sde)+ "SDE "+Double.valueOf(sde).longValue());
              long sdeLong = Double.valueOf(sde).longValue();
              cellformat.setCellValue(sde);
              //cellformat.setCellStyle(style);
              bp2com.setSde(Double.parseDouble(line.getCell(4)!=null ? String.valueOf(sdeLong) : "0"));
              bp2com.setCha(line.getCell(5)!=null ? line.getCell(5).getStringCellValue(): "");              
              bp2com.setDou(LocalDate.parse(line.getCell(6)!=null ? (line.getCell(6).getStringCellValue()): "01/01/1999",dateformat));
              bp2com.setDdc(LocalDate.parse(line.getCell(7)!=null ? (line.getCell(7).getStringCellValue()): "01/01/1999",dateformat));
              bp2com.setDdd(LocalDate.parse(line.getCell(8)!=null ? (line.getCell(8).getStringCellValue()): "01/01/1999",dateformat));
              bp2com.setDateArrete(dateArrete);
              bp2com.setNomFic(fileName);
              bp2ComService.save(bp2com);               

        }
        
    }
    
    public void chargeBP1His(String fileName, Sheet sheet, Row line, int top, int hight, DateTimeFormatter dateformat, LocalDate dateArrete) {
        /**Initialisation de la table BP1HIS si fichier déjà chargé**/
        Pageable pag = PageRequest.of(0, 100000);
        Iterable<Bp1His> bp1Hiss = bp1HisService.findAll(pag);
        for (Bp1His bp1His : bp1Hiss) {
            if (bp1His.getNomFic() != null && bp1His.getNomFic().equals(fileName)) {
                System.out.println("BP1HIS ID " + bp1His.getId());
                bp1HisService.delete(bp1His.getId());
                System.out.println("AFTER DELETE BP1HIS ");
            }
        }
        for (int index = 0; index < hight; index++) {
            line = sheet.getRow(index + top + 1);
            int z = index + top + 1;
            System.out.println("index + top + 1 = " + z);
            //System.out.println("line = "+line);
            System.out.println("cellule " + z + " = " + line.getCell(4));
            System.out.println("devise " + z + " = " + line.getCell(1));
            Bp1His bp1his = new Bp1His();
              bp1his.setDco(LocalDate.parse(line.getCell(0)!=null ? (line.getCell(0).getStringCellValue()): "01/01/1999",dateformat));
              bp1his.setAge(line.getCell(1)!=null ? line.getCell(1).getStringCellValue(): "");
              bp1his.setDev(line.getCell(2)!=null ? line.getCell(2).getStringCellValue(): "");
              bp1his.setNcp(line.getCell(3)!=null ? line.getCell(3).getStringCellValue(): "");
              bp1his.setOpe(line.getCell(4)!=null ? line.getCell(4).getStringCellValue(): "");
              bp1his.setLib(line.getCell(5)!=null ? line.getCell(5).getStringCellValue(): "");
              Cell cellformat = line.getCell(6);
              String mon = Double.toString(cellformat.getNumericCellValue()).equals("0.0")?"0":Double.toString(cellformat.getNumericCellValue());
              System.out.println("Monstr "+Double.valueOf(mon)+ "MON "+Double.valueOf(mon).longValue());
              long monLong = Double.valueOf(mon).longValue();
              cellformat.setCellValue(mon);
              //cellformat.setCellStyle(style);
              bp1his.setMon(Double.parseDouble(line.getCell(6)!=null ? String.valueOf(monLong) : "0"));
              bp1his.setSen(line.getCell(7)!=null ? line.getCell(7).getStringCellValue(): "");              
              bp1his.setPie(line.getCell(8)!=null ? line.getCell(8).getStringCellValue(): "");        
              bp1his.setNcc(line.getCell(9)!=null ? line.getCell(9).getStringCellValue(): "");
              bp1his.setUti(line.getCell(10)!=null ? line.getCell(10).getStringCellValue(): "");
              bp1his.setUtf(line.getCell(11)!=null ? line.getCell(10).getStringCellValue(): "");
              bp1his.setNat(line.getCell(12)!=null ? line.getCell(12).getStringCellValue(): "");
              bp1his.setDateArrete(dateArrete);
              bp1his.setNomFic(fileName);
              bp1HisService.save(bp1his);        
              System.out.println("AFTER SAVE bp1his "+fileName);
        }
        
    }
    
        public void chargeBP3His(String fileName, Sheet sheet, Row line, int top, int hight, DateTimeFormatter dateformat, LocalDate dateArrete) {
        /**Initialisation de la table BP1 si fichier déjà chargé**/
        Pageable pag = PageRequest.of(0, 100000);
        Iterable<Bp3His> bp3Hiss = bp3HisService.findAll(pag);
        for (Bp3His bp3His : bp3Hiss) {
            if (bp3His.getNomFic() != null && bp3His.getNomFic().equals(fileName)) {
                System.out.println("BP3HIS ID " + bp3His.getId());
                bp1HisService.delete(bp3His.getId());
                System.out.println("AFTER DELETE BP3HIS ");
            }
        }
        for (int index = 0; index < hight; index++) {
            line = sheet.getRow(index + top + 1);
            int z = index + top + 1;
            System.out.println("index + top + 1 = " + z);
            //System.out.println("line = "+line);
            System.out.println("cellule " + z + " = " + line.getCell(4));
            System.out.println("devise " + z + " = " + line.getCell(1));
                      Bp3His bp3his = new Bp3His();
              //String dcoStr = ""+line.getCell(0).getStringCellValue();
              System.out.println("dateArrete "+dateArrete);
              bp3his.setDco(dateArrete);
              bp3his.setAge(line.getCell(1)!=null ? line.getCell(1).getStringCellValue(): "");
              bp3his.setDev(line.getCell(2)!=null ? line.getCell(2).getStringCellValue(): "");
              bp3his.setNcp(line.getCell(3)!=null ? line.getCell(3).getStringCellValue(): "");
              bp3his.setOpe(line.getCell(4)!=null ? line.getCell(4).getStringCellValue(): "");
              bp3his.setLib(line.getCell(5)!=null ? line.getCell(5).getStringCellValue(): "");
              Cell cellformat = line.getCell(6);
              String mon = Double.toString(cellformat.getNumericCellValue()).equals("0.0")?"0":Double.toString(cellformat.getNumericCellValue());
              System.out.println("Monstr "+Double.valueOf(mon)+ "MON "+Double.valueOf(mon).longValue());
              long monLong = Double.valueOf(mon).longValue();
              cellformat.setCellValue(mon);
              //cellformat.setCellStyle(style);
              bp3his.setMon(Double.parseDouble(line.getCell(6)!=null ? String.valueOf(monLong) : "0"));
              bp3his.setSen(line.getCell(7)!=null ? line.getCell(7).getStringCellValue(): "");              
              bp3his.setPie(line.getCell(8)!=null ? line.getCell(8).getStringCellValue(): "");        
              bp3his.setNcc(line.getCell(9)!=null ? line.getCell(9).getStringCellValue(): "");
              bp3his.setUti(line.getCell(10)!=null ? line.getCell(10).getStringCellValue(): "");
              bp3his.setUtf(line.getCell(11)!=null ? line.getCell(11).getStringCellValue(): "");
              bp3his.setNat(line.getCell(12)!=null ? line.getCell(12).getStringCellValue(): "");
              bp3his.setDateArrete(dateArrete);
              bp3his.setNomFic(fileName);
              bp3HisService.save(bp3his);                     

        }
        
    }
        
           public void chargeCrpAtr(String fileName, Sheet sheet, Row line, int top, int hight, DateTimeFormatter dateformat, LocalDate dateArrete) {
        /**Initialisation de la table BP1 si fichier déjà chargé**/
        Pageable pag = PageRequest.of(0, 100000);
         Iterable<CrpAtr> crpATrs = crpAtrService.findAll(pag);
        for (CrpAtr crpAtr : crpATrs) {
            if (crpAtr.getNomFic() != null && crpAtr.getNomFic().equals(fileName)) {
                System.out.println("crpAtr ID " + crpAtr.getId());
                crpAtrService.delete(crpAtr.getId());
                System.out.println("AFTER DELETE crpAtr ");
            }
        }
        for (int index = 0; index < hight; index++) {
            line = sheet.getRow(index + top + 1);
            int z = index + top + 1;
            System.out.println("index + top + 1 = " + z);
            //System.out.println("line = "+line);
            System.out.println("cellule " + z + " = " + line.getCell(4));
            System.out.println("devise " + z + " = " + line.getCell(1));
                           CrpAtr crpAtr = new CrpAtr();
              crpAtr.setCenr(line.getCell(0)!=null ? line.getCell(0).getStringCellValue(): "");
              crpAtr.setRefint(line.getCell(1)!=null ? line.getCell(1).getStringCellValue(): "");
              crpAtr.setNum(line.getCell(2)!=null ? line.getCell(2).getStringCellValue(): "");
              crpAtr.setNobor(line.getCell(3)!=null ? line.getCell(3).getStringCellValue(): "");
              crpAtr.setTypenr(line.getCell(4)!=null ? line.getCell(4).getStringCellValue(): "");
              crpAtr.setIdAtr(line.getCell(5)!=null ? line.getCell(5).getStringCellValue(): "");
              crpAtr.setDemAtr(LocalDate.parse(line.getCell(6)!=null ? (line.getCell(6).getStringCellValue()): "01/01/1999",dateformat));
              crpAtr.setNumAtr(line.getCell(7)!=null ? line.getCell(7).getStringCellValue(): "");
              crpAtr.setNomRes(line.getCell(8)!=null ? line.getCell(8).getStringCellValue(): "");
              crpAtr.setCpayIso(line.getCell(9)!=null ? line.getCell(9).getStringCellValue(): "");
              crpAtr.setDatope(LocalDate.parse(line.getCell(10)!=null ? (line.getCell(10).getStringCellValue()): "01/01/1999",dateformat));
              crpAtr.setRegt(line.getCell(11)!=null ? line.getCell(11).getStringCellValue(): "");
              crpAtr.setNocli(line.getCell(12)!=null ? line.getCell(12).getStringCellValue(): "");
              crpAtr.setCatRes(line.getCell(13)!=null ? line.getCell(13).getStringCellValue(): "");
              crpAtr.setCeco(line.getCell(14)!=null ? line.getCell(14).getStringCellValue(): "");
              crpAtr.setCpayEtg(line.getCell(15)!=null ? line.getCell(15).getStringCellValue(): "");
              crpAtr.setNatcpt(line.getCell(16)!=null ? line.getCell(16).getStringCellValue(): "");
              crpAtr.setSens(line.getCell(17)!=null ? line.getCell(17).getStringCellValue(): "");
              crpAtr.setDevn(line.getCell(18)!=null ? line.getCell(18).getStringCellValue(): "");
              Cell cellformat = line.getCell(19);
              String mon = Double.toString(cellformat.getNumericCellValue()).equals("0.0")?"0":Double.toString(cellformat.getNumericCellValue());
              System.out.println("Monstr "+Double.valueOf(mon)+ "MON "+Double.valueOf(mon).longValue());
              long monLong = Double.valueOf(mon).longValue();
              cellformat.setCellValue(mon);
              //cellformat.setCellStyle(style);
              crpAtr.setMdev(Double.parseDouble(line.getCell(19)!=null ? String.valueOf(monLong) : "0"));
              crpAtr.setTaux(Integer.parseInt(line.getCell(20)!=null ? line.getCell(20).getStringCellValue(): ""));
              Cell cellformatNat = line.getCell(21);
              String monNat = Double.toString(cellformatNat.getNumericCellValue()).equals("0.0")?"0":Double.toString(cellformatNat.getNumericCellValue());
              System.out.println("Monstr "+Double.valueOf(monNat)+ "MON "+Double.valueOf(monNat).longValue());
              long monLongNat = Double.valueOf(monNat).longValue();
              cellformat.setCellValue(monNat);
              //cellformat.setCellStyle(style);
              crpAtr.setMnat(Double.parseDouble(line.getCell(21)!=null ? String.valueOf(monLongNat) : "0"));
              crpAtr.setEtab(line.getCell(22)!=null ? line.getCell(22).getStringCellValue(): "");
              crpAtr.setDateArrete(dateArrete);
              crpAtrService.save(crpAtr);                   

        }
        
    }     
   
}
