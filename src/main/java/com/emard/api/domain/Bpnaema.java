package com.emard.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Bpnaema.
 */
@Entity
@Table(name = "bpnaema")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Bpnaema implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_bqe")
    private String codeBqe;

    @Column(name = "code_cb")
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

    public String getCodeBqe() {
        return codeBqe;
    }

    public Bpnaema codeBqe(String codeBqe) {
        this.codeBqe = codeBqe;
        return this;
    }

    public void setCodeBqe(String codeBqe) {
        this.codeBqe = codeBqe;
    }

    public String getCodeCb() {
        return codeCb;
    }

    public Bpnaema codeCb(String codeCb) {
        this.codeCb = codeCb;
        return this;
    }

    public void setCodeCb(String codeCb) {
        this.codeCb = codeCb;
    }

    public String getDescription() {
        return description;
    }

    public Bpnaema description(String description) {
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
        if (!(o instanceof Bpnaema)) {
            return false;
        }
        return id != null && id.equals(((Bpnaema) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Bpnaema{" +
            "id=" + getId() +
            ", codeBqe='" + getCodeBqe() + "'" +
            ", codeCb='" + getCodeCb() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
