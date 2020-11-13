/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emard.api.generator;

import com.emard.api.domain.Bp1Infos;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Bouna
 */
public class BP1infosGenerator {
    
    private static final Logger log = LoggerFactory.getLogger(BP1infosGenerator.class);

    public static ByteArrayInputStream bp1infosToExcel(Iterable<Bp1Infos> bp1infos, File templatefile, String codeIdBank, String dateDebutPeriode, String dateFinPeriode) throws IOException {

        FileInputStream inputStream = new FileInputStream(templatefile);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);   
        
        Row row = firstSheet.getRow(7);
        row.createCell(1).setCellValue(codeIdBank);
        row.createCell(2).setCellValue(dateDebutPeriode);
        row.createCell(3).setCellValue(dateFinPeriode);
        /*
         
        D130 = D130 + D131 + D133 + D134 + D135;
        D160 = D160 + (D100<0 ? -1*D100 : D100) + D110 + D130 + D150;
        D230 = D230 + D231 + D233 + D234 + D235;
        D260 = D260 + D200 + D210 + D230 + (D250<0 ? -1*D250 : D250);*/

        
        for (Bp1Infos bp1 : bp1infos) {             
                Row row1 = firstSheet.getRow(13);
                row1.createCell(2).setCellValue(divideByMillion(bp1.getD100().replaceAll("-", "")));
                row1.createCell(5).setCellValue(divideByMillion(bp1.getD200()));
                
                Row row2 = firstSheet.getRow(14);
                row2.createCell(2).setCellValue(divideByMillion(bp1.getD110()));
                row2.createCell(5).setCellValue(divideByMillion(bp1.getD210()));
                
                Row row3 = firstSheet.getRow(15);
                row3.createCell(2).setCellValue(divideByMillion(bp1.getD120()));
                row3.createCell(5).setCellValue(divideByMillion(bp1.getD220()));
                
                Row row4 = firstSheet.getRow(16);
                row4.createCell(2).setCellValue(divideByMillion(bp1.getD130()));
                row4.createCell(5).setCellValue(divideByMillion(bp1.getD230()));

                Row row5 = firstSheet.getRow(18);
                row5.createCell(2).setCellValue(divideByMillion(bp1.getD131()));
                row5.createCell(5).setCellValue(divideByMillion(bp1.getD231()));
                
                Row row6 = firstSheet.getRow(19);
                row6.createCell(2).setCellValue(divideByMillion(bp1.getD132()));
                row6.createCell(5).setCellValue(divideByMillion(bp1.getD232()));

                Row row7 = firstSheet.getRow(20);
                row7.createCell(2).setCellValue(divideByMillion(bp1.getD133()));
                row7.createCell(5).setCellValue(divideByMillion(bp1.getD233()));
                
                Row row8 = firstSheet.getRow(21);
                row8.createCell(2).setCellValue(divideByMillion(bp1.getD134()));
                row8.createCell(5).setCellValue(divideByMillion(bp1.getD234()));

                Row row9 = firstSheet.getRow(22);
                row9.createCell(2).setCellValue(divideByMillion(bp1.getD135()));
                row9.createCell(5).setCellValue(divideByMillion(bp1.getD235()));
                
                Row row10 = firstSheet.getRow(23);
                row10.createCell(2).setCellValue(divideByMillion(bp1.getD140()));
                row10.createCell(5).setCellValue(divideByMillion(bp1.getD240())); 
                log.info("D150 {}", bp1.getD150());
                log.info("D250 {}", bp1.getD250());
                log.info("D100 {}", bp1.getD100());
                log.info("D200 {}", bp1.getD200());
                log.info("D110 {}", bp1.getD110());
                log.info("D210 {}", bp1.getD210());
                log.info("D120 {}", bp1.getD120());
                log.info("D220 {}", bp1.getD220());   
                log.info("D130 {}", bp1.getD130());
                log.info("D230 {}", bp1.getD230());
                log.info("D131 {}", bp1.getD131());
                log.info("D231 {}", bp1.getD231());
                log.info("D132 {}", bp1.getD132());
                log.info("D232 {}", bp1.getD232());
                log.info("D133 {}", bp1.getD133());
                log.info("D233 {}", bp1.getD233());
                log.info("D134 {}", bp1.getD134());
                log.info("D234 {}", bp1.getD234());                
                log.info("D135 {}", bp1.getD135());
                log.info("D235 {}", bp1.getD235());
                log.info("D140 {}", bp1.getD140());
                log.info("D240 {}", bp1.getD240());
                log.info("D160 {}", bp1.getD160());
                log.info("D260 {}", bp1.getD260());
                
                Row row11 = firstSheet.getRow(24);
                row11.createCell(2).setCellValue(divideByMillion(bp1.getD150()));
                row11.createCell(5).setCellValue(divideByMillion(bp1.getD250().replaceAll("-", "")));
                
                Row row12 = firstSheet.getRow(25);
                row12.createCell(2).setCellValue(divideByMillion(bp1.getD160()));
                row12.createCell(5).setCellValue(divideByMillion(bp1.getD260())); 

                
        }

        FileOutputStream output = new FileOutputStream(templatefile);
        workbook.write(output);
      return new ByteArrayInputStream(out.toByteArray());
    }
    
    public static String divideByMillion (String val) {
        
        String value = String.valueOf(val!=null && !val.equals("0") ? Math.round(Double.valueOf(val)/1000000) : "0");
        value = value.replace('.', ',');
        return value;
    }

}
