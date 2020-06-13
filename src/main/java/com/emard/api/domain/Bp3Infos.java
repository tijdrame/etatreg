package com.emard.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Bp3Infos.
 */
@Entity
@Table(name = "bp3infos")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Bp3Infos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_iso_devise")
    private String codeIsoDevise;

    @Column(name = "libelle_devise")
    private String libelleDevise;

    @Column(name = "acheteur_vendeur")
    private String acheteurVendeur;

    @Column(name = "achats_billet_e_tr")
    private Double achatsBilletETr;

    @Column(name = "ventes_billet_etr")
    private Double ventesBilletEtr;

    @Column(name = "achats_chq_voy")
    private Double achatsChqVoy;

    @Column(name = "ventes_chq_voy")
    private Double ventesChqVoy;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeIsoDevise() {
        return codeIsoDevise;
    }

    public Bp3Infos codeIsoDevise(String codeIsoDevise) {
        this.codeIsoDevise = codeIsoDevise;
        return this;
    }

    public void setCodeIsoDevise(String codeIsoDevise) {
        this.codeIsoDevise = codeIsoDevise;
    }

    public String getLibelleDevise() {
        return libelleDevise;
    }

    public Bp3Infos libelleDevise(String libelleDevise) {
        this.libelleDevise = libelleDevise;
        return this;
    }

    public void setLibelleDevise(String libelleDevise) {
        this.libelleDevise = libelleDevise;
    }

    public String getAcheteurVendeur() {
        return acheteurVendeur;
    }

    public Bp3Infos acheteurVendeur(String acheteurVendeur) {
        this.acheteurVendeur = acheteurVendeur;
        return this;
    }

    public void setAcheteurVendeur(String acheteurVendeur) {
        this.acheteurVendeur = acheteurVendeur;
    }

    public Double getAchatsBilletETr() {
        return achatsBilletETr;
    }

    public Bp3Infos achatsBilletETr(Double achatsBilletETr) {
        this.achatsBilletETr = achatsBilletETr;
        return this;
    }

    public void setAchatsBilletETr(Double achatsBilletETr) {
        this.achatsBilletETr = achatsBilletETr;
    }

    public Double getVentesBilletEtr() {
        return ventesBilletEtr;
    }

    public Bp3Infos ventesBilletEtr(Double ventesBilletEtr) {
        this.ventesBilletEtr = ventesBilletEtr;
        return this;
    }

    public void setVentesBilletEtr(Double ventesBilletEtr) {
        this.ventesBilletEtr = ventesBilletEtr;
    }

    public Double getAchatsChqVoy() {
        return achatsChqVoy;
    }

    public Bp3Infos achatsChqVoy(Double achatsChqVoy) {
        this.achatsChqVoy = achatsChqVoy;
        return this;
    }

    public void setAchatsChqVoy(Double achatsChqVoy) {
        this.achatsChqVoy = achatsChqVoy;
    }

    public Double getVentesChqVoy() {
        return ventesChqVoy;
    }

    public Bp3Infos ventesChqVoy(Double ventesChqVoy) {
        this.ventesChqVoy = ventesChqVoy;
        return this;
    }

    public void setVentesChqVoy(Double ventesChqVoy) {
        this.ventesChqVoy = ventesChqVoy;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bp3Infos)) {
            return false;
        }
        return id != null && id.equals(((Bp3Infos) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Bp3Infos{" +
            "id=" + getId() +
            ", codeIsoDevise='" + getCodeIsoDevise() + "'" +
            ", libelleDevise='" + getLibelleDevise() + "'" +
            ", acheteurVendeur='" + getAcheteurVendeur() + "'" +
            ", achatsBilletETr=" + getAchatsBilletETr() +
            ", ventesBilletEtr=" + getVentesBilletEtr() +
            ", achatsChqVoy=" + getAchatsChqVoy() +
            ", ventesChqVoy=" + getVentesChqVoy() +
            "}";
    }
}
