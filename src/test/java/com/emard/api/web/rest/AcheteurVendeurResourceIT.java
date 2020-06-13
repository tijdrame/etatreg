package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.AcheteurVendeur;
import com.emard.api.repository.AcheteurVendeurRepository;
import com.emard.api.service.AcheteurVendeurService;

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
 * Integration tests for the {@link AcheteurVendeurResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AcheteurVendeurResourceIT {

    private static final String DEFAULT_CODE_ACHETEUR_VENDEUR = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ACHETEUR_VENDEUR = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_INTERNE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_INTERNE = "BBBBBBBBBB";

    @Autowired
    private AcheteurVendeurRepository acheteurVendeurRepository;

    @Autowired
    private AcheteurVendeurService acheteurVendeurService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAcheteurVendeurMockMvc;

    private AcheteurVendeur acheteurVendeur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AcheteurVendeur createEntity(EntityManager em) {
        AcheteurVendeur acheteurVendeur = new AcheteurVendeur()
            .codeAcheteurVendeur(DEFAULT_CODE_ACHETEUR_VENDEUR)
            .description(DEFAULT_DESCRIPTION)
            .codeInterne(DEFAULT_CODE_INTERNE);
        return acheteurVendeur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AcheteurVendeur createUpdatedEntity(EntityManager em) {
        AcheteurVendeur acheteurVendeur = new AcheteurVendeur()
            .codeAcheteurVendeur(UPDATED_CODE_ACHETEUR_VENDEUR)
            .description(UPDATED_DESCRIPTION)
            .codeInterne(UPDATED_CODE_INTERNE);
        return acheteurVendeur;
    }

    @BeforeEach
    public void initTest() {
        acheteurVendeur = createEntity(em);
    }

    @Test
    @Transactional
    public void createAcheteurVendeur() throws Exception {
        int databaseSizeBeforeCreate = acheteurVendeurRepository.findAll().size();
        // Create the AcheteurVendeur
        restAcheteurVendeurMockMvc.perform(post("/api/acheteur-vendeurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(acheteurVendeur)))
            .andExpect(status().isCreated());

        // Validate the AcheteurVendeur in the database
        List<AcheteurVendeur> acheteurVendeurList = acheteurVendeurRepository.findAll();
        assertThat(acheteurVendeurList).hasSize(databaseSizeBeforeCreate + 1);
        AcheteurVendeur testAcheteurVendeur = acheteurVendeurList.get(acheteurVendeurList.size() - 1);
        assertThat(testAcheteurVendeur.getCodeAcheteurVendeur()).isEqualTo(DEFAULT_CODE_ACHETEUR_VENDEUR);
        assertThat(testAcheteurVendeur.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAcheteurVendeur.getCodeInterne()).isEqualTo(DEFAULT_CODE_INTERNE);
    }

    @Test
    @Transactional
    public void createAcheteurVendeurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = acheteurVendeurRepository.findAll().size();

        // Create the AcheteurVendeur with an existing ID
        acheteurVendeur.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAcheteurVendeurMockMvc.perform(post("/api/acheteur-vendeurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(acheteurVendeur)))
            .andExpect(status().isBadRequest());

        // Validate the AcheteurVendeur in the database
        List<AcheteurVendeur> acheteurVendeurList = acheteurVendeurRepository.findAll();
        assertThat(acheteurVendeurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeAcheteurVendeurIsRequired() throws Exception {
        int databaseSizeBeforeTest = acheteurVendeurRepository.findAll().size();
        // set the field null
        acheteurVendeur.setCodeAcheteurVendeur(null);

        // Create the AcheteurVendeur, which fails.


        restAcheteurVendeurMockMvc.perform(post("/api/acheteur-vendeurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(acheteurVendeur)))
            .andExpect(status().isBadRequest());

        List<AcheteurVendeur> acheteurVendeurList = acheteurVendeurRepository.findAll();
        assertThat(acheteurVendeurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAcheteurVendeurs() throws Exception {
        // Initialize the database
        acheteurVendeurRepository.saveAndFlush(acheteurVendeur);

        // Get all the acheteurVendeurList
        restAcheteurVendeurMockMvc.perform(get("/api/acheteur-vendeurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(acheteurVendeur.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeAcheteurVendeur").value(hasItem(DEFAULT_CODE_ACHETEUR_VENDEUR)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].codeInterne").value(hasItem(DEFAULT_CODE_INTERNE)));
    }
    
    @Test
    @Transactional
    public void getAcheteurVendeur() throws Exception {
        // Initialize the database
        acheteurVendeurRepository.saveAndFlush(acheteurVendeur);

        // Get the acheteurVendeur
        restAcheteurVendeurMockMvc.perform(get("/api/acheteur-vendeurs/{id}", acheteurVendeur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(acheteurVendeur.getId().intValue()))
            .andExpect(jsonPath("$.codeAcheteurVendeur").value(DEFAULT_CODE_ACHETEUR_VENDEUR))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.codeInterne").value(DEFAULT_CODE_INTERNE));
    }
    @Test
    @Transactional
    public void getNonExistingAcheteurVendeur() throws Exception {
        // Get the acheteurVendeur
        restAcheteurVendeurMockMvc.perform(get("/api/acheteur-vendeurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAcheteurVendeur() throws Exception {
        // Initialize the database
        acheteurVendeurService.save(acheteurVendeur);

        int databaseSizeBeforeUpdate = acheteurVendeurRepository.findAll().size();

        // Update the acheteurVendeur
        AcheteurVendeur updatedAcheteurVendeur = acheteurVendeurRepository.findById(acheteurVendeur.getId()).get();
        // Disconnect from session so that the updates on updatedAcheteurVendeur are not directly saved in db
        em.detach(updatedAcheteurVendeur);
        updatedAcheteurVendeur
            .codeAcheteurVendeur(UPDATED_CODE_ACHETEUR_VENDEUR)
            .description(UPDATED_DESCRIPTION)
            .codeInterne(UPDATED_CODE_INTERNE);

        restAcheteurVendeurMockMvc.perform(put("/api/acheteur-vendeurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAcheteurVendeur)))
            .andExpect(status().isOk());

        // Validate the AcheteurVendeur in the database
        List<AcheteurVendeur> acheteurVendeurList = acheteurVendeurRepository.findAll();
        assertThat(acheteurVendeurList).hasSize(databaseSizeBeforeUpdate);
        AcheteurVendeur testAcheteurVendeur = acheteurVendeurList.get(acheteurVendeurList.size() - 1);
        assertThat(testAcheteurVendeur.getCodeAcheteurVendeur()).isEqualTo(UPDATED_CODE_ACHETEUR_VENDEUR);
        assertThat(testAcheteurVendeur.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAcheteurVendeur.getCodeInterne()).isEqualTo(UPDATED_CODE_INTERNE);
    }

    @Test
    @Transactional
    public void updateNonExistingAcheteurVendeur() throws Exception {
        int databaseSizeBeforeUpdate = acheteurVendeurRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAcheteurVendeurMockMvc.perform(put("/api/acheteur-vendeurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(acheteurVendeur)))
            .andExpect(status().isBadRequest());

        // Validate the AcheteurVendeur in the database
        List<AcheteurVendeur> acheteurVendeurList = acheteurVendeurRepository.findAll();
        assertThat(acheteurVendeurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAcheteurVendeur() throws Exception {
        // Initialize the database
        acheteurVendeurService.save(acheteurVendeur);

        int databaseSizeBeforeDelete = acheteurVendeurRepository.findAll().size();

        // Delete the acheteurVendeur
        restAcheteurVendeurMockMvc.perform(delete("/api/acheteur-vendeurs/{id}", acheteurVendeur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AcheteurVendeur> acheteurVendeurList = acheteurVendeurRepository.findAll();
        assertThat(acheteurVendeurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
