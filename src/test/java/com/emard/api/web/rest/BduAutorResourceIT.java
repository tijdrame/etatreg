package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.BduAutor;
import com.emard.api.repository.BduAutorRepository;
import com.emard.api.service.BduAutorService;

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
 * Integration tests for the {@link BduAutorResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BduAutorResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_ENREG = "AAAAAAAAAA";
    private static final String UPDATED_NUM_ENREG = "BBBBBBBBBB";

    private static final String DEFAULT_NATURE_BENEFICIAIRE = "AAAAAAAAAA";
    private static final String UPDATED_NATURE_BENEFICIAIRE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PAYS_RESIDENCE = 1;
    private static final Integer UPDATED_PAYS_RESIDENCE = 2;

    private static final Integer DEFAULT_VILLE = 1;
    private static final Integer UPDATED_VILLE = 2;

    private static final Integer DEFAULT_STATUT_JURIDIQUE = 1;
    private static final Integer UPDATED_STATUT_JURIDIQUE = 2;

    private static final String DEFAULT_SEXE_BENEFICIAIRE = "AAAAAAAAAA";
    private static final String UPDATED_SEXE_BENEFICIAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_SECTEUR_ACTIVITE = "AAAAAAAAAA";
    private static final String UPDATED_SECTEUR_ACTIVITE = "BBBBBBBBBB";

    private static final String DEFAULT_TAILLE_ENTREPRISE = "AAAAAAAAAA";
    private static final String UPDATED_TAILLE_ENTREPRISE = "BBBBBBBBBB";

    private static final Double DEFAULT_MONTANT_MAX_AUTORISE = 1D;
    private static final Double UPDATED_MONTANT_MAX_AUTORISE = 2D;

    private static final Double DEFAULT_MONTANT_MAX_UTILISE = 1D;
    private static final Double UPDATED_MONTANT_MAX_UTILISE = 2D;

    private static final Double DEFAULT_SOLDE_COMPTE = 1D;
    private static final Double UPDATED_SOLDE_COMPTE = 2D;

    private static final Double DEFAULT_TAUX_INTERET = 1D;
    private static final Double UPDATED_TAUX_INTERET = 2D;

    @Autowired
    private BduAutorRepository bduAutorRepository;

    @Autowired
    private BduAutorService bduAutorService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBduAutorMockMvc;

    private BduAutor bduAutor;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BduAutor createEntity(EntityManager em) {
        BduAutor bduAutor = new BduAutor()
            .code(DEFAULT_CODE)
            .numEnreg(DEFAULT_NUM_ENREG)
            .natureBeneficiaire(DEFAULT_NATURE_BENEFICIAIRE)
            .paysResidence(DEFAULT_PAYS_RESIDENCE)
            .ville(DEFAULT_VILLE)
            .statutJuridique(DEFAULT_STATUT_JURIDIQUE)
            .sexeBeneficiaire(DEFAULT_SEXE_BENEFICIAIRE)
            .secteurActivite(DEFAULT_SECTEUR_ACTIVITE)
            .tailleEntreprise(DEFAULT_TAILLE_ENTREPRISE)
            .montantMaxAutorise(DEFAULT_MONTANT_MAX_AUTORISE)
            .montantMaxUtilise(DEFAULT_MONTANT_MAX_UTILISE)
            .soldeCompte(DEFAULT_SOLDE_COMPTE)
            .tauxInteret(DEFAULT_TAUX_INTERET);
        return bduAutor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BduAutor createUpdatedEntity(EntityManager em) {
        BduAutor bduAutor = new BduAutor()
            .code(UPDATED_CODE)
            .numEnreg(UPDATED_NUM_ENREG)
            .natureBeneficiaire(UPDATED_NATURE_BENEFICIAIRE)
            .paysResidence(UPDATED_PAYS_RESIDENCE)
            .ville(UPDATED_VILLE)
            .statutJuridique(UPDATED_STATUT_JURIDIQUE)
            .sexeBeneficiaire(UPDATED_SEXE_BENEFICIAIRE)
            .secteurActivite(UPDATED_SECTEUR_ACTIVITE)
            .tailleEntreprise(UPDATED_TAILLE_ENTREPRISE)
            .montantMaxAutorise(UPDATED_MONTANT_MAX_AUTORISE)
            .montantMaxUtilise(UPDATED_MONTANT_MAX_UTILISE)
            .soldeCompte(UPDATED_SOLDE_COMPTE)
            .tauxInteret(UPDATED_TAUX_INTERET);
        return bduAutor;
    }

    @BeforeEach
    public void initTest() {
        bduAutor = createEntity(em);
    }

    @Test
    @Transactional
    public void createBduAutor() throws Exception {
        int databaseSizeBeforeCreate = bduAutorRepository.findAll().size();
        // Create the BduAutor
        restBduAutorMockMvc.perform(post("/api/bdu-autors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bduAutor)))
            .andExpect(status().isCreated());

        // Validate the BduAutor in the database
        List<BduAutor> bduAutorList = bduAutorRepository.findAll();
        assertThat(bduAutorList).hasSize(databaseSizeBeforeCreate + 1);
        BduAutor testBduAutor = bduAutorList.get(bduAutorList.size() - 1);
        assertThat(testBduAutor.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testBduAutor.getNumEnreg()).isEqualTo(DEFAULT_NUM_ENREG);
        assertThat(testBduAutor.getNatureBeneficiaire()).isEqualTo(DEFAULT_NATURE_BENEFICIAIRE);
        assertThat(testBduAutor.getPaysResidence()).isEqualTo(DEFAULT_PAYS_RESIDENCE);
        assertThat(testBduAutor.getVille()).isEqualTo(DEFAULT_VILLE);
        assertThat(testBduAutor.getStatutJuridique()).isEqualTo(DEFAULT_STATUT_JURIDIQUE);
        assertThat(testBduAutor.getSexeBeneficiaire()).isEqualTo(DEFAULT_SEXE_BENEFICIAIRE);
        assertThat(testBduAutor.getSecteurActivite()).isEqualTo(DEFAULT_SECTEUR_ACTIVITE);
        assertThat(testBduAutor.getTailleEntreprise()).isEqualTo(DEFAULT_TAILLE_ENTREPRISE);
        assertThat(testBduAutor.getMontantMaxAutorise()).isEqualTo(DEFAULT_MONTANT_MAX_AUTORISE);
        assertThat(testBduAutor.getMontantMaxUtilise()).isEqualTo(DEFAULT_MONTANT_MAX_UTILISE);
        assertThat(testBduAutor.getSoldeCompte()).isEqualTo(DEFAULT_SOLDE_COMPTE);
        assertThat(testBduAutor.getTauxInteret()).isEqualTo(DEFAULT_TAUX_INTERET);
    }

    @Test
    @Transactional
    public void createBduAutorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bduAutorRepository.findAll().size();

        // Create the BduAutor with an existing ID
        bduAutor.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBduAutorMockMvc.perform(post("/api/bdu-autors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bduAutor)))
            .andExpect(status().isBadRequest());

        // Validate the BduAutor in the database
        List<BduAutor> bduAutorList = bduAutorRepository.findAll();
        assertThat(bduAutorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBduAutors() throws Exception {
        // Initialize the database
        bduAutorRepository.saveAndFlush(bduAutor);

        // Get all the bduAutorList
        restBduAutorMockMvc.perform(get("/api/bdu-autors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bduAutor.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].numEnreg").value(hasItem(DEFAULT_NUM_ENREG)))
            .andExpect(jsonPath("$.[*].natureBeneficiaire").value(hasItem(DEFAULT_NATURE_BENEFICIAIRE)))
            .andExpect(jsonPath("$.[*].paysResidence").value(hasItem(DEFAULT_PAYS_RESIDENCE)))
            .andExpect(jsonPath("$.[*].ville").value(hasItem(DEFAULT_VILLE)))
            .andExpect(jsonPath("$.[*].statutJuridique").value(hasItem(DEFAULT_STATUT_JURIDIQUE)))
            .andExpect(jsonPath("$.[*].sexeBeneficiaire").value(hasItem(DEFAULT_SEXE_BENEFICIAIRE)))
            .andExpect(jsonPath("$.[*].secteurActivite").value(hasItem(DEFAULT_SECTEUR_ACTIVITE)))
            .andExpect(jsonPath("$.[*].tailleEntreprise").value(hasItem(DEFAULT_TAILLE_ENTREPRISE)))
            .andExpect(jsonPath("$.[*].montantMaxAutorise").value(hasItem(DEFAULT_MONTANT_MAX_AUTORISE.doubleValue())))
            .andExpect(jsonPath("$.[*].montantMaxUtilise").value(hasItem(DEFAULT_MONTANT_MAX_UTILISE.doubleValue())))
            .andExpect(jsonPath("$.[*].soldeCompte").value(hasItem(DEFAULT_SOLDE_COMPTE.doubleValue())))
            .andExpect(jsonPath("$.[*].tauxInteret").value(hasItem(DEFAULT_TAUX_INTERET.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getBduAutor() throws Exception {
        // Initialize the database
        bduAutorRepository.saveAndFlush(bduAutor);

        // Get the bduAutor
        restBduAutorMockMvc.perform(get("/api/bdu-autors/{id}", bduAutor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bduAutor.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.numEnreg").value(DEFAULT_NUM_ENREG))
            .andExpect(jsonPath("$.natureBeneficiaire").value(DEFAULT_NATURE_BENEFICIAIRE))
            .andExpect(jsonPath("$.paysResidence").value(DEFAULT_PAYS_RESIDENCE))
            .andExpect(jsonPath("$.ville").value(DEFAULT_VILLE))
            .andExpect(jsonPath("$.statutJuridique").value(DEFAULT_STATUT_JURIDIQUE))
            .andExpect(jsonPath("$.sexeBeneficiaire").value(DEFAULT_SEXE_BENEFICIAIRE))
            .andExpect(jsonPath("$.secteurActivite").value(DEFAULT_SECTEUR_ACTIVITE))
            .andExpect(jsonPath("$.tailleEntreprise").value(DEFAULT_TAILLE_ENTREPRISE))
            .andExpect(jsonPath("$.montantMaxAutorise").value(DEFAULT_MONTANT_MAX_AUTORISE.doubleValue()))
            .andExpect(jsonPath("$.montantMaxUtilise").value(DEFAULT_MONTANT_MAX_UTILISE.doubleValue()))
            .andExpect(jsonPath("$.soldeCompte").value(DEFAULT_SOLDE_COMPTE.doubleValue()))
            .andExpect(jsonPath("$.tauxInteret").value(DEFAULT_TAUX_INTERET.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingBduAutor() throws Exception {
        // Get the bduAutor
        restBduAutorMockMvc.perform(get("/api/bdu-autors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBduAutor() throws Exception {
        // Initialize the database
        bduAutorService.save(bduAutor);

        int databaseSizeBeforeUpdate = bduAutorRepository.findAll().size();

        // Update the bduAutor
        BduAutor updatedBduAutor = bduAutorRepository.findById(bduAutor.getId()).get();
        // Disconnect from session so that the updates on updatedBduAutor are not directly saved in db
        em.detach(updatedBduAutor);
        updatedBduAutor
            .code(UPDATED_CODE)
            .numEnreg(UPDATED_NUM_ENREG)
            .natureBeneficiaire(UPDATED_NATURE_BENEFICIAIRE)
            .paysResidence(UPDATED_PAYS_RESIDENCE)
            .ville(UPDATED_VILLE)
            .statutJuridique(UPDATED_STATUT_JURIDIQUE)
            .sexeBeneficiaire(UPDATED_SEXE_BENEFICIAIRE)
            .secteurActivite(UPDATED_SECTEUR_ACTIVITE)
            .tailleEntreprise(UPDATED_TAILLE_ENTREPRISE)
            .montantMaxAutorise(UPDATED_MONTANT_MAX_AUTORISE)
            .montantMaxUtilise(UPDATED_MONTANT_MAX_UTILISE)
            .soldeCompte(UPDATED_SOLDE_COMPTE)
            .tauxInteret(UPDATED_TAUX_INTERET);

        restBduAutorMockMvc.perform(put("/api/bdu-autors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBduAutor)))
            .andExpect(status().isOk());

        // Validate the BduAutor in the database
        List<BduAutor> bduAutorList = bduAutorRepository.findAll();
        assertThat(bduAutorList).hasSize(databaseSizeBeforeUpdate);
        BduAutor testBduAutor = bduAutorList.get(bduAutorList.size() - 1);
        assertThat(testBduAutor.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testBduAutor.getNumEnreg()).isEqualTo(UPDATED_NUM_ENREG);
        assertThat(testBduAutor.getNatureBeneficiaire()).isEqualTo(UPDATED_NATURE_BENEFICIAIRE);
        assertThat(testBduAutor.getPaysResidence()).isEqualTo(UPDATED_PAYS_RESIDENCE);
        assertThat(testBduAutor.getVille()).isEqualTo(UPDATED_VILLE);
        assertThat(testBduAutor.getStatutJuridique()).isEqualTo(UPDATED_STATUT_JURIDIQUE);
        assertThat(testBduAutor.getSexeBeneficiaire()).isEqualTo(UPDATED_SEXE_BENEFICIAIRE);
        assertThat(testBduAutor.getSecteurActivite()).isEqualTo(UPDATED_SECTEUR_ACTIVITE);
        assertThat(testBduAutor.getTailleEntreprise()).isEqualTo(UPDATED_TAILLE_ENTREPRISE);
        assertThat(testBduAutor.getMontantMaxAutorise()).isEqualTo(UPDATED_MONTANT_MAX_AUTORISE);
        assertThat(testBduAutor.getMontantMaxUtilise()).isEqualTo(UPDATED_MONTANT_MAX_UTILISE);
        assertThat(testBduAutor.getSoldeCompte()).isEqualTo(UPDATED_SOLDE_COMPTE);
        assertThat(testBduAutor.getTauxInteret()).isEqualTo(UPDATED_TAUX_INTERET);
    }

    @Test
    @Transactional
    public void updateNonExistingBduAutor() throws Exception {
        int databaseSizeBeforeUpdate = bduAutorRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBduAutorMockMvc.perform(put("/api/bdu-autors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bduAutor)))
            .andExpect(status().isBadRequest());

        // Validate the BduAutor in the database
        List<BduAutor> bduAutorList = bduAutorRepository.findAll();
        assertThat(bduAutorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBduAutor() throws Exception {
        // Initialize the database
        bduAutorService.save(bduAutor);

        int databaseSizeBeforeDelete = bduAutorRepository.findAll().size();

        // Delete the bduAutor
        restBduAutorMockMvc.perform(delete("/api/bdu-autors/{id}", bduAutor.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BduAutor> bduAutorList = bduAutorRepository.findAll();
        assertThat(bduAutorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
