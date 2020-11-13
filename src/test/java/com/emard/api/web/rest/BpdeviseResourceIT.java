package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.Bpdevise;
import com.emard.api.repository.BpdeviseRepository;
import com.emard.api.service.BpdeviseService;

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
 * Integration tests for the {@link BpdeviseResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BpdeviseResourceIT {

    private static final String DEFAULT_CODE_BQE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_BQE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_CB = "AAAAAAAAAA";
    private static final String UPDATED_CODE_CB = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private BpdeviseRepository bpdeviseRepository;

    @Autowired
    private BpdeviseService bpdeviseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBpdeviseMockMvc;

    private Bpdevise bpdevise;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bpdevise createEntity(EntityManager em) {
        Bpdevise bpdevise = new Bpdevise()
            .codeBqe(DEFAULT_CODE_BQE)
            .codeCb(DEFAULT_CODE_CB)
            .description(DEFAULT_DESCRIPTION);
        return bpdevise;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bpdevise createUpdatedEntity(EntityManager em) {
        Bpdevise bpdevise = new Bpdevise()
            .codeBqe(UPDATED_CODE_BQE)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);
        return bpdevise;
    }

    @BeforeEach
    public void initTest() {
        bpdevise = createEntity(em);
    }

    @Test
    @Transactional
    public void createBpdevise() throws Exception {
        int databaseSizeBeforeCreate = bpdeviseRepository.findAll().size();
        // Create the Bpdevise
        restBpdeviseMockMvc.perform(post("/api/bpdevises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bpdevise)))
            .andExpect(status().isCreated());

        // Validate the Bpdevise in the database
        List<Bpdevise> bpdeviseList = bpdeviseRepository.findAll();
        assertThat(bpdeviseList).hasSize(databaseSizeBeforeCreate + 1);
        Bpdevise testBpdevise = bpdeviseList.get(bpdeviseList.size() - 1);
        assertThat(testBpdevise.getCodeBqe()).isEqualTo(DEFAULT_CODE_BQE);
        assertThat(testBpdevise.getCodeCb()).isEqualTo(DEFAULT_CODE_CB);
        assertThat(testBpdevise.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createBpdeviseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bpdeviseRepository.findAll().size();

        // Create the Bpdevise with an existing ID
        bpdevise.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBpdeviseMockMvc.perform(post("/api/bpdevises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bpdevise)))
            .andExpect(status().isBadRequest());

        // Validate the Bpdevise in the database
        List<Bpdevise> bpdeviseList = bpdeviseRepository.findAll();
        assertThat(bpdeviseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBpdevises() throws Exception {
        // Initialize the database
        bpdeviseRepository.saveAndFlush(bpdevise);

        // Get all the bpdeviseList
        restBpdeviseMockMvc.perform(get("/api/bpdevises?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bpdevise.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeBqe").value(hasItem(DEFAULT_CODE_BQE)))
            .andExpect(jsonPath("$.[*].codeCb").value(hasItem(DEFAULT_CODE_CB)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getBpdevise() throws Exception {
        // Initialize the database
        bpdeviseRepository.saveAndFlush(bpdevise);

        // Get the bpdevise
        restBpdeviseMockMvc.perform(get("/api/bpdevises/{id}", bpdevise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bpdevise.getId().intValue()))
            .andExpect(jsonPath("$.codeBqe").value(DEFAULT_CODE_BQE))
            .andExpect(jsonPath("$.codeCb").value(DEFAULT_CODE_CB))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingBpdevise() throws Exception {
        // Get the bpdevise
        restBpdeviseMockMvc.perform(get("/api/bpdevises/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBpdevise() throws Exception {
        // Initialize the database
        bpdeviseService.save(bpdevise);

        int databaseSizeBeforeUpdate = bpdeviseRepository.findAll().size();

        // Update the bpdevise
        Bpdevise updatedBpdevise = bpdeviseRepository.findById(bpdevise.getId()).get();
        // Disconnect from session so that the updates on updatedBpdevise are not directly saved in db
        em.detach(updatedBpdevise);
        updatedBpdevise
            .codeBqe(UPDATED_CODE_BQE)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);

        restBpdeviseMockMvc.perform(put("/api/bpdevises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBpdevise)))
            .andExpect(status().isOk());

        // Validate the Bpdevise in the database
        List<Bpdevise> bpdeviseList = bpdeviseRepository.findAll();
        assertThat(bpdeviseList).hasSize(databaseSizeBeforeUpdate);
        Bpdevise testBpdevise = bpdeviseList.get(bpdeviseList.size() - 1);
        assertThat(testBpdevise.getCodeBqe()).isEqualTo(UPDATED_CODE_BQE);
        assertThat(testBpdevise.getCodeCb()).isEqualTo(UPDATED_CODE_CB);
        assertThat(testBpdevise.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingBpdevise() throws Exception {
        int databaseSizeBeforeUpdate = bpdeviseRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBpdeviseMockMvc.perform(put("/api/bpdevises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bpdevise)))
            .andExpect(status().isBadRequest());

        // Validate the Bpdevise in the database
        List<Bpdevise> bpdeviseList = bpdeviseRepository.findAll();
        assertThat(bpdeviseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBpdevise() throws Exception {
        // Initialize the database
        bpdeviseService.save(bpdevise);

        int databaseSizeBeforeDelete = bpdeviseRepository.findAll().size();

        // Delete the bpdevise
        restBpdeviseMockMvc.perform(delete("/api/bpdevises/{id}", bpdevise.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bpdevise> bpdeviseList = bpdeviseRepository.findAll();
        assertThat(bpdeviseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
