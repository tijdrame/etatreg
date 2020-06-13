package com.emard.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A BankInfos.
 */
@Entity
@Table(name = "bank_infos")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BankInfos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "code_id", nullable = false)
    private String codeId;

    
    @Column(name = "sigle", unique = true)
    private String sigle;

    @NotNull
    @Column(name = "raison_sociale", nullable = false)
    private String raisonSociale;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeId() {
        return codeId;
    }

    public BankInfos codeId(String codeId) {
        this.codeId = codeId;
        return this;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getSigle() {
        return sigle;
    }

    public BankInfos sigle(String sigle) {
        this.sigle = sigle;
        return this;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public BankInfos raisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
        return this;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankInfos)) {
            return false;
        }
        return id != null && id.equals(((BankInfos) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankInfos{" +
            "id=" + getId() +
            ", codeId='" + getCodeId() + "'" +
            ", sigle='" + getSigle() + "'" +
            ", raisonSociale='" + getRaisonSociale() + "'" +
            "}";
    }
}
