/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emard.api.web.rest;

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
import com.emard.api.generator.CountryCode;
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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.apache.commons.collections4.IterableUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    Bp1InfosService bp1InfosService;

    @Autowired
    Bp2InfosService bp2InfosService;

    @Autowired
    Bp3InfosService bp3InfosService;
    
    @Autowired
    Bp4InfosService bp4InfosService;

    @GetMapping(value = "/generated/file2Bp/")
    public ResponseEntity<String> importExcelFiles() throws IOException, ParseException {
        // Iterable<Long>itrbl = null
        // System.out.println("AVANT "+idFile+ "dateref = "+dateRef);
        Pageable pag = PageRequest.of(0, 1000);
        Optional<FilesInfos> fileInfos = fileinfosService.findById(2l);
        Optional<BankInfos> bankInfos = bankInfoService.findOne(1l);

        ByteArrayInputStream in = null;

        if (fileInfos != null) {
            String pathOutPutFile = fileInfos.get().getInputPath();

            File bpFile = new File(pathOutPutFile);
            File repArch = new File(pathOutPutFile + "\\archives");
            repArch.mkdir();
            String codeIdBank = bankInfos.get().getCodeId();

            File[] matchingFiles = bpFile.listFiles();
            Resource resource = null;
            Bp1Com bp1Com = new Bp1Com();
            Bp2Com bp2Com = new Bp2Com();
            Bp3His bp3His = new Bp3His();
            CrpAtr crpAtr = new CrpAtr();
            ChargeFile chargeFile = new ChargeFile();

            // File[] files = bpFile.listFiles();
            File dir = new File(pathOutPutFile);
            File[] liste = dir.listFiles();
            Iterable<ChargeFile> chargeFiles = chargeFileService.findAll(pag);
            if (!IterableUtils.isEmpty(chargeFiles)) {
                for (ChargeFile chFile : chargeFiles) {
                    log.info("FILE ID [{}]" + chFile.getId());
                    chargeFileService.delete(chFile.getId());
                    log.info("AFTER DELETE CHARGEFILE");
                }
            }

            for (File item : liste) {
                String nomfic = "";
                if (item.isFile()) {
                    nomfic = item.getName();
                    log.info("Nom du fichier: [{}]", nomfic);

                    Tableau(item.getAbsolutePath(), nomfic);
                    ChargeFile chf = new ChargeFile();
                    chf.setDateCharge(LocalDate.now());
                    chf.setNomFic(nomfic);
                    chargeFileService.save(chf);
                    log.info("AFTER SAVE [{}]" + chf.getNomFic());
                }

            }

            /*saveBp1Infos();
            saveBp2Infos();
            saveBp3Infos();*/
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE));
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    public static void copyFile(InputStream inStream, File bfile) {

        // InputStream inStream = null;
        OutputStream outStream = null;

        try {

            outStream = new FileOutputStream(bfile);

            byte[] buffer = new byte[1024];

            int length;
            // copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            // System.out.println("File is copied successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String dateDebutPeriode(String dateRef) throws ParseException {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(dateRef);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        String firstDate = sdf.format(c.getTime());
        String[] tab = firstDate.split("-");

        return tab[2] + "/" + tab[1] + "/" + tab[0];
    }

    public static String dateFinPeriode(String dateRef) throws ParseException {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// ddMMyyyy

        Date dt = sdf.parse(dateRef);
        // Date dt = sdf .parse(dateRef);
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        // get last day of the month - add month, substract a day.
        /*
         * c.add(Calendar.MONTH, 0); c.add(Calendar.DAY_OF_MONTH, 0);
         */
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        String lastDate = sdf.format(c.getTime());

        String[] tab = lastDate.split("-");

        return tab[2] + "/" + tab[1] + "/" + tab[0];
    }

    public void Tableau(String fileString, String fileName) throws ParseException{

        try {

            FileInputStream file = new FileInputStream(fileString);
            Pageable pag = PageRequest.of(0, 100);
            boolean isAlreadyCharge = false;
            Workbook workbook = WorkbookFactory.create(file);
            final Sheet sheet = workbook.getSheetAt(0);// workbook.getSheet(sheetName);
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

            LocalDate dateArrete = recupDateArrete(fileName);//LocalDate.now();
            log.info("start = {}, end = {}, length = {}, top= {}, bottom = {}", start, end, length, top, bottom);
            while (length == 0) {
                top++;
                line = sheet.getRow(top);
                start = line.getFirstCellNum();
                end = line.getLastCellNum();
                length = end - start;
            }
            log.info("AFTER WHILE length = {}", length);
            int hight = bottom - top;
            log.info("AFTER WHILE hight = {}", hight);

            log.info("Nom {}", fileName);
            if (fileName.startsWith("BP1_COM")) {
                chargeBP1Com(fileName, sheet, line, top, hight, dateformat, dateArrete);
            } else if (fileName.startsWith("BP2_COM")) {
                chargeBP2Com(fileName, sheet, line, top, hight, dateformat, dateArrete);
            } else if (fileName.startsWith("BP1_HIS")) {
                chargeBP1His(fileName, sheet, line, top, hight, dateformat, dateArrete);
            } else if (fileName.startsWith("BP3_HIS")) {
                chargeBP3His(fileName, sheet, line, top, hight, dateformat, dateArrete);
            } else if (fileName.startsWith("CRP_ATR")) {
                chargeCrpAtr(fileName, sheet, line, top, hight, dateformat, dateArrete);
            }else if (fileName.startsWith("BP4_EMI") || fileName.startsWith("BP4_ACQ")) {
                chargeAndSaveBp4InfosEmisAcq(fileName, sheet, line, top, hight, dateformat, dateArrete);
            }
            workbook.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chargeBP1Com(String fileName, Sheet sheet, Row line, int top, int hight,
            DateTimeFormatter dateformat, LocalDate dateArrete) {
        /**
         * Initialisation de la table BP1 si fichier déjà chargé*
         */
        Pageable pag = PageRequest.of(0, 100000);

        log.info("BEFORE DELETE BP1COM " + fileName);
        bp1ComService.viderBp1Com(fileName);
        log.info("AFTER DELETE BP1COM " + fileName);
        for (int index = 0; index < hight; index++) {
            line = sheet.getRow(index + top + 1);
            int z = index + top + 1;
            log.info("index + top + 1 = {}", z);
            // System.out.println("line = "+line);
            log.info("cellule {}, line {}", z, line.getCell(4));
            log.info("devise {}, {}", z, line.getCell(1));
            log.info("age {}", line.getCell(0).getStringCellValue());
            Bp1Com bp1Com = new Bp1Com();
            bp1Com.setAge(line.getCell(0) != null ? line.getCell(0).getStringCellValue() : "");
            log.info("Dev {}", line.getCell(1).getStringCellValue());
            bp1Com.setDev(line.getCell(1) != null ? line.getCell(1).getStringCellValue() : "");
            log.info("Ncp {}", line.getCell(2).getStringCellValue());
            bp1Com.setNcp(line.getCell(2) != null ? line.getCell(2).getStringCellValue() : "");
            log.info("Inti {}", line.getCell(3) != null ? line.getCell(3).getStringCellValue() : "");
            bp1Com.setInti(line.getCell(3) != null ? line.getCell(3).getStringCellValue() : "");
            log.info("Sde {}", line.getCell(4).getNumericCellValue());
            Cell cellformat = line.getCell(4);
            String sde = Double.toString(cellformat.getNumericCellValue()).equals("0.0") ? "0"
                    : Double.toString(cellformat.getNumericCellValue());
            log.info("Sdestr {}, SDE {}", Double.valueOf(sde), Double.valueOf(sde).longValue());
            BigDecimal sdeLong = BigDecimal.valueOf(Double.valueOf(sde));
            cellformat.setCellValue(sde);
            // cellformat.setCellStyle(style);
            bp1Com.setSde(Double.parseDouble(line.getCell(4) != null ? String.valueOf(sdeLong.toPlainString()) : "0"));
            bp1Com.setCha(line.getCell(5) != null ? line.getCell(5).getStringCellValue() : "");
            /*bp1Com.setDou(LocalDate.parse((line.getCell(6) != null)
                    ? (!line.getCell(6).getStringCellValue().equals("") ? line.getCell(6).getStringCellValue()
                    : "01/01/1999")
                    : "01/01/1999", dateformat));
            bp1Com.setDdd(LocalDate.parse((line.getCell(7) != null)
                    ? (!line.getCell(7).getStringCellValue().equals("") ? line.getCell(7).getStringCellValue()
                    : "01/01/1999")
                    : "01/01/1999", dateformat));
            bp1Com.setDdc(LocalDate.parse((line.getCell(8) != null)
                    ? (!line.getCell(8).getStringCellValue().equals("") ? line.getCell(8).getStringCellValue()
                    : "01/01/1999")
                    : "01/01/1999", dateformat));*/
            Cell cell_10 = line.getCell(10);
            String monSdeFin = Double.toString(cell_10.getNumericCellValue()).equals("0.0") ? "0"
                    : Double.toString(cell_10.getNumericCellValue());
            BigDecimal sdeFinLong = BigDecimal.valueOf(Double.valueOf(monSdeFin));
            log.info("SdeFin CRP {}", sdeFinLong);
            cellformat.setCellValue(monSdeFin);
            bp1Com.setSdeFin(Double.parseDouble(line.getCell(10) != null ? String.valueOf(sdeFinLong.toPlainString()) : "0"));
            bp1Com.setDateDebutArrete(LocalDate.parse((line.getCell(11) != null)
                    ? (!line.getCell(11).getStringCellValue().equals("") ? line.getCell(11).getStringCellValue()
                    : "01/01/1999")
                    : "01/01/1999", dateformat));
            bp1Com.setDateArrete(dateArrete);
            log.info("age {}", bp1Com.getAge());
            log.info("Dev {}", bp1Com.getDev());
            log.info("Ncp {}", bp1Com.getNcp());
            bp1Com.setNomFic(fileName);
            bp1ComService.save(bp1Com);
            log.info("AFTER SAVE bp1Com {}", fileName);
            //dateArrete = (bp1Com.getDateArrete() != null ? bp1Com.getDateArrete() : dateArrete);
        }
        
    }

    public void chargeBP2Com(String fileName, Sheet sheet, Row line, int top, int hight, DateTimeFormatter dateformat,
            LocalDate dateArrete) {
        /**
         * Initialisation de la table BP2COM si fichier déjà chargé*
         */
        Pageable pag = PageRequest.of(0, 100000);
        /* Iterable<Bp2Com> bp2Coms = bp2ComService.findAll(pag);
        for (Bp2Com bp2Com : bp2Coms) {
            if (bp2Com.getNomFic() != null && bp2Com.getNomFic().equals(fileName)) {
                log.info("BP2COM ID {}",bp2Com.getId());
                bp2ComService.delete(bp2Com.getId());
                log.info("AFTER DELETE BP2COM ");
            }
        }*/
        log.info("BEFORE DELETE BP2COM " + fileName);
        bp2ComService.viderBp2Com(fileName);
        log.info("AFTER DELETE BP2COM " + fileName);
        for (int index = 0; index < hight; index++) {
            line = sheet.getRow(index + top + 1);
            int z = index + top + 1;
            log.info("index + top + 1 = {} ", z);
            // System.out.println("line = "+line);
            log.info("cellule {} line {}", z, line.getCell(4));
            log.info("devise {}, line {}", z, line.getCell(1));
            Bp2Com bp2com = new Bp2Com();
            bp2com.setAge(line.getCell(0) != null ? line.getCell(0).getStringCellValue() : "");
            bp2com.setDev(line.getCell(1) != null ? line.getCell(1).getStringCellValue() : "");
            bp2com.setNcp(line.getCell(2) != null ? line.getCell(2).getStringCellValue() : "");
            bp2com.setInti(line.getCell(3) != null ? line.getCell(3).getStringCellValue() : "");
            Cell cellformat = line.getCell(4);
            String sde = Double.toString(cellformat.getNumericCellValue()).equals("0.0") ? "0"
                    : Double.toString(cellformat.getNumericCellValue());
            log.info("Sdestr {}, SDE {}, ", Double.valueOf(sde), Double.valueOf(sde).longValue());
            BigDecimal sdeLong = BigDecimal.valueOf(Double.valueOf(sde));
            cellformat.setCellValue(sde);
            // cellformat.setCellStyle(style);
            bp2com.setSde(Double.parseDouble(line.getCell(4) != null ? String.valueOf(sdeLong.toPlainString()) : "0"));
            bp2com.setCha(line.getCell(5) != null ? line.getCell(5).getStringCellValue() : "");
            /*bp2com.setDou(LocalDate.parse((line.getCell(6) != null)
                    ? (!line.getCell(6).getStringCellValue().equals("") ? line.getCell(6).getStringCellValue()
                    : "01/01/1999")
                    : "01/01/1999", dateformat));
            bp2com.setDdc(LocalDate.parse((line.getCell(7) != null)
                    ? (!line.getCell(7).getStringCellValue().equals("") ? line.getCell(7).getStringCellValue()
                    : "01/01/1999")
                    : "01/01/1999", dateformat));
            bp2com.setDdd(LocalDate.parse((line.getCell(8) != null)
                    ? (!line.getCell(8).getStringCellValue().equals("") ? line.getCell(8).getStringCellValue()
                    : "01/01/1999")
                    : "01/01/1999", dateformat));*/
            bp2com.setDateArrete(dateArrete);
            bp2com.setNomFic(fileName);
            bp2ComService.save(bp2com);

        }

    }

    public void chargeBP1His(String fileName, Sheet sheet, Row line, int top, int hight, DateTimeFormatter dateformat,
            LocalDate dateArrete) {
        /**
         * Initialisation de la table BP1HIS si fichier déjà chargé*
         */
        Pageable pag = PageRequest.of(0, 100000);
        /*    Iterable<Bp1His> bp1Hiss = bp1HisService.findAll(pag);
        for (Bp1His bp1His : bp1Hiss) {
            if (bp1His.getNomFic() != null && bp1His.getNomFic().equals(fileName)) {
                log.info("BP1HIS ID {}", bp1His.getId());
                bp1HisService.delete(bp1His.getId());
                log.info("AFTER DELETE BP1HIS ");
            }
        }*/
        log.info("BEFORE DELETE BP1HIS " + fileName);
        bp1HisService.viderBp1His(fileName);
        log.info("AFTER DELETE BP1HIS " + fileName);
        for (int index = 0; index < hight; index++) {
            line = sheet.getRow(index + top + 1);
            int z = index + top + 1;
            log.info("index + top + 1 = {}", z);
            // System.out.println("line = "+line);
            log.info("cellule {}, line {}", z, line.getCell(4));
            log.info("devise {}, line {}", z, line.getCell(1));
            Bp1His bp1his = new Bp1His();
            bp1his.setDco(LocalDate.parse(
                    line.getCell(0) != null ? (line.getCell(0).getStringCellValue()) : "01/01/1999", dateformat));
            bp1his.setAge(line.getCell(1) != null ? line.getCell(1).getStringCellValue() : "");
            bp1his.setDev(line.getCell(2) != null ? line.getCell(2).getStringCellValue() : "");
            bp1his.setNcp(line.getCell(3) != null ? line.getCell(3).getStringCellValue() : "");
            bp1his.setOpe(line.getCell(4) != null ? line.getCell(4).getStringCellValue() : "");
            bp1his.setLib(line.getCell(5) != null ? line.getCell(5).getStringCellValue() : "");
            Cell cellformat = line.getCell(6);
            String mon = Double.toString(cellformat.getNumericCellValue()).equals("0.0") ? "0"
                    : Double.toString(cellformat.getNumericCellValue());
            log.info("Monstr {}, MON {}", Double.valueOf(mon), Double.valueOf(mon).longValue());
            BigDecimal monLong = BigDecimal.valueOf(Double.valueOf(mon));
            cellformat.setCellValue(mon);
            // cellformat.setCellStyle(style);
            bp1his.setMon(Double.parseDouble(line.getCell(6) != null ? String.valueOf(monLong.toPlainString()) : "0"));
            bp1his.setSen(line.getCell(7) != null ? line.getCell(7).getStringCellValue() : "");
            bp1his.setPie(line.getCell(8) != null ? line.getCell(8).getStringCellValue() : "");
            bp1his.setNcc(line.getCell(9) != null ? line.getCell(9).getStringCellValue() : "");
            bp1his.setUti(line.getCell(10) != null ? line.getCell(10).getStringCellValue() : "");
            bp1his.setUtf(line.getCell(11) != null ? line.getCell(10).getStringCellValue() : "");
            bp1his.setNat(line.getCell(12) != null ? line.getCell(12).getStringCellValue() : "");
            bp1his.setDateArrete(dateArrete);
            bp1his.setNomFic(fileName);
            bp1HisService.save(bp1his);
            log.info("AFTER SAVE bp1his {}", fileName);
        }

    }

    public void chargeBP3His(String fileName, Sheet sheet, Row line, int top, int hight, DateTimeFormatter dateformat,
            LocalDate dateArrete) {
        /**
         * Initialisation de la table BP1 si fichier déjà chargé*
         */
        Pageable pag = PageRequest.of(0, 100000);
        /*Iterable<Bp3His> bp3Hiss = bp3HisService.findAll(pag);
        for (Bp3His bp3His : bp3Hiss) {
            if (bp3His.getNomFic() != null && bp3His.getNomFic().equals(fileName)) {
                log.info("BP3HIS ID {}", bp3His.getId());
                bp3HisService.delete(bp3His.getId());
                log.info("AFTER DELETE BP3HIS ");
            }
        }*/
        log.info("BEFORE DELETE BP3HIS " + fileName);
        bp3HisService.viderBp3His(fileName);
        log.info("AFTER DELETE BP3HIS " + fileName);
        for (int index = 0; index < hight; index++) {
            line = sheet.getRow(index + top + 1);
            int z = index + top + 1;
            log.info("index + top + 1 = {}", z);
            // System.out.println("line = "+line);
            log.info("cellule {}, line {}", z, line.getCell(4));
            log.info("devise {}, line {}", z, line.getCell(1));
            Bp3His bp3his = new Bp3His();
            // String dcoStr = ""+line.getCell(0).getStringCellValue();
            log.info("dateArrete {}", dateArrete);
            
            bp3his.setDco(dateArrete);
            bp3his.setAge(line.getCell(1) != null ? line.getCell(1).getStringCellValue() : "");
            bp3his.setDev(line.getCell(2) != null ? line.getCell(2).getStringCellValue() : "");
            bp3his.setNcp(line.getCell(3) != null ? line.getCell(3).getStringCellValue() : "");
            bp3his.setOpe(line.getCell(4) != null ? line.getCell(4).getStringCellValue() : "");
            bp3his.setLib(line.getCell(5) != null ? line.getCell(5).getStringCellValue() : "");
            Cell cellformat = line.getCell(6);
            String mon = Double.toString(cellformat.getNumericCellValue()).equals("0.0") ? "0"
                    : Double.toString(cellformat.getNumericCellValue());
            log.info("Monstr {}, MON {}", Double.valueOf(mon), Double.valueOf(mon).longValue());
            BigDecimal monLong = BigDecimal.valueOf(Double.valueOf(mon));
            cellformat.setCellValue(mon);
            // cellformat.setCellStyle(style);
            bp3his.setMon(Double.parseDouble(line.getCell(6) != null ? String.valueOf(monLong.toPlainString()) : "0"));
            bp3his.setSen(line.getCell(7) != null ? line.getCell(7).getStringCellValue() : "");
            bp3his.setPie(line.getCell(8) != null ? line.getCell(8).getStringCellValue() : "");
            bp3his.setNcc(line.getCell(9) != null ? line.getCell(9).getStringCellValue() : "");
            bp3his.setUti(line.getCell(10) != null ? line.getCell(10).getStringCellValue() : "");
            bp3his.setUtf(line.getCell(11) != null ? line.getCell(11).getStringCellValue() : "");
            bp3his.setNat(line.getCell(12) != null ? line.getCell(12).getStringCellValue() : "");
            bp3his.setDateArrete(dateArrete);
            bp3his.setNomFic(fileName);
            bp3HisService.save(bp3his);

        }

    }

    public void chargeCrpAtr(String fileName, Sheet sheet, Row line, int top, int hight, DateTimeFormatter dateformat,
            LocalDate dateArrete) throws ParseException{
        /**
         * Initialisation de la table BP1 si fichier déjà chargé*
         */
        Pageable pag = PageRequest.of(0, 100000);
        Iterable<CrpAtr> crpATrs = crpAtrService.findAll(pag);
        dateformat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        log.info("BEFORE DELETE CRPATR " + fileName);
        crpAtrService.viderCrpAtr(fileName);
        log.info("AFTER DELETE CRPATR " + fileName);
        for (int index = 0; index < hight; index++) {
            line = sheet.getRow(index + top + 1);
            int z = index + top + 1;
            log.info("index + top + 1 = {}", z);
            // System.out.println("line = "+line);
            log.info("cellule {}, line {}", z, line.getCell(4));
            log.info("devise {}, line {}", z, line.getCell(1));
            CrpAtr crpAtr = new CrpAtr();
            crpAtr.setCenr(line.getCell(0) != null
                    ? (!line.getCell(0).getStringCellValue().equals("") ? line.getCell(0).getStringCellValue() : "")
                    : "");
            crpAtr.setRefint(line.getCell(1) != null
                    ? (!line.getCell(1).getStringCellValue().equals("") ? line.getCell(1).getStringCellValue() : "")
                    : "");
            crpAtr.setNum(line.getCell(2) != null
                    ? (!line.getCell(2).getStringCellValue().equals("") ? line.getCell(2).getStringCellValue() : "")
                    : "");
            crpAtr.setNobor(line.getCell(3) != null
                    ? (!line.getCell(3).getStringCellValue().equals("") ? line.getCell(3).getStringCellValue() : "")
                    : "");
            crpAtr.setTypenr(line.getCell(4) != null
                    ? (!line.getCell(4).getStringCellValue().equals("") ? line.getCell(4).getStringCellValue() : "")
                    : "");
            crpAtr.setIdAtr(line.getCell(5) != null
                    ? (!line.getCell(5).getStringCellValue().equals("") ? line.getCell(5).getStringCellValue() : "")
                    : "");
            crpAtr.setDemAtr(LocalDate.parse(line.getCell(6) != null
                    ? ((!line.getCell(6).getStringCellValue().equals("") ? line.getCell(6).getStringCellValue()
                    : "01/01/2020"))
                    : "01/01/2020", dateformat));
            crpAtr.setNumAtr(line.getCell(7) != null
                    ? (!line.getCell(7).getStringCellValue().equals("") ? line.getCell(7).getStringCellValue() : "")
                    : "");
            crpAtr.setNomRes(line.getCell(9) != null
                    ? (!line.getCell(9).getStringCellValue().equals("") ? line.getCell(9).getStringCellValue() : "")
                    : "");
            crpAtr.setCpayIso(line.getCell(10) != null
                    ? (!line.getCell(10).getStringCellValue().equals("") ? line.getCell(10).getStringCellValue() : "")
                    : "");
            String dateOpe = "";
            Cell cell_11 = line.getCell(11);
            dateOpe = new DataFormatter().formatCellValue(cell_11);
            // dateOpe = ((line.getCell(11)!=null && CellType.NUMERIC ==
            // (line.getCell(11).getCellType().NUMERIC))) ?
            // String.valueOf(line.getCell(11).getNumericCellValue()) :
            // line.getCell(11).getStringCellValue();
            crpAtr.setDatope(LocalDate.parse(
                    (dateOpe != null) ? ((!dateOpe.equals("") ? dateOpe : "01/01/2020")) : "01/01/2020", dateformat));
            crpAtr.setRegt(line.getCell(12) != null
                    ? (!line.getCell(12).getStringCellValue().equals("") ? line.getCell(12).getStringCellValue() : "")
                    : "");
            crpAtr.setNomEtr(line.getCell(13) != null
                    ? (!line.getCell(13).getStringCellValue().equals("") ? line.getCell(13).getStringCellValue() : "")
                    : "");
            crpAtr.setNocli(line.getCell(14) != null
                    ? (!line.getCell(14).getStringCellValue().equals("") ? line.getCell(14).getStringCellValue() : "")
                    : "");
            crpAtr.setCeco(line.getCell(15) != null
                    ? (!line.getCell(15).getStringCellValue().equals("") ? line.getCell(15).getStringCellValue() : "")
                    : "");
            /*crpAtr.setCpayEtg(line.getCell(16) != null
                    ? (!line.getCell(16).getStringCellValue().equals("") ? line.getCell(16).getStringCellValue() : "")
                    : "");*/
            crpAtr.setNatcpt(line.getCell(17) != null
                    ? (!line.getCell(17).getStringCellValue().equals("") ? line.getCell(17).getStringCellValue() : "")
                    : "");
            crpAtr.setSens(line.getCell(18) != null
                    ? (!line.getCell(18).getStringCellValue().equals("") ? line.getCell(18).getStringCellValue() : "")
                    : "");
           //* crpAtr.setSens(line.getCell(19) != null
                  ////  ? (!line.getCell(19).getStringCellValue().equals("") ? line.getCell(19).getStringCellValue() : "")
                   // : "");*/
            Cell cellformat_19 = line.getCell(19);
            String devn = new DataFormatter().formatCellValue(cellformat_19);
            cellformat_19.setCellValue(devn);
            crpAtr.setDevn(cellformat_19 != null
                    ? devn
                    : "");
            Cell cellformat = line.getCell(20);
            String mon = new DataFormatter().formatCellValue(cellformat);// Double.toString(cellformat.getNumericCellValue()).equals("0.0")
            // ? "0" :
            // Double.toString(cellformat.getNumericCellValue());
            log.info("Monstr CRP {}, MON {}", mon);
            ///long monLong = Double.valueOf(mon).longValue();
            cellformat.setCellValue(mon);
            // cellformat.setCellStyle(style);
            crpAtr.setMdev(Double.parseDouble(line.getCell(20) != null ? mon.replace(',', '.') : "0"));
            String taux = "";
            Cell cell_21 = line.getCell(21);
            taux = new DataFormatter().formatCellValue(cell_21);
            
            double tauxlg = NumberFormat.getNumberInstance(Locale.FRANCE).parse(taux).doubleValue();//Double.valueOf(taux);
            cell_21.setCellValue(taux);
            log.info("Monstr taux Crp {}, txlong {}", taux, tauxlg);
            crpAtr.setTaux(Double.parseDouble(cell_21 != null ? String.valueOf(tauxlg) : ""));
            Cell cellformatNat = line.getCell(22);
            String monNat = new DataFormatter().formatCellValue(cellformatNat);// Double.toString(cellformatNat.getNumericCellValue()).equals("0.0")
            // ? "0" :
            // Double.toString(cellformatNat.getNumericCellValue());
            //log.info("Monstr monNat Crp {}, MON {}", Double.valueOf(monNat), Double.valueOf(monNat).longValue());
            //BigDecimal.valueOf(crp.getMnat().longValue()).toPlainString();
            BigDecimal monLongNat = (monNat!=null && !monNat.equals("")) ? BigDecimal.valueOf(Double.valueOf(monNat).longValue()) : new BigDecimal(0.0);
            cellformat.setCellValue(monNat);
            log.info("Monstr monNat Crp {}", monLongNat);
            // cellformat.setCellStyle(style);
            crpAtr.setMnat(Double.parseDouble(line.getCell(22) != null ? String.valueOf(monLongNat.toPlainString()) : "0"));
            crpAtr.setEtab(line.getCell(23) != null
                    ? (!line.getCell(23).getStringCellValue().equals("") ? line.getCell(23).getStringCellValue() : "")
                    : "");
            crpAtr.setCpayEtg(line.getCell(24) != null
                    ? (!line.getCell(24).getStringCellValue().equals("") ? line.getCell(24).getStringCellValue() : "")
                    : "");
            Cell cellformat_26 = line.getCell(26);
            String sectInst = new DataFormatter().formatCellValue(cellformat_26);
            cellformat_26.setCellValue(sectInst);
            crpAtr.setSectInst(cellformat_26 != null
                    ? sectInst
                    : "");
            /*crpAtr.setSectInst(line.getCell(26) != null
                    ? (!line.getCell(26).getStringCellValue().equals("") ? line.getCell(26).getStringCellValue() : "")
                    : "");*/
            crpAtr.setCodenaema(line.getCell(27) != null
                    ? (!line.getCell(27).getStringCellValue().equals("") ? line.getCell(27).getStringCellValue() : "")
                    : "");            
            crpAtr.setDateArrete(dateArrete);
            crpAtr.setNomFic(fileName);
            crpAtrService.save(crpAtr);

        }

    }
    
    public void chargeAndSaveBp4InfosEmisAcq(String fileName, Sheet sheet, Row line, int top, int hight, DateTimeFormatter dateformat,
            LocalDate dateArrete) {
               /**
         * Initialisation de la table BP4INFOS si fichier déjà chargé*
         */
        Pageable pag = PageRequest.of(0, 100000);
        DateTimeFormatter dateformat0 = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter dateformat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateformat2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateformat3 = DateTimeFormatter.ofPattern("dd/MM/yy");
        log.info("BEFORE DELETE BP4INFOS " + fileName);
        bp4InfosService.viderBp4Infos(fileName);
        log.info("AFTER DELETE BP4INFOS " + fileName);
        for (int index = 0; index < hight; index++) {
            line = sheet.getRow(index + top + 1);
            
            int z = index + top + 1;
            log.info("index + top + 1 = {}", z);
            // System.out.println("line = "+line);
            log.info("cellule {}, line {}", z, line.getCell(4));
            log.info("devise {}, line {}", z, line.getCell(1));
            //log.info("date {}, line {}", z, line.getCell(0).getNumericCellValue());

        
            // line.getCell(11).getStringCellValue();
            Bp4Infos bp4infos = new Bp4Infos();
            String dateOpe = ((line.getCell(0)!=null && CellType.NUMERIC ==(line.getCell(0).getCellType()))) ?
            String.valueOf(line.getCell(0).getNumericCellValue()) : line.getCell(0).getStringCellValue();
            String mntOpe = ((line.getCell(3)!=null && CellType.NUMERIC ==(line.getCell(3).getCellType()))) ?
            String.valueOf(line.getCell(3).getNumericCellValue()) : line.getCell(3).getStringCellValue();
           // bp1his.setAge(line.getCell(1) != null ? line.getCell(1).getStringCellValue() : "");
            bp4infos.setCodeIsoPays(line.getCell(2) != null ? line.getCell(2).getStringCellValue() : "");
            log.info("setCodeIsoPays {}, ",bp4infos.getCodeIsoPays());
            
            if(!mntOpe.equals("0")) {
            bp4infos.setCodeIsoPays((bp4infos.getCodeIsoPays()!=null && !bp4infos.getCodeIsoPays().trim().equals(""))?  
                    (bp4infos.getCodeIsoPays().trim().length()>2 
                            ? CountryCode.getByCode(Integer.valueOf(bp4infos.getCodeIsoPays())).getAlpha2() 
                            : (bp4infos.getCodeIsoPays().trim().length()==2 ? bp4infos.getCodeIsoPays().trim() : CountryCode.getByCode(bp4infos.getCodeIsoPays()).getAlpha2())) 
                    : "");
            log.info("dateOpe {}, ",dateOpe);
            if(fileName.startsWith("BP4_EMI")){
                //dateOpe = String.valueOf(LocalDate.parse(dateOpe, dateformat2).format(dateformat1));
                //LocalDate date = LocalDate.parse(dateOpe,dateformat2);
                //Ora
                //LocalDate dco = LocalDate.parse(dateOpe.substring(0, 10), dateformat1);
                //dateOpe = dco.format(dateformat1);
                //UTB
                LocalDate dco = LocalDate.parse(dateOpe.substring(0, 8), dateformat3);
                dateOpe = dco.format(dateformat1);
                //dateOpe = formatDate(dateOpe);
                log.info("FORMAT EMI =, ",dateOpe);        
            bp4infos.setMntnosCartes(Double.parseDouble(!mntOpe.equals("") ? mntOpe : "0"));
            }else{
            /*LocalDate dco = LocalDate.parse(dateOpe.substring(0, 8), dateformat0);
            dateOpe = dco.format(dateformat1);*/
            //Ora
            LocalDate dco = LocalDate.parse(dateOpe.substring(0, 10), dateformat1);
            dateOpe = dco.format(dateformat1);
            //dateOpe = formatDate(dateOpe);
            System.out.println("FORMAT ACQ = " + dateOpe);
            bp4infos.setMntCartesEtr(Double.parseDouble(!mntOpe.equals("") ? mntOpe : "0"));    
            }
            bp4infos.setDco(LocalDate.parse(dateOpe != null || !dateOpe.equals("") ? dateOpe : "1999-01-01", dateformat1));
            log.info("CodeIsoPays {}, ",bp4infos.getCodeIsoPays());
            bp4infos.setLibellePays((bp4infos.getCodeIsoPays()!=null && !bp4infos.getCodeIsoPays().equals(""))? CountryCode.getByCode(bp4infos.getCodeIsoPays()).getName() : "");
            bp4infos.setDatearrete(dateArrete);
            bp4infos.setNomfic(fileName);
            bp4InfosService.save(bp4infos);
            }
        }
        
    }

    public void saveBp1Infos() {
        Bp1Com bp1com = new Bp1Com();
        Bp1His bp1His = new Bp1His();
        Bp1Infos bp1infos = new Bp1Infos();
        Double D100 = 0.0;
        Double D200 = 0.0;
        Double D150 = 0.0;
        Double D250 = 0.0;
        Double D110 = 0.0;
        Double D210 = 0.0;
        Double D131 = 0.0;
        Double D231 = 0.0;
        Double D133 = 0.0;
        Double D233 = 0.0;
        Double D134 = 0.0;
        Double D234 = 0.0;        
        Double D135 = 0.0;
        Double D235 = 0.0;
        Double D130 = 0.0;
        Double D160 = 0.0;
        Double D230 = 0.0;
        Double D260 = 0.0;
        log.info("BEFORE DELETE BP1INFOS " );
        bp1InfosService.viderBp1Infos();
        log.info("AFTER DELETE BP1INFOS " );
        Pageable pag = PageRequest.of(0, 1000000);
       /* Iterable<Bp1Com> bp1Coms = bp1ComService.findAll(pag);
        for (Bp1Com bp1 : bp1Coms) {
            if (bp1.getSde() != null && bp1.getSde() < 0) {
                D100 = D100 + bp1.getSde();
                //D250 = D250 + bp1.getSde();
            }
            if (bp1.getSde() != null && bp1.getSde() > 0) {
                D200 = D200 + bp1.getSde();
                //D150 = D150 + bp1.getSde();
            }
            
            if (bp1.getSdeFin()!= null && bp1.getSdeFin() < 0) {
                D250 = D250 + bp1.getSdeFin();
            }
            if (bp1.getSdeFin() != null && bp1.getSdeFin() > 0) {
                D150 = D150 + bp1.getSdeFin();
            }
        }*/
        D100 = bp1ComService.findSumSoldeDebutBySoldeDebutNeg();
        D200 = bp1ComService.findSumSoldeDebutBySoldeDebutPos();
        D250 = bp1ComService.findSumSoldeFinBySoldeFinNeg();
        D150 = bp1ComService.findSumSoldeFinBySoldeFinPos();
        //D250 = bp1ComService.findSumSoldeDebutBySoldeDebutNeg();
        //D150 = bp1ComService.findSumSoldeDebutBySoldeDebutPos();

        
        Iterable<Bp1His> bp1Hiss = bp1HisService.findAll(pag);
        for (Bp1His bpHis : bp1Hiss) {
            // Double D133 = 0.0;Double D133 = 0.0;
            log.info("SEN {}", bpHis.getSen());
            if ((bpHis.getMon() != null)
                    && (bpHis.getNat() != null && (bpHis.getNat().equals("RGVCDI") || bpHis.getNat().equals("RGPPTI")))) {
                D110 = D110 + bpHis.getMon();
            }
            if ((bpHis.getMon() != null)
                    && (bpHis.getNat() != null && (bpHis.getNat().equals("RGVCDE") || bpHis.getNat().equals("RGPPTE")))) {
                D210 = D210 + bpHis.getMon();
            }
            if ((bpHis.getMon() != null && bpHis.getSen().equals("C"))
                    && (bpHis.getNat() != null && bpHis.getNat().equals("TOCLIV"))) {
                D131 = D131 + bpHis.getMon();
            }
            if ((bpHis.getMon() != null && bpHis.getSen().equals("D"))
                    && (bpHis.getNat() != null && bpHis.getNat().equals("TOCLIV"))) {
                D231 = D231 + bpHis.getMon();
            }
            //Laisser à zéro
            /*if ((bpHis.getMon() != null && bpHis.getSen().equals("D"))
                    && (bpHis.getNat() != null && (bpHis.getNat().equals("VIRBAN")))) {
                D133 = D133 + bpHis.getMon();
            }
            if ((bpHis.getMon() != null && bpHis.getSen().equals("C"))
                    && (bpHis.getNat() != null && (bpHis.getNat().equals("VIRBAN")))) {
                D233 = D233 + bpHis.getMon();
            }*/
            if ((bpHis.getMon() != null)
                    && (bpHis.getNat() != null && (bpHis.getNat().equals("SAITRF")))) {
                D134 = D134 + bpHis.getMon();
            }
            if ((bpHis.getMon() != null)
                    && (bpHis.getNat() != null && (bpHis.getNat().equals("SAIRPT")))) {
                D234 = D234 + bpHis.getMon();
            }
            //à zéro
           /* if ((bpHis.getMon() != null && bpHis.getSen().equals("D"))
                    && (bpHis.getNat() != null && (bpHis.getNat().equals("SAITRF")))) {
                D135 = D135 + bpHis.getMon();
            }
            
            if ((bpHis.getMon() != null && bpHis.getSen().equals("C"))
                    && (bpHis.getNat() != null && (bpHis.getNat().equals("SAITRF")))) {
                D235 = D235 + bpHis.getMon();
            }*/
        }
        D130 = D130 + D131 + D133 + D134 + D135;
        D160 = D160 + (D100<0 ? -1*D100 : D100) + D110 + D130 + D150;
        D230 = D230 + D231 + D233 + D234 + D235;
        D260 = D260 + D200 + D210 + D230 + (D250<0 ? -1*D250 : D250);
        log.info("D100 {}", D100);
        bp1infos.setD100(String.valueOf(Double.valueOf(D100).longValue()));
        log.info("D100 get {}", bp1infos.getD100());
        bp1infos.setD200(String.valueOf(Double.valueOf(D200).longValue()));
        bp1infos.setD110(String.valueOf(Double.valueOf(D110).longValue()));
        bp1infos.setD210(String.valueOf(Double.valueOf(D210).longValue()));
        // bp1infos.setD120(String.valueOf(D120));
        // bp1infos.setD220(String.valueOf(D220));
        bp1infos.setD130(String.valueOf(Double.valueOf(D130).longValue()));
        bp1infos.setD230(String.valueOf(Double.valueOf(D230).longValue()));
        bp1infos.setD131(String.valueOf(Double.valueOf(D131).longValue()));
        bp1infos.setD231(String.valueOf(Double.valueOf(D231).longValue()));
        // bp1infos.setD132(String.valueOf(D132));
        // bp1infos.setD232(String.valueOf(D232));
        bp1infos.setD133(String.valueOf(Double.valueOf(D133).longValue()));
        bp1infos.setD233(String.valueOf(Double.valueOf(D233).longValue()));
        bp1infos.setD134(String.valueOf(Double.valueOf(D134).longValue()));
        bp1infos.setD234(String.valueOf(Double.valueOf(D234).longValue()));
        bp1infos.setD135(String.valueOf(Double.valueOf(D135).longValue()));
        bp1infos.setD235(String.valueOf(Double.valueOf(D235).longValue()));
        // bp1infos.setD140(String.valueOf(D140));
        // bp1infos.setD240(String.valueOf(D240));
        bp1infos.setD150(String.valueOf(Double.valueOf(D150).longValue()));
        bp1infos.setD250(String.valueOf(Double.valueOf(D250).longValue()));
        bp1infos.setD160(String.valueOf(Double.valueOf(D160).longValue()));
        bp1infos.setD260(String.valueOf(Double.valueOf(D260).longValue()));
        bp1InfosService.save(bp1infos);
    }

    public void saveBp2Infos() {

        Double actifBilletEtRcai = 0.0;
        Double actifMaisonMere = 0.0;
        Double actifAuTresor = 0.0;
        Double actifClientDeb = 0.0;
        Double actifEffesCpte = 0.0;
        Double actifEffetEnc = 0.0;
        Double passifMaisonMere = 0.0;
        Double passifAuTresor = 0.0;
        Double passifCliCptVue = 0.0;
        Double passifCliCpteCh = 0.0;
        Double passifCptApresEnc = 0.0;
        Pageable pag = PageRequest.of(0, 100000);
        // List<Bp2Com> bp2ComListEur = bp2ComService.findByDev("952");
        List<String> listDevise = Arrays.asList("952","978", "840", "124", "826", "756", "392", "156");
        
        List<String> chapitresAutresCorr = Arrays.asList("1111","1112","1131","1141","1151","1161","1181");
        //'1111','1112','1131','1141','1151','1161','1181'
        List<String> chapitresCptAVue4 = Arrays.asList("2511");
        
        List<String> chapitresCptAVue5 = Arrays.asList("25311","25321","25331","25391");
        
        List<String> chapitresPassifCliCpteCh = Arrays.asList("2521");
        
        List<String> chapitresPassifCptApresEnc = Arrays.asList("9612","9614");
        
        List<String> chapitresActifEffetEnc = Arrays.asList("9611", "9613");
        
        //
        //chapitres 9612 et 9614
        //chapitres '2511' créditeur et ,'25311','25321','25331','25391'

        log.info("BEFORE SAVE BP2INFOS");

        for (String dev : listDevise) {
            Bp2Infos bp2 = new Bp2Infos();
            bp2.setActifBilletEtRcai(0.0);
            bp2.setActifMaisonMere(0.0);
            bp2.setActifAuTresor(0.0);
            bp2.setActifClientDeb(0.0);
            bp2.setActifEffesCpte(0.0);
            bp2.setActifEffetEnc(0.0);
            bp2.setPassifMaisonMere(0.0);
            bp2.setPassifAuTresor(0.0);
            bp2.setPassifCliCptVue(0.0);
            bp2.setPassifCliCpteCh(0.0);
            bp2.setPassifCptApresEnc(0.0);
            String devise = dev;
            log.info("BEFORE SAVE BP2INFOS DEV {}", devise);
            bp2.setActifBilletEtRcai((bp2ComService.findLikeCha("101", devise) != null
                    && bp2ComService.findLikeCha("101", devise)!=null)
                    ? bp2ComService.findLikeCha("101", devise)
                    : 0.0);
            //Laisser à zero
            /*bp2.setActifMaisonMere((bp2ComService.findLikeChaAndSdeNeg("11", devise) != null
                    && bp2ComService.findLikeChaAndSdeNeg("11", devise).size() > 0)
                    ? bp2ComService.findLikeChaAndSdeNeg("11", devise).get(0).getSde()
                    : 0.0);*/
            
            bp2.setActifAuTresor((bp2ComService.findByChapitresAndSdeNeg(chapitresAutresCorr, devise) != null
                    && bp2ComService.findByChapitresAndSdeNeg(chapitresAutresCorr, devise)!=null)
                    ? bp2ComService.findByChapitresAndSdeNeg(chapitresAutresCorr, devise)
                    : 0.0); //'1111','1112','1131','1141','1151','1161','1181' 
            bp2.setActifClientDeb((bp2ComService.findLikeCha1OrCha2AndSdeNeg("2511", "292", devise) != null
                    && bp2ComService.findLikeCha1OrCha2AndSdeNeg("2511", "292", devise)!=null)
                    ? bp2ComService.findLikeCha1OrCha2AndSdeNeg("2511", "292", devise)
                    : 0.0);
            /*bp2.setActifEffesCpte((bp2ComService.findLikeCha1OrCha2("9611", "9613", devise) != null
                    && bp2ComService.findLikeCha1OrCha2("9611", "9613", devise).size() > 0)
                    ? bp2ComService.findLikeCha1OrCha2("9611", "9613", devise).get(0).getSde()
                    : 0.0);*/
            bp2.setActifEffetEnc((bp2ComService.findByChapitresAndSdePos(chapitresActifEffetEnc, devise) != null
                    && bp2ComService.findByChapitresAndSdePos(chapitresActifEffetEnc, devise)!=null)
                    ? bp2ComService.findByChapitresAndSdePos(chapitresActifEffetEnc, devise)
                    : 0.0);
            /*bp2.setPassifMaisonMere((bp2ComService.findLikeChaAndSdePos("11", devise) != null
                    && bp2ComService.findLikeChaAndSdePos("11", devise).size() > 0)
                    ? bp2ComService.findLikeChaAndSdePos("11", devise).get(0).getSde()
                    : 0.0);*/
            bp2.setPassifAuTresor((bp2ComService.findByChapitresAndSdePos(chapitresAutresCorr, devise) != null
                    && bp2ComService.findByChapitresAndSdePos(chapitresAutresCorr, devise)!=null)
                    ? bp2ComService.findByChapitresAndSdePos(chapitresAutresCorr, devise)
                    : 0.0);
            bp2.setPassifCliCptVue((bp2ComService.findByChapitres4And5AndSdePos(chapitresCptAVue4, chapitresCptAVue5, devise) != null
                    && bp2ComService.findByChapitres4And5AndSdePos(chapitresCptAVue4, chapitresCptAVue5, devise)!=null)
                    ? bp2ComService.findByChapitres4And5AndSdePos(chapitresCptAVue4, chapitresCptAVue5, devise)
                    : 0.0);
            bp2.setPassifCliCpteCh((bp2ComService.findByChapitresAndSdePos(chapitresPassifCliCpteCh, devise) != null
                    && bp2ComService.findByChapitresAndSdePos(chapitresPassifCliCpteCh, devise)!=null)
                    ? bp2ComService.findByChapitresAndSdePos(chapitresPassifCliCpteCh, devise)
                    : 0.0);
            bp2.setPassifCptApresEnc((bp2ComService.findByChapitresAndSdePos(chapitresPassifCptApresEnc, devise) != null
                    && bp2ComService.findByChapitresAndSdePos(chapitresPassifCptApresEnc, devise)!=null)
                    ? bp2ComService.findByChapitresAndSdePos(chapitresPassifCptApresEnc, devise)
                    : 0.0);
            log.info("bp2.setActifBilletEtRcai {}", bp2.getActifBilletEtRcai());
            log.info("bp2.setActifMaisonMere {}", bp2.getActifMaisonMere());
            log.info("bp2.setActifAuTresor {}", bp2.getActifAuTresor());
            log.info("bp2.setActifClientDeb {}", bp2.getActifClientDeb());
            log.info("bp2.setActifEffesCpte {}", bp2.getActifEffesCpte());
            log.info("bp2.setActifEffetEnc {}", bp2.getActifEffetEnc());
            log.info("bp2.setPassifMaisonMere {}", bp2.getPassifMaisonMere());
            log.info("bp2.setPassifAuTresor {}", bp2.getPassifAuTresor());
            log.info("bp2.setPassifCliCptVue {}", bp2.getPassifCliCptVue());
            log.info("bp2.setPassifCliCpteCh {}", bp2.getPassifCliCpteCh());
            log.info("bp2.setPassifCptApresEnc {}", bp2.getPassifCptApresEnc());
            if (dev != null && dev.equals("952")) {
                bp2.setId(1l);
                bp2.setCodeIsoDevise("XOF");
                log.info(" SAVE BP2INFOS 978 {}", bp2.getCodeIsoDevise());
            }
            if (dev != null && dev.equals("978")) {
                bp2.setId(2l);
                bp2.setCodeIsoDevise("EUR");
                log.info(" SAVE BP2INFOS 978 {}", bp2.getCodeIsoDevise());
            } else if (dev != null && dev.equals("840")) {
                bp2.setId(3l);
                bp2.setCodeIsoDevise("USD");
                log.info(" SAVE BP2INFOS 840 {}", bp2.getCodeIsoDevise());
            } else if (dev != null && dev.equals("124")) {
                bp2.setId(4l);
                bp2.setCodeIsoDevise("CAD");
                log.info(" SAVE BP2INFOS 124 {}", bp2.getCodeIsoDevise());
            } else if (dev != null && dev.equals("826")) {
                bp2.setId(5l);
                bp2.setCodeIsoDevise("GBF");
                log.info(" SAVE BP2INFOS 826 {}", bp2.getCodeIsoDevise());
            } else if (dev != null && dev.equals("756")) {
                bp2.setId(6l);
                bp2.setCodeIsoDevise("CHF");
                log.info(" SAVE BP2INFOS 756 {}", bp2.getCodeIsoDevise());
            } else if (dev != null && dev.equals("392")) {
                bp2.setId(7l);
                bp2.setCodeIsoDevise("JPY");
                log.info(" SAVE BP2INFOS 392 {}", bp2.getCodeIsoDevise());
            } else if (dev != null && dev.equals("156")) {
                bp2.setId(8l);
                bp2.setCodeIsoDevise("CNY");
                log.info(" SAVE BP2INFOS 156 {}", bp2.getCodeIsoDevise());
            }
            bp2InfosService.save(bp2);
            log.info(" AFTER SAVE BP2INFOS devise {}", devise);
        }

    }

    public void saveBp3Infos() {

        //List<String> listDevise = Arrays.asList("952", "978", "840", "124", "826", "756", "392", "156");
        List<String> listDevise = Arrays.asList("XOF", "EUR", "USD", "CAD", "826", "756", "392", "156");
        List<String> listAchVend = Arrays.asList("CORRESETRANG", "CLIENTELE");

        log.info("BEFORE SAVE BP2INFOS");
        for (String AchVend : listAchVend) {
            if (AchVend.equals("CORRESETRANG")) {
                for (String dev : listDevise) {
                    if (dev != null && dev.equals("XOF")) {
                        Bp3Infos bp3 = new Bp3Infos();
                        bp3.setId(new Long(25));
                        bp3.setCodeIsoDevise("XOF");
                        bp3.setLibelleDevise("Franc CFA BCEAO");
                        bp3.acheteurVendeur(AchVend);
                        log.info("SIZE {},", bp3HisService.findSumMonByDevAndNatAndMonPos("SAILOT", dev));
                        bp3.setAchatsBilletETr((bp3HisService.findSumMonByDevAndNatAndMonNeg("SAILOT", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonNeg("SAILOT", dev).doubleValue()
                                : 0.0);
                        bp3.setVentesBilletEtr((bp3HisService.findSumMonByDevAndNatAndMonPos("SAILOT", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonPos("SAILOT", dev).doubleValue()
                                : 0.0);
                        bp3.setAchatsChqVoy(0.0);
                        bp3.setVentesChqVoy(0.0);
                        bp3InfosService.save(bp3);
                        log.info(" SAVE BP3INFOS 978 {}", bp3.getCodeIsoDevise());
                    }
                    if (dev != null && dev.equals("EUR")) {
                        Bp3Infos bp3 = new Bp3Infos();
                        bp3.setId(26l);
                        bp3.setCodeIsoDevise("EUR");
                        bp3.setLibelleDevise("Euro");
                        bp3.acheteurVendeur(AchVend);
                        bp3.setAchatsBilletETr((bp3HisService.findSumMonByDevAndNatAndMonNeg("SAILOT", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonNeg("SAILOT", dev).doubleValue()
                                : 0.0);
                        bp3.setVentesBilletEtr((bp3HisService.findSumMonByDevAndNatAndMonPos("SAILOT", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonPos("SAILOT", dev).doubleValue()
                                : 0.0);
                        bp3.setAchatsChqVoy(0.0);
                        bp3.setVentesChqVoy(0.0);
                        bp3InfosService.save(bp3);
                        log.info(" SAVE BP3INFOS 978 {}", bp3.getCodeIsoDevise());
                    } else if (dev != null && dev.equals("USD")) {
                        Bp3Infos bp3 = new Bp3Infos();
                        bp3.setId(27l);
                        bp3.setCodeIsoDevise("USD");
                        bp3.setLibelleDevise("Dollar US");
                        bp3.acheteurVendeur(AchVend);
                        bp3.setAchatsBilletETr((bp3HisService.findSumMonByDevAndNatAndMonNeg("SAILOT", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonNeg("SAILOT", dev).doubleValue()
                                : 0.0);
                        bp3.setVentesBilletEtr((bp3HisService.findSumMonByDevAndNatAndMonPos("SAILOT", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonPos("SAILOT", dev).doubleValue()
                                : 0.0);
                        bp3.setAchatsChqVoy(0.0);
                        bp3.setVentesChqVoy(0.0);
                        bp3InfosService.save(bp3);
                        log.info(" SAVE BP3INFOS 840 {}", bp3.getCodeIsoDevise());
                    } else if (dev != null && dev.equals("CAD")) {
                        Bp3Infos bp3 = new Bp3Infos();
                        bp3.setId(28l);
                        bp3.setCodeIsoDevise("CAD");
                        bp3.setLibelleDevise("Dollar Canadien");
                        bp3.acheteurVendeur(AchVend);
                        bp3.setAchatsBilletETr((bp3HisService.findSumMonByDevAndNatAndMonNeg("SAILOT", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonNeg("SAILOT", dev).doubleValue()
                                : 0.0);
                        bp3.setVentesBilletEtr((bp3HisService.findSumMonByDevAndNatAndMonPos("SAILOT", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonPos("SAILOT", dev).doubleValue()
                                : 0.0);
                        bp3.setAchatsChqVoy(0.0);
                        bp3.setVentesChqVoy(0.0);
                        bp3InfosService.save(bp3);
                        log.info(" SAVE BP3INFOS 124 {}", bp3.getCodeIsoDevise());
                    } else if (dev != null && dev.equals("GBF")) {
                        Bp3Infos bp3 = new Bp3Infos();
                        bp3.setId(29l);
                        bp3.setCodeIsoDevise("GBF");
                        bp3.setLibelleDevise("Livre Sterling");
                        bp3.acheteurVendeur(AchVend);
                        bp3.setAchatsBilletETr((bp3HisService.findSumMonByDevAndNatAndMonNeg("SAILOT", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonNeg("SAILOT", dev).doubleValue()
                                : 0.0);
                        bp3.setVentesBilletEtr((bp3HisService.findSumMonByDevAndNatAndMonPos("SAILOT", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonPos("SAILOT", dev).doubleValue()
                                : 0.0);
                        bp3.setAchatsChqVoy(0.0);
                        bp3.setVentesChqVoy(0.0);
                        bp3InfosService.save(bp3);
                        log.info(" SAVE BP3INFOS 826 {}", bp3.getCodeIsoDevise());
                    } else if (dev != null && dev.equals("CHF")) {
                        Bp3Infos bp3 = new Bp3Infos();
                        bp3.setId(30l);
                        bp3.setCodeIsoDevise("CHF");
                        bp3.setLibelleDevise("Franc Suisse");
                        bp3.acheteurVendeur(AchVend);
                        bp3.setAchatsBilletETr((bp3HisService.findSumMonByDevAndNatAndMonNeg("SAILOT", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonNeg("SAILOT", dev).doubleValue()
                                : 0.0);
                        bp3.setVentesBilletEtr((bp3HisService.findSumMonByDevAndNatAndMonPos("SAILOT", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonPos("SAILOT", dev).doubleValue()
                                : 0.0);
                        bp3.setAchatsChqVoy(0.0);
                        bp3.setVentesChqVoy(0.0);
                        bp3InfosService.save(bp3);
                        log.info(" SAVE BP3INFOS 756 {}", bp3.getCodeIsoDevise());
                    } else if (dev != null && dev.equals("JPY")) {
                        Bp3Infos bp3 = new Bp3Infos();
                        bp3.setId(31l);
                        bp3.setCodeIsoDevise("JPY");
                        bp3.setLibelleDevise("Yen Japonais");
                        bp3.acheteurVendeur(AchVend);
                        bp3.setAchatsBilletETr((bp3HisService.findSumMonByDevAndNatAndMonNeg("SAILOT", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonNeg("SAILOT", dev).doubleValue()
                                : 0.0);
                        bp3.setVentesBilletEtr((bp3HisService.findSumMonByDevAndNatAndMonPos("SAILOT", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonPos("SAILOT", dev).doubleValue()
                                : 0.0);
                        bp3.setAchatsChqVoy(0.0);
                        bp3.setVentesChqVoy(0.0);
                        bp3InfosService.save(bp3);
                        log.info(" SAVE BP3INFOS 392 {}", bp3.getCodeIsoDevise());
                    } else if (dev != null && dev.equals("CNY")) {
                        Bp3Infos bp3 = new Bp3Infos();
                        bp3.setId(32l);
                        bp3.setCodeIsoDevise("CNY");
                        bp3.setLibelleDevise("Yuan");
                        bp3.acheteurVendeur(AchVend);
                        bp3.setAchatsBilletETr((bp3HisService.findSumMonByDevAndNatAndMonNeg("SAILOT", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonNeg("SAILOT", dev).doubleValue()
                                : 0.0);
                        bp3.setVentesBilletEtr((bp3HisService.findSumMonByDevAndNatAndMonPos("SAILOT", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonPos("SAILOT", dev).doubleValue()
                                : 0.0);
                        bp3.setAchatsChqVoy(0.0);
                        bp3.setVentesChqVoy(0.0);
                        bp3InfosService.save(bp3);
                        log.info(" SAVE BP2INFOS 156 {}", bp3.getCodeIsoDevise());
                    }

                }
            } else if (AchVend.equals("CLIENTELE")) {
                for (String dev : listDevise) {

                    if (dev != null && dev.equals("XOF")) {
                        Bp3Infos bp3 = new Bp3Infos();
                        bp3.setId(1l);
                        bp3.setCodeIsoDevise("XOF");
                        bp3.setLibelleDevise("Franc CFA BCEAO");
                        bp3.acheteurVendeur(AchVend);
                        bp3.setAchatsBilletETr((bp3HisService.findSumMonByDevAndNatAndMonNeg("ACHDEV", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonNeg("ACHDEV", dev).doubleValue()
                                : 0.0);
                        bp3.setVentesBilletEtr((bp3HisService.findSumMonByDevAndNatAndMonPos("VENDEV", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonPos("VENDEV", dev).doubleValue()
                                : 0.0);
                        bp3.setAchatsChqVoy(0.0);
                        bp3.setVentesChqVoy(0.0);
                        bp3InfosService.save(bp3);
                        log.info(" SAVE BP3INFOS 978 {}", bp3.getCodeIsoDevise());
                    }
                    if (dev != null && dev.equals("EUR")) {
                        Bp3Infos bp3 = new Bp3Infos();
                        bp3.setId(2l);
                        bp3.setCodeIsoDevise("EUR");
                        bp3.setLibelleDevise("Euro");
                        bp3.acheteurVendeur(AchVend);
                        bp3.setAchatsBilletETr((bp3HisService.findSumMonByDevAndNatAndMonNeg("ACHDEV", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonNeg("ACHDEV", dev).doubleValue()
                                : 0.0);
                        bp3.setVentesBilletEtr((bp3HisService.findSumMonByDevAndNatAndMonPos("VENDEV", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonPos("VENDEV", dev).doubleValue()
                                : 0.0);
                        bp3.setAchatsChqVoy(0.0);
                        bp3.setVentesChqVoy(0.0);
                        bp3InfosService.save(bp3);
                        log.info(" SAVE BP3INFOS 978 {}", bp3.getCodeIsoDevise());
                    } else if (dev != null && dev.equals("USD")) {
                        Bp3Infos bp3 = new Bp3Infos();
                        bp3.setId(3l);
                        bp3.setCodeIsoDevise("USD");
                        bp3.setLibelleDevise("Dollar US");
                        bp3.acheteurVendeur(AchVend);
                        bp3.setAchatsBilletETr((bp3HisService.findSumMonByDevAndNatAndMonNeg("ACHDEV", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonNeg("ACHDEV", dev).doubleValue()
                                : 0.0);
                        bp3.setVentesBilletEtr((bp3HisService.findSumMonByDevAndNatAndMonPos("VENDEV", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonPos("VENDEV", dev).doubleValue()
                                : 0.0);
                        bp3.setAchatsChqVoy(0.0);
                        bp3.setVentesChqVoy(0.0);
                        bp3InfosService.save(bp3);
                        log.info(" SAVE BP3INFOS 840 {}", bp3.getCodeIsoDevise());
                    } else if (dev != null && dev.equals("CAD")) {
                        Bp3Infos bp3 = new Bp3Infos();
                        bp3.setId(4l);
                        bp3.setCodeIsoDevise("CAD");
                        bp3.setLibelleDevise("Dollar Canadien");
                        bp3.acheteurVendeur(AchVend);
                        bp3.setAchatsBilletETr((bp3HisService.findSumMonByDevAndNatAndMonNeg("ACHDEV", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonNeg("ACHDEV", dev).doubleValue()
                                : 0.0);
                        bp3.setVentesBilletEtr((bp3HisService.findSumMonByDevAndNatAndMonPos("VENDEV", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonPos("VENDEV", dev).doubleValue()
                                : 0.0);
                        bp3.setAchatsChqVoy(0.0);
                        bp3.setVentesChqVoy(0.0);
                        bp3InfosService.save(bp3);
                        log.info(" SAVE BP3INFOS 124 {}", bp3.getCodeIsoDevise());
                    } else if (dev != null && dev.equals("GBF")) {
                        Bp3Infos bp3 = new Bp3Infos();
                        bp3.setId(5l);
                        bp3.setCodeIsoDevise("GBF");
                        bp3.setLibelleDevise("Livre Sterling");
                        bp3.acheteurVendeur(AchVend);
                        bp3.setAchatsBilletETr((bp3HisService.findSumMonByDevAndNatAndMonNeg("ACHDEV", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonNeg("ACHDEV", dev).doubleValue()
                                : 0.0);
                        bp3.setVentesBilletEtr((bp3HisService.findSumMonByDevAndNatAndMonPos("VENDEV", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonPos("VENDEV", dev).doubleValue()
                                : 0.0);
                        bp3.setAchatsChqVoy(0.0);
                        bp3.setVentesChqVoy(0.0);
                        bp3InfosService.save(bp3);
                        log.info(" SAVE BP3INFOS 826 {}", bp3.getCodeIsoDevise());
                    } else if (dev != null && dev.equals("756")) {
                        Bp3Infos bp3 = new Bp3Infos();
                        bp3.setId(6l);
                        bp3.setCodeIsoDevise("CHF");
                        bp3.setLibelleDevise("Franc Suisse");
                        bp3.acheteurVendeur(AchVend);
                        bp3.setAchatsBilletETr((bp3HisService.findSumMonByDevAndNatAndMonNeg("ACHDEV", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonNeg("ACHDEV", dev).doubleValue()
                                : 0.0);
                        bp3.setVentesBilletEtr((bp3HisService.findSumMonByDevAndNatAndMonPos("VENDEV", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonPos("VENDEV", dev).doubleValue()
                                : 0.0);
                        bp3.setAchatsChqVoy(0.0);
                        bp3.setVentesChqVoy(0.0);
                        bp3InfosService.save(bp3);
                        log.info(" SAVE BP3INFOS 756 {}", bp3.getCodeIsoDevise());
                    } else if (dev != null && dev.equals("392")) {
                        Bp3Infos bp3 = new Bp3Infos();
                        bp3.setId(7l);
                        bp3.setCodeIsoDevise("JPY");
                        bp3.setLibelleDevise("Yen Japonais");
                        bp3.acheteurVendeur(AchVend);
                        bp3.setAchatsBilletETr((bp3HisService.findSumMonByDevAndNatAndMonNeg("ACHDEV", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonNeg("ACHDEV", dev).doubleValue()
                                : 0.0);
                        bp3.setVentesBilletEtr((bp3HisService.findSumMonByDevAndNatAndMonPos("VENDEV", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonPos("VENDEV", dev).doubleValue()
                                : 0.0);
                        bp3.setAchatsChqVoy(0.0);
                        bp3.setVentesChqVoy(0.0);
                        bp3InfosService.save(bp3);
                        log.info(" SAVE BP3INFOS 392 {}", bp3.getCodeIsoDevise());
                    } else if (dev != null && dev.equals("156")) {
                        Bp3Infos bp3 = new Bp3Infos();
                        bp3.setId(8l);
                        bp3.setCodeIsoDevise("CNY");
                        bp3.setLibelleDevise("Yuan");
                        bp3.acheteurVendeur(AchVend);
                        bp3.setAchatsBilletETr((bp3HisService.findSumMonByDevAndNatAndMonNeg("ACHDEV", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonNeg("ACHDEV", dev).doubleValue()
                                : 0.0);
                        bp3.setVentesBilletEtr((bp3HisService.findSumMonByDevAndNatAndMonPos("VENDEV", dev) != null)
                                ? bp3HisService.findSumMonByDevAndNatAndMonPos("VENDEV", dev).doubleValue()
                                : 0.0);
                        bp3.setAchatsChqVoy(0.0);
                        bp3.setVentesChqVoy(0.0);
                        bp3InfosService.save(bp3);
                        log.info(" SAVE BP2INFOS 156 {}", bp3.getCodeIsoDevise());
                    }
                }
            }
        }
    }

    public LocalDate recupDateArrete(String filename) {

        DateTimeFormatter dateformat1 = DateTimeFormatter.ofPattern("ddMMyyyy");
        DateTimeFormatter dateformat2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("DATE = " + filename.substring(8, 16));
        LocalDate dateArrete = LocalDate.parse(filename.substring(8, 16), dateformat1);
        System.out.println("FORMAT = " + dateArrete.format(dateformat2));

        return dateArrete;
    }
    
 private static SimpleDateFormat inSDF = new SimpleDateFormat("dd/mm/yyyy");
  private static SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-mm-dd");

  public static String formatDate(String inDate) {
    String outDate = "";
    System.out.println("date inDate "+inDate);
    if (inDate != null) {
        try {
            Date date = inSDF.parse(inDate);
            System.out.println("date inSDF "+date);
            outDate = outSDF.format(date);
            System.out.println("date outSDF "+outDate);
        } catch (ParseException ex){ 
        }
    }
    return outDate;
  }
  
  public class CurrencyHelper {

    /*private static Map<Integer, Currency> currencies = new HashMap<>();

    static {
        Set<Currency> set = Currency.getAvailableCurrencies();
        for (Currency currency : set) {
             currencies.put(currency.getNumericCode(), currency);
        }
    }

    public static Currency getInstance(Integer code) {
        return currencies.get(code);
    }*/
}
    

}
