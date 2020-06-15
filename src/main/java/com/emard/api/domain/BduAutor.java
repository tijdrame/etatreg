package com.emard.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A BduAutor.
 */
@Entity
@Table(name = "bdu_autor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BduAutor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "num_enreg")
    private String numEnreg;

    @Column(name = "nature_beneficiaire")
    private String natureBeneficiaire;

    @Column(name = "pays_residence")
    private Integer paysResidence;

    @Column(name = "ville")
    private Integer ville;

    @Column(name = "statut_juridique")
    private Integer statutJuridique;

    @Column(name = "sexe_beneficiaire")
    private String sexeBeneficiaire;

    @Column(name = "secteur_activite")
    private String secteurActivite;

    @Column(name = "taille_entreprise")
    private String tailleEntreprise;

    @Column(name = "montant_max_autorise")
    private Double montantMaxAutorise;

    @Column(name = "montant_max_utilise")
    private Double montantMaxUtilise;

    @Column(name = "solde_compte")
    private Double soldeCompte;

    @Column(name = "taux_interet")
    private Double tauxInteret;

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

    public BduAutor code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumEnreg() {
        return numEnreg;
    }

    public BduAutor numEnreg(String numEnreg) {
        this.numEnreg = numEnreg;
        return this;
    }

    public void setNumEnreg(String numEnreg) {
        this.numEnreg = numEnreg;
    }

    public String getNatureBeneficiaire() {
        return natureBeneficiaire;
    }

    public BduAutor natureBeneficiaire(String natureBeneficiaire) {
        this.natureBeneficiaire = natureBeneficiaire;
        return this;
    }

    public void setNatureBeneficiaire(String natureBeneficiaire) {
        this.natureBeneficiaire = natureBeneficiaire;
    }

    public Integer getPaysResidence() {
        return paysResidence;
    }

    public BduAutor paysResidence(Integer paysResidence) {
        this.paysResidence = paysResidence;
        return this;
    }

    public void setPaysResidence(Integer paysResidence) {
        this.paysResidence = paysResidence;
    }

    public Integer getVille() {
        return ville;
    }

    public BduAutor ville(Integer ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(Integer ville) {
        this.ville = ville;
    }

    public Integer getStatutJuridique() {
        return statutJuridique;
    }

    public BduAutor statutJuridique(Integer statutJuridique) {
        this.statutJuridique = statutJuridique;
        return this;
    }

    public void setStatutJuridique(Integer statutJuridique) {
        this.statutJuridique = statutJuridique;
    }

    public String getSexeBeneficiaire() {
        return sexeBeneficiaire;
    }

    public BduAutor sexeBeneficiaire(String sexeBeneficiaire) {
        this.sexeBeneficiaire = sexeBeneficiaire;
        return this;
    }

    public void setSexeBeneficiaire(String sexeBeneficiaire) {
        this.sexeBeneficiaire = sexeBeneficiaire;
    }

    public String getSecteurActivite() {
        return secteurActivite;
    }

    public BduAutor secteurActivite(String secteurActivite) {
        this.secteurActivite = secteurActivite;
        return this;
    }

    public void setSecteurActivite(String secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    public String getTailleEntreprise() {
        return tailleEntreprise;
    }

    public BduAutor tailleEntreprise(String tailleEntreprise) {
        this.tailleEntreprise = tailleEntreprise;
        return this;
    }

    public void setTailleEntreprise(String tailleEntreprise) {
        this.tailleEntreprise = tailleEntreprise;
    }

    public Double getMontantMaxAutorise() {
        return montantMaxAutorise;
    }

    public BduAutor montantMaxAutorise(Double montantMaxAutorise) {
        this.montantMaxAutorise = montantMaxAutorise;
        return this;
    }

    public void setMontantMaxAutorise(Double montantMaxAutorise) {
        this.montantMaxAutorise = montantMaxAutorise;
    }

    public Double getMontantMaxUtilise() {
        return montantMaxUtilise;
    }

    public BduAutor montantMaxUtilise(Double montantMaxUtilise) {
        this.montantMaxUtilise = montantMaxUtilise;
        return this;
    }

    public void setMontantMaxUtilise(Double montantMaxUtilise) {
        this.montantMaxUtilise = montantMaxUtilise;
    }

    public Double getSoldeCompte() {
        return soldeCompte;
    }

    public BduAutor soldeCompte(Double soldeCompte) {
        this.soldeCompte = soldeCompte;
        return this;
    }

    public void setSoldeCompte(Double soldeCompte) {
        this.soldeCompte = soldeCompte;
    }

    public Double getTauxInteret() {
        return tauxInteret;
    }

    public BduAutor tauxInteret(Double tauxInteret) {
        this.tauxInteret = tauxInteret;
        return this;
    }

    public void setTauxInteret(Double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BduAutor)) {
            return false;
        }
        return id != null && id.equals(((BduAutor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BduAutor{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", numEnreg='" + getNumEnreg() + "'" +
            ", natureBeneficiaire='" + getNatureBeneficiaire() + "'" +
            ", paysResidence=" + getPaysResidence() +
            ", ville=" + getVille() +
            ", statutJuridique=" + getStatutJuridique() +
            ", sexeBeneficiaire='" + getSexeBeneficiaire() + "'" +
            ", secteurActivite='" + getSecteurActivite() + "'" +
            ", tailleEntreprise='" + getTailleEntreprise() + "'" +
            ", montantMaxAutorise=" + getMontantMaxAutorise() +
            ", montantMaxUtilise=" + getMontantMaxUtilise() +
            ", soldeCompte=" + getSoldeCompte() +
            ", tauxInteret=" + getTauxInteret() +
            "}";
    }
}
