/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emard.api.generator;

import com.emard.api.domain.BankInfos;
import com.emard.api.domain.Bp4Infos;
import com.emard.api.domain.CrpAtr;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
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

    public static ByteArrayInputStream crpInfosToCsv(Iterable<CrpAtr> crpAtr, File templatefile,String codeIdBank, String dateDebutPeriode, String dateFinPeriode) throws IOException {

        /* String excelFilePath = "C:\\Users\\Bouna\\Documents\\PERSO\\DOC BPR\\ORAGW\\Envoie\\BPR_BP4_INFOS_XLS.XLS";
         File file = new File(excelFilePath);*/
     // FileInputStream inputStream = new FileInputStream(templatefile);
      
        //Workbook workbook = new HSSFWorkbook(inputStream);
        
      String lineHeader = "Prefixe; Numero Dossier; Numero Sequence; Numero Enregistrement; Type CRP; Code Interm Declarant Emetteur ATR; Date Emission ATR; Num ATR; Num CRP; Nom Interm Declarant; Code Interm Declarant; Pays de residence du donneur d'ordre; Date Operation; Reglement Compte Propre; Nom Donneur Ordre ou Beneficiaire; Code Donneur Ordre ou Beneficiaire; Secteur Institutionnel Donneur Ordre ou Beneficiaire; Code NAEMA Donneur Ordre ou Beneficiaire; Pays de residence beneficiaire; Nature Compte Mouvemente; Sens Operation; Devise Reglement; Montant Operation; Taux Change; Montant FCFA; Code ITRS; Domiciliation; Modalite Transfert";

     
	//FileOutputStream fos = new FileOutputStream(fout);
      FileOutputStream output = new FileOutputStream(templatefile);
      ByteArrayOutputStream out = new ByteArrayOutputStream();
        //workbook.write(output);
        //FileOutputStream fos = new FileOutputStream(fout);
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output));
      
      bw.write(lineHeader);
      bw.newLine();
      int i = 0;
      for(CrpAtr crp : crpAtr) {
            i=i+1;
            if(crp!=null && crp.getCenr().equals("CRP")) {
            String prefixe = crp.getCenr();
            String numDos  = crp.getRefint();
            String numSeq  = ""+i;//++
            String numEnr  = ""+i;
            String typCrp  = "1";
            String codIntE = "";
            String datEmi  = String.valueOf(crp.getDatope());
            String numAtr  = "";
            String numCrp  = "";
            String nomInt  = crp.getNomRes();
            String codIntD = codeIdBank;
            String paysD   = "";
            String datOpe  = String.valueOf(crp.getDatope());
            String regCpt  = "";
            String nomDon  = "";
            String codDon  = "";
            String sectIns = "";
            String codenam = "X";
            String payResB = "";
            String natCpt  = "";
            String sensOpe = "";
            String devReg  = crp.getDevn();
            String mntOpe  = String.valueOf(crp.getMnat());
            String txChang = String.valueOf(crp.getTaux());
            String mntCfa  = String.valueOf(crp.getMnat() * crp.getTaux());
            String codItrs = crp.getCeco();
            String dom     = "1";
            String modTrf  = "1";
            String line = String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",prefixe,numDos ,numSeq ,numEnr ,typCrp ,codIntE,datEmi ,numAtr ,numCrp ,nomInt ,codIntD,paysD  ,datOpe ,regCpt ,nomDon ,codDon ,sectIns,codenam,payResB,natCpt ,sensOpe,devReg ,mntOpe ,txChang,mntCfa ,codItrs,dom,modTrf);
            //String line = String.format(prefixe,numDos ,numSeq ,numEnr ,typCrp ,codIntE,datEmi ,numAtr ,numCrp ,nomInt ,codIntD,paysD  ,datOpe ,regCpt ,nomDon ,codDon ,sectIns,codenam,payResB,natCpt ,sensOpe,devReg ,mntOpe ,txChang,mntCfa ,codItrs,dom,modTrf);
            System.out.println("line "+line);
            bw.write(line);
            bw.newLine();
        }
            
      }    
      bw.close();
      out.close();
      output.close();
      //inputStream.close();
      return new ByteArrayInputStream(out.toByteArray());
    }
    
    public static ByteArrayInputStream atrInfosToCsv(Iterable<CrpAtr> crpAtr, File templatefile,BankInfos bankInfos, String dateDebutPeriode, String dateFinPeriode) throws IOException {

    /*  FileInputStream inputStream = new FileInputStream(templatefile);
      ByteArrayOutputStream out = new ByteArrayOutputStream();*/
        //Workbook workbook = new HSSFWorkbook(inputStream);
        
      String lineHeader = "Prefixe;Code Enregistrement;Type Atr;Num Atr;Code ID Receveur;Reference interne;Date Operation;nom ID Teneur;Code ID Teneur;Nom Donneur Ordre;Pays Provenance;Nom Beneficiaire;Code Naema Beneficiaire;Devise Reglement;Montant Operation;Montant Fcfa";
      FileOutputStream output = new FileOutputStream(templatefile);
      ByteArrayOutputStream out = new ByteArrayOutputStream();
        //workbook.write(output);
        //FileOutputStream fos = new FileOutputStream(fout);
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output));
      
      bw.write(lineHeader);
      bw.newLine();

      String codIdRec= bankInfos.getSigle();
      String nomIdTe = bankInfos.getRaisonSociale();
      String codIdTe = bankInfos.getCodeId();
   
      int i = 0;
      for(CrpAtr crp : crpAtr) {
          if(crp!=null && crp.getCenr().equals("ATR")) {
          i=i+1;
            String prefixe = crp.getCenr();
            String numEnr  = ""+i;
            String typCrp  = "1";
            String numAtr  = "GW"+i;
            String numCrp  = ""+i+2;
            String refInt  = crp.getRefint();
            String datOpe  = String.valueOf(crp.getDatope());
            String nomDon  = crp.getNomEtr();
            String payProv = crp.getCpayIso();
            String nomBen  = crp.getNomRes();
            String codenam = "X";
            String devReg  = crp.getDevn();
            String mntOpe  = String.valueOf(crp.getMnat());
            String mntCfa  = String.valueOf(crp.getMnat() * crp.getTaux());
            
            String line = String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",prefixe ,numEnr  ,typCrp  ,numAtr  ,numCrp  ,codIdRec,refInt  ,datOpe  ,nomIdTe ,codIdTe ,nomDon  ,payProv ,nomBen  ,codenam ,devReg  ,mntOpe  ,mntCfa);
            bw.write(line);
            bw.newLine();
          }
      }
      bw.close();
      out.close();
      output.close();
      return new ByteArrayInputStream(out.toByteArray());
    }

}
