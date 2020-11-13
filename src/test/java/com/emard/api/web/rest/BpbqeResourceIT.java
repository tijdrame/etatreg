package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.Bpbqe;
import com.emard.api.repository.BpbqeRepository;
import com.emard.api.service.BpbqeService;

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
 * Integration tests for the {@link BpbqeResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BpbqeResourceIT {

    private static final String DEFAULT_CODE_BQE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_BQE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_CB = "AAAAAAAAAA";
    private static final String UPDATED_CODE_CB = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private BpbqeRepository bpbqeRepository;

    @Autowired
    private BpbqeService bpbqeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBpbqeMockMvc;

    private Bpbqe bpbqe;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bpbqe createEntity(EntityManager em) {
        Bpbqe bpbqe = new Bpbqe()
            .codeBqe(DEFAULT_CODE_BQE)
            .codeCb(DEFAULT_CODE_CB)
            .description(DEFAULT_DESCRIPTION);
        return bpbqe;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bpbqe createUpdatedEntity(EntityManager em) {
        Bpbqe bpbqe = new Bpbqe()
            .codeBqe(UPDATED_CODE_BQE)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);
        return bpbqe;
    }

    @BeforeEach
    public void initTest() {
        bpbqe = createEntity(em);
    }

    @Test
    @Transactional
    public void createBpbqe() throws Exception {
        int databaseSizeBeforeCreate = bpbqeRepository.findAll().size();
        // Create the Bpbqe
        restBpbqeMockMvc.perform(post("/api/bpbqes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bpbqe)))
            .andExpect(status().isCreated());

        // Validate the Bpbqe in the database
        List<Bpbqe> bpbqeList = bpbqeRepository.findAll();
        assertThat(bpbqeList).hasSize(databaseSizeBeforeCreate + 1);
        Bpbqe testBpbqe = bpbqeList.get(bpbqeList.size() - 1);
        assertThat(testBpbqe.getCodeBqe()).isEqualTo(DEFAULT_CODE_BQE);
        assertThat(testBpbqe.getCodeCb()).isEqualTo(DEFAULT_CODE_CB);
        assertThat(testBpbqe.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createBpbqeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bpbqeRepository.findAll().size();

        // Create the Bpbqe with an existing ID
        bpbqe.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBpbqeMockMvc.perform(post("/api/bpbqes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bpbqe)))
            .andExpect(status().isBadRequest());

        // Validate the Bpbqe in the database
        List<Bpbqe> bpbqeList = bpbqeRepository.findAll();
        assertThat(bpbqeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBpbqes() throws Exception {
        // Initialize the database
        bpbqeRepository.saveAndFlush(bpbqe);

        // Get all the bpbqeList
        restBpbqeMockMvc.perform(get("/api/bpbqes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bpbqe.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeBqe").value(hasItem(DEFAULT_CODE_BQE)))
            .andExpect(jsonPath("$.[*].codeCb").value(hasItem(DEFAULT_CODE_CB)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getBpbqe() throws Exception {
        // Initialize the database
        bpbqeRepository.saveAndFlush(bpbqe);

        // Get the bpbqe
        restBpbqeMockMvc.perform(get("/api/bpbqes/{id}", bpbqe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bpbqe.getId().intValue()))
            .andExpect(jsonPath("$.codeBqe").value(DEFAULT_CODE_BQE))
            .andExpect(jsonPath("$.codeCb").value(DEFAULT_CODE_CB))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingBpbqe() throws Exception {
        // Get the bpbqe
        restBpbqeMockMvc.perform(get("/api/bpbqes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBpbqe() throws Exception {
        // Initialize the database
        bpbqeService.save(bpbqe);

        int databaseSizeBeforeUpdate = bpbqeRepository.findAll().size();

        // Update the bpbqe
        Bpbqe updatedBpbqe = bpbqeRepository.findById(bpbqe.getId()).get();
        // Disconnect from session so that the updates on updatedBpbqe are not directly saved in db
        em.detach(updatedBpbqe);
        updatedBpbqe
            .codeBqe(UPDATED_CODE_BQE)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);

        restBpbqeMockMvc.perform(put("/api/bpbqes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBpbqe)))
            .andExpect(status().isOk());

        // Validate the Bpbqe in the database
        List<Bpbqe> bpbqeList = bpbqeRepository.findAll();
        assertThat(bpbqeList).hasSize(databaseSizeBeforeUpdate);
        Bpbqe testBpbqe = bpbqeList.get(bpbqeList.size() - 1);
        assertThat(testBpbqe.getCodeBqe()).isEqualTo(UPDATED_CODE_BQE);
        assertThat(testBpbqe.getCodeCb()).isEqualTo(UPDATED_CODE_CB);
        assertThat(testBpbqe.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingBpbqe() throws Exception {
        int databaseSizeBeforeUpdate = bpbqeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBpbqeMockMvc.perform(put("/api/bpbqes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bpbqe)))
            .andExpect(status().isBadRequest());

        // Validate the Bpbqe in the database
        List<Bpbqe> bpbqeList = bpbqeRepository.findAll();
        assertThat(bpbqeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBpbqe() throws Exception {
        // Initialize the database
        bpbqeService.save(bpbqe);

        int databaseSizeBeforeDelete = bpbqeRepository.findAll().size();

        // Delete the bpbqe
        restBpbqeMockMvc.perform(delete("/api/bpbqes/{id}", bpbqe.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bpbqe> bpbqeList = bpbqeRepository.findAll();
        assertThat(bpbqeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
