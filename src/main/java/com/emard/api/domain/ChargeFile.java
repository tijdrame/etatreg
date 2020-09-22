package com.emard.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A ChargeFile.
 */
@Entity
@Table(name = "charge_file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChargeFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nom_fic")
    private String nomFic;

    @Column(name = "date_charge")
    private LocalDate dateCharge;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomFic() {
        return nomFic;
    }

    public ChargeFile nomFic(String nomFic) {
        this.nomFic = nomFic;
        return this;
    }

    public void setNomFic(String nomFic) {
        this.nomFic = nomFic;
    }

    public LocalDate getDateCharge() {
        return dateCharge;
    }

    public ChargeFile dateCharge(LocalDate dateCharge) {
        this.dateCharge = dateCharge;
        return this;
    }

    public void setDateCharge(LocalDate dateCharge) {
        this.dateCharge = dateCharge;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChargeFile)) {
            return false;
        }
        return id != null && id.equals(((ChargeFile) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChargeFile{" +
            "id=" + getId() +
            ", nomFic='" + getNomFic() + "'" +
            ", dateCharge='" + getDateCharge() + "'" +
            "}";
    }
}
