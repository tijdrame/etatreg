package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.Bp3Infos;
import com.emard.api.repository.Bp3InfosRepository;
import com.emard.api.service.Bp3InfosService;

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
 * Integration tests for the {@link Bp3InfosResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class Bp3InfosResourceIT {

    private static final String DEFAULT_CODE_ISO_DEVISE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ISO_DEVISE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE_DEVISE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_DEVISE = "BBBBBBBBBB";

    private static final String DEFAULT_ACHETEUR_VENDEUR = "AAAAAAAAAA";
    private static final String UPDATED_ACHETEUR_VENDEUR = "BBBBBBBBBB";

    private static final Double DEFAULT_ACHATS_BILLET_E_TR = 1D;
    private static final Double UPDATED_ACHATS_BILLET_E_TR = 2D;

    private static final Double DEFAULT_VENTES_BILLET_ETR = 1D;
    private static final Double UPDATED_VENTES_BILLET_ETR = 2D;

    private static final Double DEFAULT_ACHATS_CHQ_VOY = 1D;
    private static final Double UPDATED_ACHATS_CHQ_VOY = 2D;

    private static final Double DEFAULT_VENTES_CHQ_VOY = 1D;
    private static final Double UPDATED_VENTES_CHQ_VOY = 2D;

    @Autowired
    private Bp3InfosRepository bp3InfosRepository;

    @Autowired
    private Bp3InfosService bp3InfosService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBp3InfosMockMvc;

    private Bp3Infos bp3Infos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bp3Infos createEntity(EntityManager em) {
        Bp3Infos bp3Infos = new Bp3Infos()
            .codeIsoDevise(DEFAULT_CODE_ISO_DEVISE)
            .libelleDevise(DEFAULT_LIBELLE_DEVISE)
            .acheteurVendeur(DEFAULT_ACHETEUR_VENDEUR)
            .achatsBilletETr(DEFAULT_ACHATS_BILLET_E_TR)
            .ventesBilletEtr(DEFAULT_VENTES_BILLET_ETR)
            .achatsChqVoy(DEFAULT_ACHATS_CHQ_VOY)
            .ventesChqVoy(DEFAULT_VENTES_CHQ_VOY);
        return bp3Infos;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bp3Infos createUpdatedEntity(EntityManager em) {
        Bp3Infos bp3Infos = new Bp3Infos()
            .codeIsoDevise(UPDATED_CODE_ISO_DEVISE)
            .libelleDevise(UPDATED_LIBELLE_DEVISE)
            .acheteurVendeur(UPDATED_ACHETEUR_VENDEUR)
            .achatsBilletETr(UPDATED_ACHATS_BILLET_E_TR)
            .ventesBilletEtr(UPDATED_VENTES_BILLET_ETR)
            .achatsChqVoy(UPDATED_ACHATS_CHQ_VOY)
            .ventesChqVoy(UPDATED_VENTES_CHQ_VOY);
        return bp3Infos;
    }

    @BeforeEach
    public void initTest() {
        bp3Infos = createEntity(em);
    }

    @Test
    @Transactional
    public void createBp3Infos() throws Exception {
        int databaseSizeBeforeCreate = bp3InfosRepository.findAll().size();
        // Create the Bp3Infos
        restBp3InfosMockMvc.perform(post("/api/bp-3-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp3Infos)))
            .andExpect(status().isCreated());

        // Validate the Bp3Infos in the database
        List<Bp3Infos> bp3InfosList = bp3InfosRepository.findAll();
        assertThat(bp3InfosList).hasSize(databaseSizeBeforeCreate + 1);
        Bp3Infos testBp3Infos = bp3InfosList.get(bp3InfosList.size() - 1);
        assertThat(testBp3Infos.getCodeIsoDevise()).isEqualTo(DEFAULT_CODE_ISO_DEVISE);
        assertThat(testBp3Infos.getLibelleDevise()).isEqualTo(DEFAULT_LIBELLE_DEVISE);
        assertThat(testBp3Infos.getAcheteurVendeur()).isEqualTo(DEFAULT_ACHETEUR_VENDEUR);
        assertThat(testBp3Infos.getAchatsBilletETr()).isEqualTo(DEFAULT_ACHATS_BILLET_E_TR);
        assertThat(testBp3Infos.getVentesBilletEtr()).isEqualTo(DEFAULT_VENTES_BILLET_ETR);
        assertThat(testBp3Infos.getAchatsChqVoy()).isEqualTo(DEFAULT_ACHATS_CHQ_VOY);
        assertThat(testBp3Infos.getVentesChqVoy()).isEqualTo(DEFAULT_VENTES_CHQ_VOY);
    }

    @Test
    @Transactional
    public void createBp3InfosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bp3InfosRepository.findAll().size();

        // Create the Bp3Infos with an existing ID
        bp3Infos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBp3InfosMockMvc.perform(post("/api/bp-3-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp3Infos)))
            .andExpect(status().isBadRequest());

        // Validate the Bp3Infos in the database
        List<Bp3Infos> bp3InfosList = bp3InfosRepository.findAll();
        assertThat(bp3InfosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBp3Infos() throws Exception {
        // Initialize the database
        bp3InfosRepository.saveAndFlush(bp3Infos);

        // Get all the bp3InfosList
        restBp3InfosMockMvc.perform(get("/api/bp-3-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bp3Infos.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeIsoDevise").value(hasItem(DEFAULT_CODE_ISO_DEVISE)))
            .andExpect(jsonPath("$.[*].libelleDevise").value(hasItem(DEFAULT_LIBELLE_DEVISE)))
            .andExpect(jsonPath("$.[*].acheteurVendeur").value(hasItem(DEFAULT_ACHETEUR_VENDEUR)))
            .andExpect(jsonPath("$.[*].achatsBilletETr").value(hasItem(DEFAULT_ACHATS_BILLET_E_TR.doubleValue())))
            .andExpect(jsonPath("$.[*].ventesBilletEtr").value(hasItem(DEFAULT_VENTES_BILLET_ETR.doubleValue())))
            .andExpect(jsonPath("$.[*].achatsChqVoy").value(hasItem(DEFAULT_ACHATS_CHQ_VOY.doubleValue())))
            .andExpect(jsonPath("$.[*].ventesChqVoy").value(hasItem(DEFAULT_VENTES_CHQ_VOY.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getBp3Infos() throws Exception {
        // Initialize the database
        bp3InfosRepository.saveAndFlush(bp3Infos);

        // Get the bp3Infos
        restBp3InfosMockMvc.perform(get("/api/bp-3-infos/{id}", bp3Infos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bp3Infos.getId().intValue()))
            .andExpect(jsonPath("$.codeIsoDevise").value(DEFAULT_CODE_ISO_DEVISE))
            .andExpect(jsonPath("$.libelleDevise").value(DEFAULT_LIBELLE_DEVISE))
            .andExpect(jsonPath("$.acheteurVendeur").value(DEFAULT_ACHETEUR_VENDEUR))
            .andExpect(jsonPath("$.achatsBilletETr").value(DEFAULT_ACHATS_BILLET_E_TR.doubleValue()))
            .andExpect(jsonPath("$.ventesBilletEtr").value(DEFAULT_VENTES_BILLET_ETR.doubleValue()))
            .andExpect(jsonPath("$.achatsChqVoy").value(DEFAULT_ACHATS_CHQ_VOY.doubleValue()))
            .andExpect(jsonPath("$.ventesChqVoy").value(DEFAULT_VENTES_CHQ_VOY.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingBp3Infos() throws Exception {
        // Get the bp3Infos
        restBp3InfosMockMvc.perform(get("/api/bp-3-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBp3Infos() throws Exception {
        // Initialize the database
        bp3InfosService.save(bp3Infos);

        int databaseSizeBeforeUpdate = bp3InfosRepository.findAll().size();

        // Update the bp3Infos
        Bp3Infos updatedBp3Infos = bp3InfosRepository.findById(bp3Infos.getId()).get();
        // Disconnect from session so that the updates on updatedBp3Infos are not directly saved in db
        em.detach(updatedBp3Infos);
        updatedBp3Infos
            .codeIsoDevise(UPDATED_CODE_ISO_DEVISE)
            .libelleDevise(UPDATED_LIBELLE_DEVISE)
            .acheteurVendeur(UPDATED_ACHETEUR_VENDEUR)
            .achatsBilletETr(UPDATED_ACHATS_BILLET_E_TR)
            .ventesBilletEtr(UPDATED_VENTES_BILLET_ETR)
            .achatsChqVoy(UPDATED_ACHATS_CHQ_VOY)
            .ventesChqVoy(UPDATED_VENTES_CHQ_VOY);

        restBp3InfosMockMvc.perform(put("/api/bp-3-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBp3Infos)))
            .andExpect(status().isOk());

        // Validate the Bp3Infos in the database
        List<Bp3Infos> bp3InfosList = bp3InfosRepository.findAll();
        assertThat(bp3InfosList).hasSize(databaseSizeBeforeUpdate);
        Bp3Infos testBp3Infos = bp3InfosList.get(bp3InfosList.size() - 1);
        assertThat(testBp3Infos.getCodeIsoDevise()).isEqualTo(UPDATED_CODE_ISO_DEVISE);
        assertThat(testBp3Infos.getLibelleDevise()).isEqualTo(UPDATED_LIBELLE_DEVISE);
        assertThat(testBp3Infos.getAcheteurVendeur()).isEqualTo(UPDATED_ACHETEUR_VENDEUR);
        assertThat(testBp3Infos.getAchatsBilletETr()).isEqualTo(UPDATED_ACHATS_BILLET_E_TR);
        assertThat(testBp3Infos.getVentesBilletEtr()).isEqualTo(UPDATED_VENTES_BILLET_ETR);
        assertThat(testBp3Infos.getAchatsChqVoy()).isEqualTo(UPDATED_ACHATS_CHQ_VOY);
        assertThat(testBp3Infos.getVentesChqVoy()).isEqualTo(UPDATED_VENTES_CHQ_VOY);
    }

    @Test
    @Transactional
    public void updateNonExistingBp3Infos() throws Exception {
        int databaseSizeBeforeUpdate = bp3InfosRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBp3InfosMockMvc.perform(put("/api/bp-3-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp3Infos)))
            .andExpect(status().isBadRequest());

        // Validate the Bp3Infos in the database
        List<Bp3Infos> bp3InfosList = bp3InfosRepository.findAll();
        assertThat(bp3InfosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBp3Infos() throws Exception {
        // Initialize the database
        bp3InfosService.save(bp3Infos);

        int databaseSizeBeforeDelete = bp3InfosRepository.findAll().size();

        // Delete the bp3Infos
        restBp3InfosMockMvc.perform(delete("/api/bp-3-infos/{id}", bp3Infos.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bp3Infos> bp3InfosList = bp3InfosRepository.findAll();
        assertThat(bp3InfosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
