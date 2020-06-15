package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.BduEffet;
import com.emard.api.repository.BduEffetRepository;
import com.emard.api.service.BduEffetService;

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
 * Integration tests for the {@link BduEffetResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BduEffetResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_ENREG = "AAAAAAAAAA";
    private static final String UPDATED_NUM_ENREG = "BBBBBBBBBB";

    private static final Integer DEFAULT_NATURE_DEPOSANT = 1;
    private static final Integer UPDATED_NATURE_DEPOSANT = 2;

    private static final Integer DEFAULT_PAYS_RESIDENCE = 1;
    private static final Integer UPDATED_PAYS_RESIDENCE = 2;

    private static final Integer DEFAULT_VILLE = 1;
    private static final Integer UPDATED_VILLE = 2;

    private static final Double DEFAULT_MONTANT_EFFET = 1D;
    private static final Double UPDATED_MONTANT_EFFET = 2D;

    private static final LocalDate DEFAULT_DATE_ESCOMPTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ESCOMPTE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_ECHEANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ECHEANCE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_NBRE_JOURS = 1;
    private static final Integer UPDATED_NBRE_JOURS = 2;

    private static final Double DEFAULT_TAUX_INTERET = 1D;
    private static final Double UPDATED_TAUX_INTERET = 2D;

    private static final Double DEFAULT_MONTANT_CHARGES = 1D;
    private static final Double UPDATED_MONTANT_CHARGES = 2D;

    @Autowired
    private BduEffetRepository bduEffetRepository;

    @Autowired
    private BduEffetService bduEffetService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBduEffetMockMvc;

    private BduEffet bduEffet;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BduEffet createEntity(EntityManager em) {
        BduEffet bduEffet = new BduEffet()
            .code(DEFAULT_CODE)
            .numEnreg(DEFAULT_NUM_ENREG)
            .natureDeposant(DEFAULT_NATURE_DEPOSANT)
            .paysResidence(DEFAULT_PAYS_RESIDENCE)
            .ville(DEFAULT_VILLE)
            .montantEffet(DEFAULT_MONTANT_EFFET)
            .dateEscompte(DEFAULT_DATE_ESCOMPTE)
            .dateEcheance(DEFAULT_DATE_ECHEANCE)
            .nbreJours(DEFAULT_NBRE_JOURS)
            .tauxInteret(DEFAULT_TAUX_INTERET)
            .montantCharges(DEFAULT_MONTANT_CHARGES);
        return bduEffet;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BduEffet createUpdatedEntity(EntityManager em) {
        BduEffet bduEffet = new BduEffet()
            .code(UPDATED_CODE)
            .numEnreg(UPDATED_NUM_ENREG)
            .natureDeposant(UPDATED_NATURE_DEPOSANT)
            .paysResidence(UPDATED_PAYS_RESIDENCE)
            .ville(UPDATED_VILLE)
            .montantEffet(UPDATED_MONTANT_EFFET)
            .dateEscompte(UPDATED_DATE_ESCOMPTE)
            .dateEcheance(UPDATED_DATE_ECHEANCE)
            .nbreJours(UPDATED_NBRE_JOURS)
            .tauxInteret(UPDATED_TAUX_INTERET)
            .montantCharges(UPDATED_MONTANT_CHARGES);
        return bduEffet;
    }

    @BeforeEach
    public void initTest() {
        bduEffet = createEntity(em);
    }

    @Test
    @Transactional
    public void createBduEffet() throws Exception {
        int databaseSizeBeforeCreate = bduEffetRepository.findAll().size();
        // Create the BduEffet
        restBduEffetMockMvc.perform(post("/api/bdu-effets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bduEffet)))
            .andExpect(status().isCreated());

        // Validate the BduEffet in the database
        List<BduEffet> bduEffetList = bduEffetRepository.findAll();
        assertThat(bduEffetList).hasSize(databaseSizeBeforeCreate + 1);
        BduEffet testBduEffet = bduEffetList.get(bduEffetList.size() - 1);
        assertThat(testBduEffet.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testBduEffet.getNumEnreg()).isEqualTo(DEFAULT_NUM_ENREG);
        assertThat(testBduEffet.getNatureDeposant()).isEqualTo(DEFAULT_NATURE_DEPOSANT);
        assertThat(testBduEffet.getPaysResidence()).isEqualTo(DEFAULT_PAYS_RESIDENCE);
        assertThat(testBduEffet.getVille()).isEqualTo(DEFAULT_VILLE);
        assertThat(testBduEffet.getMontantEffet()).isEqualTo(DEFAULT_MONTANT_EFFET);
        assertThat(testBduEffet.getDateEscompte()).isEqualTo(DEFAULT_DATE_ESCOMPTE);
        assertThat(testBduEffet.getDateEcheance()).isEqualTo(DEFAULT_DATE_ECHEANCE);
        assertThat(testBduEffet.getNbreJours()).isEqualTo(DEFAULT_NBRE_JOURS);
        assertThat(testBduEffet.getTauxInteret()).isEqualTo(DEFAULT_TAUX_INTERET);
        assertThat(testBduEffet.getMontantCharges()).isEqualTo(DEFAULT_MONTANT_CHARGES);
    }

    @Test
    @Transactional
    public void createBduEffetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bduEffetRepository.findAll().size();

        // Create the BduEffet with an existing ID
        bduEffet.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBduEffetMockMvc.perform(post("/api/bdu-effets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bduEffet)))
            .andExpect(status().isBadRequest());

        // Validate the BduEffet in the database
        List<BduEffet> bduEffetList = bduEffetRepository.findAll();
        assertThat(bduEffetList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBduEffets() throws Exception {
        // Initialize the database
        bduEffetRepository.saveAndFlush(bduEffet);

        // Get all the bduEffetList
        restBduEffetMockMvc.perform(get("/api/bdu-effets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bduEffet.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].numEnreg").value(hasItem(DEFAULT_NUM_ENREG)))
            .andExpect(jsonPath("$.[*].natureDeposant").value(hasItem(DEFAULT_NATURE_DEPOSANT)))
            .andExpect(jsonPath("$.[*].paysResidence").value(hasItem(DEFAULT_PAYS_RESIDENCE)))
            .andExpect(jsonPath("$.[*].ville").value(hasItem(DEFAULT_VILLE)))
            .andExpect(jsonPath("$.[*].montantEffet").value(hasItem(DEFAULT_MONTANT_EFFET.doubleValue())))
            .andExpect(jsonPath("$.[*].dateEscompte").value(hasItem(DEFAULT_DATE_ESCOMPTE.toString())))
            .andExpect(jsonPath("$.[*].dateEcheance").value(hasItem(DEFAULT_DATE_ECHEANCE.toString())))
            .andExpect(jsonPath("$.[*].nbreJours").value(hasItem(DEFAULT_NBRE_JOURS)))
            .andExpect(jsonPath("$.[*].tauxInteret").value(hasItem(DEFAULT_TAUX_INTERET.doubleValue())))
            .andExpect(jsonPath("$.[*].montantCharges").value(hasItem(DEFAULT_MONTANT_CHARGES.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getBduEffet() throws Exception {
        // Initialize the database
        bduEffetRepository.saveAndFlush(bduEffet);

        // Get the bduEffet
        restBduEffetMockMvc.perform(get("/api/bdu-effets/{id}", bduEffet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bduEffet.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.numEnreg").value(DEFAULT_NUM_ENREG))
            .andExpect(jsonPath("$.natureDeposant").value(DEFAULT_NATURE_DEPOSANT))
            .andExpect(jsonPath("$.paysResidence").value(DEFAULT_PAYS_RESIDENCE))
            .andExpect(jsonPath("$.ville").value(DEFAULT_VILLE))
            .andExpect(jsonPath("$.montantEffet").value(DEFAULT_MONTANT_EFFET.doubleValue()))
            .andExpect(jsonPath("$.dateEscompte").value(DEFAULT_DATE_ESCOMPTE.toString()))
            .andExpect(jsonPath("$.dateEcheance").value(DEFAULT_DATE_ECHEANCE.toString()))
            .andExpect(jsonPath("$.nbreJours").value(DEFAULT_NBRE_JOURS))
            .andExpect(jsonPath("$.tauxInteret").value(DEFAULT_TAUX_INTERET.doubleValue()))
            .andExpect(jsonPath("$.montantCharges").value(DEFAULT_MONTANT_CHARGES.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingBduEffet() throws Exception {
        // Get the bduEffet
        restBduEffetMockMvc.perform(get("/api/bdu-effets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBduEffet() throws Exception {
        // Initialize the database
        bduEffetService.save(bduEffet);

        int databaseSizeBeforeUpdate = bduEffetRepository.findAll().size();

        // Update the bduEffet
        BduEffet updatedBduEffet = bduEffetRepository.findById(bduEffet.getId()).get();
        // Disconnect from session so that the updates on updatedBduEffet are not directly saved in db
        em.detach(updatedBduEffet);
        updatedBduEffet
            .code(UPDATED_CODE)
            .numEnreg(UPDATED_NUM_ENREG)
            .natureDeposant(UPDATED_NATURE_DEPOSANT)
            .paysResidence(UPDATED_PAYS_RESIDENCE)
            .ville(UPDATED_VILLE)
            .montantEffet(UPDATED_MONTANT_EFFET)
            .dateEscompte(UPDATED_DATE_ESCOMPTE)
            .dateEcheance(UPDATED_DATE_ECHEANCE)
            .nbreJours(UPDATED_NBRE_JOURS)
            .tauxInteret(UPDATED_TAUX_INTERET)
            .montantCharges(UPDATED_MONTANT_CHARGES);

        restBduEffetMockMvc.perform(put("/api/bdu-effets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBduEffet)))
            .andExpect(status().isOk());

        // Validate the BduEffet in the database
        List<BduEffet> bduEffetList = bduEffetRepository.findAll();
        assertThat(bduEffetList).hasSize(databaseSizeBeforeUpdate);
        BduEffet testBduEffet = bduEffetList.get(bduEffetList.size() - 1);
        assertThat(testBduEffet.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testBduEffet.getNumEnreg()).isEqualTo(UPDATED_NUM_ENREG);
        assertThat(testBduEffet.getNatureDeposant()).isEqualTo(UPDATED_NATURE_DEPOSANT);
        assertThat(testBduEffet.getPaysResidence()).isEqualTo(UPDATED_PAYS_RESIDENCE);
        assertThat(testBduEffet.getVille()).isEqualTo(UPDATED_VILLE);
        assertThat(testBduEffet.getMontantEffet()).isEqualTo(UPDATED_MONTANT_EFFET);
        assertThat(testBduEffet.getDateEscompte()).isEqualTo(UPDATED_DATE_ESCOMPTE);
        assertThat(testBduEffet.getDateEcheance()).isEqualTo(UPDATED_DATE_ECHEANCE);
        assertThat(testBduEffet.getNbreJours()).isEqualTo(UPDATED_NBRE_JOURS);
        assertThat(testBduEffet.getTauxInteret()).isEqualTo(UPDATED_TAUX_INTERET);
        assertThat(testBduEffet.getMontantCharges()).isEqualTo(UPDATED_MONTANT_CHARGES);
    }

    @Test
    @Transactional
    public void updateNonExistingBduEffet() throws Exception {
        int databaseSizeBeforeUpdate = bduEffetRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBduEffetMockMvc.perform(put("/api/bdu-effets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bduEffet)))
            .andExpect(status().isBadRequest());

        // Validate the BduEffet in the database
        List<BduEffet> bduEffetList = bduEffetRepository.findAll();
        assertThat(bduEffetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBduEffet() throws Exception {
        // Initialize the database
        bduEffetService.save(bduEffet);

        int databaseSizeBeforeDelete = bduEffetRepository.findAll().size();

        // Delete the bduEffet
        restBduEffetMockMvc.perform(delete("/api/bdu-effets/{id}", bduEffet.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BduEffet> bduEffetList = bduEffetRepository.findAll();
        assertThat(bduEffetList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
