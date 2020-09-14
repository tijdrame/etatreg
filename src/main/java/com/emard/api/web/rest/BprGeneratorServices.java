/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emard.api.web.rest;

import com.emard.api.domain.BankInfos;
import com.emard.api.domain.Bp1Infos;
import com.emard.api.domain.Bp2Infos;
import com.emard.api.domain.Bp3Infos;
import com.emard.api.domain.Bp4Infos;
import com.emard.api.domain.FilesInfos;
import com.emard.api.generator.BP1infosGenerator;
import com.emard.api.generator.BP2infosGenerator;
import com.emard.api.generator.BP3infosGenerator;
import com.emard.api.generator.BP4infosGenerator;
import com.emard.api.generator.CRPinfosGenerator;
import com.emard.api.repository.Bp2InfosRepository;
import com.emard.api.repository.FilesInfosRepository;
import com.emard.api.service.BankInfosService;
import com.emard.api.service.Bp1InfosService;
import com.emard.api.service.Bp2InfosService;
import com.emard.api.service.Bp3InfosService;
import com.emard.api.service.Bp4InfosService;
import com.emard.api.service.FilesInfosService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
public class BprGeneratorServices {

    private final Logger log = LoggerFactory.getLogger(BprGeneratorServices.class);

    @Autowired
    Bp1InfosService bp1Service;
    
    @Autowired
    Bp2InfosService bp2Service;
    
    @Autowired
    Bp3InfosService bp3Service;
    
    @Autowired
    Bp4InfosService bp4Service;
    
    @Autowired
    FilesInfosRepository fileinfosService;
    
    @Autowired
    BankInfosService bankInfoService;

