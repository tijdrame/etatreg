package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.Bp1His;
import com.emard.api.repository.Bp1HisRepository;
import com.emard.api.service.Bp1HisService;

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
 * Integration tests for the {@link Bp1HisResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class Bp1HisResourceIT {

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
    private Bp1HisRepository bp1HisRepository;

    @Autowired
    private Bp1HisService bp1HisService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBp1HisMockMvc;

    private Bp1His bp1His;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bp1His createEntity(EntityManager em) {
        Bp1His bp1His = new Bp1His()
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
        return bp1His;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bp1His createUpdatedEntity(EntityManager em) {
        Bp1His bp1His = new Bp1His()
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
        return bp1His;
    }

    @BeforeEach
    public void initTest() {
        bp1His = createEntity(em);
    }

    @Test
    @Transactional
    public void createBp1His() throws Exception {
        int databaseSizeBeforeCreate = bp1HisRepository.findAll().size();
        // Create the Bp1His
        restBp1HisMockMvc.perform(post("/api/bp-1-his")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp1His)))
            .andExpect(status().isCreated());

        // Validate the Bp1His in the database
        List<Bp1His> bp1HisList = bp1HisRepository.findAll();
        assertThat(bp1HisList).hasSize(databaseSizeBeforeCreate + 1);
        Bp1His testBp1His = bp1HisList.get(bp1HisList.size() - 1);
        assertThat(testBp1His.getDco()).isEqualTo(DEFAULT_DCO);
        assertThat(testBp1His.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testBp1His.getDev()).isEqualTo(DEFAULT_DEV);
        assertThat(testBp1His.getNcp()).isEqualTo(DEFAULT_NCP);
        assertThat(testBp1His.getOpe()).isEqualTo(DEFAULT_OPE);
        assertThat(testBp1His.getLib()).isEqualTo(DEFAULT_LIB);
        assertThat(testBp1His.getMon()).isEqualTo(DEFAULT_MON);
        assertThat(testBp1His.getSen()).isEqualTo(DEFAULT_SEN);
        assertThat(testBp1His.getPie()).isEqualTo(DEFAULT_PIE);
        assertThat(testBp1His.getNcc()).isEqualTo(DEFAULT_NCC);
        assertThat(testBp1His.getUti()).isEqualTo(DEFAULT_UTI);
        assertThat(testBp1His.getUtf()).isEqualTo(DEFAULT_UTF);
        assertThat(testBp1His.getNat()).isEqualTo(DEFAULT_NAT);
        assertThat(testBp1His.getNomFic()).isEqualTo(DEFAULT_NOM_FIC);
        assertThat(testBp1His.getDateArrete()).isEqualTo(DEFAULT_DATE_ARRETE);
    }

    @Test
    @Transactional
    public void createBp1HisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bp1HisRepository.findAll().size();

        // Create the Bp1His with an existing ID
        bp1His.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBp1HisMockMvc.perform(post("/api/bp-1-his")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp1His)))
            .andExpect(status().isBadRequest());

        // Validate the Bp1His in the database
        List<Bp1His> bp1HisList = bp1HisRepository.findAll();
        assertThat(bp1HisList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBp1His() throws Exception {
        // Initialize the database
        bp1HisRepository.saveAndFlush(bp1His);

        // Get all the bp1HisList
        restBp1HisMockMvc.perform(get("/api/bp-1-his?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bp1His.getId().intValue())))
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
    public void getBp1His() throws Exception {
        // Initialize the database
        bp1HisRepository.saveAndFlush(bp1His);

        // Get the bp1His
        restBp1HisMockMvc.perform(get("/api/bp-1-his/{id}", bp1His.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bp1His.getId().intValue()))
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
    public void getNonExistingBp1His() throws Exception {
        // Get the bp1His
        restBp1HisMockMvc.perform(get("/api/bp-1-his/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBp1His() throws Exception {
        // Initialize the database
        bp1HisService.save(bp1His);

        int databaseSizeBeforeUpdate = bp1HisRepository.findAll().size();

        // Update the bp1His
        Bp1His updatedBp1His = bp1HisRepository.findById(bp1His.getId()).get();
        // Disconnect from session so that the updates on updatedBp1His are not directly saved in db
        em.detach(updatedBp1His);
        updatedBp1His
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

        restBp1HisMockMvc.perform(put("/api/bp-1-his")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBp1His)))
            .andExpect(status().isOk());

        // Validate the Bp1His in the database
        List<Bp1His> bp1HisList = bp1HisRepository.findAll();
        assertThat(bp1HisList).hasSize(databaseSizeBeforeUpdate);
        Bp1His testBp1His = bp1HisList.get(bp1HisList.size() - 1);
        assertThat(testBp1His.getDco()).isEqualTo(UPDATED_DCO);
        assertThat(testBp1His.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testBp1His.getDev()).isEqualTo(UPDATED_DEV);
        assertThat(testBp1His.getNcp()).isEqualTo(UPDATED_NCP);
        assertThat(testBp1His.getOpe()).isEqualTo(UPDATED_OPE);
        assertThat(testBp1His.getLib()).isEqualTo(UPDATED_LIB);
        assertThat(testBp1His.getMon()).isEqualTo(UPDATED_MON);
        assertThat(testBp1His.getSen()).isEqualTo(UPDATED_SEN);
        assertThat(testBp1His.getPie()).isEqualTo(UPDATED_PIE);
        assertThat(testBp1His.getNcc()).isEqualTo(UPDATED_NCC);
        assertThat(testBp1His.getUti()).isEqualTo(UPDATED_UTI);
        assertThat(testBp1His.getUtf()).isEqualTo(UPDATED_UTF);
        assertThat(testBp1His.getNat()).isEqualTo(UPDATED_NAT);
        assertThat(testBp1His.getNomFic()).isEqualTo(UPDATED_NOM_FIC);
        assertThat(testBp1His.getDateArrete()).isEqualTo(UPDATED_DATE_ARRETE);
    }

    @Test
    @Transactional
    public void updateNonExistingBp1His() throws Exception {
        int databaseSizeBeforeUpdate = bp1HisRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBp1HisMockMvc.perform(put("/api/bp-1-his")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp1His)))
            .andExpect(status().isBadRequest());

        // Validate the Bp1His in the database
        List<Bp1His> bp1HisList = bp1HisRepository.findAll();
        assertThat(bp1HisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBp1His() throws Exception {
        // Initialize the database
        bp1HisService.save(bp1His);

        int databaseSizeBeforeDelete = bp1HisRepository.findAll().size();

        // Delete the bp1His
        restBp1HisMockMvc.perform(delete("/api/bp-1-his/{id}", bp1His.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bp1His> bp1HisList = bp1HisRepository.findAll();
        assertThat(bp1HisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
