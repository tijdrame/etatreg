/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emard.api.generator;

import com.emard.api.domain.Bp1Infos;
import com.emard.api.domain.FilesInfos;
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
public class BP1infosGenerator {

    public static ByteArrayInputStream bp1infosToExcel(Iterable<Bp1Infos> bp1infos, File templatefile, String codeIdBank, String dateDebutPeriode, String dateFinPeriode) throws IOException {

       /* String excelFilePath = "C:\\Users\\Bouna\\Documents\\PERSO\\DOC BPR\\ORAGW\\Envoie\\BPR_BP1_INFOS_XLS.XLS";
        File file = new File(excelFilePath);*/
        FileInputStream inputStream = new FileInputStream(templatefile);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);

        /*firstSheet.getRow(6).getCell(1).setCellValue("SS0172B1");
        firstSheet.getRow(6).getCell(2).setCellValue("01/07/2019");
        firstSheet.getRow(6).getCell(3).setCellValue("31/07/2019");*/
        
        
        Row row = firstSheet.getRow(7);
        row.createCell(1).setCellValue(codeIdBank);
        row.createCell(2).setCellValue(dateDebutPeriode);
        row.createCell(3).setCellValue(dateFinPeriode);

       /* cellCodeId.setCellValue("SS0172B1");
        cellDateDebut.setCellValue("01/07/2019");
        cellDateFin.setCellValue("31/07/2019");*/
        
        for (Bp1Infos bp1 : bp1infos) {             
                Row row1 = firstSheet.getRow(13);
                row1.createCell(2).setCellValue(bp1.getD100());
                row1.createCell(5).setCellValue(bp1.getD200());
                
                Row row2 = firstSheet.getRow(14);
                row2.createCell(2).setCellValue(bp1.getD110());
                row2.createCell(5).setCellValue(bp1.getD210());
                
                Row row3 = firstSheet.getRow(15);
                row3.createCell(2).setCellValue(bp1.getD120());
                row3.createCell(5).setCellValue(bp1.getD220());
                
                Row row4 = firstSheet.getRow(16);
                row4.createCell(2).setCellValue(bp1.getD130());
                row4.createCell(5).setCellValue(bp1.getD230());

                Row row5 = firstSheet.getRow(18);
                row5.createCell(2).setCellValue(bp1.getD131());
                row5.createCell(5).setCellValue(bp1.getD231());
                
                Row row6 = firstSheet.getRow(19);
                row6.createCell(2).setCellValue(bp1.getD132());
                row6.createCell(5).setCellValue(bp1.getD232());

                Row row7 = firstSheet.getRow(20);
                row7.createCell(2).setCellValue(bp1.getD133());
                row7.createCell(5).setCellValue(bp1.getD233());
                
                Row row8 = firstSheet.getRow(21);
                row8.createCell(2).setCellValue(bp1.getD134());
                row8.createCell(5).setCellValue(bp1.getD234());

                Row row9 = firstSheet.getRow(22);
                row9.createCell(2).setCellValue(bp1.getD135());
                row9.createCell(5).setCellValue(bp1.getD235());
                
                Row row10 = firstSheet.getRow(23);
                row10.createCell(2).setCellValue(bp1.getD140());
                row10.createCell(5).setCellValue(bp1.getD240()); 
                
                Row row11 = firstSheet.getRow(24);
                row9.createCell(2).setCellValue(bp1.getD150());
                row9.createCell(5).setCellValue(bp1.getD250());
                
                Row row12 = firstSheet.getRow(25);
                row12.createCell(2).setCellValue(bp1.getD160());
                row12.createCell(5).setCellValue(bp1.getD260()); 

                
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
