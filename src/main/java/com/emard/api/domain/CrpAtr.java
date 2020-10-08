package com.emard.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A CrpAtr.
 */
@Entity
@Table(name = "CrpAtr")// crp_atr
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CrpAtr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cenr")
    private String cenr;

    @Column(name = "refint")
    private String refint;

    @Column(name = "num")
    private String num;

    @Column(name = "nobor")
    private String nobor;

    @Column(name = "typenr")
    private String typenr;

    @Column(name = "id_atr")
    private String idAtr;

    @Column(name = "dem_atr")
    private LocalDate demAtr;

    @Column(name = "num_atr")
    private String numAtr;

    @Column(name = "nom_res")
    private String nomRes;

    @Column(name = "cpay_iso")
    private String cpayIso;

    @Column(name = "datope")
    private LocalDate datope;

    @Column(name = "regt")
    private String regt;

    @Column(name = "nom_etr")
    private String nomEtr;

    @Column(name = "nocli")
    private String nocli;

    @Column(name = "cat_res")
    private String catRes;

    @Column(name = "ceco")
    private String ceco;

    @Column(name = "cpay_etg")
    private String cpayEtg;

    @Column(name = "natcpt")
    private String natcpt;

    @Column(name = "sens")
    private String sens;

    @Column(name = "devn")
    private String devn;

    @Column(name = "mdev")
    private Double mdev;

    @Column(name = "taux")
    private Double taux;

    @Column(name = "mnat")
    private Double mnat;

    @Column(name = "etab")
    private String etab;

    @Column(name = "nom_fic")
    private String nomFic;

    @Column(name = "date_arrete")
    private LocalDate dateArrete;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCenr() {
        return cenr;
    }

    public CrpAtr cenr(String cenr) {
        this.cenr = cenr;
        return this;
    }

    public void setCenr(String cenr) {
        this.cenr = cenr;
    }

    public String getRefint() {
        return refint;
    }

    public CrpAtr refint(String refint) {
        this.refint = refint;
        return this;
    }

    public void setRefint(String refint) {
        this.refint = refint;
    }

    public String getNum() {
        return num;
    }

    public CrpAtr num(String num) {
        this.num = num;
        return this;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNobor() {
        return nobor;
    }

    public CrpAtr nobor(String nobor) {
        this.nobor = nobor;
        return this;
    }

    public void setNobor(String nobor) {
        this.nobor = nobor;
    }

    public String getTypenr() {
        return typenr;
    }

    public CrpAtr typenr(String typenr) {
        this.typenr = typenr;
        return this;
    }

    public void setTypenr(String typenr) {
        this.typenr = typenr;
    }

    public String getIdAtr() {
        return idAtr;
    }

    public CrpAtr idAtr(String idAtr) {
        this.idAtr = idAtr;
        return this;
    }

    public void setIdAtr(String idAtr) {
        this.idAtr = idAtr;
    }

    public LocalDate getDemAtr() {
        return demAtr;
    }

    public CrpAtr demAtr(LocalDate demAtr) {
        this.demAtr = demAtr;
        return this;
    }

    public void setDemAtr(LocalDate demAtr) {
        this.demAtr = demAtr;
    }

    public String getNumAtr() {
        return numAtr;
    }

    public CrpAtr numAtr(String numAtr) {
        this.numAtr = numAtr;
        return this;
    }

    public void setNumAtr(String numAtr) {
        this.numAtr = numAtr;
    }

    public String getNomRes() {
        return nomRes;
    }

    public CrpAtr nomRes(String nomRes) {
        this.nomRes = nomRes;
        return this;
    }

    public void setNomRes(String nomRes) {
        this.nomRes = nomRes;
    }

    public String getCpayIso() {
        return cpayIso;
    }

    public CrpAtr cpayIso(String cpayIso) {
        this.cpayIso = cpayIso;
        return this;
    }

    public void setCpayIso(String cpayIso) {
        this.cpayIso = cpayIso;
    }

    public LocalDate getDatope() {
        return datope;
    }

    public CrpAtr datope(LocalDate datope) {
        this.datope = datope;
        return this;
    }

    public void setDatope(LocalDate datope) {
        this.datope = datope;
    }

    public String getRegt() {
        return regt;
    }

    public CrpAtr regt(String regt) {
        this.regt = regt;
        return this;
    }

    public void setRegt(String regt) {
        this.regt = regt;
    }

    public String getNomEtr() {
        return nomEtr;
    }

    public CrpAtr nomEtr(String nomEtr) {
        this.nomEtr = nomEtr;
        return this;
    }

    public void setNomEtr(String nomEtr) {
        this.nomEtr = nomEtr;
    }

    public String getNocli() {
        return nocli;
    }

    public CrpAtr nocli(String nocli) {
        this.nocli = nocli;
        return this;
    }

    public void setNocli(String nocli) {
        this.nocli = nocli;
    }

    public String getCatRes() {
        return catRes;
    }

    public CrpAtr catRes(String catRes) {
        this.catRes = catRes;
        return this;
    }

    public void setCatRes(String catRes) {
        this.catRes = catRes;
    }

    public String getCeco() {
        return ceco;
    }

    public CrpAtr ceco(String ceco) {
        this.ceco = ceco;
        return this;
    }

    public void setCeco(String ceco) {
        this.ceco = ceco;
    }

    public String getCpayEtg() {
        return cpayEtg;
    }

    public CrpAtr cpayEtg(String cpayEtg) {
        this.cpayEtg = cpayEtg;
        return this;
    }

    public void setCpayEtg(String cpayEtg) {
        this.cpayEtg = cpayEtg;
    }

    public String getNatcpt() {
        return natcpt;
    }

    public CrpAtr natcpt(String natcpt) {
        this.natcpt = natcpt;
        return this;
    }

    public void setNatcpt(String natcpt) {
        this.natcpt = natcpt;
    }

    public String getSens() {
        return sens;
    }

    public CrpAtr sens(String sens) {
        this.sens = sens;
        return this;
    }

    public void setSens(String sens) {
        this.sens = sens;
    }

    public String getDevn() {
        return devn;
    }

    public CrpAtr devn(String devn) {
        this.devn = devn;
        return this;
    }

    public void setDevn(String devn) {
        this.devn = devn;
    }

    public Double getMdev() {
        return mdev;
    }

    public CrpAtr mdev(Double mdev) {
        this.mdev = mdev;
        return this;
    }

    public void setMdev(Double mdev) {
        this.mdev = mdev;
    }

    public Double getTaux() {
        return taux;
    }

    public CrpAtr taux(Double taux) {
        this.taux = taux;
        return this;
    }

    public void setTaux(Double taux) {
        this.taux = taux;
    }

    public Double getMnat() {
        return mnat;
    }

    public CrpAtr mnat(Double mnat) {
        this.mnat = mnat;
        return this;
    }

    public void setMnat(Double mnat) {
        this.mnat = mnat;
    }

    public String getEtab() {
        return etab;
    }

    public CrpAtr etab(String etab) {
        this.etab = etab;
        return this;
    }

    public void setEtab(String etab) {
        this.etab = etab;
    }

    public String getNomFic() {
        return nomFic;
    }

    public CrpAtr nomFic(String nomFic) {
        this.nomFic = nomFic;
        return this;
    }

    public void setNomFic(String nomFic) {
        this.nomFic = nomFic;
    }

    public LocalDate getDateArrete() {
        return dateArrete;
    }

    public CrpAtr dateArrete(LocalDate dateArrete) {
        this.dateArrete = dateArrete;
        return this;
    }

    public void setDateArrete(LocalDate dateArrete) {
        this.dateArrete = dateArrete;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CrpAtr)) {
            return false;
        }
        return id != null && id.equals(((CrpAtr) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CrpAtr{" +
            "id=" + getId() +
            ", cenr='" + getCenr() + "'" +
            ", refint='" + getRefint() + "'" +
            ", num='" + getNum() + "'" +
            ", nobor='" + getNobor() + "'" +
            ", typenr='" + getTypenr() + "'" +
            ", idAtr='" + getIdAtr() + "'" +
            ", demAtr='" + getDemAtr() + "'" +
            ", numAtr='" + getNumAtr() + "'" +
            ", nomRes='" + getNomRes() + "'" +
            ", cpayIso='" + getCpayIso() + "'" +
            ", datope='" + getDatope() + "'" +
            ", regt='" + getRegt() + "'" +
            ", nomEtr='" + getNomEtr() + "'" +
            ", nocli='" + getNocli() + "'" +
            ", catRes='" + getCatRes() + "'" +
            ", ceco='" + getCeco() + "'" +
            ", cpayEtg='" + getCpayEtg() + "'" +
            ", natcpt='" + getNatcpt() + "'" +
            ", sens='" + getSens() + "'" +
            ", devn='" + getDevn() + "'" +
            ", mdev=" + getMdev() +
            ", taux=" + getTaux() +
            ", mnat=" + getMnat() +
            ", etab='" + getEtab() + "'" +
            ", nomFic='" + getNomFic() + "'" +
            ", dateArrete='" + getDateArrete() + "'" +
            "}";
    }
}
