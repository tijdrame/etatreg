/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emard.api.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Bouna
 */
@Entity
@Table(name = "bp1infos")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Bp1Infos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "D100")
    private String D100;
    @Column(name = "D110")
    private String D110;
    @Column(name = "D120")
    private String D120;
    @Column(name = "D130")
    private String D130;
    @Column(name = "D131")
    private String D131;
    @Column(name = "D132")
    private String D132;
    @Column(name = "D133")
    private String D133;
    @Column(name = "D134")
    private String D134;
    @Column(name = "D135")
    private String D135;
    @Column(name = "D140")
    private String D140;
    @Column(name = "D150")
    private String D150;
    @Column(name = "D160")
    private String D160;
    @Column(name = "D200")
    private String D200;
    @Column(name = "D210")
    private String D210;
    @Column(name = "D220")
    private String D220;
    @Column(name = "D230")
    private String D230;
    @Column(name = "D231")
    private String D231;
    @Column(name = "D232")
    private String D232;
    @Column(name = "D233")
    private String D233;
    @Column(name = "D234")
    private String D234;
    @Column(name = "D235")
    private String D235;
    @Column(name = "D240")
    private String D240;
    @Column(name = "D250")
    private String D250;
    @Column(name = "D260")
    private String D260;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getD100() {
        return D100;
    }

    public void setD100(String D100) {
        this.D100 = D100;
    }

    public String getD110() {
        return D110;
    }

    public void setD110(String D110) {
        this.D110 = D110;
    }

    public String getD120() {
        return D120;
    }

    public void setD120(String D120) {
        this.D120 = D120;
    }

    public String getD130() {
        return D130;
    }

    public void setD130(String D130) {
        this.D130 = D130;
    }

    public String getD131() {
        return D131;
    }

    public void setD131(String D131) {
        this.D131 = D131;
    }

    public String getD132() {
        return D132;
    }

    public void setD132(String D132) {
        this.D132 = D132;
    }

    public String getD133() {
        return D133;
    }

    public void setD133(String D133) {
        this.D133 = D133;
    }

    public String getD134() {
        return D134;
    }

    public void setD134(String D134) {
        this.D134 = D134;
    }

    public String getD135() {
        return D135;
    }

    public void setD135(String D135) {
        this.D135 = D135;
    }

    public String getD140() {
        return D140;
    }

    public void setD140(String D140) {
        this.D140 = D140;
    }

    public String getD150() {
        return D150;
    }

    public void setD150(String D150) {
        this.D150 = D150;
    }

    public String getD160() {
        return D160;
    }

    public void setD160(String D160) {
        this.D160 = D160;
    }

    public String getD200() {
        return D200;
    }

    public void setD200(String D200) {
        this.D200 = D200;
    }

    public String getD210() {
        return D210;
    }

    public void setD210(String D210) {
        this.D210 = D210;
    }

    public String getD220() {
        return D220;
    }

    public void setD220(String D220) {
        this.D220 = D220;
    }

    public String getD230() {
        return D230;
    }

    public void setD230(String D230) {
        this.D230 = D230;
    }

    public String getD231() {
        return D231;
    }

    public void setD231(String D231) {
        this.D231 = D231;
    }

    public String getD232() {
        return D232;
    }

    public void setD232(String D232) {
        this.D232 = D232;
    }

    public String getD233() {
        return D233;
    }

    public void setD233(String D233) {
        this.D233 = D233;
    }

    public String getD234() {
        return D234;
    }

    public void setD234(String D234) {
        this.D234 = D234;
    }

    public String getD235() {
        return D235;
    }

    public void setD235(String D235) {
        this.D235 = D235;
    }

    public String getD240() {
        return D240;
    }

    public void setD240(String D240) {
        this.D240 = D240;
    }

    public String getD250() {
        return D250;
    }

    public void setD250(String D250) {
        this.D250 = D250;
    }

    public String getD260() {
        return D260;
    }

    public void setD260(String D260) {
        this.D260 = D260;
    }

    protected Bp1Infos() {
    }

    @Override
    public String toString() {
        return "Bp1infos{" + "id=" + id + "D100=" + D100 + ", D110=" + D110 + ", D120=" + D120 + ", D130=" + D130
                + ", D131=" + D131 + ", D132=" + D132 + ", D133=" + D133 + ", D134=" + D134 + ", D135=" + D135
                + ", D140=" + D140 + ", D150=" + D150 + ", D160=" + D160 + ", D200=" + D200 + ", D210=" + D210
                + ", D220=" + D220 + ", D230=" + D230 + ", D231=" + D231 + ", D232=" + D232 + ", D233=" + D233
                + ", D234=" + D234 + ", D235=" + D235 + ", D240=" + D240 + ", D250=" + D250 + ", D260=" + D260 + '}';
    }

    public Bp1Infos(Long id, String D100, String D110, String D120, String D130, String D131, String D132, String D133,
            String D134, String D135, String D140, String D150, String D160, String D200, String D210, String D220,
            String D230, String D231, String D232, String D233, String D234, String D235, String D240, String D250,
            String D260) {
        this.id = id;
        this.D100 = D100;
        this.D110 = D110;
        this.D120 = D120;
        this.D130 = D130;
        this.D131 = D131;
        this.D132 = D132;
        this.D133 = D133;
        this.D134 = D134;
        this.D135 = D135;
        this.D140 = D140;
        this.D150 = D150;
        this.D160 = D160;
        this.D200 = D200;
        this.D210 = D210;
        this.D220 = D220;
        this.D230 = D230;
        this.D231 = D231;
        this.D232 = D232;
        this.D233 = D233;
        this.D234 = D234;
        this.D235 = D235;
        this.D240 = D240;
        this.D250 = D250;
        this.D260 = D260;
    }

}
