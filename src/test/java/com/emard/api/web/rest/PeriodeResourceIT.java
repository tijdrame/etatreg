package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.Periode;
import com.emard.api.repository.PeriodeRepository;
import com.emard.api.service.PeriodeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PeriodeResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PeriodeResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private PeriodeRepository periodeRepository;

    @Autowired
    private PeriodeService periodeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPeriodeMockMvc;

    private Periode periode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Periode createEntity(EntityManager em) {
        Periode periode = new Periode()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return periode;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Periode createUpdatedEntity(EntityManager em) {
        Periode periode = new Periode()
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        return periode;
    }

    @BeforeEach
    public void initTest() {
        periode = createEntity(em);
    }

    @Test
    @Transactional
    public void createPeriode() throws Exception {
        int databaseSizeBeforeCreate = periodeRepository.findAll().size();
        // Create the Periode
        restPeriodeMockMvc.perform(post("/api/periodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(periode)))
            .andExpect(status().isCreated());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeCreate + 1);
        Periode testPeriode = periodeList.get(periodeList.size() - 1);
        assertThat(testPeriode.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testPeriode.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createPeriodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = periodeRepository.findAll().size();

        // Create the Periode with an existing ID
        periode.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPeriodeMockMvc.perform(post("/api/periodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(periode)))
            .andExpect(status().isBadRequest());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPeriodes() throws Exception {
        // Initialize the database
        periodeRepository.saveAndFlush(periode);

        // Get all the periodeList
        restPeriodeMockMvc.perform(get("/api/periodes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(periode.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)));
    }
    
    @Test
    @Transactional
    public void getPeriode() throws Exception {
        // Initialize the database
        periodeRepository.saveAndFlush(periode);

        // Get the periode
        restPeriodeMockMvc.perform(get("/api/periodes/{id}", periode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(periode.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE));
    }
    @Test
    @Transactional
    public void getNonExistingPeriode() throws Exception {
        // Get the periode
        restPeriodeMockMvc.perform(get("/api/periodes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePeriode() throws Exception {
        // Initialize the database
        periodeService.save(periode);

        int databaseSizeBeforeUpdate = periodeRepository.findAll().size();

        // Update the periode
        Periode updatedPeriode = periodeRepository.findById(periode.getId()).get();
        // Disconnect from session so that the updates on updatedPeriode are not directly saved in db
        em.detach(updatedPeriode);
        updatedPeriode
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);

        restPeriodeMockMvc.perform(put("/api/periodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPeriode)))
            .andExpect(status().isOk());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeUpdate);
        Periode testPeriode = periodeList.get(periodeList.size() - 1);
        assertThat(testPeriode.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testPeriode.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingPeriode() throws Exception {
        int databaseSizeBeforeUpdate = periodeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPeriodeMockMvc.perform(put("/api/periodes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(periode)))
            .andExpect(status().isBadRequest());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePeriode() throws Exception {
        // Initialize the database
        periodeService.save(periode);

        int databaseSizeBeforeDelete = periodeRepository.findAll().size();

        // Delete the periode
        restPeriodeMockMvc.perform(delete("/api/periodes/{id}", periode.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
