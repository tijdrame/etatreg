/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emard.api.generator;

import com.emard.api.domain.Bp3Infos;
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
public class BP3infosGenerator {

    public static ByteArrayInputStream bp3infosToExcel(Iterable<Bp3Infos> bp3infos, File templatefile, String codeIdBank, String dateDebutPeriode, String dateFinPeriode) throws IOException {


        FileInputStream inputStream = new FileInputStream(templatefile);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);

        Row row = firstSheet.getRow(9);
        row.createCell(1).setCellValue(codeIdBank);
        row.createCell(2).setCellValue(dateDebutPeriode);
        row.createCell(3).setCellValue(dateFinPeriode);
        Double mntAchatsBilletEtr = new Double(0);
        Double mntVentesBilletEtr = new Double(0);
        Double mntAchatsChqVoy = new Double(0);
        Double mntVentesChqVoy = new Double(0);
        
        for (Bp3Infos bp3 : bp3infos) {
            
                mntAchatsBilletEtr = bp3.getAchatsBilletETr();
                mntVentesBilletEtr = bp3.getVentesBilletEtr();
                mntAchatsChqVoy = bp3.getAchatsChqVoy();
                mntVentesChqVoy = bp3.getVentesChqVoy();

            if (("CLIENTELE").equals(bp3.getAcheteurVendeur())) {
                if (bp3.getCodeIsoDevise().equals("XOF")) {
                    Row row1 = firstSheet.getRow(14);
                    if (row1 == null) row1 = firstSheet.createRow(14);
                    row1.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row1.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row1.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row1.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("EUR")) {
                    Row row2 = firstSheet.getRow(15);
                    if (row2 == null) row2 = firstSheet.createRow(15);
                    row2.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row2.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row2.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row2.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("USD")) {
                    Row row3 = firstSheet.getRow(16);
                    if (row3 == null) row3 = firstSheet.createRow(16);
                    row3.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row3.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row3.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row3.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("CAD")) {
                    Row row4 = firstSheet.getRow(17);
                    if (row4 == null) row4 = firstSheet.createRow(17);
                    row4.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row4.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row4.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row4.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }

                if (bp3.getCodeIsoDevise().equals("GBP")) {
                    Row row5 = firstSheet.getRow(18);
                    if (row5 == null) row5 = firstSheet.createRow(18);
                    row5.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row5.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row5.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row5.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }

                if (bp3.getCodeIsoDevise().equals("CHF")) {
                    Row row6 = firstSheet.getRow(19);
                    if (row6 == null) row6 = firstSheet.createRow(19);
                    row6.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row6.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row6.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row6.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("JPY")) {
                    Row row7 = firstSheet.getRow(20);
                    if (row7 == null) row7 = firstSheet.createRow(20);
                    row7.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row7.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row7.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row7.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("CNY")) {
                    Row row8 = firstSheet.getRow(21);
                    if (row8 == null) row8 = firstSheet.createRow(21);
                    row8.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row8.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row8.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row8.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
            }
            if (("BCEAO").equals(bp3.getAcheteurVendeur())) {
                if (bp3.getCodeIsoDevise().equals("XOF")) {
                    Row row1 = firstSheet.getRow(22);
                    if (row1 == null) row1 = firstSheet.createRow(22);
                    row1.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row1.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row1.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row1.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("EUR")) {
                    Row row2 = firstSheet.getRow(23);
                    if (row2 == null) row2 = firstSheet.createRow(23);
                    row2.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row2.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row2.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row2.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("USD")) {
                    Row row3 = firstSheet.getRow(24);
                    if (row3 == null) row3 = firstSheet.createRow(24);
                    row3.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row3.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row3.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row3.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("CAD")) {
                    Row row4 = firstSheet.getRow(25);
                    if (row4 == null) row4 = firstSheet.createRow(25);
                    row4.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row4.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row4.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row4.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }

                if (bp3.getCodeIsoDevise().equals("GBP")) {
                    Row row5 = firstSheet.getRow(26);
                    if (row5 == null) row5 = firstSheet.createRow(26);
                    row5.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row5.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row5.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row5.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }

                if (bp3.getCodeIsoDevise().equals("CHF")) {
                    Row row6 = firstSheet.getRow(27);
                    if (row6 == null) row6 = firstSheet.createRow(27);
                    row6.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row6.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row6.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row6.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("JPY")) {
                    Row row7 = firstSheet.getRow(28);
                    if (row7 == null) row7 = firstSheet.createRow(28);
                    row7.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row7.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row7.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row7.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("CNY")) {
                    Row row8 = firstSheet.getRow(29);
                    if (row8 == null) row8 = firstSheet.createRow(29);
                    row8.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row8.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row8.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row8.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
            }
            if (("CORRESLOCAUX").equals(bp3.getAcheteurVendeur())) {
                if (bp3.getCodeIsoDevise().equals("XOF")) {
                    Row row1 = firstSheet.getRow(30);
                    if (row1 == null) row1 = firstSheet.createRow(30);
                    row1.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row1.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row1.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row1.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("EUR")) {
                    Row row2 = firstSheet.getRow(31);
                    if (row2 == null) row2 = firstSheet.createRow(31);
                    row2.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row2.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row2.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row2.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("USD")) {
                    Row row3 = firstSheet.getRow(32);
                    if (row3 == null) row3 = firstSheet.createRow(32);
                    row3.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row3.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row3.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row3.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("CAD")) {
                    Row row4 = firstSheet.getRow(33);
                    if (row4 == null) row4 = firstSheet.createRow(33);
                    row4.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row4.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row4.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row4.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }

                if (bp3.getCodeIsoDevise().equals("GBP")) {
                    Row row5 = firstSheet.getRow(34);
                    if (row5 == null) row5 = firstSheet.createRow(34);
                    row5.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row5.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row5.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row5.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }

                if (bp3.getCodeIsoDevise().equals("CHF")) {
                    Row row6 = firstSheet.getRow(35);
                    if (row6 == null) row6 = firstSheet.createRow(35);
                    row6.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row6.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row6.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row6.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("JPY")) {
                    Row row7 = firstSheet.getRow(36);
                    if (row7 == null) row7 = firstSheet.createRow(36);
                    row7.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row7.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row7.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row7.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("CNY")) {
                    Row row8 = firstSheet.getRow(37);
                    if (row8 == null) row8 = firstSheet.createRow(37);
                    row8.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row8.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row8.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row8.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
            }
            if (("CORRESETRANG").equals(bp3.getAcheteurVendeur())) {
                if (bp3.getCodeIsoDevise().equals("XOF")) {
                    Row row1 = firstSheet.getRow(38);
                    if (row1 == null) row1 = firstSheet.createRow(38);
                    row1.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row1.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row1.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row1.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("EUR")) {
                    Row row2 = firstSheet.getRow(39);
                    if (row2 == null) row2 = firstSheet.createRow(39);
                    row2.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row2.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row2.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row2.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("USD")) {
                    Row row3 = firstSheet.getRow(40);
                    if (row3 == null) row3 = firstSheet.createRow(40);
                    row3.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row3.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row3.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row3.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("CAD")) {
                    Row row4 = firstSheet.getRow(41);
                    if (row4 == null) row4 = firstSheet.createRow(41);
                    row4.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row4.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row4.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row4.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }

                if (bp3.getCodeIsoDevise().equals("GBP")) {
                    Row row5 = firstSheet.getRow(42);
                    if (row == null) row5 = firstSheet.createRow(42);
                    row5.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row5.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row5.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row5.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }

                if (bp3.getCodeIsoDevise().equals("CHF")) {
                    Row row6 = firstSheet.getRow(43);
                    if (row6 == null) row6 = firstSheet.createRow(43);
                    row6.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row6.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row6.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row6.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("JPY")) {
                    Row row7 = firstSheet.getRow(44);
                    if (row7 == null) row7 = firstSheet.createRow(44);
                    row7.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row7.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row7.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row7.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
                if (bp3.getCodeIsoDevise().equals("CNY")) {
                    Row row8 = firstSheet.getRow(45);
                    if (row8 == null) row8 = firstSheet.createRow(45);
                    row8.createCell(3).setCellValue(divideByMillion(mntAchatsBilletEtr));
                    row8.createCell(4).setCellValue(divideByMillion(mntVentesBilletEtr));
                    row8.createCell(5).setCellValue(divideByMillion(mntAchatsChqVoy));
                    row8.createCell(6).setCellValue(divideByMillion(mntVentesChqVoy));
                }
            }

        }

        FileOutputStream output = new FileOutputStream(templatefile);
        workbook.write(output);
        return new ByteArrayInputStream(out.toByteArray());
    }

    public static Double divideByMillion (Double val) {      
        Double value = val!=null && val!=0 ? Double.valueOf(Math.round(val/1000000)) : 0;
        value = value<0 ? value*(-1) : value; 
        return value;
    }
}
