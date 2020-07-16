package com.emard.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A BduCdb.
 */
@Entity
@Table(name = "bdu_cdb")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BduCdb implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "num_enreg")
    private String numEnreg;

    @Column(name = "date_traitement")
    private LocalDate dateTraitement;

    @Column(name = "type_credit")
    private Integer typeCredit;

    @Column(name = "objet_credit")
    private Integer objetCredit;

    @Column(name = "montant_credit_demande")
    private Double montantCreditDemande;

    @Column(name = "duree_credit_demande")
    private Integer dureeCreditDemande;

    @Column(name = "taux_interet_souhaite")
    private String tauxInteretSouhaite;

    @Column(name = "nature_debiteur")
    private String natureDebiteur;

    @Column(name = "pays_residence")
    private Integer paysResidence;

    @Column(name = "ville")
    private Integer ville;

    @Column(name = "statut_juridique")
    private Integer statutJuridique;

    @Column(name = "sexe_debiteur")
    private String sexeDebiteur;

    @Column(name = "secteur_activite")
    private String secteurActivite;

    @Column(name = "taille_entreprise")
    private String tailleEntreprise;

    @Column(name = "decision")
    private Integer decision;

    @Column(name = "motif_rejet")
    private String motifRejet;

    @Column(name = "date_credit")
    private LocalDate dateCredit;

    @Column(name = "montant_credit_accorde")
    private Double montantCreditAccorde;

    @Column(name = "duree_credit_accorde")
    private Double dureeCreditAccorde;

    @Column(name = "periodicite_remboursement")
    private Double periodiciteRemboursement;

    @Column(name = "taux_interet_applique")
    private String tauxInteretApplique;

    @Column(name = "montant_interet")
    private Double montantInteret;

    @Column(name = "montant_charges")
    private Double montantCharges;

    @Column(name = "montant_commission")
    private Double montantCommission;

    @Column(name = "autres_commissions")
    private Double autresCommissions;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public BduCdb code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumEnreg() {
        return numEnreg;
    }

    public BduCdb numEnreg(String numEnreg) {
        this.numEnreg = numEnreg;
        return this;
    }

    public void setNumEnreg(String numEnreg) {
        this.numEnreg = numEnreg;
    }

    public LocalDate getDateTraitement() {
        return dateTraitement;
    }

    public BduCdb dateTraitement(LocalDate dateTraitement) {
        this.dateTraitement = dateTraitement;
        return this;
    }

    public void setDateTraitement(LocalDate dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public Integer getTypeCredit() {
        return typeCredit;
    }

    public BduCdb typeCredit(Integer typeCredit) {
        this.typeCredit = typeCredit;
        return this;
    }

    public void setTypeCredit(Integer typeCredit) {
        this.typeCredit = typeCredit;
    }

    public Integer getObjetCredit() {
        return objetCredit;
    }

    public BduCdb objetCredit(Integer objetCredit) {
        this.objetCredit = objetCredit;
        return this;
    }

    public void setObjetCredit(Integer objetCredit) {
        this.objetCredit = objetCredit;
    }

    public Double getMontantCreditDemande() {
        return montantCreditDemande;
    }

    public BduCdb montantCreditDemande(Double montantCreditDemande) {
        this.montantCreditDemande = montantCreditDemande;
        return this;
    }

    public void setMontantCreditDemande(Double montantCreditDemande) {
        this.montantCreditDemande = montantCreditDemande;
    }

    public Integer getDureeCreditDemande() {
        return dureeCreditDemande;
    }

    public BduCdb dureeCreditDemande(Integer dureeCreditDemande) {
        this.dureeCreditDemande = dureeCreditDemande;
        return this;
    }

    public void setDureeCreditDemande(Integer dureeCreditDemande) {
        this.dureeCreditDemande = dureeCreditDemande;
    }

    public String getTauxInteretSouhaite() {
        return tauxInteretSouhaite;
    }

    public BduCdb tauxInteretSouhaite(String tauxInteretSouhaite) {
        this.tauxInteretSouhaite = tauxInteretSouhaite;
        return this;
    }

    public void setTauxInteretSouhaite(String tauxInteretSouhaite) {
        this.tauxInteretSouhaite = tauxInteretSouhaite;
    }

    public String getNatureDebiteur() {
        return natureDebiteur;
    }

    public BduCdb natureDebiteur(String natureDebiteur) {
        this.natureDebiteur = natureDebiteur;
        return this;
    }

    public void setNatureDebiteur(String natureDebiteur) {
        this.natureDebiteur = natureDebiteur;
    }

    public Integer getPaysResidence() {
        return paysResidence;
    }

    public BduCdb paysResidence(Integer paysResidence) {
        this.paysResidence = paysResidence;
        return this;
    }

    public void setPaysResidence(Integer paysResidence) {
        this.paysResidence = paysResidence;
    }

    public Integer getVille() {
        return ville;
    }

    public BduCdb ville(Integer ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(Integer ville) {
        this.ville = ville;
    }

    public Integer getStatutJuridique() {
        return statutJuridique;
    }

    public BduCdb statutJuridique(Integer statutJuridique) {
        this.statutJuridique = statutJuridique;
        return this;
    }

    public void setStatutJuridique(Integer statutJuridique) {
        this.statutJuridique = statutJuridique;
    }

    public String getSexeDebiteur() {
        return sexeDebiteur;
    }

    public BduCdb sexeDebiteur(String sexeDebiteur) {
        this.sexeDebiteur = sexeDebiteur;
        return this;
    }

    public void setSexeDebiteur(String sexeDebiteur) {
        this.sexeDebiteur = sexeDebiteur;
    }

    public String getSecteurActivite() {
        return secteurActivite;
    }

    public BduCdb secteurActivite(String secteurActivite) {
        this.secteurActivite = secteurActivite;
        return this;
    }

    public void setSecteurActivite(String secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    public String getTailleEntreprise() {
        return tailleEntreprise;
    }

    public BduCdb tailleEntreprise(String tailleEntreprise) {
        this.tailleEntreprise = tailleEntreprise;
        return this;
    }

    public void setTailleEntreprise(String tailleEntreprise) {
        this.tailleEntreprise = tailleEntreprise;
    }

    public Integer getDecision() {
        return decision;
    }

    public BduCdb decision(Integer decision) {
        this.decision = decision;
        return this;
    }

    public void setDecision(Integer decision) {
        this.decision = decision;
    }

    public String getMotifRejet() {
        return motifRejet;
    }

    public BduCdb motifRejet(String motifRejet) {
        this.motifRejet = motifRejet;
        return this;
    }

    public void setMotifRejet(String motifRejet) {
        this.motifRejet = motifRejet;
    }

    public LocalDate getDateCredit() {
        return dateCredit;
    }

    public BduCdb dateCredit(LocalDate dateCredit) {
        this.dateCredit = dateCredit;
        return this;
    }

    public void setDateCredit(LocalDate dateCredit) {
        this.dateCredit = dateCredit;
    }

    public Double getMontantCreditAccorde() {
        return montantCreditAccorde;
    }

    public BduCdb montantCreditAccorde(Double montantCreditAccorde) {
        this.montantCreditAccorde = montantCreditAccorde;
        return this;
    }

    public void setMontantCreditAccorde(Double montantCreditAccorde) {
        this.montantCreditAccorde = montantCreditAccorde;
    }

    public Double getDureeCreditAccorde() {
        return dureeCreditAccorde;
    }

    public BduCdb dureeCreditAccorde(Double dureeCreditAccorde) {
        this.dureeCreditAccorde = dureeCreditAccorde;
        return this;
    }

    public void setDureeCreditAccorde(Double dureeCreditAccorde) {
        this.dureeCreditAccorde = dureeCreditAccorde;
    }

    public Double getPeriodiciteRemboursement() {
        return periodiciteRemboursement;
    }

    public BduCdb periodiciteRemboursement(Double periodiciteRemboursement) {
        this.periodiciteRemboursement = periodiciteRemboursement;
        return this;
    }

    public void setPeriodiciteRemboursement(Double periodiciteRemboursement) {
        this.periodiciteRemboursement = periodiciteRemboursement;
    }

    public String getTauxInteretApplique() {
        return tauxInteretApplique;
    }

    public BduCdb tauxInteretApplique(String tauxInteretApplique) {
        this.tauxInteretApplique = tauxInteretApplique;
        return this;
    }

    public void setTauxInteretApplique(String tauxInteretApplique) {
        this.tauxInteretApplique = tauxInteretApplique;
    }

    public Double getMontantInteret() {
        return montantInteret;
    }

    public BduCdb montantInteret(Double montantInteret) {
        this.montantInteret = montantInteret;
        return this;
    }

    public void setMontantInteret(Double montantInteret) {
        this.montantInteret = montantInteret;
    }

    public Double getMontantCharges() {
        return montantCharges;
    }

    public BduCdb montantCharges(Double montantCharges) {
        this.montantCharges = montantCharges;
        return this;
    }

    public void setMontantCharges(Double montantCharges) {
        this.montantCharges = montantCharges;
    }

    public Double getMontantCommission() {
        return montantCommission;
    }

    public BduCdb montantCommission(Double montantCommission) {
        this.montantCommission = montantCommission;
        return this;
    }

    public void setMontantCommission(Double montantCommission) {
        this.montantCommission = montantCommission;
    }

    public Double getAutresCommissions() {
        return autresCommissions;
    }

    public BduCdb autresCommissions(Double autresCommissions) {
        this.autresCommissions = autresCommissions;
        return this;
    }

    public void setAutresCommissions(Double autresCommissions) {
        this.autresCommissions = autresCommissions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BduCdb)) {
            return false;
        }
        return id != null && id.equals(((BduCdb) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BduCdb{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", numEnreg='" + getNumEnreg() + "'" +
            ", dateTraitement='" + getDateTraitement() + "'" +
            ", typeCredit=" + getTypeCredit() +
            ", objetCredit=" + getObjetCredit() +
            ", montantCreditDemande=" + getMontantCreditDemande() +
            ", dureeCreditDemande=" + getDureeCreditDemande() +
            ", tauxInteretSouhaite='" + getTauxInteretSouhaite() + "'" +
            ", natureDebiteur='" + getNatureDebiteur() + "'" +
            ", paysResidence=" + getPaysResidence() +
            ", ville=" + getVille() +
            ", statutJuridique=" + getStatutJuridique() +
            ", sexeDebiteur='" + getSexeDebiteur() + "'" +
            ", secteurActivite='" + getSecteurActivite() + "'" +
            ", tailleEntreprise='" + getTailleEntreprise() + "'" +
            ", decision=" + getDecision() +
            ", motifRejet='" + getMotifRejet() + "'" +
            ", dateCredit='" + getDateCredit() + "'" +
            ", montantCreditAccorde=" + getMontantCreditAccorde() +
            ", dureeCreditAccorde=" + getDureeCreditAccorde() +
            ", periodiciteRemboursement=" + getPeriodiciteRemboursement() +
            ", tauxInteretApplique='" + getTauxInteretApplique() + "'" +
            ", montantInteret=" + getMontantInteret() +
            ", montantCharges=" + getMontantCharges() +
            ", montantCommission=" + getMontantCommission() +
            ", autresCommissions=" + getAutresCommissions() +
            "}";
    }
}
