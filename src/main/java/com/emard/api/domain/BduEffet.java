package com.emard.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A BduEffet.
 */
@Entity
@Table(name = "bdu_effet")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BduEffet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "num_enreg")
    private String numEnreg;

    @Column(name = "nature_deposant")
    private Integer natureDeposant;

    @Column(name = "pays_residence")
    private Integer paysResidence;

    @Column(name = "ville")
    private Integer ville;

    @Column(name = "montant_effet")
    private Double montantEffet;

    @Column(name = "date_escompte")
    private LocalDate dateEscompte;

    @Column(name = "date_echeance")
    private LocalDate dateEcheance;

    @Column(name = "nbre_jours")
    private Integer nbreJours;

    @Column(name = "taux_interet")
    private Double tauxInteret;

    @Column(name = "montant_charges")
    private Double montantCharges;

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

    public BduEffet code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumEnreg() {
        return numEnreg;
    }

    public BduEffet numEnreg(String numEnreg) {
        this.numEnreg = numEnreg;
        return this;
    }

    public void setNumEnreg(String numEnreg) {
        this.numEnreg = numEnreg;
    }

    public Integer getNatureDeposant() {
        return natureDeposant;
    }

    public BduEffet natureDeposant(Integer natureDeposant) {
        this.natureDeposant = natureDeposant;
        return this;
    }

    public void setNatureDeposant(Integer natureDeposant) {
        this.natureDeposant = natureDeposant;
    }

    public Integer getPaysResidence() {
        return paysResidence;
    }

    public BduEffet paysResidence(Integer paysResidence) {
        this.paysResidence = paysResidence;
        return this;
    }

    public void setPaysResidence(Integer paysResidence) {
        this.paysResidence = paysResidence;
    }

    public Integer getVille() {
        return ville;
    }

    public BduEffet ville(Integer ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(Integer ville) {
        this.ville = ville;
    }

    public Double getMontantEffet() {
        return montantEffet;
    }

    public BduEffet montantEffet(Double montantEffet) {
        this.montantEffet = montantEffet;
        return this;
    }

    public void setMontantEffet(Double montantEffet) {
        this.montantEffet = montantEffet;
    }

    public LocalDate getDateEscompte() {
        return dateEscompte;
    }

    public BduEffet dateEscompte(LocalDate dateEscompte) {
        this.dateEscompte = dateEscompte;
        return this;
    }

    public void setDateEscompte(LocalDate dateEscompte) {
        this.dateEscompte = dateEscompte;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public BduEffet dateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
        return this;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public Integer getNbreJours() {
        return nbreJours;
    }

    public BduEffet nbreJours(Integer nbreJours) {
        this.nbreJours = nbreJours;
        return this;
    }

    public void setNbreJours(Integer nbreJours) {
        this.nbreJours = nbreJours;
    }

    public Double getTauxInteret() {
        return tauxInteret;
    }

    public BduEffet tauxInteret(Double tauxInteret) {
        this.tauxInteret = tauxInteret;
        return this;
    }

    public void setTauxInteret(Double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public Double getMontantCharges() {
        return montantCharges;
    }

    public BduEffet montantCharges(Double montantCharges) {
        this.montantCharges = montantCharges;
        return this;
    }

    public void setMontantCharges(Double montantCharges) {
        this.montantCharges = montantCharges;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BduEffet)) {
            return false;
        }
        return id != null && id.equals(((BduEffet) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BduEffet{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", numEnreg='" + getNumEnreg() + "'" +
            ", natureDeposant=" + getNatureDeposant() +
            ", paysResidence=" + getPaysResidence() +
            ", ville=" + getVille() +
            ", montantEffet=" + getMontantEffet() +
            ", dateEscompte='" + getDateEscompte() + "'" +
            ", dateEcheance='" + getDateEcheance() + "'" +
            ", nbreJours=" + getNbreJours() +
            ", tauxInteret=" + getTauxInteret() +
            ", montantCharges=" + getMontantCharges() +
            "}";
    }
}
