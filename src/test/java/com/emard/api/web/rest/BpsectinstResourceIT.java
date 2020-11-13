package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.Bpsectinst;
import com.emard.api.repository.BpsectinstRepository;
import com.emard.api.service.BpsectinstService;

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
 * Integration tests for the {@link BpsectinstResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BpsectinstResourceIT {

    private static final String DEFAULT_CODE_BQE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_BQE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_CB = "AAAAAAAAAA";
    private static final String UPDATED_CODE_CB = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private BpsectinstRepository bpsectinstRepository;

    @Autowired
    private BpsectinstService bpsectinstService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBpsectinstMockMvc;

    private Bpsectinst bpsectinst;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bpsectinst createEntity(EntityManager em) {
        Bpsectinst bpsectinst = new Bpsectinst()
            .codeBqe(DEFAULT_CODE_BQE)
            .codeCb(DEFAULT_CODE_CB)
            .description(DEFAULT_DESCRIPTION);
        return bpsectinst;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bpsectinst createUpdatedEntity(EntityManager em) {
        Bpsectinst bpsectinst = new Bpsectinst()
            .codeBqe(UPDATED_CODE_BQE)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);
        return bpsectinst;
    }

    @BeforeEach
    public void initTest() {
        bpsectinst = createEntity(em);
    }

    @Test
    @Transactional
    public void createBpsectinst() throws Exception {
        int databaseSizeBeforeCreate = bpsectinstRepository.findAll().size();
        // Create the Bpsectinst
        restBpsectinstMockMvc.perform(post("/api/bpsectinsts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bpsectinst)))
            .andExpect(status().isCreated());

        // Validate the Bpsectinst in the database
        List<Bpsectinst> bpsectinstList = bpsectinstRepository.findAll();
        assertThat(bpsectinstList).hasSize(databaseSizeBeforeCreate + 1);
        Bpsectinst testBpsectinst = bpsectinstList.get(bpsectinstList.size() - 1);
        assertThat(testBpsectinst.getCodeBqe()).isEqualTo(DEFAULT_CODE_BQE);
        assertThat(testBpsectinst.getCodeCb()).isEqualTo(DEFAULT_CODE_CB);
        assertThat(testBpsectinst.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createBpsectinstWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bpsectinstRepository.findAll().size();

        // Create the Bpsectinst with an existing ID
        bpsectinst.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBpsectinstMockMvc.perform(post("/api/bpsectinsts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bpsectinst)))
            .andExpect(status().isBadRequest());

        // Validate the Bpsectinst in the database
        List<Bpsectinst> bpsectinstList = bpsectinstRepository.findAll();
        assertThat(bpsectinstList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBpsectinsts() throws Exception {
        // Initialize the database
        bpsectinstRepository.saveAndFlush(bpsectinst);

        // Get all the bpsectinstList
        restBpsectinstMockMvc.perform(get("/api/bpsectinsts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bpsectinst.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeBqe").value(hasItem(DEFAULT_CODE_BQE)))
            .andExpect(jsonPath("$.[*].codeCb").value(hasItem(DEFAULT_CODE_CB)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getBpsectinst() throws Exception {
        // Initialize the database
        bpsectinstRepository.saveAndFlush(bpsectinst);

        // Get the bpsectinst
        restBpsectinstMockMvc.perform(get("/api/bpsectinsts/{id}", bpsectinst.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bpsectinst.getId().intValue()))
            .andExpect(jsonPath("$.codeBqe").value(DEFAULT_CODE_BQE))
            .andExpect(jsonPath("$.codeCb").value(DEFAULT_CODE_CB))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingBpsectinst() throws Exception {
        // Get the bpsectinst
        restBpsectinstMockMvc.perform(get("/api/bpsectinsts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBpsectinst() throws Exception {
        // Initialize the database
        bpsectinstService.save(bpsectinst);

        int databaseSizeBeforeUpdate = bpsectinstRepository.findAll().size();

        // Update the bpsectinst
        Bpsectinst updatedBpsectinst = bpsectinstRepository.findById(bpsectinst.getId()).get();
        // Disconnect from session so that the updates on updatedBpsectinst are not directly saved in db
        em.detach(updatedBpsectinst);
        updatedBpsectinst
            .codeBqe(UPDATED_CODE_BQE)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);

        restBpsectinstMockMvc.perform(put("/api/bpsectinsts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBpsectinst)))
            .andExpect(status().isOk());

        // Validate the Bpsectinst in the database
        List<Bpsectinst> bpsectinstList = bpsectinstRepository.findAll();
        assertThat(bpsectinstList).hasSize(databaseSizeBeforeUpdate);
        Bpsectinst testBpsectinst = bpsectinstList.get(bpsectinstList.size() - 1);
        assertThat(testBpsectinst.getCodeBqe()).isEqualTo(UPDATED_CODE_BQE);
        assertThat(testBpsectinst.getCodeCb()).isEqualTo(UPDATED_CODE_CB);
        assertThat(testBpsectinst.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingBpsectinst() throws Exception {
        int databaseSizeBeforeUpdate = bpsectinstRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBpsectinstMockMvc.perform(put("/api/bpsectinsts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bpsectinst)))
            .andExpect(status().isBadRequest());

        // Validate the Bpsectinst in the database
        List<Bpsectinst> bpsectinstList = bpsectinstRepository.findAll();
        assertThat(bpsectinstList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBpsectinst() throws Exception {
        // Initialize the database
        bpsectinstService.save(bpsectinst);

        int databaseSizeBeforeDelete = bpsectinstRepository.findAll().size();

        // Delete the bpsectinst
        restBpsectinstMockMvc.perform(delete("/api/bpsectinsts/{id}", bpsectinst.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bpsectinst> bpsectinstList = bpsectinstRepository.findAll();
        assertThat(bpsectinstList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
