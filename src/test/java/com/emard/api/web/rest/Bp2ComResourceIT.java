package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.Bp2Com;
import com.emard.api.repository.Bp2ComRepository;
import com.emard.api.service.Bp2ComService;

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
 * Integration tests for the {@link Bp2ComResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class Bp2ComResourceIT {

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
    private Bp2ComRepository bp2ComRepository;

    @Autowired
    private Bp2ComService bp2ComService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBp2ComMockMvc;

    private Bp2Com bp2Com;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bp2Com createEntity(EntityManager em) {
        Bp2Com bp2Com = new Bp2Com()
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
        return bp2Com;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bp2Com createUpdatedEntity(EntityManager em) {
        Bp2Com bp2Com = new Bp2Com()
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
        return bp2Com;
    }

    @BeforeEach
    public void initTest() {
        bp2Com = createEntity(em);
    }

    @Test
    @Transactional
    public void createBp2Com() throws Exception {
        int databaseSizeBeforeCreate = bp2ComRepository.findAll().size();
        // Create the Bp2Com
        restBp2ComMockMvc.perform(post("/api/bp-2-coms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp2Com)))
            .andExpect(status().isCreated());

        // Validate the Bp2Com in the database
        List<Bp2Com> bp2ComList = bp2ComRepository.findAll();
        assertThat(bp2ComList).hasSize(databaseSizeBeforeCreate + 1);
        Bp2Com testBp2Com = bp2ComList.get(bp2ComList.size() - 1);
        assertThat(testBp2Com.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testBp2Com.getDev()).isEqualTo(DEFAULT_DEV);
        assertThat(testBp2Com.getNcp()).isEqualTo(DEFAULT_NCP);
        assertThat(testBp2Com.getInti()).isEqualTo(DEFAULT_INTI);
        assertThat(testBp2Com.getSde()).isEqualTo(DEFAULT_SDE);
        assertThat(testBp2Com.getCha()).isEqualTo(DEFAULT_CHA);
        assertThat(testBp2Com.getDou()).isEqualTo(DEFAULT_DOU);
        assertThat(testBp2Com.getDdd()).isEqualTo(DEFAULT_DDD);
        assertThat(testBp2Com.getDdc()).isEqualTo(DEFAULT_DDC);
        assertThat(testBp2Com.getNomFic()).isEqualTo(DEFAULT_NOM_FIC);
        assertThat(testBp2Com.getDateArrete()).isEqualTo(DEFAULT_DATE_ARRETE);
    }

    @Test
    @Transactional
    public void createBp2ComWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bp2ComRepository.findAll().size();

        // Create the Bp2Com with an existing ID
        bp2Com.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBp2ComMockMvc.perform(post("/api/bp-2-coms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp2Com)))
            .andExpect(status().isBadRequest());

        // Validate the Bp2Com in the database
        List<Bp2Com> bp2ComList = bp2ComRepository.findAll();
        assertThat(bp2ComList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBp2Coms() throws Exception {
        // Initialize the database
        bp2ComRepository.saveAndFlush(bp2Com);

        // Get all the bp2ComList
        restBp2ComMockMvc.perform(get("/api/bp-2-coms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bp2Com.getId().intValue())))
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
    public void getBp2Com() throws Exception {
        // Initialize the database
        bp2ComRepository.saveAndFlush(bp2Com);

        // Get the bp2Com
        restBp2ComMockMvc.perform(get("/api/bp-2-coms/{id}", bp2Com.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bp2Com.getId().intValue()))
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
    public void getNonExistingBp2Com() throws Exception {
        // Get the bp2Com
        restBp2ComMockMvc.perform(get("/api/bp-2-coms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBp2Com() throws Exception {
        // Initialize the database
        bp2ComService.save(bp2Com);

        int databaseSizeBeforeUpdate = bp2ComRepository.findAll().size();

        // Update the bp2Com
        Bp2Com updatedBp2Com = bp2ComRepository.findById(bp2Com.getId()).get();
        // Disconnect from session so that the updates on updatedBp2Com are not directly saved in db
        em.detach(updatedBp2Com);
        updatedBp2Com
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

        restBp2ComMockMvc.perform(put("/api/bp-2-coms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBp2Com)))
            .andExpect(status().isOk());

        // Validate the Bp2Com in the database
        List<Bp2Com> bp2ComList = bp2ComRepository.findAll();
        assertThat(bp2ComList).hasSize(databaseSizeBeforeUpdate);
        Bp2Com testBp2Com = bp2ComList.get(bp2ComList.size() - 1);
        assertThat(testBp2Com.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testBp2Com.getDev()).isEqualTo(UPDATED_DEV);
        assertThat(testBp2Com.getNcp()).isEqualTo(UPDATED_NCP);
        assertThat(testBp2Com.getInti()).isEqualTo(UPDATED_INTI);
        assertThat(testBp2Com.getSde()).isEqualTo(UPDATED_SDE);
        assertThat(testBp2Com.getCha()).isEqualTo(UPDATED_CHA);
        assertThat(testBp2Com.getDou()).isEqualTo(UPDATED_DOU);
        assertThat(testBp2Com.getDdd()).isEqualTo(UPDATED_DDD);
        assertThat(testBp2Com.getDdc()).isEqualTo(UPDATED_DDC);
        assertThat(testBp2Com.getNomFic()).isEqualTo(UPDATED_NOM_FIC);
        assertThat(testBp2Com.getDateArrete()).isEqualTo(UPDATED_DATE_ARRETE);
    }

    @Test
    @Transactional
    public void updateNonExistingBp2Com() throws Exception {
        int databaseSizeBeforeUpdate = bp2ComRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBp2ComMockMvc.perform(put("/api/bp-2-coms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp2Com)))
            .andExpect(status().isBadRequest());

        // Validate the Bp2Com in the database
        List<Bp2Com> bp2ComList = bp2ComRepository.findAll();
        assertThat(bp2ComList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBp2Com() throws Exception {
        // Initialize the database
        bp2ComService.save(bp2Com);

        int databaseSizeBeforeDelete = bp2ComRepository.findAll().size();

        // Delete the bp2Com
        restBp2ComMockMvc.perform(delete("/api/bp-2-coms/{id}", bp2Com.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bp2Com> bp2ComList = bp2ComRepository.findAll();
        assertThat(bp2ComList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
