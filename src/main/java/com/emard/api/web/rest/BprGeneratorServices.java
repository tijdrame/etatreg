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
import com.emard.api.repository.Bp2InfosRepository;
import com.emard.api.repository.FilesInfosRepository;
import com.emard.api.service.BankInfosService;
import com.emard.api.service.Bp1InfosService;
import com.emard.api.service.Bp2InfosService;
import com.emard.api.service.Bp3InfosService;
import com.emard.api.service.Bp4InfosService;
import com.emard.api.service.FilesInfosService;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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

import io.github.jhipster.web.util.HeaderUtil;

/**
 *
 * @author Bouna
 */
@RestController
@RequestMapping("/api/bpr")
public class BprGeneratorServices {

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
    public ResponseEntity<InputStreamResource> excelBP2Report(@PathVariable("idFile") Long idFile,
            @PathVariable("dateRef") String dateRef, @PathVariable("version") String version)
            throws IOException, ParseException {
        // Iterable<Long>itrbl = null
        System.out.println("AVANT " + idFile + "dateref = " + dateRef);
        Pageable pag = PageRequest.of(0, 100);
        Optional<FilesInfos> fileInfos = fileinfosService.findById(idFile);
        Optional<BankInfos> bankInfos = bankInfoService.findOne(1l);

        ByteArrayInputStream in = null;
        if (fileInfos != null) {
            String pathOutPutFile = fileInfos.get().getOutputPath() + "/" + fileInfos.get().getCodeApplication() + "_"
                    + bankInfos.get().getSigle() + "_" + dateRef + "_M_" + fileInfos.get().getCodeFile() + "_" + version
                    + "_" + fileInfos.get().getCodeFormat() + "." + fileInfos.get().getCodeExtension();
            File bpFile = new File(pathOutPutFile);
            String codeIdBank = bankInfos.get().getCodeId();
            String dateDebutPeriode = dateDebutPeriode(dateRef);
            String dateFinPeriode = dateFinPeriode(dateRef);

            switch (fileInfos.get().getCodeFile()) {
                case "BP1":
                    URL urlBp1 = getClass().getClassLoader().getResource("fichierstemplate/BPR_BP1_INFOS_XLS.XLS");
                    File templateFileBp1 = new File(urlBp1.getPath());
                    copyFile(templateFileBp1, bpFile);
                    Iterable<Bp1Infos> bp1infos = bp1Service.findAll(pag);
                    in = BP1infosGenerator.bp1infosToExcel(bp1infos, bpFile, codeIdBank, dateDebutPeriode,
                            dateFinPeriode);
                    break;
                case "BP2":
                    System.out.println("Path " + getClass().getClassLoader().getResource(""));
                    URL urlBp2 = getClass().getClassLoader().getResource("fichierstemplate/BPR_BP2_INFOS_XLS.XLS");
                    File templatefileBp2 = new File(urlBp2.getPath());
                    copyFile(templatefileBp2, bpFile);
                    Iterable<Bp2Infos> bp2infos = bp2Service.findAll(pag);
                    in = BP2infosGenerator.bp2infosToExcel(bp2infos, bpFile, codeIdBank, dateDebutPeriode,
                            dateFinPeriode);
                    //templatefileBp2.setWritable(true);
                    break;
                case "BP3":
                    URL urlBp3 = getClass().getClassLoader().getResource("fichierstemplate/BPR_BP3_INFOS_XLS.XLS");
                    File templatefileBp3 = new File(urlBp3.getPath());
                    copyFile(templatefileBp3, bpFile);
                    Iterable<Bp3Infos> bp3infos = bp3Service.findAll(pag);
                    in = BP3infosGenerator.bp3infosToExcel(bp3infos, bpFile, codeIdBank, dateDebutPeriode,
                            dateFinPeriode);
                    break;
                case "BP4":
                    URL urlBp4 = getClass().getClassLoader().getResource("fichierstemplate/BPR_BP4_INFOS_XLS.XLS");
                    File templatefileBp4 = new File(urlBp4.getPath());
                    copyFile(templatefileBp4, bpFile);
                    Iterable<Bp4Infos> bp4infos = bp4Service.findAll(pag);
                    in = BP4infosGenerator.bp4infosToExcel(bp4infos, templatefileBp4, codeIdBank, dateDebutPeriode,
                            dateFinPeriode);
                    break;
                default:
                    System.out.println("Fichier inexistant ");
            }
        } else {
            System.out.println("Aucun fichier sélectionné");
        }
        in.close();
        //in.nullInputStream()
        // return IOUtils.toByteArray(in);
        /*
         * BP2infosGenerator.
         * Tableau("C:\\Users\\Bouna\\Documents\\PERSO\\DOC BPR\\ORAGW\\GROUPE1\\ExtractionsCI\\bkcom_0619.xlsx"
         * );
         */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE));
        return new ResponseEntity<>(new InputStreamResource(in), headers, HttpStatus.OK);
    }

    public static void copyFile(File afile, File bfile) {

        InputStream inStream = null;
        OutputStream outStream = null;

        try {

            /*
             * afile =new File("Afile.txt"); bfile =new File("Bfile.txt");
             */

            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[] buffer = new byte[1024];

            int length;
            // copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            System.out.println("File is copied successful!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String dateDebutPeriode(String dateRef) throws ParseException {

        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = sdf.parse("31/07/2019");
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        String firstDate = sdf.format(c.getTime());
        System.out.println("firstDate " + firstDate);
        return firstDate;
    }

    public static String dateFinPeriode(String dateRef) throws ParseException {

        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = sdf.parse("31/07/2019");
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        // get last day of the month - add month, substract a day.
        /*
         * c.add(Calendar.MONTH, 0); c.add(Calendar.DAY_OF_MONTH, 0);
         */
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        String lastDate = sdf.format(c.getTime());
        System.out.println("lastDate " + lastDate);
        return lastDate;
    }

}
