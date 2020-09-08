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
import java.io.FileWriter;
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
public class CRPinfosGenerator {

    public static ByteArrayInputStream crpInfosToCsv(Iterable<Bp4Infos> bp4infos, File templatefile,String codeIdBank, String dateDebutPeriode, String dateFinPeriode) throws IOException {

        /* String excelFilePath = "C:\\Users\\Bouna\\Documents\\PERSO\\DOC BPR\\ORAGW\\Envoie\\BPR_BP4_INFOS_XLS.XLS";
         File file = new File(excelFilePath);*/
        FileInputStream inputStream = new FileInputStream(templatefile);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //Workbook workbook = new HSSFWorkbook(inputStream);
        
      FileOutputStream output = new FileOutputStream(templatefile);
        //workbook.write(output);

      return new ByteArrayInputStream(out.toByteArray());
    }
    
    public static ByteArrayInputStream atrInfosToCsv(Iterable<Bp4Infos> bp4infos, File templatefile,String codeIdBank, String dateDebutPeriode, String dateFinPeriode) throws IOException {

        /* String excelFilePath = "C:\\Users\\Bouna\\Documents\\PERSO\\DOC BPR\\ORAGW\\Envoie\\BPR_BP4_INFOS_XLS.XLS";
         File file = new File(excelFilePath);*/
        FileInputStream inputStream = new FileInputStream(templatefile);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //Workbook workbook = new HSSFWorkbook(inputStream);
        
      FileOutputStream output = new FileOutputStream(templatefile);
        //workbook.write(output);

      return new ByteArrayInputStream(out.toByteArray());
    }

}
