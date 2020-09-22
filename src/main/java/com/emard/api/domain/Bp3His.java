package com.emard.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Bp3His.
 */
@Entity
@Table(name = "bp3his")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Bp3His implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "dco")
    private LocalDate dco;

    @Column(name = "age")
    private String age;

    @Column(name = "dev")
    private String dev;

    @Column(name = "ncp")
    private String ncp;

    @Column(name = "ope")
    private String ope;

    @Column(name = "lib")
    private String lib;

    @Column(name = "mon")
    private Double mon;

    @Column(name = "sen")
    private String sen;

    @Column(name = "pie")
    private String pie;

    @Column(name = "ncc")
    private String ncc;

    @Column(name = "uti")
    private String uti;

    @Column(name = "utf")
    private String utf;

    @Column(name = "nat")
    private String nat;

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

    public LocalDate getDco() {
        return dco;
    }

    public Bp3His dco(LocalDate dco) {
        this.dco = dco;
        return this;
    }

    public void setDco(LocalDate dco) {
        this.dco = dco;
    }

    public String getAge() {
        return age;
    }

    public Bp3His age(String age) {
        this.age = age;
        return this;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDev() {
        return dev;
    }

    public Bp3His dev(String dev) {
        this.dev = dev;
        return this;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public String getNcp() {
        return ncp;
    }

    public Bp3His ncp(String ncp) {
        this.ncp = ncp;
        return this;
    }

    public void setNcp(String ncp) {
        this.ncp = ncp;
    }

    public String getOpe() {
        return ope;
    }

    public Bp3His ope(String ope) {
        this.ope = ope;
        return this;
    }

    public void setOpe(String ope) {
        this.ope = ope;
    }

    public String getLib() {
        return lib;
    }

    public Bp3His lib(String lib) {
        this.lib = lib;
        return this;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public Double getMon() {
        return mon;
    }

    public Bp3His mon(Double mon) {
        this.mon = mon;
        return this;
    }

    public void setMon(Double mon) {
        this.mon = mon;
    }

    public String getSen() {
        return sen;
    }

    public Bp3His sen(String sen) {
        this.sen = sen;
        return this;
    }

    public void setSen(String sen) {
        this.sen = sen;
    }

    public String getPie() {
        return pie;
    }

    public Bp3His pie(String pie) {
        this.pie = pie;
        return this;
    }

    public void setPie(String pie) {
        this.pie = pie;
    }

    public String getNcc() {
        return ncc;
    }

    public Bp3His ncc(String ncc) {
        this.ncc = ncc;
        return this;
    }

    public void setNcc(String ncc) {
        this.ncc = ncc;
    }

    public String getUti() {
        return uti;
    }

    public Bp3His uti(String uti) {
        this.uti = uti;
        return this;
    }

    public void setUti(String uti) {
        this.uti = uti;
    }

    public String getUtf() {
        return utf;
    }

    public Bp3His utf(String utf) {
        this.utf = utf;
        return this;
    }

    public void setUtf(String utf) {
        this.utf = utf;
    }

    public String getNat() {
        return nat;
    }

    public Bp3His nat(String nat) {
        this.nat = nat;
        return this;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public String getNomFic() {
        return nomFic;
    }

    public Bp3His nomFic(String nomFic) {
        this.nomFic = nomFic;
        return this;
    }

    public void setNomFic(String nomFic) {
        this.nomFic = nomFic;
    }

    public LocalDate getDateArrete() {
        return dateArrete;
    }

    public Bp3His dateArrete(LocalDate dateArrete) {
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
        if (!(o instanceof Bp3His)) {
            return false;
        }
        return id != null && id.equals(((Bp3His) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Bp3His{" +
            "id=" + getId() +
            ", dco='" + getDco() + "'" +
            ", age='" + getAge() + "'" +
            ", dev='" + getDev() + "'" +
            ", ncp='" + getNcp() + "'" +
            ", ope='" + getOpe() + "'" +
            ", lib='" + getLib() + "'" +
            ", mon=" + getMon() +
            ", sen='" + getSen() + "'" +
            ", pie='" + getPie() + "'" +
            ", ncc='" + getNcc() + "'" +
            ", uti='" + getUti() + "'" +
            ", utf='" + getUtf() + "'" +
            ", nat='" + getNat() + "'" +
            ", nomFic='" + getNomFic() + "'" +
            ", dateArrete='" + getDateArrete() + "'" +
            "}";
    }
}
