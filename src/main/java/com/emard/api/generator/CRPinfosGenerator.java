/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emard.api.generator;

import com.emard.api.domain.BankInfos;
import com.emard.api.domain.CrpAtr;
import com.emard.api.repository.SecteurActiviteRepository;
import com.emard.api.service.NatureDepotService;
import com.emard.api.service.ObjetCreditService;
import com.emard.api.service.SecteurActiviteService;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

/**
 *
 * @author Bouna
 */
public class CRPinfosGenerator {
    


    public ByteArrayInputStream crpInfosToCsv(Iterable<CrpAtr> crpAtr, File templatefile, BankInfos bankInfos, String codeIdBank, String dateDebutPeriode, String dateFinPeriode,SecteurActiviteService secteurActivite, NatureDepotService natureDepotService) throws IOException {

        String lineHeader = "Prefixe; Numero Dossier; Numero Sequence; Numero Enregistrement; Type CRP; Code Interm Declarant Emetteur ATR; Date Emission ATR; Num ATR; Num CRP; Nom Interm Declarant; Code Interm Declarant; Pays de residence du donneur d'ordre; Date Operation; Reglement Compte Propre; Nom Donneur Ordre ou Beneficiaire; Code Donneur Ordre ou Beneficiaire; Secteur Institutionnel Donneur Ordre ou Beneficiaire; Code NAEMA Donneur Ordre ou Beneficiaire; Pays de residence beneficiaire; Nature Compte Mouvemente; Sens Operation; Devise Reglement; Montant Operation; Taux Change; Montant FCFA; Code ITRS; Domiciliation; Modalite Transfert";

        String codIdRec = bankInfos.getSigle();
        String nomIdTe = bankInfos.getRaisonSociale();
        String codIdTe = bankInfos.getCodeId();
        
        FileOutputStream output = new FileOutputStream(templatefile);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output));

        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        bw.write(lineHeader);
        bw.newLine();
        int i = 0;
        for (CrpAtr crp : crpAtr) {
            i = i + 1;
            if (crp != null && crp.getCenr().equals("CRP")) {
                String prefixe = crp.getCenr();
                String numDos = crp.getRefint();
                String numSeq = "" + i;//++
                String numEnr = "" + i;
                String typCrp = "1";
                String codIntE = "";
                String datEmi = "";//String.valueOf(crp.getDatope());
                String numAtr = "";
                String numCrp = "2" + i;//numero chronologique base annuelle : creer une table de numerotation annuelle
                String nomInt = nomIdTe;//"ORABANK TOGO (Ex FINANCIAL BANK TOGO)";//nomIdTe;
                String codIntD = codeIdBank; //"TT0116K1";//codeIdBank;    
                //String paysD   = crp.getCpayIso();//!=null && !crp.getCpayIso().equals("") ? (CountryCode.getByCode(Integer.valueOf(crp.getCpayIso()))!=null ? CountryCode.getByCode(Integer.valueOf(crp.getCpayIso())).getAlpha2() : "" ) : "" ; //Emis ou Reçu
                String paysD = crp.getCpayIso();//!paysD.equals("") ? paysD : crp.getCpayIso();
                String datOpe = crp.getDatope()!=null && !crp.getDatope().equals("") ? String.valueOf(crp.getDatope().format(dateformat)) : "31/07/2019";
                String regCpt = crp.getRegt(); //1,2,3 //mettre 1
                String nomDon = crp.getNomRes(); //
                String codDon = crp.getNocli();
                String sectIns = crp.getSectInst(); //T042
                System.out.println("getCodenaema " + crp.getCodenaema());
                String codenam = crp.getCodenaema()!=null && !crp.getCodenaema().equals("") ? 
                        (secteurActivite.findCodeNaemaByCodeInterne(crp.getCodenaema())!=null && !secteurActivite.findCodeNaemaByCodeInterne(crp.getCodenaema()).equals("") ?
                        secteurActivite.findCodeNaemaByCodeInterne(crp.getCodenaema()) : "X") : "X"; //T071
                System.out.println("secteurActivite.findCodeNaemaByCodeInterne(crp.getCodenaema()) "+secteurActivite.findCodeNaemaByCodeInterne(crp.getCodenaema()));
                String payResB = crp.getCpayEtg();//crp.getCpayEtg()!=null && !crp.getCpayEtg().equals("") ? (CountryCode.getByCode(Integer.valueOf(crp.getCpayEtg()))!=null ? CountryCode.getByCode(Integer.valueOf(crp.getCpayEtg())).getAlpha2() : "" ) : "";
                //payResB = !payResB.equals("") ? payResB : crp.getCpayEtg();
                String natCpt = crp.getNatcpt();
                String sensOpe = crp.getSens();
                String devReg = (crp.getDevn() != null && !crp.getDevn().equals("")) ? (getCurrencyByCode(Integer.valueOf(crp.getDevn())).getCurrencyCode()) : "XOF";//correspondance alphnumerique
                String mntOpe = String.format("%s",BigDecimal.valueOf(crp.getMdev()).toPlainString());
                mntOpe = mntOpe.replace('.', ',');//mdev
                String txChang = (String.valueOf(crp.getTaux()).replace('.', ',')); // separateur decimal avec virgule (655,957)
                //String mntCfa = BigDecimal.valueOf(crp.getMnat()).toPlainString();
                String mntCfa = String.format("%s",BigDecimal.valueOf(crp.getMnat()).toPlainString());
                mntCfa = mntCfa.replace('.', ',');                
                System.out.println("mntCfa " + mntCfa);
                String codItrs = crp.getCeco()!=null && !crp.getCeco().equals("") ? 
                        (natureDepotService.findCodeItrsByCodeInterne(crp.getCeco())!=null && !natureDepotService.findCodeItrsByCodeInterne(crp.getCeco()).equals("") ?
                        natureDepotService.findCodeItrsByCodeInterne(crp.getCeco()) : "100") : "100"; 
                //crp.getCeco();//codeitrs mapping à faire T062
                String dom = crp.getCeco().equals("100") ? "1" : "2";//mapping à faire T062
                String modTrf = crp.getDevn().equals("952") ? "1" : "3";
                String line = String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s", prefixe, numDos, numSeq, numEnr, typCrp, codIntE, datEmi, numAtr, numCrp, nomInt, codIntD, paysD, datOpe, regCpt, nomDon, codDon, sectIns, codenam, payResB, natCpt, sensOpe, devReg, mntOpe, txChang, mntCfa, codItrs, dom, modTrf);
                System.out.println("line " + line);
                bw.write(line);
                bw.newLine();
            }

        }
        bw.close();
        out.close();
        output.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    public static ByteArrayInputStream atrInfosToCsv(Iterable<CrpAtr> crpAtr, File templatefile, BankInfos bankInfos, String dateDebutPeriode, String dateFinPeriode) throws IOException {

        String lineHeader = "Prefixe;Code Enregistrement;Type Atr;Num Atr;Code ID Receveur;Reference interne;Date Operation;nom ID Teneur;Code ID Teneur;Nom Donneur Ordre;Pays Provenance;Nom Beneficiaire;Code Naema Beneficiaire;Devise Reglement;Montant Operation;Montant Fcfa";
        FileOutputStream output = new FileOutputStream(templatefile);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output));

        bw.write(lineHeader);
        bw.newLine();

        String codIdRec = bankInfos.getCodeId();//correspondance bkbqe
        String nomIdTe = bankInfos.getRaisonSociale();//correspondance bkbqe
        String codIdTe = "";//mapping bankInfos.getCodeId();

        int i = 0;
        for (CrpAtr crp : crpAtr) {
            System.out.println("atr " + crp.getCenr());

            String line = "";
            i = i + 1;
            System.out.println("i = " + i);
            String prefixe = crp.getCenr();
            String numEnr = "" + i;
            String typCrp = "1";
            String numAtr = "GW" + i;
            String numCrp = "" + i + 2;
            String refInt = crp.getRefint();
            String datOpe = String.valueOf(crp.getDatope());
            String nomDon = crp.getNomEtr();
            System.out.println("crp.getCpayIso() " + Integer.valueOf(crp.getCpayIso()));
            System.out.println("crp.getCpayIso() L " + Long.valueOf(crp.getCpayIso()));
            System.out.println("crp.getCpayIso() i " + Integer.valueOf(crp.getCpayIso()));
            String payProv = crp.getCpayIso() != null && !crp.getCpayIso().equals("") ? (CountryCode.getByCode(Integer.valueOf(crp.getCpayIso())) != null ? CountryCode.getByCode(Integer.valueOf(crp.getCpayIso())).getAlpha2() : "") : "";
            System.out.println("com.emard.api.generator.CRPinfosGenerator.atrInfosToCsv() " + payProv);
            String nomBen = crp.getNomRes();
            String codenam = "X";//T071
            String devReg = (crp.getDevn() != null && !crp.getDevn().equals("")) ? (getCurrencyByCode(Integer.valueOf(crp.getDevn())).getCurrencyCode()) : "EUR";
            String mntOpe = BigDecimal.valueOf(crp.getMnat().longValue()).toPlainString();
            String mntCfa = BigDecimal.valueOf(Double.valueOf(mntOpe) * crp.getTaux().longValue()).toPlainString();
            line = String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s", prefixe, numEnr, typCrp, numAtr, codIdRec, refInt, datOpe, nomIdTe, codIdTe, nomDon, payProv, nomBen, codenam, devReg, mntOpe, mntCfa);
            bw.write(line);
            bw.newLine();
            System.out.println("ii = " + i);
        }
        bw.close();
        out.close();
        output.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    public static Currency getCurrencyByCode(int code) {
        System.out.println("com.emard.api.generator.CRPinfosGenerator.getCurrencyByCode() " + code);
        for (Currency currency : Currency.getAvailableCurrencies()) {
            if (currency.getNumericCode() == code) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Unkown currency code: " + code);
    }

    public static String formaterEnDecimal(String value) {
        String pattern = "#,###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        String format = decimalFormat.format(value);
        System.out.println(format);

        return format;
    }

    public static String convertFromScientificNotation(double number) {
        // Check if in scientific notation
        if (String.valueOf(number).toLowerCase().contains("e")) {
            System.out.println("The scientific notation number'"
                    + number
                    + "' detected, it will be converted to normal representation with 25 maximum fraction digits.");
            NumberFormat formatter = new DecimalFormat("0");
            formatter.setMaximumFractionDigits(25);
            return formatter.format(number);
        } else {
            return String.valueOf(number);
        }
    }

}
