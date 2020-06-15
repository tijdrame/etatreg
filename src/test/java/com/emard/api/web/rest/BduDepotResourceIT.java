package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.BduDepot;
import com.emard.api.repository.BduDepotRepository;
import com.emard.api.service.BduDepotService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BduDepotResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BduDepotResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_ENREG = "AAAAAAAAAA";
    private static final String UPDATED_NUM_ENREG = "BBBBBBBBBB";

    private static final Integer DEFAULT_NATURE_DEPOT = 1;
    private static final Integer UPDATED_NATURE_DEPOT = 2;

    private static final Integer DEFAULT_PAYS_RESIDENCE = 1;
    private static final Integer UPDATED_PAYS_RESIDENCE = 2;

    private static final Integer DEFAULT_VILLE = 1;
    private static final Integer UPDATED_VILLE = 2;

    private static final Integer DEFAULT_NATURE_DEPOSANT = 1;
    private static final Integer UPDATED_NATURE_DEPOSANT = 2;

    private static final Integer DEFAULT_STATUT_JURIDIQUE = 1;
    private static final Integer UPDATED_STATUT_JURIDIQUE = 2;

    private static final Integer DEFAULT_SEXE_DEPOSANT = 1;
    private static final Integer UPDATED_SEXE_DEPOSANT = 2;

    private static final Integer DEFAULT_SECTEUR_ACTIVITE = 1;
    private static final Integer UPDATED_SECTEUR_ACTIVITE = 2;

    private static final Integer DEFAULT_TAILLE_ENTREPRISE = 1;
    private static final Integer UPDATED_TAILLE_ENTREPRISE = 2;

    private static final LocalDate DEFAULT_DATE_DEPOT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEPOT = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_TERME_DEPOT = 1;
    private static final Integer UPDATED_TERME_DEPOT = 2;

    private static final Double DEFAULT_MONTANT_DEPOT = 1D;
    private static final Double UPDATED_MONTANT_DEPOT = 2D;

    private static final Double DEFAULT_TAUX_RENUMERATION = 1D;
    private static final Double UPDATED_TAUX_RENUMERATION = 2D;

    @Autowired
    private BduDepotRepository bduDepotRepository;

    @Autowired
    private BduDepotService bduDepotService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBduDepotMockMvc;

    private BduDepot bduDepot;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BduDepot createEntity(EntityManager em) {
        BduDepot bduDepot = new BduDepot()
            .code(DEFAULT_CODE)
            .numEnreg(DEFAULT_NUM_ENREG)
            .natureDepot(DEFAULT_NATURE_DEPOT)
            .paysResidence(DEFAULT_PAYS_RESIDENCE)
            .ville(DEFAULT_VILLE)
            .natureDeposant(DEFAULT_NATURE_DEPOSANT)
            .statutJuridique(DEFAULT_STATUT_JURIDIQUE)
            .sexeDeposant(DEFAULT_SEXE_DEPOSANT)
            .secteurActivite(DEFAULT_SECTEUR_ACTIVITE)
            .tailleEntreprise(DEFAULT_TAILLE_ENTREPRISE)
            .dateDepot(DEFAULT_DATE_DEPOT)
            .termeDepot(DEFAULT_TERME_DEPOT)
            .montantDepot(DEFAULT_MONTANT_DEPOT)
            .tauxRenumeration(DEFAULT_TAUX_RENUMERATION);
        return bduDepot;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BduDepot createUpdatedEntity(EntityManager em) {
        BduDepot bduDepot = new BduDepot()
            .code(UPDATED_CODE)
            .numEnreg(UPDATED_NUM_ENREG)
            .natureDepot(UPDATED_NATURE_DEPOT)
            .paysResidence(UPDATED_PAYS_RESIDENCE)
            .ville(UPDATED_VILLE)
            .natureDeposant(UPDATED_NATURE_DEPOSANT)
            .statutJuridique(UPDATED_STATUT_JURIDIQUE)
            .sexeDeposant(UPDATED_SEXE_DEPOSANT)
            .secteurActivite(UPDATED_SECTEUR_ACTIVITE)
            .tailleEntreprise(UPDATED_TAILLE_ENTREPRISE)
            .dateDepot(UPDATED_DATE_DEPOT)
            .termeDepot(UPDATED_TERME_DEPOT)
            .montantDepot(UPDATED_MONTANT_DEPOT)
            .tauxRenumeration(UPDATED_TAUX_RENUMERATION);
        return bduDepot;
    }

    @BeforeEach
    public void initTest() {
        bduDepot = createEntity(em);
    }

    @Test
    @Transactional
    public void createBduDepot() throws Exception {
        int databaseSizeBeforeCreate = bduDepotRepository.findAll().size();
        // Create the BduDepot
        restBduDepotMockMvc.perform(post("/api/bdu-depots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bduDepot)))
            .andExpect(status().isCreated());

        // Validate the BduDepot in the database
        List<BduDepot> bduDepotList = bduDepotRepository.findAll();
        assertThat(bduDepotList).hasSize(databaseSizeBeforeCreate + 1);
        BduDepot testBduDepot = bduDepotList.get(bduDepotList.size() - 1);
        assertThat(testBduDepot.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testBduDepot.getNumEnreg()).isEqualTo(DEFAULT_NUM_ENREG);
        assertThat(testBduDepot.getNatureDepot()).isEqualTo(DEFAULT_NATURE_DEPOT);
        assertThat(testBduDepot.getPaysResidence()).isEqualTo(DEFAULT_PAYS_RESIDENCE);
        assertThat(testBduDepot.getVille()).isEqualTo(DEFAULT_VILLE);
        assertThat(testBduDepot.getNatureDeposant()).isEqualTo(DEFAULT_NATURE_DEPOSANT);
        assertThat(testBduDepot.getStatutJuridique()).isEqualTo(DEFAULT_STATUT_JURIDIQUE);
        assertThat(testBduDepot.getSexeDeposant()).isEqualTo(DEFAULT_SEXE_DEPOSANT);
        assertThat(testBduDepot.getSecteurActivite()).isEqualTo(DEFAULT_SECTEUR_ACTIVITE);
        assertThat(testBduDepot.getTailleEntreprise()).isEqualTo(DEFAULT_TAILLE_ENTREPRISE);
        assertThat(testBduDepot.getDateDepot()).isEqualTo(DEFAULT_DATE_DEPOT);
        assertThat(testBduDepot.getTermeDepot()).isEqualTo(DEFAULT_TERME_DEPOT);
        assertThat(testBduDepot.getMontantDepot()).isEqualTo(DEFAULT_MONTANT_DEPOT);
        assertThat(testBduDepot.getTauxRenumeration()).isEqualTo(DEFAULT_TAUX_RENUMERATION);
    }

    @Test
    @Transactional
    public void createBduDepotWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bduDepotRepository.findAll().size();

        // Create the BduDepot with an existing ID
        bduDepot.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBduDepotMockMvc.perform(post("/api/bdu-depots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bduDepot)))
            .andExpect(status().isBadRequest());

        // Validate the BduDepot in the database
        List<BduDepot> bduDepotList = bduDepotRepository.findAll();
        assertThat(bduDepotList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBduDepots() throws Exception {
        // Initialize the database
        bduDepotRepository.saveAndFlush(bduDepot);

        // Get all the bduDepotList
        restBduDepotMockMvc.perform(get("/api/bdu-depots?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bduDepot.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].numEnreg").value(hasItem(DEFAULT_NUM_ENREG)))
            .andExpect(jsonPath("$.[*].natureDepot").value(hasItem(DEFAULT_NATURE_DEPOT)))
            .andExpect(jsonPath("$.[*].paysResidence").value(hasItem(DEFAULT_PAYS_RESIDENCE)))
            .andExpect(jsonPath("$.[*].ville").value(hasItem(DEFAULT_VILLE)))
            .andExpect(jsonPath("$.[*].natureDeposant").value(hasItem(DEFAULT_NATURE_DEPOSANT)))
            .andExpect(jsonPath("$.[*].statutJuridique").value(hasItem(DEFAULT_STATUT_JURIDIQUE)))
            .andExpect(jsonPath("$.[*].sexeDeposant").value(hasItem(DEFAULT_SEXE_DEPOSANT)))
            .andExpect(jsonPath("$.[*].secteurActivite").value(hasItem(DEFAULT_SECTEUR_ACTIVITE)))
            .andExpect(jsonPath("$.[*].tailleEntreprise").value(hasItem(DEFAULT_TAILLE_ENTREPRISE)))
            .andExpect(jsonPath("$.[*].dateDepot").value(hasItem(DEFAULT_DATE_DEPOT.toString())))
            .andExpect(jsonPath("$.[*].termeDepot").value(hasItem(DEFAULT_TERME_DEPOT)))
            .andExpect(jsonPath("$.[*].montantDepot").value(hasItem(DEFAULT_MONTANT_DEPOT.doubleValue())))
            .andExpect(jsonPath("$.[*].tauxRenumeration").value(hasItem(DEFAULT_TAUX_RENUMERATION.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getBduDepot() throws Exception {
        // Initialize the database
        bduDepotRepository.saveAndFlush(bduDepot);

        // Get the bduDepot
        restBduDepotMockMvc.perform(get("/api/bdu-depots/{id}", bduDepot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bduDepot.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.numEnreg").value(DEFAULT_NUM_ENREG))
            .andExpect(jsonPath("$.natureDepot").value(DEFAULT_NATURE_DEPOT))
            .andExpect(jsonPath("$.paysResidence").value(DEFAULT_PAYS_RESIDENCE))
            .andExpect(jsonPath("$.ville").value(DEFAULT_VILLE))
            .andExpect(jsonPath("$.natureDeposant").value(DEFAULT_NATURE_DEPOSANT))
            .andExpect(jsonPath("$.statutJuridique").value(DEFAULT_STATUT_JURIDIQUE))
            .andExpect(jsonPath("$.sexeDeposant").value(DEFAULT_SEXE_DEPOSANT))
            .andExpect(jsonPath("$.secteurActivite").value(DEFAULT_SECTEUR_ACTIVITE))
            .andExpect(jsonPath("$.tailleEntreprise").value(DEFAULT_TAILLE_ENTREPRISE))
            .andExpect(jsonPath("$.dateDepot").value(DEFAULT_DATE_DEPOT.toString()))
            .andExpect(jsonPath("$.termeDepot").value(DEFAULT_TERME_DEPOT))
            .andExpect(jsonPath("$.montantDepot").value(DEFAULT_MONTANT_DEPOT.doubleValue()))
            .andExpect(jsonPath("$.tauxRenumeration").value(DEFAULT_TAUX_RENUMERATION.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingBduDepot() throws Exception {
        // Get the bduDepot
        restBduDepotMockMvc.perform(get("/api/bdu-depots/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBduDepot() throws Exception {
        // Initialize the database
        bduDepotService.save(bduDepot);

        int databaseSizeBeforeUpdate = bduDepotRepository.findAll().size();

        // Update the bduDepot
        BduDepot updatedBduDepot = bduDepotRepository.findById(bduDepot.getId()).get();
        // Disconnect from session so that the updates on updatedBduDepot are not directly saved in db
        em.detach(updatedBduDepot);
        updatedBduDepot
            .code(UPDATED_CODE)
            .numEnreg(UPDATED_NUM_ENREG)
            .natureDepot(UPDATED_NATURE_DEPOT)
            .paysResidence(UPDATED_PAYS_RESIDENCE)
            .ville(UPDATED_VILLE)
            .natureDeposant(UPDATED_NATURE_DEPOSANT)
            .statutJuridique(UPDATED_STATUT_JURIDIQUE)
            .sexeDeposant(UPDATED_SEXE_DEPOSANT)
            .secteurActivite(UPDATED_SECTEUR_ACTIVITE)
            .tailleEntreprise(UPDATED_TAILLE_ENTREPRISE)
            .dateDepot(UPDATED_DATE_DEPOT)
            .termeDepot(UPDATED_TERME_DEPOT)
            .montantDepot(UPDATED_MONTANT_DEPOT)
            .tauxRenumeration(UPDATED_TAUX_RENUMERATION);

        restBduDepotMockMvc.perform(put("/api/bdu-depots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBduDepot)))
            .andExpect(status().isOk());

        // Validate the BduDepot in the database
        List<BduDepot> bduDepotList = bduDepotRepository.findAll();
        assertThat(bduDepotList).hasSize(databaseSizeBeforeUpdate);
        BduDepot testBduDepot = bduDepotList.get(bduDepotList.size() - 1);
        assertThat(testBduDepot.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testBduDepot.getNumEnreg()).isEqualTo(UPDATED_NUM_ENREG);
        assertThat(testBduDepot.getNatureDepot()).isEqualTo(UPDATED_NATURE_DEPOT);
        assertThat(testBduDepot.getPaysResidence()).isEqualTo(UPDATED_PAYS_RESIDENCE);
        assertThat(testBduDepot.getVille()).isEqualTo(UPDATED_VILLE);
        assertThat(testBduDepot.getNatureDeposant()).isEqualTo(UPDATED_NATURE_DEPOSANT);
        assertThat(testBduDepot.getStatutJuridique()).isEqualTo(UPDATED_STATUT_JURIDIQUE);
        assertThat(testBduDepot.getSexeDeposant()).isEqualTo(UPDATED_SEXE_DEPOSANT);
        assertThat(testBduDepot.getSecteurActivite()).isEqualTo(UPDATED_SECTEUR_ACTIVITE);
        assertThat(testBduDepot.getTailleEntreprise()).isEqualTo(UPDATED_TAILLE_ENTREPRISE);
        assertThat(testBduDepot.getDateDepot()).isEqualTo(UPDATED_DATE_DEPOT);
        assertThat(testBduDepot.getTermeDepot()).isEqualTo(UPDATED_TERME_DEPOT);
        assertThat(testBduDepot.getMontantDepot()).isEqualTo(UPDATED_MONTANT_DEPOT);
        assertThat(testBduDepot.getTauxRenumeration()).isEqualTo(UPDATED_TAUX_RENUMERATION);
    }

    @Test
    @Transactional
    public void updateNonExistingBduDepot() throws Exception {
        int databaseSizeBeforeUpdate = bduDepotRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBduDepotMockMvc.perform(put("/api/bdu-depots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bduDepot)))
            .andExpect(status().isBadRequest());

        // Validate the BduDepot in the database
        List<BduDepot> bduDepotList = bduDepotRepository.findAll();
        assertThat(bduDepotList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBduDepot() throws Exception {
        // Initialize the database
        bduDepotService.save(bduDepot);

        int databaseSizeBeforeDelete = bduDepotRepository.findAll().size();

        // Delete the bduDepot
        restBduDepotMockMvc.perform(delete("/api/bdu-depots/{id}", bduDepot.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BduDepot> bduDepotList = bduDepotRepository.findAll();
        assertThat(bduDepotList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
