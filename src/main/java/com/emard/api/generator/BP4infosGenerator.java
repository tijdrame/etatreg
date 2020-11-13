/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emard.api.generator;

import com.emard.api.service.Bp4InfosService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Bouna
 */
public class BP4infosGenerator {
    
    @Autowired
    Bp4InfosService bp4Service;

    public static ByteArrayInputStream bp4infosToExcel(List<Object[]> bp4infos , File templatefile,String codeIdBank, String dateDebutPeriode, String dateFinPeriode) throws IOException {

        
        FileInputStream inputStream = new FileInputStream(templatefile);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);

        
        Row row = firstSheet.getRow(8);
        row.createCell(1).setCellValue(codeIdBank);
        row.createCell(2).setCellValue(dateDebutPeriode);
        row.createCell(3).setCellValue(dateFinPeriode);


        int i=12;
        CellStyle cs = workbook.createCellStyle();
        cs.setDataFormat(workbook.createDataFormat().getFormat("0,0"));
        for (Object[] bp4 : bp4infos) {
                Row row1 = (firstSheet.getRow(i)!= null ? firstSheet.getRow(i) : firstSheet.createRow(i));
                System.out.println("com.emard.api.generator.BP4infosGenerator.bp4infosToExcel()"+bp4[0]!=null && bp4[0].toString()!=null ? bp4[0].toString() : "");
                if (bp4[0]!=null && bp4[0].toString()!=null) {
                System.out.println("com.emard.api.generator.BP4infosGenerator.bp4infosToExcel() name "+bp4[1].toString()+ " -i- "+i+" - row = "+row1.getRowNum());
                //String paysName = CountryCode.getByCode(bp4[0].toString()).getName();
                row1.createCell(0).setCellValue(bp4[1]!=null && bp4[1].toString()!=null ? bp4[1].toString() : "");
                String mntEmi = bp4[2]!=null && bp4[2].toString()!=null ? BigDecimal.valueOf(Double.valueOf(bp4[2].toString())).toPlainString() : "0"; 
                String mntAcq = bp4[3]!=null && bp4[3].toString()!=null ? BigDecimal.valueOf(Double.valueOf(bp4[3].toString())).toPlainString() : "0";
                mntEmi = String.format("%s",BigDecimal.valueOf(Double.valueOf(mntEmi)).toPlainString());
                mntAcq = String.format("%s",BigDecimal.valueOf(Double.valueOf(mntAcq)).toPlainString());
                mntEmi = mntEmi.substring(0, mntEmi.length()-2);
                mntAcq = mntAcq.substring(0, mntAcq.length()-2);
                Double valEmi = new Double(0);
                Double valAcq = new Double(0);
                valEmi = Double.valueOf(mntEmi);
                valAcq = Double.valueOf(mntAcq);
                System.out.println("mntEmi = "+mntEmi);
                System.out.println("mntAcq = "+mntAcq);
                    Cell cell_2 = row1.createCell(2);
                    Cell cell_3 = row1.createCell(3);
                    //cell_2.setCellStyle(cs);
                    //cell_3.setCellStyle(cs);
                row1.createCell(1).setCellValue(bp4[0].toString());
                cell_2.setCellValue(bp4[2]!=null && bp4[2].toString()!=null ? divideByMillion(Double.valueOf(mntEmi)) : Double.valueOf("0"));
                cell_3.setCellValue(bp4[3]!=null && bp4[3].toString()!=null ? divideByMillion(Double.valueOf(mntAcq)) : Double.valueOf("0"));
                i=i+1;
                }
        }

        FileOutputStream output = new FileOutputStream(templatefile);
        workbook.write(output);
      return new ByteArrayInputStream(out.toByteArray());
    }
    
    public static Double divideByMillion (Double val) {
        System.out.println("Double = "+val);
        Double value = val!=null && val!=0 ? Double.valueOf(Math.round(val/1000000)) : 0;
        value = value<0 ? value*(-1) : value;
        System.out.println(" Str = "+String.valueOf(value));
        return value;
    }
    

}
