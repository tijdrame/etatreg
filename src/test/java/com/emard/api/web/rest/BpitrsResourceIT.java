package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.Bpitrs;
import com.emard.api.repository.BpitrsRepository;
import com.emard.api.service.BpitrsService;

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
 * Integration tests for the {@link BpitrsResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BpitrsResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_CB = "AAAAAAAAAA";
    private static final String UPDATED_CODE_CB = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_BQE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_BQE = "BBBBBBBBBB";

    @Autowired
    private BpitrsRepository bpitrsRepository;

    @Autowired
    private BpitrsService bpitrsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBpitrsMockMvc;

    private Bpitrs bpitrs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bpitrs createEntity(EntityManager em) {
        Bpitrs bpitrs = new Bpitrs()
            .description(DEFAULT_DESCRIPTION)
            .codeCb(DEFAULT_CODE_CB)
            .codeBqe(DEFAULT_CODE_BQE);
        return bpitrs;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bpitrs createUpdatedEntity(EntityManager em) {
        Bpitrs bpitrs = new Bpitrs()
            .description(UPDATED_DESCRIPTION)
            .codeCb(UPDATED_CODE_CB)
            .codeBqe(UPDATED_CODE_BQE);
        return bpitrs;
    }

    @BeforeEach
    public void initTest() {
        bpitrs = createEntity(em);
    }

    @Test
    @Transactional
    public void createBpitrs() throws Exception {
        int databaseSizeBeforeCreate = bpitrsRepository.findAll().size();
        // Create the Bpitrs
        restBpitrsMockMvc.perform(post("/api/bpitrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bpitrs)))
            .andExpect(status().isCreated());

        // Validate the Bpitrs in the database
        List<Bpitrs> bpitrsList = bpitrsRepository.findAll();
        assertThat(bpitrsList).hasSize(databaseSizeBeforeCreate + 1);
        Bpitrs testBpitrs = bpitrsList.get(bpitrsList.size() - 1);
        assertThat(testBpitrs.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBpitrs.getCodeCb()).isEqualTo(DEFAULT_CODE_CB);
        assertThat(testBpitrs.getCodeBqe()).isEqualTo(DEFAULT_CODE_BQE);
    }

    @Test
    @Transactional
    public void createBpitrsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bpitrsRepository.findAll().size();

        // Create the Bpitrs with an existing ID
        bpitrs.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBpitrsMockMvc.perform(post("/api/bpitrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bpitrs)))
            .andExpect(status().isBadRequest());

        // Validate the Bpitrs in the database
        List<Bpitrs> bpitrsList = bpitrsRepository.findAll();
        assertThat(bpitrsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBpitrs() throws Exception {
        // Initialize the database
        bpitrsRepository.saveAndFlush(bpitrs);

        // Get all the bpitrsList
        restBpitrsMockMvc.perform(get("/api/bpitrs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bpitrs.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].codeCb").value(hasItem(DEFAULT_CODE_CB)))
            .andExpect(jsonPath("$.[*].codeBqe").value(hasItem(DEFAULT_CODE_BQE)));
    }
    
    @Test
    @Transactional
    public void getBpitrs() throws Exception {
        // Initialize the database
        bpitrsRepository.saveAndFlush(bpitrs);

        // Get the bpitrs
        restBpitrsMockMvc.perform(get("/api/bpitrs/{id}", bpitrs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bpitrs.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.codeCb").value(DEFAULT_CODE_CB))
            .andExpect(jsonPath("$.codeBqe").value(DEFAULT_CODE_BQE));
    }
    @Test
    @Transactional
    public void getNonExistingBpitrs() throws Exception {
        // Get the bpitrs
        restBpitrsMockMvc.perform(get("/api/bpitrs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBpitrs() throws Exception {
        // Initialize the database
        bpitrsService.save(bpitrs);

        int databaseSizeBeforeUpdate = bpitrsRepository.findAll().size();

        // Update the bpitrs
        Bpitrs updatedBpitrs = bpitrsRepository.findById(bpitrs.getId()).get();
        // Disconnect from session so that the updates on updatedBpitrs are not directly saved in db
        em.detach(updatedBpitrs);
        updatedBpitrs
            .description(UPDATED_DESCRIPTION)
            .codeCb(UPDATED_CODE_CB)
            .codeBqe(UPDATED_CODE_BQE);

        restBpitrsMockMvc.perform(put("/api/bpitrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBpitrs)))
            .andExpect(status().isOk());

        // Validate the Bpitrs in the database
        List<Bpitrs> bpitrsList = bpitrsRepository.findAll();
        assertThat(bpitrsList).hasSize(databaseSizeBeforeUpdate);
        Bpitrs testBpitrs = bpitrsList.get(bpitrsList.size() - 1);
        assertThat(testBpitrs.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBpitrs.getCodeCb()).isEqualTo(UPDATED_CODE_CB);
        assertThat(testBpitrs.getCodeBqe()).isEqualTo(UPDATED_CODE_BQE);
    }

    @Test
    @Transactional
    public void updateNonExistingBpitrs() throws Exception {
        int databaseSizeBeforeUpdate = bpitrsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBpitrsMockMvc.perform(put("/api/bpitrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bpitrs)))
            .andExpect(status().isBadRequest());

        // Validate the Bpitrs in the database
        List<Bpitrs> bpitrsList = bpitrsRepository.findAll();
        assertThat(bpitrsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBpitrs() throws Exception {
        // Initialize the database
        bpitrsService.save(bpitrs);

        int databaseSizeBeforeDelete = bpitrsRepository.findAll().size();

        // Delete the bpitrs
        restBpitrsMockMvc.perform(delete("/api/bpitrs/{id}", bpitrs.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bpitrs> bpitrsList = bpitrsRepository.findAll();
        assertThat(bpitrsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
