/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emard.api.generator;

import com.emard.api.domain.Bp2Infos;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Bouna
 */
public class BP2infosGenerator {
    
    

    public  static ByteArrayInputStream bp2infosToExcel(Iterable<Bp2Infos> bp2infos, File templatefile, String codeIdBank, String dateDebutPeriode, String dateFinPeriode) throws IOException {
        
    
            
        /*String excelFilePath = "C:\\Users\\Bouna\\Documents\\PERSO\\DOC BPR\\ORAGW\\Envoie\\BPR_BP2_INFOS_XLS.XLS";
        File file = new File(excelFilePath);*/
        System.out.println(" templatefile before = "+templatefile);
        
        FileInputStream inputStream = new FileInputStream(templatefile);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);

        System.out.println("codeIdBank = "+codeIdBank+ " templatefile = "+templatefile);
        System.out.println("firstSheet = "+firstSheet.getSheetName());
        /*firstSheet.getRow(6).getCell(1).setCellValue("SS0172B1");
        firstSheet.getRow(6).getCell(2).setCellValue("01/07/2019");
        firstSheet.getRow(6).getCell(3).setCellValue("31/07/2019");*/
            try {
        Row row = firstSheet.getRow(6);
        if (row == null) row = firstSheet.createRow(6);
        System.out.println("row "+row.toString());
        row.createCell(1).setCellValue(codeIdBank);
        row.createCell(2).setCellValue(dateDebutPeriode);
        row.createCell(3).setCellValue(dateFinPeriode);

       /* cellCodeId.setCellValue("SS0172B1");
        cellDateDebut.setCellValue("01/07/2019");
        cellDateFin.setCellValue("31/07/2019");*/

        for (Bp2Infos bp2 : bp2infos) {             
            
            if (bp2.getCodeIsoDevise().equals("XOF")) {
                Row row1 = firstSheet.getRow(12);
                if (row1 == null) row1 = firstSheet.createRow(12);
                row1.createCell(2).setCellValue(bp2.getActifBilletEtRcai());
                row1.createCell(3).setCellValue(bp2.getActifMaisonMere());
                row1.createCell(4).setCellValue(bp2.getActifAuTresor());
                row1.createCell(5).setCellValue(bp2.getActifClientDeb());
                row1.createCell(6).setCellValue(bp2.getActifEffesCpte());
                row1.createCell(7).setCellValue(bp2.getActifEffetEnc());
                row1.createCell(8).setCellValue(bp2.getPassifMaisonMere());
                row1.createCell(9).setCellValue(bp2.getPassifAuTresor());
                row1.createCell(10).setCellValue(bp2.getPassifCliCptVue());
                row1.createCell(11).setCellValue(bp2.getPassifCliCpteCh());
                row1.createCell(12).setCellValue(bp2.getPassifCptApresEnc());
            }
            if (bp2.getCodeIsoDevise().equals("EUR")) {
                Row row2 = firstSheet.getRow(13);
                if (row2 == null) row2 = firstSheet.createRow(13);
                row2.createCell(2).setCellValue(bp2.getActifBilletEtRcai());
                row2.createCell(3).setCellValue(bp2.getActifMaisonMere());
                row2.createCell(4).setCellValue(bp2.getActifAuTresor());
                row2.createCell(5).setCellValue(bp2.getActifClientDeb());
                row2.createCell(6).setCellValue(bp2.getActifEffesCpte());
                row2.createCell(7).setCellValue(bp2.getActifEffetEnc());
                row2.createCell(8).setCellValue(bp2.getPassifMaisonMere());
                row2.createCell(9).setCellValue(bp2.getPassifAuTresor());
                row2.createCell(10).setCellValue(bp2.getPassifCliCptVue());
                row2.createCell(11).setCellValue(bp2.getPassifCliCpteCh());
                row2.createCell(12).setCellValue(bp2.getPassifCptApresEnc());
            }
            if (bp2.getCodeIsoDevise().equals("USD")) {
                Row row3 = firstSheet.getRow(14);
                if (row3 == null) row3 = firstSheet.createRow(14);
                row3.createCell(2).setCellValue(bp2.getActifBilletEtRcai());
                row3.createCell(3).setCellValue(bp2.getActifMaisonMere());
                row3.createCell(4).setCellValue(bp2.getActifAuTresor());
                row3.createCell(5).setCellValue(bp2.getActifClientDeb());
                row3.createCell(6).setCellValue(bp2.getActifEffesCpte());
                row3.createCell(7).setCellValue(bp2.getActifEffetEnc());
                row3.createCell(8).setCellValue(bp2.getPassifMaisonMere());
                row3.createCell(9).setCellValue(bp2.getPassifAuTresor());
                row3.createCell(10).setCellValue(bp2.getPassifCliCptVue());
                row3.createCell(11).setCellValue(bp2.getPassifCliCpteCh());
                row3.createCell(12).setCellValue(bp2.getPassifCptApresEnc());
            }
            if (bp2.getCodeIsoDevise().equals("CAD")) {
                Row row4 = firstSheet.getRow(15);
                if (row4 == null) row4 = firstSheet.createRow(15);
                row4.createCell(2).setCellValue(bp2.getActifBilletEtRcai());
                row4.createCell(3).setCellValue(bp2.getActifMaisonMere());
                row4.createCell(4).setCellValue(bp2.getActifAuTresor());
                row4.createCell(5).setCellValue(bp2.getActifClientDeb());
                row4.createCell(6).setCellValue(bp2.getActifEffesCpte());
                row4.createCell(7).setCellValue(bp2.getActifEffetEnc());
                row4.createCell(8).setCellValue(bp2.getPassifMaisonMere());
                row4.createCell(9).setCellValue(bp2.getPassifAuTresor());
                row4.createCell(10).setCellValue(bp2.getPassifCliCptVue());
                row4.createCell(11).setCellValue(bp2.getPassifCliCpteCh());
                row4.createCell(12).setCellValue(bp2.getPassifCptApresEnc());
            }

            if (bp2.getCodeIsoDevise().equals("GBP")) {
                Row row5 = firstSheet.getRow(16);
                if (row5 == null) row5 = firstSheet.createRow(16);
                row5.createCell(2).setCellValue(bp2.getActifBilletEtRcai());
                row5.createCell(3).setCellValue(bp2.getActifMaisonMere());
                row5.createCell(4).setCellValue(bp2.getActifAuTresor());
                row5.createCell(5).setCellValue(bp2.getActifClientDeb());
                row5.createCell(6).setCellValue(bp2.getActifEffesCpte());
                row5.createCell(7).setCellValue(bp2.getActifEffetEnc());
                row5.createCell(8).setCellValue(bp2.getPassifMaisonMere());
                row5.createCell(9).setCellValue(bp2.getPassifAuTresor());
                row5.createCell(10).setCellValue(bp2.getPassifCliCptVue());
                row5.createCell(11).setCellValue(bp2.getPassifCliCpteCh());
                row5.createCell(12).setCellValue(bp2.getPassifCptApresEnc());
            }

            if (bp2.getCodeIsoDevise().equals("CHF")) {
                Row row6 = firstSheet.getRow(17);
                if (row6 == null) row6 = firstSheet.createRow(7);
                row6.createCell(2).setCellValue(bp2.getActifBilletEtRcai());
                row6.createCell(3).setCellValue(bp2.getActifMaisonMere());
                row6.createCell(4).setCellValue(bp2.getActifAuTresor());
                row6.createCell(5).setCellValue(bp2.getActifClientDeb());
                row6.createCell(6).setCellValue(bp2.getActifEffesCpte());
                row6.createCell(7).setCellValue(bp2.getActifEffetEnc());
                row6.createCell(8).setCellValue(bp2.getPassifMaisonMere());
                row6.createCell(9).setCellValue(bp2.getPassifAuTresor());
                row6.createCell(10).setCellValue(bp2.getPassifCliCptVue());
                row6.createCell(11).setCellValue(bp2.getPassifCliCpteCh());
                row6.createCell(12).setCellValue(bp2.getPassifCptApresEnc());
            }
            if (bp2.getCodeIsoDevise().equals("JPY")) {
                Row row7 = firstSheet.getRow(18);
                if (row7 == null) row7 = firstSheet.createRow(18);
                row7.createCell(2).setCellValue(bp2.getActifBilletEtRcai());
                row7.createCell(3).setCellValue(bp2.getActifMaisonMere());
                row7.createCell(4).setCellValue(bp2.getActifAuTresor());
                row7.createCell(5).setCellValue(bp2.getActifClientDeb());
                row7.createCell(6).setCellValue(bp2.getActifEffesCpte());
                row7.createCell(7).setCellValue(bp2.getActifEffetEnc());
                row7.createCell(8).setCellValue(bp2.getPassifMaisonMere());
                row7.createCell(9).setCellValue(bp2.getPassifAuTresor());
                row7.createCell(10).setCellValue(bp2.getPassifCliCptVue());
                row7.createCell(11).setCellValue(bp2.getPassifCliCpteCh());
                row7.createCell(12).setCellValue(bp2.getPassifCptApresEnc());
            }

            if (bp2.getCodeIsoDevise().equals("CNY")) {
                Row row8 = firstSheet.getRow(19);
                if (row8 == null) row8 = firstSheet.createRow(19);
                row8.createCell(2).setCellValue(bp2.getActifBilletEtRcai());
                row8.createCell(3).setCellValue(bp2.getActifMaisonMere());
                row8.createCell(4).setCellValue(bp2.getActifAuTresor());
                row8.createCell(5).setCellValue(bp2.getActifClientDeb());
                row8.createCell(6).setCellValue(bp2.getActifEffesCpte());
                row8.createCell(7).setCellValue(bp2.getActifEffetEnc());
                row8.createCell(8).setCellValue(bp2.getPassifMaisonMere());
                row8.createCell(9).setCellValue(bp2.getPassifAuTresor());
                row8.createCell(10).setCellValue(bp2.getPassifCliCptVue());
                row8.createCell(11).setCellValue(bp2.getPassifCliCpteCh());
                row8.createCell(12).setCellValue(bp2.getPassifCptApresEnc());
            }
        }
       /* FileOutputStream output = new FileOutputStream("BP2");
            try {
                
            firstSheet.getWorkbook().write(output);
            output.close();
            } catch (FileNotFoundException e){
            e.printStackTrace();
            } catch(IOException e){
            e.printStackTrace();
            }*/
               
        FileOutputStream output = new FileOutputStream(templatefile);
        workbook.write(output);
        } catch (Exception e){
                System.out.println("EXCEPTION = "+e.getMessage());
        }
      return new ByteArrayInputStream(out.toByteArray());
    }

    public static void Tableau(String fileName)
    {
        try
        {
            
            FileInputStream file = new FileInputStream(fileName);
            Workbook workbook = WorkbookFactory.create(file);
            final Sheet sheet = workbook.getSheetAt(0);//workbook.getSheet(sheetName);
            int top = sheet.getFirstRowNum();
            int bottom = sheet.getLastRowNum();
            Row line = sheet.getRow(top);
            int start = line.getFirstCellNum();
            int end = line.getLastCellNum();    
            int length = end - start;
            
            System.out.println("start = "+start+ " end = "+end+" length = "+length+" top ="+top+ " bottom ="+bottom);
            while(length == 0)
            {
                top++;
                line = sheet.getRow(top);
                start = line.getFirstCellNum();
                end = line.getLastCellNum();    
                length = end - start;
            }
            System.out.println("AFTER WHILE length = "+length);
            int hight = bottom - top;
            System.out.println("AFTER WHILE hight = "+hight);
            Double D100 = new Double(0);
            Double D200 = new Double(0);
            for (int index = 0; index < hight; index++) 
            {
                line = sheet.getRow(index + top + 1);
                int z = index + top + 1;
                System.out.println("index + top + 1 = "+index + top + 1);
                //System.out.println("line = "+line);
                System.out.println("cellule "+z+" = "+line.getCell(4));
                System.out.println("devise "+z+" = "+line.getCell(1));
                Cell cellule = line.getCell(4);
                if (cellule!=null) {
                 if (cellule.getNumericCellValue() < 0) {
                     D100 = D100 + cellule.getNumericCellValue();
                 } else {
                     D200 = D200 + cellule.getNumericCellValue();
                 }
                         
                }
                System.out.println("D100 = "+D100+ " D200 = "+D200);

            }
            workbook.close();
            file.close();
        }
         catch (IOException e) 
        {
             e.printStackTrace();
        }
    }
    
    public static void copyFile(File afile, File bfile)
    {

        InputStream inStream = null;
        OutputStream outStream = null;

        try{

            afile =new File("Afile.txt");
            bfile =new File("Bfile.txt");

            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[]buffer = new byte[1024];

            int length;
           //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0){

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            System.out.println("File is copied successful!");

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
