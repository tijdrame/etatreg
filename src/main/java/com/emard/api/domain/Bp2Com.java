package com.emard.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Bp2Com.
 */
@Entity
@Table(name = "bp2com")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Bp2Com implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "age")
    private String age;

    @Column(name = "dev")
    private String dev;

    @Column(name = "ncp")
    private String ncp;

    @Column(name = "inti")
    private String inti;

    @Column(name = "sde")
    private Integer sde;

    @Column(name = "cha")
    private String cha;

    @Column(name = "dou")
    private LocalDate dou;

    @Column(name = "ddd")
    private LocalDate ddd;

    @Column(name = "ddc")
    private LocalDate ddc;

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

    public String getAge() {
        return age;
    }

    public Bp2Com age(String age) {
        this.age = age;
        return this;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDev() {
        return dev;
    }

    public Bp2Com dev(String dev) {
        this.dev = dev;
        return this;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public String getNcp() {
        return ncp;
    }

    public Bp2Com ncp(String ncp) {
        this.ncp = ncp;
        return this;
    }

    public void setNcp(String ncp) {
        this.ncp = ncp;
    }

    public String getInti() {
        return inti;
    }

    public Bp2Com inti(String inti) {
        this.inti = inti;
        return this;
    }

    public void setInti(String inti) {
        this.inti = inti;
    }

    public Integer getSde() {
        return sde;
    }

    public Bp2Com sde(Integer sde) {
        this.sde = sde;
        return this;
    }

    public void setSde(Integer sde) {
        this.sde = sde;
    }

    public String getCha() {
        return cha;
    }

    public Bp2Com cha(String cha) {
        this.cha = cha;
        return this;
    }

    public void setCha(String cha) {
        this.cha = cha;
    }

    public LocalDate getDou() {
        return dou;
    }

    public Bp2Com dou(LocalDate dou) {
        this.dou = dou;
        return this;
    }

    public void setDou(LocalDate dou) {
        this.dou = dou;
    }

    public LocalDate getDdd() {
        return ddd;
    }

    public Bp2Com ddd(LocalDate ddd) {
        this.ddd = ddd;
        return this;
    }

    public void setDdd(LocalDate ddd) {
        this.ddd = ddd;
    }

    public LocalDate getDdc() {
        return ddc;
    }

    public Bp2Com ddc(LocalDate ddc) {
        this.ddc = ddc;
        return this;
    }

    public void setDdc(LocalDate ddc) {
        this.ddc = ddc;
    }

    public String getNomFic() {
        return nomFic;
    }

    public Bp2Com nomFic(String nomFic) {
        this.nomFic = nomFic;
        return this;
    }

    public void setNomFic(String nomFic) {
        this.nomFic = nomFic;
    }

    public LocalDate getDateArrete() {
        return dateArrete;
    }

    public Bp2Com dateArrete(LocalDate dateArrete) {
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
        if (!(o instanceof Bp2Com)) {
            return false;
        }
        return id != null && id.equals(((Bp2Com) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Bp2Com{" +
            "id=" + getId() +
            ", age='" + getAge() + "'" +
            ", dev='" + getDev() + "'" +
            ", ncp='" + getNcp() + "'" +
            ", inti='" + getInti() + "'" +
            ", sde=" + getSde() +
            ", cha='" + getCha() + "'" +
            ", dou='" + getDou() + "'" +
            ", ddd='" + getDdd() + "'" +
            ", ddc='" + getDdc() + "'" +
            ", nomFic='" + getNomFic() + "'" +
            ", dateArrete='" + getDateArrete() + "'" +
            "}";
    }
}
