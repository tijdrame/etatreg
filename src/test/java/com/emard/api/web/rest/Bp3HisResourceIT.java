package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.Bp3His;
import com.emard.api.repository.Bp3HisRepository;
import com.emard.api.service.Bp3HisService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link Bp3HisResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class Bp3HisResourceIT {

    private static final LocalDate DEFAULT_DCO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DCO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_AGE = "AAAAAAAAAA";
    private static final String UPDATED_AGE = "BBBBBBBBBB";

    private static final String DEFAULT_DEV = "AAAAAAAAAA";
    private static final String UPDATED_DEV = "BBBBBBBBBB";

    private static final String DEFAULT_NCP = "AAAAAAAAAA";
    private static final String UPDATED_NCP = "BBBBBBBBBB";

    private static final String DEFAULT_OPE = "AAAAAAAAAA";
    private static final String UPDATED_OPE = "BBBBBBBBBB";

    private static final String DEFAULT_LIB = "AAAAAAAAAA";
    private static final String UPDATED_LIB = "BBBBBBBBBB";

    private static final Double DEFAULT_MON = 1d;
    private static final Double UPDATED_MON = 2d;

    private static final String DEFAULT_SEN = "AAAAAAAAAA";
    private static final String UPDATED_SEN = "BBBBBBBBBB";

    private static final String DEFAULT_PIE = "AAAAAAAAAA";
    private static final String UPDATED_PIE = "BBBBBBBBBB";

    private static final String DEFAULT_NCC = "AAAAAAAAAA";
    private static final String UPDATED_NCC = "BBBBBBBBBB";

    private static final String DEFAULT_UTI = "AAAAAAAAAA";
    private static final String UPDATED_UTI = "BBBBBBBBBB";

    private static final String DEFAULT_UTF = "AAAAAAAAAA";
    private static final String UPDATED_UTF = "BBBBBBBBBB";

    private static final String DEFAULT_NAT = "AAAAAAAAAA";
    private static final String UPDATED_NAT = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_FIC = "AAAAAAAAAA";
    private static final String UPDATED_NOM_FIC = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_ARRETE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ARRETE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private Bp3HisRepository bp3HisRepository;

    @Autowired
    private Bp3HisService bp3HisService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBp3HisMockMvc;

    private Bp3His bp3His;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bp3His createEntity(EntityManager em) {
        Bp3His bp3His = new Bp3His()
            .dco(DEFAULT_DCO)
            .age(DEFAULT_AGE)
            .dev(DEFAULT_DEV)
            .ncp(DEFAULT_NCP)
            .ope(DEFAULT_OPE)
            .lib(DEFAULT_LIB)
            .mon(DEFAULT_MON)
            .sen(DEFAULT_SEN)
            .pie(DEFAULT_PIE)
            .ncc(DEFAULT_NCC)
            .uti(DEFAULT_UTI)
            .utf(DEFAULT_UTF)
            .nat(DEFAULT_NAT)
            .nomFic(DEFAULT_NOM_FIC)
            .dateArrete(DEFAULT_DATE_ARRETE);
        return bp3His;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bp3His createUpdatedEntity(EntityManager em) {
        Bp3His bp3His = new Bp3His()
            .dco(UPDATED_DCO)
            .age(UPDATED_AGE)
            .dev(UPDATED_DEV)
            .ncp(UPDATED_NCP)
            .ope(UPDATED_OPE)
            .lib(UPDATED_LIB)
            .mon(UPDATED_MON)
            .sen(UPDATED_SEN)
            .pie(UPDATED_PIE)
            .ncc(UPDATED_NCC)
            .uti(UPDATED_UTI)
            .utf(UPDATED_UTF)
            .nat(UPDATED_NAT)
            .nomFic(UPDATED_NOM_FIC)
            .dateArrete(UPDATED_DATE_ARRETE);
        return bp3His;
    }

    @BeforeEach
    public void initTest() {
        bp3His = createEntity(em);
    }

    @Test
    @Transactional
    public void createBp3His() throws Exception {
        int databaseSizeBeforeCreate = bp3HisRepository.findAll().size();
        // Create the Bp3His
        restBp3HisMockMvc.perform(post("/api/bp-3-his")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp3His)))
            .andExpect(status().isCreated());

        // Validate the Bp3His in the database
        List<Bp3His> bp3HisList = bp3HisRepository.findAll();
        assertThat(bp3HisList).hasSize(databaseSizeBeforeCreate + 1);
        Bp3His testBp3His = bp3HisList.get(bp3HisList.size() - 1);
        assertThat(testBp3His.getDco()).isEqualTo(DEFAULT_DCO);
        assertThat(testBp3His.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testBp3His.getDev()).isEqualTo(DEFAULT_DEV);
        assertThat(testBp3His.getNcp()).isEqualTo(DEFAULT_NCP);
        assertThat(testBp3His.getOpe()).isEqualTo(DEFAULT_OPE);
        assertThat(testBp3His.getLib()).isEqualTo(DEFAULT_LIB);
        assertThat(testBp3His.getMon()).isEqualTo(DEFAULT_MON);
        assertThat(testBp3His.getSen()).isEqualTo(DEFAULT_SEN);
        assertThat(testBp3His.getPie()).isEqualTo(DEFAULT_PIE);
        assertThat(testBp3His.getNcc()).isEqualTo(DEFAULT_NCC);
        assertThat(testBp3His.getUti()).isEqualTo(DEFAULT_UTI);
        assertThat(testBp3His.getUtf()).isEqualTo(DEFAULT_UTF);
        assertThat(testBp3His.getNat()).isEqualTo(DEFAULT_NAT);
        assertThat(testBp3His.getNomFic()).isEqualTo(DEFAULT_NOM_FIC);
        assertThat(testBp3His.getDateArrete()).isEqualTo(DEFAULT_DATE_ARRETE);
    }

    @Test
    @Transactional
    public void createBp3HisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bp3HisRepository.findAll().size();

        // Create the Bp3His with an existing ID
        bp3His.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBp3HisMockMvc.perform(post("/api/bp-3-his")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp3His)))
            .andExpect(status().isBadRequest());

        // Validate the Bp3His in the database
        List<Bp3His> bp3HisList = bp3HisRepository.findAll();
        assertThat(bp3HisList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBp3His() throws Exception {
        // Initialize the database
        bp3HisRepository.saveAndFlush(bp3His);

        // Get all the bp3HisList
        restBp3HisMockMvc.perform(get("/api/bp-3-his?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bp3His.getId().intValue())))
            .andExpect(jsonPath("$.[*].dco").value(hasItem(DEFAULT_DCO.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].dev").value(hasItem(DEFAULT_DEV)))
            .andExpect(jsonPath("$.[*].ncp").value(hasItem(DEFAULT_NCP)))
            .andExpect(jsonPath("$.[*].ope").value(hasItem(DEFAULT_OPE)))
            .andExpect(jsonPath("$.[*].lib").value(hasItem(DEFAULT_LIB)))
            .andExpect(jsonPath("$.[*].mon").value(hasItem(DEFAULT_MON)))
            .andExpect(jsonPath("$.[*].sen").value(hasItem(DEFAULT_SEN)))
            .andExpect(jsonPath("$.[*].pie").value(hasItem(DEFAULT_PIE)))
            .andExpect(jsonPath("$.[*].ncc").value(hasItem(DEFAULT_NCC)))
            .andExpect(jsonPath("$.[*].uti").value(hasItem(DEFAULT_UTI)))
            .andExpect(jsonPath("$.[*].utf").value(hasItem(DEFAULT_UTF)))
            .andExpect(jsonPath("$.[*].nat").value(hasItem(DEFAULT_NAT)))
            .andExpect(jsonPath("$.[*].nomFic").value(hasItem(DEFAULT_NOM_FIC)))
            .andExpect(jsonPath("$.[*].dateArrete").value(hasItem(DEFAULT_DATE_ARRETE.toString())));
    }
    
    @Test
    @Transactional
    public void getBp3His() throws Exception {
        // Initialize the database
        bp3HisRepository.saveAndFlush(bp3His);

        // Get the bp3His
        restBp3HisMockMvc.perform(get("/api/bp-3-his/{id}", bp3His.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bp3His.getId().intValue()))
            .andExpect(jsonPath("$.dco").value(DEFAULT_DCO.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.dev").value(DEFAULT_DEV))
            .andExpect(jsonPath("$.ncp").value(DEFAULT_NCP))
            .andExpect(jsonPath("$.ope").value(DEFAULT_OPE))
            .andExpect(jsonPath("$.lib").value(DEFAULT_LIB))
            .andExpect(jsonPath("$.mon").value(DEFAULT_MON))
            .andExpect(jsonPath("$.sen").value(DEFAULT_SEN))
            .andExpect(jsonPath("$.pie").value(DEFAULT_PIE))
            .andExpect(jsonPath("$.ncc").value(DEFAULT_NCC))
            .andExpect(jsonPath("$.uti").value(DEFAULT_UTI))
            .andExpect(jsonPath("$.utf").value(DEFAULT_UTF))
            .andExpect(jsonPath("$.nat").value(DEFAULT_NAT))
            .andExpect(jsonPath("$.nomFic").value(DEFAULT_NOM_FIC))
            .andExpect(jsonPath("$.dateArrete").value(DEFAULT_DATE_ARRETE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBp3His() throws Exception {
        // Get the bp3His
        restBp3HisMockMvc.perform(get("/api/bp-3-his/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBp3His() throws Exception {
        // Initialize the database
        bp3HisService.save(bp3His);

        int databaseSizeBeforeUpdate = bp3HisRepository.findAll().size();

        // Update the bp3His
        Bp3His updatedBp3His = bp3HisRepository.findById(bp3His.getId()).get();
        // Disconnect from session so that the updates on updatedBp3His are not directly saved in db
        em.detach(updatedBp3His);
        updatedBp3His
            .dco(UPDATED_DCO)
            .age(UPDATED_AGE)
            .dev(UPDATED_DEV)
            .ncp(UPDATED_NCP)
            .ope(UPDATED_OPE)
            .lib(UPDATED_LIB)
            .mon(UPDATED_MON)
            .sen(UPDATED_SEN)
            .pie(UPDATED_PIE)
            .ncc(UPDATED_NCC)
            .uti(UPDATED_UTI)
            .utf(UPDATED_UTF)
            .nat(UPDATED_NAT)
            .nomFic(UPDATED_NOM_FIC)
            .dateArrete(UPDATED_DATE_ARRETE);

        restBp3HisMockMvc.perform(put("/api/bp-3-his")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBp3His)))
            .andExpect(status().isOk());

        // Validate the Bp3His in the database
        List<Bp3His> bp3HisList = bp3HisRepository.findAll();
        assertThat(bp3HisList).hasSize(databaseSizeBeforeUpdate);
        Bp3His testBp3His = bp3HisList.get(bp3HisList.size() - 1);
        assertThat(testBp3His.getDco()).isEqualTo(UPDATED_DCO);
        assertThat(testBp3His.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testBp3His.getDev()).isEqualTo(UPDATED_DEV);
        assertThat(testBp3His.getNcp()).isEqualTo(UPDATED_NCP);
        assertThat(testBp3His.getOpe()).isEqualTo(UPDATED_OPE);
        assertThat(testBp3His.getLib()).isEqualTo(UPDATED_LIB);
        assertThat(testBp3His.getMon()).isEqualTo(UPDATED_MON);
        assertThat(testBp3His.getSen()).isEqualTo(UPDATED_SEN);
        assertThat(testBp3His.getPie()).isEqualTo(UPDATED_PIE);
        assertThat(testBp3His.getNcc()).isEqualTo(UPDATED_NCC);
        assertThat(testBp3His.getUti()).isEqualTo(UPDATED_UTI);
        assertThat(testBp3His.getUtf()).isEqualTo(UPDATED_UTF);
        assertThat(testBp3His.getNat()).isEqualTo(UPDATED_NAT);
        assertThat(testBp3His.getNomFic()).isEqualTo(UPDATED_NOM_FIC);
        assertThat(testBp3His.getDateArrete()).isEqualTo(UPDATED_DATE_ARRETE);
    }

    @Test
    @Transactional
    public void updateNonExistingBp3His() throws Exception {
        int databaseSizeBeforeUpdate = bp3HisRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBp3HisMockMvc.perform(put("/api/bp-3-his")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp3His)))
            .andExpect(status().isBadRequest());

        // Validate the Bp3His in the database
        List<Bp3His> bp3HisList = bp3HisRepository.findAll();
        assertThat(bp3HisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBp3His() throws Exception {
        // Initialize the database
        bp3HisService.save(bp3His);

        int databaseSizeBeforeDelete = bp3HisRepository.findAll().size();

        // Delete the bp3His
        restBp3HisMockMvc.perform(delete("/api/bp-3-his/{id}", bp3His.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bp3His> bp3HisList = bp3HisRepository.findAll();
        assertThat(bp3HisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
