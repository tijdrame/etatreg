package com.emard.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ObjetCredit.
 */
@Entity
@Table(name = "objet_credit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ObjetCredit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "code_bdu", nullable = false)
    private String codeBdu;

    @NotNull
    @Column(name = "code_cb", nullable = false)
    private String codeCb;

    @Column(name = "description")
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeBdu() {
        return codeBdu;
    }

    public ObjetCredit codeBdu(String codeBdu) {
        this.codeBdu = codeBdu;
        return this;
    }

    public void setCodeBdu(String codeBdu) {
        this.codeBdu = codeBdu;
    }

    public String getCodeCb() {
        return codeCb;
    }

    public ObjetCredit codeCb(String codeCb) {
        this.codeCb = codeCb;
        return this;
    }

    public void setCodeCb(String codeCb) {
        this.codeCb = codeCb;
    }

    public String getDescription() {
        return description;
    }

    public ObjetCredit description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjetCredit)) {
            return false;
        }
        return id != null && id.equals(((ObjetCredit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ObjetCredit{" +
            "id=" + getId() +
            ", codeBdu='" + getCodeBdu() + "'" +
            ", codeCb='" + getCodeCb() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
