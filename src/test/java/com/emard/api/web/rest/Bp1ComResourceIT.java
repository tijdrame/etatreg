package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.Bp1Com;
import com.emard.api.repository.Bp1ComRepository;
import com.emard.api.service.Bp1ComService;

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
 * Integration tests for the {@link Bp1ComResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class Bp1ComResourceIT {

    private static final String DEFAULT_AGE = "AAAAAAAAAA";
    private static final String UPDATED_AGE = "BBBBBBBBBB";

    private static final String DEFAULT_DEV = "AAAAAAAAAA";
    private static final String UPDATED_DEV = "BBBBBBBBBB";

    private static final String DEFAULT_NCP = "AAAAAAAAAA";
    private static final String UPDATED_NCP = "BBBBBBBBBB";

    private static final String DEFAULT_INTI = "AAAAAAAAAA";
    private static final String UPDATED_INTI = "BBBBBBBBBB";

    private static final Double DEFAULT_SDE = 1d;
    private static final Double UPDATED_SDE = 2d;

    private static final String DEFAULT_CHA = "AAAAAAAAAA";
    private static final String UPDATED_CHA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DOU = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOU = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DDD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DDD = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DDC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DDC = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NOM_FIC = "AAAAAAAAAA";
    private static final String UPDATED_NOM_FIC = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_ARRETE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ARRETE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private Bp1ComRepository bp1ComRepository;

    @Autowired
    private Bp1ComService bp1ComService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBp1ComMockMvc;

    private Bp1Com bp1Com;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bp1Com createEntity(EntityManager em) {
        Bp1Com bp1Com = new Bp1Com()
            .age(DEFAULT_AGE)
            .dev(DEFAULT_DEV)
            .ncp(DEFAULT_NCP)
            .inti(DEFAULT_INTI)
            .sde(DEFAULT_SDE)
            .cha(DEFAULT_CHA)
            .dou(DEFAULT_DOU)
            .ddd(DEFAULT_DDD)
            .ddc(DEFAULT_DDC)
            .nomFic(DEFAULT_NOM_FIC)
            .dateArrete(DEFAULT_DATE_ARRETE);
        return bp1Com;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bp1Com createUpdatedEntity(EntityManager em) {
        Bp1Com bp1Com = new Bp1Com()
            .age(UPDATED_AGE)
            .dev(UPDATED_DEV)
            .ncp(UPDATED_NCP)
            .inti(UPDATED_INTI)
            .sde(UPDATED_SDE)
            .cha(UPDATED_CHA)
            .dou(UPDATED_DOU)
            .ddd(UPDATED_DDD)
            .ddc(UPDATED_DDC)
            .nomFic(UPDATED_NOM_FIC)
            .dateArrete(UPDATED_DATE_ARRETE);
        return bp1Com;
    }

    @BeforeEach
    public void initTest() {
        bp1Com = createEntity(em);
    }

    @Test
    @Transactional
    public void createBp1Com() throws Exception {
        int databaseSizeBeforeCreate = bp1ComRepository.findAll().size();
        // Create the Bp1Com
        restBp1ComMockMvc.perform(post("/api/bp-1-coms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp1Com)))
            .andExpect(status().isCreated());

        // Validate the Bp1Com in the database
        List<Bp1Com> bp1ComList = bp1ComRepository.findAll();
        assertThat(bp1ComList).hasSize(databaseSizeBeforeCreate + 1);
        Bp1Com testBp1Com = bp1ComList.get(bp1ComList.size() - 1);
        assertThat(testBp1Com.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testBp1Com.getDev()).isEqualTo(DEFAULT_DEV);
        assertThat(testBp1Com.getNcp()).isEqualTo(DEFAULT_NCP);
        assertThat(testBp1Com.getInti()).isEqualTo(DEFAULT_INTI);
        assertThat(testBp1Com.getSde()).isEqualTo(DEFAULT_SDE);
        assertThat(testBp1Com.getCha()).isEqualTo(DEFAULT_CHA);
        assertThat(testBp1Com.getDou()).isEqualTo(DEFAULT_DOU);
        assertThat(testBp1Com.getDdd()).isEqualTo(DEFAULT_DDD);
        assertThat(testBp1Com.getDdc()).isEqualTo(DEFAULT_DDC);
        assertThat(testBp1Com.getNomFic()).isEqualTo(DEFAULT_NOM_FIC);
        assertThat(testBp1Com.getDateArrete()).isEqualTo(DEFAULT_DATE_ARRETE);
    }

    @Test
    @Transactional
    public void createBp1ComWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bp1ComRepository.findAll().size();

        // Create the Bp1Com with an existing ID
        bp1Com.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBp1ComMockMvc.perform(post("/api/bp-1-coms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp1Com)))
            .andExpect(status().isBadRequest());

        // Validate the Bp1Com in the database
        List<Bp1Com> bp1ComList = bp1ComRepository.findAll();
        assertThat(bp1ComList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBp1Coms() throws Exception {
        // Initialize the database
        bp1ComRepository.saveAndFlush(bp1Com);

        // Get all the bp1ComList
        restBp1ComMockMvc.perform(get("/api/bp-1-coms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bp1Com.getId().intValue())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].dev").value(hasItem(DEFAULT_DEV)))
            .andExpect(jsonPath("$.[*].ncp").value(hasItem(DEFAULT_NCP)))
            .andExpect(jsonPath("$.[*].inti").value(hasItem(DEFAULT_INTI)))
            .andExpect(jsonPath("$.[*].sde").value(hasItem(DEFAULT_SDE)))
            .andExpect(jsonPath("$.[*].cha").value(hasItem(DEFAULT_CHA)))
            .andExpect(jsonPath("$.[*].dou").value(hasItem(DEFAULT_DOU.toString())))
            .andExpect(jsonPath("$.[*].ddd").value(hasItem(DEFAULT_DDD.toString())))
            .andExpect(jsonPath("$.[*].ddc").value(hasItem(DEFAULT_DDC.toString())))
            .andExpect(jsonPath("$.[*].nomFic").value(hasItem(DEFAULT_NOM_FIC)))
            .andExpect(jsonPath("$.[*].dateArrete").value(hasItem(DEFAULT_DATE_ARRETE.toString())));
    }
    
    @Test
    @Transactional
    public void getBp1Com() throws Exception {
        // Initialize the database
        bp1ComRepository.saveAndFlush(bp1Com);

        // Get the bp1Com
        restBp1ComMockMvc.perform(get("/api/bp-1-coms/{id}", bp1Com.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bp1Com.getId().intValue()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.dev").value(DEFAULT_DEV))
            .andExpect(jsonPath("$.ncp").value(DEFAULT_NCP))
            .andExpect(jsonPath("$.inti").value(DEFAULT_INTI))
            .andExpect(jsonPath("$.sde").value(DEFAULT_SDE))
            .andExpect(jsonPath("$.cha").value(DEFAULT_CHA))
            .andExpect(jsonPath("$.dou").value(DEFAULT_DOU.toString()))
            .andExpect(jsonPath("$.ddd").value(DEFAULT_DDD.toString()))
            .andExpect(jsonPath("$.ddc").value(DEFAULT_DDC.toString()))
            .andExpect(jsonPath("$.nomFic").value(DEFAULT_NOM_FIC))
            .andExpect(jsonPath("$.dateArrete").value(DEFAULT_DATE_ARRETE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBp1Com() throws Exception {
        // Get the bp1Com
        restBp1ComMockMvc.perform(get("/api/bp-1-coms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBp1Com() throws Exception {
        // Initialize the database
        bp1ComService.save(bp1Com);

        int databaseSizeBeforeUpdate = bp1ComRepository.findAll().size();

        // Update the bp1Com
        Bp1Com updatedBp1Com = bp1ComRepository.findById(bp1Com.getId()).get();
        // Disconnect from session so that the updates on updatedBp1Com are not directly saved in db
        em.detach(updatedBp1Com);
        updatedBp1Com
            .age(UPDATED_AGE)
            .dev(UPDATED_DEV)
            .ncp(UPDATED_NCP)
            .inti(UPDATED_INTI)
            .sde(UPDATED_SDE)
            .cha(UPDATED_CHA)
            .dou(UPDATED_DOU)
            .ddd(UPDATED_DDD)
            .ddc(UPDATED_DDC)
            .nomFic(UPDATED_NOM_FIC)
            .dateArrete(UPDATED_DATE_ARRETE);

        restBp1ComMockMvc.perform(put("/api/bp-1-coms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBp1Com)))
            .andExpect(status().isOk());

        // Validate the Bp1Com in the database
        List<Bp1Com> bp1ComList = bp1ComRepository.findAll();
        assertThat(bp1ComList).hasSize(databaseSizeBeforeUpdate);
        Bp1Com testBp1Com = bp1ComList.get(bp1ComList.size() - 1);
        assertThat(testBp1Com.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testBp1Com.getDev()).isEqualTo(UPDATED_DEV);
        assertThat(testBp1Com.getNcp()).isEqualTo(UPDATED_NCP);
        assertThat(testBp1Com.getInti()).isEqualTo(UPDATED_INTI);
        assertThat(testBp1Com.getSde()).isEqualTo(UPDATED_SDE);
        assertThat(testBp1Com.getCha()).isEqualTo(UPDATED_CHA);
        assertThat(testBp1Com.getDou()).isEqualTo(UPDATED_DOU);
        assertThat(testBp1Com.getDdd()).isEqualTo(UPDATED_DDD);
        assertThat(testBp1Com.getDdc()).isEqualTo(UPDATED_DDC);
        assertThat(testBp1Com.getNomFic()).isEqualTo(UPDATED_NOM_FIC);
        assertThat(testBp1Com.getDateArrete()).isEqualTo(UPDATED_DATE_ARRETE);
    }

    @Test
    @Transactional
    public void updateNonExistingBp1Com() throws Exception {
        int databaseSizeBeforeUpdate = bp1ComRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBp1ComMockMvc.perform(put("/api/bp-1-coms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp1Com)))
            .andExpect(status().isBadRequest());

        // Validate the Bp1Com in the database
        List<Bp1Com> bp1ComList = bp1ComRepository.findAll();
        assertThat(bp1ComList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBp1Com() throws Exception {
        // Initialize the database
        bp1ComService.save(bp1Com);

        int databaseSizeBeforeDelete = bp1ComRepository.findAll().size();

        // Delete the bp1Com
        restBp1ComMockMvc.perform(delete("/api/bp-1-coms/{id}", bp1Com.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bp1Com> bp1ComList = bp1ComRepository.findAll();
        assertThat(bp1ComList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
