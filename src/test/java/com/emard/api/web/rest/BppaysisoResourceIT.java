package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.Bppaysiso;
import com.emard.api.repository.BppaysisoRepository;
import com.emard.api.service.BppaysisoService;

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
 * Integration tests for the {@link BppaysisoResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BppaysisoResourceIT {

    private static final String DEFAULT_CODE_BQE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_BQE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_CB = "AAAAAAAAAA";
    private static final String UPDATED_CODE_CB = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private BppaysisoRepository bppaysisoRepository;

    @Autowired
    private BppaysisoService bppaysisoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBppaysisoMockMvc;

    private Bppaysiso bppaysiso;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bppaysiso createEntity(EntityManager em) {
        Bppaysiso bppaysiso = new Bppaysiso()
            .codeBqe(DEFAULT_CODE_BQE)
            .codeCb(DEFAULT_CODE_CB)
            .description(DEFAULT_DESCRIPTION);
        return bppaysiso;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bppaysiso createUpdatedEntity(EntityManager em) {
        Bppaysiso bppaysiso = new Bppaysiso()
            .codeBqe(UPDATED_CODE_BQE)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);
        return bppaysiso;
    }

    @BeforeEach
    public void initTest() {
        bppaysiso = createEntity(em);
    }

    @Test
    @Transactional
    public void createBppaysiso() throws Exception {
        int databaseSizeBeforeCreate = bppaysisoRepository.findAll().size();
        // Create the Bppaysiso
        restBppaysisoMockMvc.perform(post("/api/bppaysisos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bppaysiso)))
            .andExpect(status().isCreated());

        // Validate the Bppaysiso in the database
        List<Bppaysiso> bppaysisoList = bppaysisoRepository.findAll();
        assertThat(bppaysisoList).hasSize(databaseSizeBeforeCreate + 1);
        Bppaysiso testBppaysiso = bppaysisoList.get(bppaysisoList.size() - 1);
        assertThat(testBppaysiso.getCodeBqe()).isEqualTo(DEFAULT_CODE_BQE);
        assertThat(testBppaysiso.getCodeCb()).isEqualTo(DEFAULT_CODE_CB);
        assertThat(testBppaysiso.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createBppaysisoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bppaysisoRepository.findAll().size();

        // Create the Bppaysiso with an existing ID
        bppaysiso.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBppaysisoMockMvc.perform(post("/api/bppaysisos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bppaysiso)))
            .andExpect(status().isBadRequest());

        // Validate the Bppaysiso in the database
        List<Bppaysiso> bppaysisoList = bppaysisoRepository.findAll();
        assertThat(bppaysisoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBppaysisos() throws Exception {
        // Initialize the database
        bppaysisoRepository.saveAndFlush(bppaysiso);

        // Get all the bppaysisoList
        restBppaysisoMockMvc.perform(get("/api/bppaysisos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bppaysiso.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeBqe").value(hasItem(DEFAULT_CODE_BQE)))
            .andExpect(jsonPath("$.[*].codeCb").value(hasItem(DEFAULT_CODE_CB)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getBppaysiso() throws Exception {
        // Initialize the database
        bppaysisoRepository.saveAndFlush(bppaysiso);

        // Get the bppaysiso
        restBppaysisoMockMvc.perform(get("/api/bppaysisos/{id}", bppaysiso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bppaysiso.getId().intValue()))
            .andExpect(jsonPath("$.codeBqe").value(DEFAULT_CODE_BQE))
            .andExpect(jsonPath("$.codeCb").value(DEFAULT_CODE_CB))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingBppaysiso() throws Exception {
        // Get the bppaysiso
        restBppaysisoMockMvc.perform(get("/api/bppaysisos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBppaysiso() throws Exception {
        // Initialize the database
        bppaysisoService.save(bppaysiso);

        int databaseSizeBeforeUpdate = bppaysisoRepository.findAll().size();

        // Update the bppaysiso
        Bppaysiso updatedBppaysiso = bppaysisoRepository.findById(bppaysiso.getId()).get();
        // Disconnect from session so that the updates on updatedBppaysiso are not directly saved in db
        em.detach(updatedBppaysiso);
        updatedBppaysiso
            .codeBqe(UPDATED_CODE_BQE)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);

        restBppaysisoMockMvc.perform(put("/api/bppaysisos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBppaysiso)))
            .andExpect(status().isOk());

        // Validate the Bppaysiso in the database
        List<Bppaysiso> bppaysisoList = bppaysisoRepository.findAll();
        assertThat(bppaysisoList).hasSize(databaseSizeBeforeUpdate);
        Bppaysiso testBppaysiso = bppaysisoList.get(bppaysisoList.size() - 1);
        assertThat(testBppaysiso.getCodeBqe()).isEqualTo(UPDATED_CODE_BQE);
        assertThat(testBppaysiso.getCodeCb()).isEqualTo(UPDATED_CODE_CB);
        assertThat(testBppaysiso.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingBppaysiso() throws Exception {
        int databaseSizeBeforeUpdate = bppaysisoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBppaysisoMockMvc.perform(put("/api/bppaysisos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bppaysiso)))
            .andExpect(status().isBadRequest());

        // Validate the Bppaysiso in the database
        List<Bppaysiso> bppaysisoList = bppaysisoRepository.findAll();
        assertThat(bppaysisoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBppaysiso() throws Exception {
        // Initialize the database
        bppaysisoService.save(bppaysiso);

        int databaseSizeBeforeDelete = bppaysisoRepository.findAll().size();

        // Delete the bppaysiso
        restBppaysisoMockMvc.perform(delete("/api/bppaysisos/{id}", bppaysiso.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bppaysiso> bppaysisoList = bppaysisoRepository.findAll();
        assertThat(bppaysisoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
