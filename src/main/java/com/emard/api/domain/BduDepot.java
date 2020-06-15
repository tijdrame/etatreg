package com.emard.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A BduDepot.
 */
@Entity
@Table(name = "bdu_depot")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BduDepot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "num_enreg")
    private String numEnreg;

    @Column(name = "nature_depot")
    private Integer natureDepot;

    @Column(name = "pays_residence")
    private Integer paysResidence;

    @Column(name = "ville")
    private Integer ville;

    @Column(name = "nature_deposant")
    private Integer natureDeposant;

    @Column(name = "statut_juridique")
    private Integer statutJuridique;

    @Column(name = "sexe_deposant")
    private Integer sexeDeposant;

    @Column(name = "secteur_activite")
    private Integer secteurActivite;

    @Column(name = "taille_entreprise")
    private Integer tailleEntreprise;

    @Column(name = "date_depot")
    private LocalDate dateDepot;

    @Column(name = "terme_depot")
    private Integer termeDepot;

    @Column(name = "montant_depot")
    private Double montantDepot;

    @Column(name = "taux_renumeration")
    private Double tauxRenumeration;

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

    public BduDepot code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumEnreg() {
        return numEnreg;
    }

    public BduDepot numEnreg(String numEnreg) {
        this.numEnreg = numEnreg;
        return this;
    }

    public void setNumEnreg(String numEnreg) {
        this.numEnreg = numEnreg;
    }

    public Integer getNatureDepot() {
        return natureDepot;
    }

    public BduDepot natureDepot(Integer natureDepot) {
        this.natureDepot = natureDepot;
        return this;
    }

    public void setNatureDepot(Integer natureDepot) {
        this.natureDepot = natureDepot;
    }

    public Integer getPaysResidence() {
        return paysResidence;
    }

    public BduDepot paysResidence(Integer paysResidence) {
        this.paysResidence = paysResidence;
        return this;
    }

    public void setPaysResidence(Integer paysResidence) {
        this.paysResidence = paysResidence;
    }

    public Integer getVille() {
        return ville;
    }

    public BduDepot ville(Integer ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(Integer ville) {
        this.ville = ville;
    }

    public Integer getNatureDeposant() {
        return natureDeposant;
    }

    public BduDepot natureDeposant(Integer natureDeposant) {
        this.natureDeposant = natureDeposant;
        return this;
    }

    public void setNatureDeposant(Integer natureDeposant) {
        this.natureDeposant = natureDeposant;
    }

    public Integer getStatutJuridique() {
        return statutJuridique;
    }

    public BduDepot statutJuridique(Integer statutJuridique) {
        this.statutJuridique = statutJuridique;
        return this;
    }

    public void setStatutJuridique(Integer statutJuridique) {
        this.statutJuridique = statutJuridique;
    }

    public Integer getSexeDeposant() {
        return sexeDeposant;
    }

    public BduDepot sexeDeposant(Integer sexeDeposant) {
        this.sexeDeposant = sexeDeposant;
        return this;
    }

    public void setSexeDeposant(Integer sexeDeposant) {
        this.sexeDeposant = sexeDeposant;
    }

    public Integer getSecteurActivite() {
        return secteurActivite;
    }

    public BduDepot secteurActivite(Integer secteurActivite) {
        this.secteurActivite = secteurActivite;
        return this;
    }

    public void setSecteurActivite(Integer secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    public Integer getTailleEntreprise() {
        return tailleEntreprise;
    }

    public BduDepot tailleEntreprise(Integer tailleEntreprise) {
        this.tailleEntreprise = tailleEntreprise;
        return this;
    }

    public void setTailleEntreprise(Integer tailleEntreprise) {
        this.tailleEntreprise = tailleEntreprise;
    }

    public LocalDate getDateDepot() {
        return dateDepot;
    }

    public BduDepot dateDepot(LocalDate dateDepot) {
        this.dateDepot = dateDepot;
        return this;
    }

    public void setDateDepot(LocalDate dateDepot) {
        this.dateDepot = dateDepot;
    }

    public Integer getTermeDepot() {
        return termeDepot;
    }

    public BduDepot termeDepot(Integer termeDepot) {
        this.termeDepot = termeDepot;
        return this;
    }

    public void setTermeDepot(Integer termeDepot) {
        this.termeDepot = termeDepot;
    }

    public Double getMontantDepot() {
        return montantDepot;
    }

    public BduDepot montantDepot(Double montantDepot) {
        this.montantDepot = montantDepot;
        return this;
    }

    public void setMontantDepot(Double montantDepot) {
        this.montantDepot = montantDepot;
    }

    public Double getTauxRenumeration() {
        return tauxRenumeration;
    }

    public BduDepot tauxRenumeration(Double tauxRenumeration) {
        this.tauxRenumeration = tauxRenumeration;
        return this;
    }

    public void setTauxRenumeration(Double tauxRenumeration) {
        this.tauxRenumeration = tauxRenumeration;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BduDepot)) {
            return false;
        }
        return id != null && id.equals(((BduDepot) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BduDepot{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", numEnreg='" + getNumEnreg() + "'" +
            ", natureDepot=" + getNatureDepot() +
            ", paysResidence=" + getPaysResidence() +
            ", ville=" + getVille() +
            ", natureDeposant=" + getNatureDeposant() +
            ", statutJuridique=" + getStatutJuridique() +
            ", sexeDeposant=" + getSexeDeposant() +
            ", secteurActivite=" + getSecteurActivite() +
            ", tailleEntreprise=" + getTailleEntreprise() +
            ", dateDepot='" + getDateDepot() + "'" +
            ", termeDepot=" + getTermeDepot() +
            ", montantDepot=" + getMontantDepot() +
            ", tauxRenumeration=" + getTauxRenumeration() +
            "}";
    }
}
