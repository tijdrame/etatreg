package com.emard.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Bp4Infos.
 */
@Entity
@Table(name = "bp4infos")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Bp4Infos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code_iso_pays")
    private String codeIsoPays;

    @Column(name = "libelle_pays")
    private String libellePays;

    @Column(name = "mntnos_cartes")
    private Double mntnosCartes;

    @Column(name = "mnt_cartes_etr")
    private Double mntCartesEtr;
    
    @Column(name = "dco")
    private LocalDate dco;
	
    @Column(name = "datearrete")
    private LocalDate datearrete;
	
    @Column(name = "nom_fic")
    private String nomfic;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeIsoPays() {
        return codeIsoPays;
    }

    public Bp4Infos codeIsoPays(String codeIsoPays) {
        this.codeIsoPays = codeIsoPays;
        return this;
    }

    public void setCodeIsoPays(String codeIsoPays) {
        this.codeIsoPays = codeIsoPays;
    }

    public String getLibellePays() {
        return libellePays;
    }

    public Bp4Infos libellePays(String libellePays) {
        this.libellePays = libellePays;
        return this;
    }

    public void setLibellePays(String libellePays) {
        this.libellePays = libellePays;
    }

    public Double getMntnosCartes() {
        return mntnosCartes;
    }

    public Bp4Infos mntnosCartes(Double mntnosCartes) {
        this.mntnosCartes = mntnosCartes;
        return this;
    }

    public void setMntnosCartes(Double mntnosCartes) {
        this.mntnosCartes = mntnosCartes;
    }

    public Double getMntCartesEtr() {
        return mntCartesEtr;
    }

    public Bp4Infos mntCartesEtr(Double mntCartesEtr) {
        this.mntCartesEtr = mntCartesEtr;
        return this;
    }

    public void setMntCartesEtr(Double mntCartesEtr) {
        this.mntCartesEtr = mntCartesEtr;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    public LocalDate getDco() {
        return dco;
    }

    public void setDco(LocalDate dco) {
        this.dco = dco;
    }

    public LocalDate getDatearrete() {
        return datearrete;
    }

    public void setDatearrete(LocalDate datearrete) {
        this.datearrete = datearrete;
    }

    public String getNomfic() {
        return nomfic;
    }

    public void setNomfic(String nomfic) {
        this.nomfic = nomfic;
    }
    
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bp4Infos)) {
            return false;
        }
        return id != null && id.equals(((Bp4Infos) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Bp4Infos{" +
            "id=" + getId() +
            ", codeIsoPays='" + getCodeIsoPays() + "'" +
            ", libellePays='" + getLibellePays() + "'" +
            ", mntnosCartes=" + getMntnosCartes() +
            ", mntCartesEtr=" + getMntCartesEtr() +
            ", nom_fic='" + getNomfic() + "'" +
            ", dco=" + getDco() +
            ", date_arrete=" + getDatearrete() +
            "}";
    }
}
