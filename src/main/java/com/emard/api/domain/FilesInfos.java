package com.emard.api.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A FilesInfos.
 */
@Entity
@Table(name = "files_infos")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FilesInfos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code_file", nullable = false)
    private String codeFile;

    @Column(name = "description")
    private String description;

    @Column(name = "input_path")
    private String inputPath;

    @Column(name = "output_path")
    private String outputPath;

    @Column(name = "date_dernier_chargement")
    private Instant dateDernierChargement;

    @Column(name = "date_dernier_dechargement")
    private Instant dateDernierDechargement;

    @Column(name = "code_application")
    private String codeApplication;

    @Column(name = "code_format")
    private String codeFormat;

    @Column(name = "code_extension")
    private String codeExtension;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeFile() {
        return codeFile;
    }

    public FilesInfos codeFile(String codeFile) {
        this.codeFile = codeFile;
        return this;
    }

    public void setCodeFile(String codeFile) {
        this.codeFile = codeFile;
    }

    public String getDescription() {
        return description;
    }

    public FilesInfos description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInputPath() {
        return inputPath;
    }

    public FilesInfos inputPath(String inputPath) {
        this.inputPath = inputPath;
        return this;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public FilesInfos outputPath(String outputPath) {
        this.outputPath = outputPath;
        return this;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public Instant getDateDernierChargement() {
        return dateDernierChargement;
    }

    public FilesInfos dateDernierChargement(Instant dateDernierChargement) {
        this.dateDernierChargement = dateDernierChargement;
        return this;
    }

    public void setDateDernierChargement(Instant dateDernierChargement) {
        this.dateDernierChargement = dateDernierChargement;
    }

    public Instant getDateDernierDechargement() {
        return dateDernierDechargement;
    }

    public FilesInfos dateDernierDechargement(Instant dateDernierDechargement) {
        this.dateDernierDechargement = dateDernierDechargement;
        return this;
    }

    public void setDateDernierDechargement(Instant dateDernierDechargement) {
        this.dateDernierDechargement = dateDernierDechargement;
    }

    public String getCodeApplication() {
        return codeApplication;
    }

    public FilesInfos codeApplication(String codeApplication) {
        this.codeApplication = codeApplication;
        return this;
    }

    public void setCodeApplication(String codeApplication) {
        this.codeApplication = codeApplication;
    }

    public String getCodeFormat() {
        return codeFormat;
    }

    public FilesInfos codeFormat(String codeFormat) {
        this.codeFormat = codeFormat;
        return this;
    }

    public void setCodeFormat(String codeFormat) {
        this.codeFormat = codeFormat;
    }

    public String getCodeExtension() {
        return codeExtension;
    }

    public FilesInfos codeExtension(String codeExtension) {
        this.codeExtension = codeExtension;
        return this;
    }

    public void setCodeExtension(String codeExtension) {
        this.codeExtension = codeExtension;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FilesInfos)) {
            return false;
        }
        return id != null && id.equals(((FilesInfos) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FilesInfos{" +
            "id=" + getId() +
            ", codeFile='" + getCodeFile() + "'" +
            ", description='" + getDescription() + "'" +
            ", inputPath='" + getInputPath() + "'" +
            ", outputPath='" + getOutputPath() + "'" +
            ", dateDernierChargement='" + getDateDernierChargement() + "'" +
            ", dateDernierDechargement='" + getDateDernierDechargement() + "'" +
            ", codeApplication='" + getCodeApplication() + "'" +
            ", codeFormat='" + getCodeFormat() + "'" +
            ", codeExtension='" + getCodeExtension() + "'" +
            "}";
    }
}
