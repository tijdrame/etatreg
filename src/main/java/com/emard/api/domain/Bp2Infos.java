package com.emard.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Bp2Infos.
 */
@Entity
@Table(name = "bp2infos")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Bp2Infos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_iso_devise")
    private String codeIsoDevise;

    @Column(name = "actif_billet_et_rcai")
    private Double actifBilletEtRcai;

    @Column(name = "actif_maison_mere")
    private Double actifMaisonMere;

    @Column(name = "actif_au_tresor")
    private Double actifAuTresor;

    @Column(name = "actif_client_deb")
    private Double actifClientDeb;

    @Column(name = "actif_effes_cpte")
    private Double actifEffesCpte;

    @Column(name = "actif_effet_enc")
    private Double actifEffetEnc;

    @Column(name = "passif_maison_mere")
    private Double passifMaisonMere;

    @Column(name = "passif_au_tresor")
    private Double passifAuTresor;

    @Column(name = "passif_cli_cpte_ch")
    private Double passifCliCpteCh;

    @Column(name = "passif_cpt_apres_enc")
    private Double passifCptApresEnc;

    @Column(name = "date_chargement")
    private LocalDate dateChargement;

    @Column(name = "date_dechargement")
    private LocalDate dateDechargement;

    @Column(name = "passif_cli_cpt_vue")
    private LocalDate passifCliCptVue;

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

    public Bp2Infos codeIsoDevise(String codeIsoDevise) {
        this.codeIsoDevise = codeIsoDevise;
        return this;
    }

    public void setCodeIsoDevise(String codeIsoDevise) {
        this.codeIsoDevise = codeIsoDevise;
    }

    public Double getActifBilletEtRcai() {
        return actifBilletEtRcai;
    }

    public Bp2Infos actifBilletEtRcai(Double actifBilletEtRcai) {
        this.actifBilletEtRcai = actifBilletEtRcai;
        return this;
    }

    public void setActifBilletEtRcai(Double actifBilletEtRcai) {
        this.actifBilletEtRcai = actifBilletEtRcai;
    }

    public Double getActifMaisonMere() {
        return actifMaisonMere;
    }

    public Bp2Infos actifMaisonMere(Double actifMaisonMere) {
        this.actifMaisonMere = actifMaisonMere;
        return this;
    }

    public void setActifMaisonMere(Double actifMaisonMere) {
        this.actifMaisonMere = actifMaisonMere;
    }

    public Double getActifAuTresor() {
        return actifAuTresor;
    }

    public Bp2Infos actifAuTresor(Double actifAuTresor) {
        this.actifAuTresor = actifAuTresor;
        return this;
    }

    public void setActifAuTresor(Double actifAuTresor) {
        this.actifAuTresor = actifAuTresor;
    }

    public Double getActifClientDeb() {
        return actifClientDeb;
    }

    public Bp2Infos actifClientDeb(Double actifClientDeb) {
        this.actifClientDeb = actifClientDeb;
        return this;
    }

    public void setActifClientDeb(Double actifClientDeb) {
        this.actifClientDeb = actifClientDeb;
    }

    public Double getActifEffesCpte() {
        return actifEffesCpte;
    }

    public Bp2Infos actifEffesCpte(Double actifEffesCpte) {
        this.actifEffesCpte = actifEffesCpte;
        return this;
    }

    public void setActifEffesCpte(Double actifEffesCpte) {
        this.actifEffesCpte = actifEffesCpte;
    }

    public Double getActifEffetEnc() {
        return actifEffetEnc;
    }

    public Bp2Infos actifEffetEnc(Double actifEffetEnc) {
        this.actifEffetEnc = actifEffetEnc;
        return this;
    }

    public void setActifEffetEnc(Double actifEffetEnc) {
        this.actifEffetEnc = actifEffetEnc;
    }

    public Double getPassifMaisonMere() {
        return passifMaisonMere;
    }

    public Bp2Infos passifMaisonMere(Double passifMaisonMere) {
        this.passifMaisonMere = passifMaisonMere;
        return this;
    }

    public void setPassifMaisonMere(Double passifMaisonMere) {
        this.passifMaisonMere = passifMaisonMere;
    }

    public Double getPassifAuTresor() {
        return passifAuTresor;
    }

    public Bp2Infos passifAuTresor(Double passifAuTresor) {
        this.passifAuTresor = passifAuTresor;
        return this;
    }

    public void setPassifAuTresor(Double passifAuTresor) {
        this.passifAuTresor = passifAuTresor;
    }

    public Double getPassifCliCpteCh() {
        return passifCliCpteCh;
    }

    public Bp2Infos passifCliCpteCh(Double passifCliCpteCh) {
        this.passifCliCpteCh = passifCliCpteCh;
        return this;
    }

    public void setPassifCliCpteCh(Double passifCliCpteCh) {
        this.passifCliCpteCh = passifCliCpteCh;
    }

    public Double getPassifCptApresEnc() {
        return passifCptApresEnc;
    }

    public Bp2Infos passifCptApresEnc(Double passifCptApresEnc) {
        this.passifCptApresEnc = passifCptApresEnc;
        return this;
    }

    public void setPassifCptApresEnc(Double passifCptApresEnc) {
        this.passifCptApresEnc = passifCptApresEnc;
    }

    public LocalDate getDateChargement() {
        return dateChargement;
    }

    public Bp2Infos dateChargement(LocalDate dateChargement) {
        this.dateChargement = dateChargement;
        return this;
    }

    public void setDateChargement(LocalDate dateChargement) {
        this.dateChargement = dateChargement;
    }

    public LocalDate getDateDechargement() {
        return dateDechargement;
    }

    public Bp2Infos dateDechargement(LocalDate dateDechargement) {
        this.dateDechargement = dateDechargement;
        return this;
    }

    public void setDateDechargement(LocalDate dateDechargement) {
        this.dateDechargement = dateDechargement;
    }

    public LocalDate getPassifCliCptVue() {
        return passifCliCptVue;
    }

    public Bp2Infos passifCliCptVue(LocalDate passifCliCptVue) {
        this.passifCliCptVue = passifCliCptVue;
        return this;
    }

    public void setPassifCliCptVue(LocalDate passifCliCptVue) {
        this.passifCliCptVue = passifCliCptVue;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bp2Infos)) {
            return false;
        }
        return id != null && id.equals(((Bp2Infos) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Bp2Infos{" +
            "id=" + getId() +
            ", codeIsoDevise='" + getCodeIsoDevise() + "'" +
            ", actifBilletEtRcai=" + getActifBilletEtRcai() +
            ", actifMaisonMere=" + getActifMaisonMere() +
            ", actifAuTresor=" + getActifAuTresor() +
            ", actifClientDeb=" + getActifClientDeb() +
            ", actifEffesCpte=" + getActifEffesCpte() +
            ", actifEffetEnc=" + getActifEffetEnc() +
            ", passifMaisonMere=" + getPassifMaisonMere() +
            ", passifAuTresor=" + getPassifAuTresor() +
            ", passifCliCpteCh=" + getPassifCliCpteCh() +
            ", passifCptApresEnc=" + getPassifCptApresEnc() +
            ", dateChargement='" + getDateChargement() + "'" +
            ", dateDechargement='" + getDateDechargement() + "'" +
            ", passifCliCptVue='" + getPassifCliCptVue() + "'" +
            "}";
    }
}
