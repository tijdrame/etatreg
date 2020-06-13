package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.NatureDebiteur;
import com.emard.api.repository.NatureDebiteurRepository;
import com.emard.api.service.NatureDebiteurService;

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
 * Integration tests for the {@link NatureDebiteurResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NatureDebiteurResourceIT {

    private static final String DEFAULT_CODE_BDU = "AAAAAAAAAA";
    private static final String UPDATED_CODE_BDU = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_CB = "AAAAAAAAAA";
    private static final String UPDATED_CODE_CB = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private NatureDebiteurRepository natureDebiteurRepository;

    @Autowired
    private NatureDebiteurService natureDebiteurService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNatureDebiteurMockMvc;

    private NatureDebiteur natureDebiteur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NatureDebiteur createEntity(EntityManager em) {
        NatureDebiteur natureDebiteur = new NatureDebiteur()
            .codeBdu(DEFAULT_CODE_BDU)
            .codeCb(DEFAULT_CODE_CB)
            .description(DEFAULT_DESCRIPTION);
        return natureDebiteur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NatureDebiteur createUpdatedEntity(EntityManager em) {
        NatureDebiteur natureDebiteur = new NatureDebiteur()
            .codeBdu(UPDATED_CODE_BDU)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);
        return natureDebiteur;
    }

    @BeforeEach
    public void initTest() {
        natureDebiteur = createEntity(em);
    }

    @Test
    @Transactional
    public void createNatureDebiteur() throws Exception {
        int databaseSizeBeforeCreate = natureDebiteurRepository.findAll().size();
        // Create the NatureDebiteur
        restNatureDebiteurMockMvc.perform(post("/api/nature-debiteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureDebiteur)))
            .andExpect(status().isCreated());

        // Validate the NatureDebiteur in the database
        List<NatureDebiteur> natureDebiteurList = natureDebiteurRepository.findAll();
        assertThat(natureDebiteurList).hasSize(databaseSizeBeforeCreate + 1);
        NatureDebiteur testNatureDebiteur = natureDebiteurList.get(natureDebiteurList.size() - 1);
        assertThat(testNatureDebiteur.getCodeBdu()).isEqualTo(DEFAULT_CODE_BDU);
        assertThat(testNatureDebiteur.getCodeCb()).isEqualTo(DEFAULT_CODE_CB);
        assertThat(testNatureDebiteur.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createNatureDebiteurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = natureDebiteurRepository.findAll().size();

        // Create the NatureDebiteur with an existing ID
        natureDebiteur.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNatureDebiteurMockMvc.perform(post("/api/nature-debiteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureDebiteur)))
            .andExpect(status().isBadRequest());

        // Validate the NatureDebiteur in the database
        List<NatureDebiteur> natureDebiteurList = natureDebiteurRepository.findAll();
        assertThat(natureDebiteurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeBduIsRequired() throws Exception {
        int databaseSizeBeforeTest = natureDebiteurRepository.findAll().size();
        // set the field null
        natureDebiteur.setCodeBdu(null);

        // Create the NatureDebiteur, which fails.


        restNatureDebiteurMockMvc.perform(post("/api/nature-debiteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureDebiteur)))
            .andExpect(status().isBadRequest());

        List<NatureDebiteur> natureDebiteurList = natureDebiteurRepository.findAll();
        assertThat(natureDebiteurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeCbIsRequired() throws Exception {
        int databaseSizeBeforeTest = natureDebiteurRepository.findAll().size();
        // set the field null
        natureDebiteur.setCodeCb(null);

        // Create the NatureDebiteur, which fails.


        restNatureDebiteurMockMvc.perform(post("/api/nature-debiteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureDebiteur)))
            .andExpect(status().isBadRequest());

        List<NatureDebiteur> natureDebiteurList = natureDebiteurRepository.findAll();
        assertThat(natureDebiteurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNatureDebiteurs() throws Exception {
        // Initialize the database
        natureDebiteurRepository.saveAndFlush(natureDebiteur);

        // Get all the natureDebiteurList
        restNatureDebiteurMockMvc.perform(get("/api/nature-debiteurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(natureDebiteur.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeBdu").value(hasItem(DEFAULT_CODE_BDU)))
            .andExpect(jsonPath("$.[*].codeCb").value(hasItem(DEFAULT_CODE_CB)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getNatureDebiteur() throws Exception {
        // Initialize the database
        natureDebiteurRepository.saveAndFlush(natureDebiteur);

        // Get the natureDebiteur
        restNatureDebiteurMockMvc.perform(get("/api/nature-debiteurs/{id}", natureDebiteur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(natureDebiteur.getId().intValue()))
            .andExpect(jsonPath("$.codeBdu").value(DEFAULT_CODE_BDU))
            .andExpect(jsonPath("$.codeCb").value(DEFAULT_CODE_CB))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingNatureDebiteur() throws Exception {
        // Get the natureDebiteur
        restNatureDebiteurMockMvc.perform(get("/api/nature-debiteurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNatureDebiteur() throws Exception {
        // Initialize the database
        natureDebiteurService.save(natureDebiteur);

        int databaseSizeBeforeUpdate = natureDebiteurRepository.findAll().size();

        // Update the natureDebiteur
        NatureDebiteur updatedNatureDebiteur = natureDebiteurRepository.findById(natureDebiteur.getId()).get();
        // Disconnect from session so that the updates on updatedNatureDebiteur are not directly saved in db
        em.detach(updatedNatureDebiteur);
        updatedNatureDebiteur
            .codeBdu(UPDATED_CODE_BDU)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);

        restNatureDebiteurMockMvc.perform(put("/api/nature-debiteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedNatureDebiteur)))
            .andExpect(status().isOk());

        // Validate the NatureDebiteur in the database
        List<NatureDebiteur> natureDebiteurList = natureDebiteurRepository.findAll();
        assertThat(natureDebiteurList).hasSize(databaseSizeBeforeUpdate);
        NatureDebiteur testNatureDebiteur = natureDebiteurList.get(natureDebiteurList.size() - 1);
        assertThat(testNatureDebiteur.getCodeBdu()).isEqualTo(UPDATED_CODE_BDU);
        assertThat(testNatureDebiteur.getCodeCb()).isEqualTo(UPDATED_CODE_CB);
        assertThat(testNatureDebiteur.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingNatureDebiteur() throws Exception {
        int databaseSizeBeforeUpdate = natureDebiteurRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNatureDebiteurMockMvc.perform(put("/api/nature-debiteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureDebiteur)))
            .andExpect(status().isBadRequest());

        // Validate the NatureDebiteur in the database
        List<NatureDebiteur> natureDebiteurList = natureDebiteurRepository.findAll();
        assertThat(natureDebiteurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNatureDebiteur() throws Exception {
        // Initialize the database
        natureDebiteurService.save(natureDebiteur);

        int databaseSizeBeforeDelete = natureDebiteurRepository.findAll().size();

        // Delete the natureDebiteur
        restNatureDebiteurMockMvc.perform(delete("/api/nature-debiteurs/{id}", natureDebiteur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NatureDebiteur> natureDebiteurList = natureDebiteurRepository.findAll();
        assertThat(natureDebiteurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
