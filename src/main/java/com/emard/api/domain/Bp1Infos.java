/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emard.api.domain;

import java.io.Serializable;
import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "D100")
    private String d100;
    @Column(name = "D110")
    private String d110;
    @Column(name = "D120")
    private String d120;
    @Column(name = "D130")
    private String d130;
    @Column(name = "D131")
    private String d131;
    @Column(name = "D132")
    private String d132;
    @Column(name = "D133")
    private String d133;
    @Column(name = "D134")
    private String d134;
    @Column(name = "D135")
    private String d135;
    @Column(name = "D140")
    private String d140;
    @Column(name = "D150")
    private String d150;
    @Column(name = "D160")
    private String d160;
    @Column(name = "D200")
    private String d200;
    @Column(name = "D210")
    private String d210;
    @Column(name = "D220")
    private String d220;
    @Column(name = "D230")
    private String d230;
    @Column(name = "D231")
    private String d231;
    @Column(name = "D232")
    private String d232;
    @Column(name = "D233")
    private String d233;
    @Column(name = "D234")
    private String d234;
    @Column(name = "D235")
    private String d235;
    @Column(name = "D240")
    private String d240;
    @Column(name = "D250")
    private String d250;
    @Column(name = "D260")
    private String d260;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getD100() {
        return d100;
    }

    public void setD100(String d100) {
        this.d100 = d100;
    }

    public String getD110() {
        return d110;
    }

    public void setD110(String d110) {
        this.d110 = d110;
    }

    public String getD120() {
        return d120;
    }

    public void setD120(String d120) {
        this.d120 = d120;
    }

    public String getD130() {
        return d130;
    }

    public void setD130(String d130) {
        this.d130 = d130;
    }

    public String getD131() {
        return d131;
    }

    public void setD131(String d131) {
        this.d131 = d131;
    }

    public String getD132() {
        return d132;
    }

    public void setD132(String d132) {
        this.d132 = d132;
    }

    public String getD133() {
        return d133;
    }

    public void setD133(String d133) {
        this.d133 = d133;
    }

    public String getD134() {
        return d134;
    }

    public void setD134(String d134) {
        this.d134 = d134;
    }

    public String getD135() {
        return d135;
    }

    public void setD135(String d135) {
        this.d135 = d135;
    }

    public String getD140() {
        return d140;
    }

    public void setD140(String d140) {
        this.d140 = d140;
    }

    public String getD150() {
        return d150;
    }

    public void setD150(String d150) {
        this.d150 = d150;
    }

    public String getD160() {
        return d160;
    }

    public void setD160(String d160) {
        this.d160 = d160;
    }

    public String getD200() {
        return d200;
    }

    public void setD200(String d200) {
        this.d200 = d200;
    }

    public String getD210() {
        return d210;
    }

    public void setD210(String d210) {
        this.d210 = d210;
    }

    public String getD220() {
        return d220;
    }

    public void setD220(String d220) {
        this.d220 = d220;
    }

    public String getD230() {
        return d230;
    }

    public void setD230(String d230) {
        this.d230 = d230;
    }

    public String getD231() {
        return d231;
    }

    public void setD231(String d231) {
        this.d231 = d231;
    }

    public String getD232() {
        return d232;
    }

    public void setD232(String d232) {
        this.d232 = d232;
    }

    public String getD233() {
        return d233;
    }

    public void setD233(String d233) {
        this.d233 = d233;
    }

    public String getD234() {
        return d234;
    }

    public void setD234(String d234) {
        this.d234 = d234;
    }

    public String getD235() {
        return d235;
    }

    public void setD235(String d235) {
        this.d235 = d235;
    }

    public String getD240() {
        return d240;
    }

    public void setD240(String d240) {
        this.d240 = d240;
    }

    public String getD250() {
        return d250;
    }

    public void setD250(String d250) {
        this.d250 = d250;
    }

    public String getD260() {
        return d260;
    }

    public void setD260(String d260) {
        this.d260 = d260;
    }

    public Bp1Infos() {
    }

    @Override
    public String toString() {
        return "Bp1infos{" + "id=" + id + "D100=" + d100 + ", D110=" + d110 + ", D120=" + d120 + ", D130=" + d130
                + ", D131=" + d131 + ", D132=" + d132 + ", D133=" + d133 + ", D134=" + d134 + ", D135=" + d135
                + ", D140=" + d140 + ", D150=" + d150 + ", D160=" + d160 + ", D200=" + d200 + ", D210=" + d210
                + ", D220=" + d220 + ", D230=" + d230 + ", D231=" + d231 + ", D232=" + d232 + ", D233=" + d233
                + ", D234=" + d234 + ", D235=" + d235 + ", D240=" + d240 + ", D250=" + d250 + ", D260=" + d260 + '}';
    }

    public Bp1Infos(Long id, String D100, String D110, String D120, String D130, String D131, String D132, String D133,
            String D134, String D135, String D140, String D150, String D160, String D200, String D210, String D220,
            String D230, String D231, String D232, String D233, String D234, String D235, String D240, String D250,
            String D260) {
        this.id = id;
        this.d100 = D100;
        this.d110 = D110;
        this.d120 = D120;
        this.d130 = D130;
        this.d131 = D131;
        this.d132 = D132;
        this.d133 = D133;
        this.d134 = D134;
        this.d135 = D135;
        this.d140 = D140;
        this.d150 = D150;
        this.d160 = D160;
        this.d200 = D200;
        this.d210 = D210;
        this.d220 = D220;
        this.d230 = D230;
        this.d231 = D231;
        this.d232 = D232;
        this.d233 = D233;
        this.d234 = D234;
        this.d235 = D235;
        this.d240 = D240;
        this.d250 = D250;
        this.d260 = D260;
    }

}
