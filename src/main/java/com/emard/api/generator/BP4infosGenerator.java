/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emard.api.generator;

import com.emard.api.domain.Bp4Infos;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Bouna
 */
public class BP4infosGenerator {

    public static ByteArrayInputStream bp4infosToExcel(Iterable<Bp4Infos> bp4infos, File templatefile,String codeIdBank, String dateDebutPeriode, String dateFinPeriode) throws IOException {

        /* String excelFilePath = "C:\\Users\\Bouna\\Documents\\PERSO\\DOC BPR\\ORAGW\\Envoie\\BPR_BP4_INFOS_XLS.XLS";
         File file = new File(excelFilePath);*/
        FileInputStream inputStream = new FileInputStream(templatefile);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);

        /*firstSheet.getRow(6).getCell(1).setCellValue("SS0172B1");
        firstSheet.getRow(6).getCell(2).setCellValue("01/07/2019");
        firstSheet.getRow(6).getCell(3).setCellValue("31/07/2019");*/
        
        Row row = firstSheet.getRow(8);
        row.createCell(1).setCellValue(codeIdBank);
        row.createCell(2).setCellValue(dateDebutPeriode);
        row.createCell(3).setCellValue(dateFinPeriode);

       /* cellCodeId.setCellValue("SS0172B1");
        cellDateDebut.setCellValue("01/07/2019");
        cellDateFin.setCellValue("31/07/2019");*/
        int i=12;
        for (Bp4Infos bp4 : bp4infos) {             
                Row row1 = firstSheet.getRow(i);
                row1.createCell(0).setCellValue(bp4.getLibellePays());
                row1.createCell(1).setCellValue(bp4.getCodeIsoPays());
                row1.createCell(2).setCellValue(bp4.getMntnosCartes());
                row1.createCell(3).setCellValue(bp4.getMntCartesEtr());
                i=i+1;
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
      return new ByteArrayInputStream(out.toByteArray());
    }

}
