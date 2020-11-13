package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.Bpnaema;
import com.emard.api.repository.BpnaemaRepository;
import com.emard.api.service.BpnaemaService;

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
 * Integration tests for the {@link BpnaemaResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BpnaemaResourceIT {

    private static final String DEFAULT_CODE_BQE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_BQE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_CB = "AAAAAAAAAA";
    private static final String UPDATED_CODE_CB = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private BpnaemaRepository bpnaemaRepository;

    @Autowired
    private BpnaemaService bpnaemaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBpnaemaMockMvc;

    private Bpnaema bpnaema;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bpnaema createEntity(EntityManager em) {
        Bpnaema bpnaema = new Bpnaema()
            .codeBqe(DEFAULT_CODE_BQE)
            .codeCb(DEFAULT_CODE_CB)
            .description(DEFAULT_DESCRIPTION);
        return bpnaema;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bpnaema createUpdatedEntity(EntityManager em) {
        Bpnaema bpnaema = new Bpnaema()
            .codeBqe(UPDATED_CODE_BQE)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);
        return bpnaema;
    }

    @BeforeEach
    public void initTest() {
        bpnaema = createEntity(em);
    }

    @Test
    @Transactional
    public void createBpnaema() throws Exception {
        int databaseSizeBeforeCreate = bpnaemaRepository.findAll().size();
        // Create the Bpnaema
        restBpnaemaMockMvc.perform(post("/api/bpnaemas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bpnaema)))
            .andExpect(status().isCreated());

        // Validate the Bpnaema in the database
        List<Bpnaema> bpnaemaList = bpnaemaRepository.findAll();
        assertThat(bpnaemaList).hasSize(databaseSizeBeforeCreate + 1);
        Bpnaema testBpnaema = bpnaemaList.get(bpnaemaList.size() - 1);
        assertThat(testBpnaema.getCodeBqe()).isEqualTo(DEFAULT_CODE_BQE);
        assertThat(testBpnaema.getCodeCb()).isEqualTo(DEFAULT_CODE_CB);
        assertThat(testBpnaema.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createBpnaemaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bpnaemaRepository.findAll().size();

        // Create the Bpnaema with an existing ID
        bpnaema.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBpnaemaMockMvc.perform(post("/api/bpnaemas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bpnaema)))
            .andExpect(status().isBadRequest());

        // Validate the Bpnaema in the database
        List<Bpnaema> bpnaemaList = bpnaemaRepository.findAll();
        assertThat(bpnaemaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBpnaemas() throws Exception {
        // Initialize the database
        bpnaemaRepository.saveAndFlush(bpnaema);

        // Get all the bpnaemaList
        restBpnaemaMockMvc.perform(get("/api/bpnaemas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bpnaema.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeBqe").value(hasItem(DEFAULT_CODE_BQE)))
            .andExpect(jsonPath("$.[*].codeCb").value(hasItem(DEFAULT_CODE_CB)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getBpnaema() throws Exception {
        // Initialize the database
        bpnaemaRepository.saveAndFlush(bpnaema);

        // Get the bpnaema
        restBpnaemaMockMvc.perform(get("/api/bpnaemas/{id}", bpnaema.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bpnaema.getId().intValue()))
            .andExpect(jsonPath("$.codeBqe").value(DEFAULT_CODE_BQE))
            .andExpect(jsonPath("$.codeCb").value(DEFAULT_CODE_CB))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingBpnaema() throws Exception {
        // Get the bpnaema
        restBpnaemaMockMvc.perform(get("/api/bpnaemas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBpnaema() throws Exception {
        // Initialize the database
        bpnaemaService.save(bpnaema);

        int databaseSizeBeforeUpdate = bpnaemaRepository.findAll().size();

        // Update the bpnaema
        Bpnaema updatedBpnaema = bpnaemaRepository.findById(bpnaema.getId()).get();
        // Disconnect from session so that the updates on updatedBpnaema are not directly saved in db
        em.detach(updatedBpnaema);
        updatedBpnaema
            .codeBqe(UPDATED_CODE_BQE)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);

        restBpnaemaMockMvc.perform(put("/api/bpnaemas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBpnaema)))
            .andExpect(status().isOk());

        // Validate the Bpnaema in the database
        List<Bpnaema> bpnaemaList = bpnaemaRepository.findAll();
        assertThat(bpnaemaList).hasSize(databaseSizeBeforeUpdate);
        Bpnaema testBpnaema = bpnaemaList.get(bpnaemaList.size() - 1);
        assertThat(testBpnaema.getCodeBqe()).isEqualTo(UPDATED_CODE_BQE);
        assertThat(testBpnaema.getCodeCb()).isEqualTo(UPDATED_CODE_CB);
        assertThat(testBpnaema.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingBpnaema() throws Exception {
        int databaseSizeBeforeUpdate = bpnaemaRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBpnaemaMockMvc.perform(put("/api/bpnaemas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bpnaema)))
            .andExpect(status().isBadRequest());

        // Validate the Bpnaema in the database
        List<Bpnaema> bpnaemaList = bpnaemaRepository.findAll();
        assertThat(bpnaemaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBpnaema() throws Exception {
        // Initialize the database
        bpnaemaService.save(bpnaema);

        int databaseSizeBeforeDelete = bpnaemaRepository.findAll().size();

        // Delete the bpnaema
        restBpnaemaMockMvc.perform(delete("/api/bpnaemas/{id}", bpnaema.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bpnaema> bpnaemaList = bpnaemaRepository.findAll();
        assertThat(bpnaemaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
