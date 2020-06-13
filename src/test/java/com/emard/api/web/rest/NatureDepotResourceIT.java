package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.NatureDepot;
import com.emard.api.repository.NatureDepotRepository;
import com.emard.api.service.NatureDepotService;

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
 * Integration tests for the {@link NatureDepotResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NatureDepotResourceIT {

    private static final String DEFAULT_CODE_BDU = "AAAAAAAAAA";
    private static final String UPDATED_CODE_BDU = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_CB = "AAAAAAAAAA";
    private static final String UPDATED_CODE_CB = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private NatureDepotRepository natureDepotRepository;

    @Autowired
    private NatureDepotService natureDepotService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNatureDepotMockMvc;

    private NatureDepot natureDepot;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NatureDepot createEntity(EntityManager em) {
        NatureDepot natureDepot = new NatureDepot()
            .codeBdu(DEFAULT_CODE_BDU)
            .codeCb(DEFAULT_CODE_CB)
            .description(DEFAULT_DESCRIPTION);
        return natureDepot;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NatureDepot createUpdatedEntity(EntityManager em) {
        NatureDepot natureDepot = new NatureDepot()
            .codeBdu(UPDATED_CODE_BDU)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);
        return natureDepot;
    }

    @BeforeEach
    public void initTest() {
        natureDepot = createEntity(em);
    }

    @Test
    @Transactional
    public void createNatureDepot() throws Exception {
        int databaseSizeBeforeCreate = natureDepotRepository.findAll().size();
        // Create the NatureDepot
        restNatureDepotMockMvc.perform(post("/api/nature-depots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureDepot)))
            .andExpect(status().isCreated());

        // Validate the NatureDepot in the database
        List<NatureDepot> natureDepotList = natureDepotRepository.findAll();
        assertThat(natureDepotList).hasSize(databaseSizeBeforeCreate + 1);
        NatureDepot testNatureDepot = natureDepotList.get(natureDepotList.size() - 1);
        assertThat(testNatureDepot.getCodeBdu()).isEqualTo(DEFAULT_CODE_BDU);
        assertThat(testNatureDepot.getCodeCb()).isEqualTo(DEFAULT_CODE_CB);
        assertThat(testNatureDepot.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createNatureDepotWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = natureDepotRepository.findAll().size();

        // Create the NatureDepot with an existing ID
        natureDepot.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNatureDepotMockMvc.perform(post("/api/nature-depots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureDepot)))
            .andExpect(status().isBadRequest());

        // Validate the NatureDepot in the database
        List<NatureDepot> natureDepotList = natureDepotRepository.findAll();
        assertThat(natureDepotList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeBduIsRequired() throws Exception {
        int databaseSizeBeforeTest = natureDepotRepository.findAll().size();
        // set the field null
        natureDepot.setCodeBdu(null);

        // Create the NatureDepot, which fails.


        restNatureDepotMockMvc.perform(post("/api/nature-depots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureDepot)))
            .andExpect(status().isBadRequest());

        List<NatureDepot> natureDepotList = natureDepotRepository.findAll();
        assertThat(natureDepotList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeCbIsRequired() throws Exception {
        int databaseSizeBeforeTest = natureDepotRepository.findAll().size();
        // set the field null
        natureDepot.setCodeCb(null);

        // Create the NatureDepot, which fails.


        restNatureDepotMockMvc.perform(post("/api/nature-depots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureDepot)))
            .andExpect(status().isBadRequest());

        List<NatureDepot> natureDepotList = natureDepotRepository.findAll();
        assertThat(natureDepotList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNatureDepots() throws Exception {
        // Initialize the database
        natureDepotRepository.saveAndFlush(natureDepot);

        // Get all the natureDepotList
        restNatureDepotMockMvc.perform(get("/api/nature-depots?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(natureDepot.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeBdu").value(hasItem(DEFAULT_CODE_BDU)))
            .andExpect(jsonPath("$.[*].codeCb").value(hasItem(DEFAULT_CODE_CB)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getNatureDepot() throws Exception {
        // Initialize the database
        natureDepotRepository.saveAndFlush(natureDepot);

        // Get the natureDepot
        restNatureDepotMockMvc.perform(get("/api/nature-depots/{id}", natureDepot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(natureDepot.getId().intValue()))
            .andExpect(jsonPath("$.codeBdu").value(DEFAULT_CODE_BDU))
            .andExpect(jsonPath("$.codeCb").value(DEFAULT_CODE_CB))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingNatureDepot() throws Exception {
        // Get the natureDepot
        restNatureDepotMockMvc.perform(get("/api/nature-depots/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNatureDepot() throws Exception {
        // Initialize the database
        natureDepotService.save(natureDepot);

        int databaseSizeBeforeUpdate = natureDepotRepository.findAll().size();

        // Update the natureDepot
        NatureDepot updatedNatureDepot = natureDepotRepository.findById(natureDepot.getId()).get();
        // Disconnect from session so that the updates on updatedNatureDepot are not directly saved in db
        em.detach(updatedNatureDepot);
        updatedNatureDepot
            .codeBdu(UPDATED_CODE_BDU)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);

        restNatureDepotMockMvc.perform(put("/api/nature-depots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedNatureDepot)))
            .andExpect(status().isOk());

        // Validate the NatureDepot in the database
        List<NatureDepot> natureDepotList = natureDepotRepository.findAll();
        assertThat(natureDepotList).hasSize(databaseSizeBeforeUpdate);
        NatureDepot testNatureDepot = natureDepotList.get(natureDepotList.size() - 1);
        assertThat(testNatureDepot.getCodeBdu()).isEqualTo(UPDATED_CODE_BDU);
        assertThat(testNatureDepot.getCodeCb()).isEqualTo(UPDATED_CODE_CB);
        assertThat(testNatureDepot.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingNatureDepot() throws Exception {
        int databaseSizeBeforeUpdate = natureDepotRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNatureDepotMockMvc.perform(put("/api/nature-depots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureDepot)))
            .andExpect(status().isBadRequest());

        // Validate the NatureDepot in the database
        List<NatureDepot> natureDepotList = natureDepotRepository.findAll();
        assertThat(natureDepotList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNatureDepot() throws Exception {
        // Initialize the database
        natureDepotService.save(natureDepot);

        int databaseSizeBeforeDelete = natureDepotRepository.findAll().size();

        // Delete the natureDepot
        restNatureDepotMockMvc.perform(delete("/api/nature-depots/{id}", natureDepot.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NatureDepot> natureDepotList = natureDepotRepository.findAll();
        assertThat(natureDepotList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
