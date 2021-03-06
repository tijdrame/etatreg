package com.emard.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A AcheteurVendeur.
 */
@Entity
@Table(name = "acheteur_vendeur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AcheteurVendeur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code_acheteur_vendeur", nullable = false)
    private String codeAcheteurVendeur;

    @Column(name = "description")
    private String description;

    @Column(name = "code_interne")
    private String codeInterne;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeAcheteurVendeur() {
        return codeAcheteurVendeur;
    }

    public AcheteurVendeur codeAcheteurVendeur(String codeAcheteurVendeur) {
        this.codeAcheteurVendeur = codeAcheteurVendeur;
        return this;
    }

    public void setCodeAcheteurVendeur(String codeAcheteurVendeur) {
        this.codeAcheteurVendeur = codeAcheteurVendeur;
    }

    public String getDescription() {
        return description;
    }

    public AcheteurVendeur description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCodeInterne() {
        return codeInterne;
    }

    public AcheteurVendeur codeInterne(String codeInterne) {
        this.codeInterne = codeInterne;
        return this;
    }

    public void setCodeInterne(String codeInterne) {
        this.codeInterne = codeInterne;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AcheteurVendeur)) {
            return false;
        }
        return id != null && id.equals(((AcheteurVendeur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AcheteurVendeur{" +
            "id=" + getId() +
            ", codeAcheteurVendeur='" + getCodeAcheteurVendeur() + "'" +
            ", description='" + getDescription() + "'" +
            ", codeInterne='" + getCodeInterne() + "'" +
            "}";
    }
}