    @GetMapping(value = "/generated/bp2file/{idFile}/{dateRef}/{version}")
    public ResponseEntity<InputStreamResource> excelBP2Report(@PathVariable("idFile") Long idFile, @PathVariable("dateRef") String dateRef, @PathVariable("version") String version) throws IOException, ParseException {
        //Iterable<Long>itrbl = null
        //System.out.println("AVANT "+idFile+ "dateref = "+dateRef);
        Pageable pag = PageRequest.of(0, 100);
        Optional<FilesInfos> fileInfos = fileinfosService.findById(idFile);
        Optional<BankInfos> bankInfos = bankInfoService.findOne(1l);
        ByteArrayInputStream in = null;
        try{
        
        if (fileInfos!=null) {
            String dateDebutPeriode = dateDebutPeriode(dateRef); 
            String dateFinPeriode = dateFinPeriode(dateRef);
            String [] formatD = dateRef.split("-");
            String deb = formatD[2]+formatD[1]+formatD[0];
            String pathOutPutFile = fileInfos.get().getOutputPath()+"/"+fileInfos.get().getCodeApplication()+"_"+bankInfos.get().getSigle()+"_"+deb+"_M_"+fileInfos.get().getCodeFile()+"_"+version+"_"+fileInfos.get().getCodeFormat()+"."+fileInfos.get().getCodeExtension();
            
            File bpFile = new File(pathOutPutFile);
            
            String codeIdBank = bankInfos.get().getCodeId(); 
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
            //formatter = formatter.withLocale(Locale.getDefault());  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
            //LocalDate dateFormater = LocalDate.parse(dateRef, formatter);
            //System.out.println("formater="+dateFormater);
            
            Resource resource = null;
//System.out.println("dateDebutPeriode = "+dateDebutPeriode+ "dateFinPeriode = "+dateFinPeriode);
            switch (fileInfos.get().getCodeFile()) {
                case "BP1":
                    // replace by resource
                    // URL urlBp1 = getClass().getClassLoader().getResource("fichierstemplate/BPR_BP1_INFOS_XLS.XLS");
                    //File templateFileBp1 = new File(urlBp1.getPath());
                    
                    resource = new ClassPathResource("fichierstemplate/BPR_BP1_INFOS_XLS.XLS");
                    InputStream templateFileBp1 = resource.getInputStream();
                    copyFile(templateFileBp1, bpFile);
                    Iterable<Bp1Infos> bp1infos = bp1Service.findAll(pag);
                    in = BP1infosGenerator.bp1infosToExcel(bp1infos,bpFile,codeIdBank,dateDebutPeriode,dateFinPeriode);
                    break;
                case "BP2":
                log.info("Path {}",getClass().getClassLoader().getResource(""));
                    resource = new ClassPathResource("fichierstemplate/BPR_BP2_INFOS_XLS.XLS");
                    InputStream templateFileBp2 = resource.getInputStream();
                    copyFile(templateFileBp2, bpFile);
                    Iterable<Bp2Infos> bp2infos = bp2Service.findAll(pag);
                    in = BP2infosGenerator.bp2infosToExcel(bp2infos,bpFile,codeIdBank,dateDebutPeriode,dateFinPeriode);
                    break;
                case "BP3" :
                    resource = new ClassPathResource("fichierstemplate/BPR_BP3_INFOS_XLS.XLS");
                    InputStream templatefileBp3 = resource.getInputStream();
                    copyFile(templatefileBp3, bpFile);
                    Iterable<Bp3Infos> bp3infos = bp3Service.findAll(pag);
                    in = BP3infosGenerator.bp3infosToExcel(bp3infos,bpFile,codeIdBank,dateDebutPeriode,dateFinPeriode);
                    break;
                case "BP4" :
                    resource = new ClassPathResource("fichierstemplate/BPR_BP4_INFOS_XLS.XLS");
                    InputStream templatefileBp4 = resource.getInputStream();
                    copyFile(templatefileBp4, bpFile);
                    Iterable<Bp4Infos> bp4infos = bp4Service.findAll(pag);
                    
                    in = BP4infosGenerator.bp4infosToExcel(bp4infos,bpFile, codeIdBank,dateDebutPeriode,dateFinPeriode);
                    break;
                case "CRP" :
                    resource = new ClassPathResource("fichierstemplate/BPR_CRP_INFOS_CSV.CSV");
                    InputStream templatefileCrp = resource.getInputStream();
                    copyFile(templatefileCrp, bpFile);
                    Iterable<Bp4Infos> crpinfos = bp4Service.findAll(pag);
                    
                    in = CRPinfosGenerator.crpInfosToCsv(crpinfos,bpFile, codeIdBank,dateDebutPeriode,dateFinPeriode);
                    break;
                case "ATR" :
                    resource = new ClassPathResource("fichierstemplate/BPR_ATR_INFOS_CSV.CSV");
                    InputStream templatefileAtr = resource.getInputStream();
                    copyFile(templatefileAtr, bpFile);
                    Iterable<Bp4Infos> atrinfos = bp4Service.findAll(pag);
                    
                    in = CRPinfosGenerator.atrInfosToCsv(atrinfos,bpFile, codeIdBank,dateDebutPeriode,dateFinPeriode);
                     break; 
                default:
                    log.info("Fichier inexistant ");
            }
        } else {
            log.info("Aucun fichier sélectionné");
        }
        if (in!=null) 
            in.close();
        // return IOUtils.toByteArray(in);
        /*BP2infosGenerator.Tableau("C:\\Users\\Bouna\\Documents\\PERSO\\DOC BPR\\ORAGW\\GROUPE1\\ExtractionsCI\\bkcom_0619.xlsx");*/
        
        //headers.add("Content-Disposition", "attachment; filename=customers.xlsx");

        
        }catch (Exception e){
                e.printStackTrace();
        } 
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE));
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }
    
    public static void copyFile(InputStream inStream, File bfile)
    {

        //InputStream inStream = null;
        OutputStream outStream = null;

        try{

            /*afile =new File("Afile.txt");
            bfile =new File("Bfile.txt");*/

            //inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[]buffer = new byte[1024];

            int length;
           //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0){

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            //System.out.println("File is copied successful!");

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static String dateDebutPeriode (String dateRef) throws ParseException {
        
        
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//yyyy-MM-dd
        //Date dt = sdf .parse(dateRef.substring(0,1)+"/"+dateRef.substring(2, 3)+"/"+dateRef.substring(4, 7));
               /*System.out.println("jj = "+dateRef.substring(6));
       System.out.println("mm = "+dateRef.substring(2,4));
       System.out.println("yyyy = "+dateRef.substring(0,4));*/
       Date dt = sdf .parse(dateRef);
       Calendar c = Calendar.getInstance();
       c.setTime(dt);
       c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
       String firstDate = sdf.format(c.getTime());
       String [] tab = firstDate.split("-"); 
       //System.out.println("firstDate "+firstDate);
       //System.out.println("date deb ret = "+firstDate.substring(6)+"/"+firstDate.substring(2, 4)+"/"+firstDate.substring(0,4));
       //return firstDate.substring(4)+"/"+firstDate.substring(2, 4)+"/"+firstDate.substring(0,2);
       return tab[2]+"/"+tab[1]+"/"+tab[0];
    }
    
    public static String dateFinPeriode (String dateRef) throws ParseException {
        
       DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//ddMMyyyy
       //System.out.println("jj = [{}]"+dateRef.substring(6));
       //System.out.println("mm = "+dateRef.substring(2,4));
       //System.out.println("yyyy = "+dateRef.substring(5));
       Date dt = sdf .parse(dateRef);
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
       String [] tab = lastDate.split("-"); 
       //return lastDate.substring(0,2)+"/"+lastDate.substring(2, 4)+"/"+lastDate.substring(4);
       return tab[2]+"/"+tab[1]+"/"+tab[0];
    }

}
