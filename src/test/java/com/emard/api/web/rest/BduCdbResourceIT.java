package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.BduCdb;
import com.emard.api.repository.BduCdbRepository;
import com.emard.api.service.BduCdbService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BduCdbResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BduCdbResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_ENREG = "AAAAAAAAAA";
    private static final String UPDATED_NUM_ENREG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_TRAITEMENT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TRAITEMENT = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_TYPE_CREDIT = 1;
    private static final Integer UPDATED_TYPE_CREDIT = 2;

    private static final Integer DEFAULT_OBJET_CREDIT = 1;
    private static final Integer UPDATED_OBJET_CREDIT = 2;

    private static final Double DEFAULT_MONTANT_CREDIT_DEMANDE = 1D;
    private static final Double UPDATED_MONTANT_CREDIT_DEMANDE = 2D;

    private static final Integer DEFAULT_DUREE_CREDIT_DEMANDE = 1;
    private static final Integer UPDATED_DUREE_CREDIT_DEMANDE = 2;

    private static final String DEFAULT_TAUX_INTERET_SOUHAITE = "AAAAAAAAAA";
    private static final String UPDATED_TAUX_INTERET_SOUHAITE = "BBBBBBBBBB";

    private static final String DEFAULT_NATURE_DEBITEUR = "AAAAAAAAAA";
    private static final String UPDATED_NATURE_DEBITEUR = "BBBBBBBBBB";

    private static final Integer DEFAULT_PAYS_RESIDENCE = 1;
    private static final Integer UPDATED_PAYS_RESIDENCE = 2;

    private static final Integer DEFAULT_VILLE = 1;
    private static final Integer UPDATED_VILLE = 2;

    private static final Integer DEFAULT_STATUT_JURIDIQUE = 1;
    private static final Integer UPDATED_STATUT_JURIDIQUE = 2;

    private static final String DEFAULT_SEXE_DEBITEUR = "AAAAAAAAAA";
    private static final String UPDATED_SEXE_DEBITEUR = "BBBBBBBBBB";

    private static final String DEFAULT_SECTEUR_ACTIVITE = "AAAAAAAAAA";
    private static final String UPDATED_SECTEUR_ACTIVITE = "BBBBBBBBBB";

    private static final String DEFAULT_TAILLE_ENTREPRISE = "AAAAAAAAAA";
    private static final String UPDATED_TAILLE_ENTREPRISE = "BBBBBBBBBB";

    private static final Integer DEFAULT_DECISION = 1;
    private static final Integer UPDATED_DECISION = 2;

    private static final String DEFAULT_MOTIF_REJET = "AAAAAAAAAA";
    private static final String UPDATED_MOTIF_REJET = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_CREDIT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CREDIT = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_MONTANT_CREDIT_ACCORDE = 1D;
    private static final Double UPDATED_MONTANT_CREDIT_ACCORDE = 2D;

    private static final Double DEFAULT_DUREE_CREDIT_ACCORDE = 1D;
    private static final Double UPDATED_DUREE_CREDIT_ACCORDE = 2D;

    private static final Double DEFAULT_PERIODICITE_REMBOURSEMENT = 1D;
    private static final Double UPDATED_PERIODICITE_REMBOURSEMENT = 2D;

    private static final String DEFAULT_TAUX_INTERET_APPLIQUE = "AAAAAAAAAA";
    private static final String UPDATED_TAUX_INTERET_APPLIQUE = "BBBBBBBBBB";

    private static final Double DEFAULT_MONTANT_INTERET = 1D;
    private static final Double UPDATED_MONTANT_INTERET = 2D;

    private static final Double DEFAULT_MONTANT_CHARGES = 1D;
    private static final Double UPDATED_MONTANT_CHARGES = 2D;

    private static final Double DEFAULT_MONTANT_COMMISSION = 1D;
    private static final Double UPDATED_MONTANT_COMMISSION = 2D;

    private static final Double DEFAULT_AUTRES_COMMISSIONS = 1D;
    private static final Double UPDATED_AUTRES_COMMISSIONS = 2D;

    @Autowired
    private BduCdbRepository bduCdbRepository;

    @Autowired
    private BduCdbService bduCdbService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBduCdbMockMvc;

    private BduCdb bduCdb;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BduCdb createEntity(EntityManager em) {
        BduCdb bduCdb = new BduCdb()
            .code(DEFAULT_CODE)
            .numEnreg(DEFAULT_NUM_ENREG)
            .dateTraitement(DEFAULT_DATE_TRAITEMENT)
            .typeCredit(DEFAULT_TYPE_CREDIT)
            .objetCredit(DEFAULT_OBJET_CREDIT)
            .montantCreditDemande(DEFAULT_MONTANT_CREDIT_DEMANDE)
            .dureeCreditDemande(DEFAULT_DUREE_CREDIT_DEMANDE)
            .tauxInteretSouhaite(DEFAULT_TAUX_INTERET_SOUHAITE)
            .natureDebiteur(DEFAULT_NATURE_DEBITEUR)
            .paysResidence(DEFAULT_PAYS_RESIDENCE)
            .ville(DEFAULT_VILLE)
            .statutJuridique(DEFAULT_STATUT_JURIDIQUE)
            .sexeDebiteur(DEFAULT_SEXE_DEBITEUR)
            .secteurActivite(DEFAULT_SECTEUR_ACTIVITE)
            .tailleEntreprise(DEFAULT_TAILLE_ENTREPRISE)
            .decision(DEFAULT_DECISION)
            .motifRejet(DEFAULT_MOTIF_REJET)
            .dateCredit(DEFAULT_DATE_CREDIT)
            .montantCreditAccorde(DEFAULT_MONTANT_CREDIT_ACCORDE)
            .dureeCreditAccorde(DEFAULT_DUREE_CREDIT_ACCORDE)
            .periodiciteRemboursement(DEFAULT_PERIODICITE_REMBOURSEMENT)
            .tauxInteretApplique(DEFAULT_TAUX_INTERET_APPLIQUE)
            .montantInteret(DEFAULT_MONTANT_INTERET)
            .montantCharges(DEFAULT_MONTANT_CHARGES)
            .montantCommission(DEFAULT_MONTANT_COMMISSION)
            .autresCommissions(DEFAULT_AUTRES_COMMISSIONS);
        return bduCdb;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BduCdb createUpdatedEntity(EntityManager em) {
        BduCdb bduCdb = new BduCdb()
            .code(UPDATED_CODE)
            .numEnreg(UPDATED_NUM_ENREG)
            .dateTraitement(UPDATED_DATE_TRAITEMENT)
            .typeCredit(UPDATED_TYPE_CREDIT)
            .objetCredit(UPDATED_OBJET_CREDIT)
            .montantCreditDemande(UPDATED_MONTANT_CREDIT_DEMANDE)
            .dureeCreditDemande(UPDATED_DUREE_CREDIT_DEMANDE)
            .tauxInteretSouhaite(UPDATED_TAUX_INTERET_SOUHAITE)
            .natureDebiteur(UPDATED_NATURE_DEBITEUR)
            .paysResidence(UPDATED_PAYS_RESIDENCE)
            .ville(UPDATED_VILLE)
            .statutJuridique(UPDATED_STATUT_JURIDIQUE)
            .sexeDebiteur(UPDATED_SEXE_DEBITEUR)
            .secteurActivite(UPDATED_SECTEUR_ACTIVITE)
            .tailleEntreprise(UPDATED_TAILLE_ENTREPRISE)
            .decision(UPDATED_DECISION)
            .motifRejet(UPDATED_MOTIF_REJET)
            .dateCredit(UPDATED_DATE_CREDIT)
            .montantCreditAccorde(UPDATED_MONTANT_CREDIT_ACCORDE)
            .dureeCreditAccorde(UPDATED_DUREE_CREDIT_ACCORDE)
            .periodiciteRemboursement(UPDATED_PERIODICITE_REMBOURSEMENT)
            .tauxInteretApplique(UPDATED_TAUX_INTERET_APPLIQUE)
            .montantInteret(UPDATED_MONTANT_INTERET)
            .montantCharges(UPDATED_MONTANT_CHARGES)
            .montantCommission(UPDATED_MONTANT_COMMISSION)
            .autresCommissions(UPDATED_AUTRES_COMMISSIONS);
        return bduCdb;
    }

    @BeforeEach
    public void initTest() {
        bduCdb = createEntity(em);
    }

    @Test
    @Transactional
    public void createBduCdb() throws Exception {
        int databaseSizeBeforeCreate = bduCdbRepository.findAll().size();
        // Create the BduCdb
        restBduCdbMockMvc.perform(post("/api/bdu-cdbs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bduCdb)))
            .andExpect(status().isCreated());

        // Validate the BduCdb in the database
        List<BduCdb> bduCdbList = bduCdbRepository.findAll();
        assertThat(bduCdbList).hasSize(databaseSizeBeforeCreate + 1);
        BduCdb testBduCdb = bduCdbList.get(bduCdbList.size() - 1);
        assertThat(testBduCdb.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testBduCdb.getNumEnreg()).isEqualTo(DEFAULT_NUM_ENREG);
        assertThat(testBduCdb.getDateTraitement()).isEqualTo(DEFAULT_DATE_TRAITEMENT);
        assertThat(testBduCdb.getTypeCredit()).isEqualTo(DEFAULT_TYPE_CREDIT);
        assertThat(testBduCdb.getObjetCredit()).isEqualTo(DEFAULT_OBJET_CREDIT);
        assertThat(testBduCdb.getMontantCreditDemande()).isEqualTo(DEFAULT_MONTANT_CREDIT_DEMANDE);
        assertThat(testBduCdb.getDureeCreditDemande()).isEqualTo(DEFAULT_DUREE_CREDIT_DEMANDE);
        assertThat(testBduCdb.getTauxInteretSouhaite()).isEqualTo(DEFAULT_TAUX_INTERET_SOUHAITE);
        assertThat(testBduCdb.getNatureDebiteur()).isEqualTo(DEFAULT_NATURE_DEBITEUR);
        assertThat(testBduCdb.getPaysResidence()).isEqualTo(DEFAULT_PAYS_RESIDENCE);
        assertThat(testBduCdb.getVille()).isEqualTo(DEFAULT_VILLE);
        assertThat(testBduCdb.getStatutJuridique()).isEqualTo(DEFAULT_STATUT_JURIDIQUE);
        assertThat(testBduCdb.getSexeDebiteur()).isEqualTo(DEFAULT_SEXE_DEBITEUR);
        assertThat(testBduCdb.getSecteurActivite()).isEqualTo(DEFAULT_SECTEUR_ACTIVITE);
        assertThat(testBduCdb.getTailleEntreprise()).isEqualTo(DEFAULT_TAILLE_ENTREPRISE);
        assertThat(testBduCdb.getDecision()).isEqualTo(DEFAULT_DECISION);
        assertThat(testBduCdb.getMotifRejet()).isEqualTo(DEFAULT_MOTIF_REJET);
        assertThat(testBduCdb.getDateCredit()).isEqualTo(DEFAULT_DATE_CREDIT);
        assertThat(testBduCdb.getMontantCreditAccorde()).isEqualTo(DEFAULT_MONTANT_CREDIT_ACCORDE);
        assertThat(testBduCdb.getDureeCreditAccorde()).isEqualTo(DEFAULT_DUREE_CREDIT_ACCORDE);
        assertThat(testBduCdb.getPeriodiciteRemboursement()).isEqualTo(DEFAULT_PERIODICITE_REMBOURSEMENT);
        assertThat(testBduCdb.getTauxInteretApplique()).isEqualTo(DEFAULT_TAUX_INTERET_APPLIQUE);
        assertThat(testBduCdb.getMontantInteret()).isEqualTo(DEFAULT_MONTANT_INTERET);
        assertThat(testBduCdb.getMontantCharges()).isEqualTo(DEFAULT_MONTANT_CHARGES);
        assertThat(testBduCdb.getMontantCommission()).isEqualTo(DEFAULT_MONTANT_COMMISSION);
        assertThat(testBduCdb.getAutresCommissions()).isEqualTo(DEFAULT_AUTRES_COMMISSIONS);
    }

    @Test
    @Transactional
    public void createBduCdbWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bduCdbRepository.findAll().size();

        // Create the BduCdb with an existing ID
        bduCdb.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBduCdbMockMvc.perform(post("/api/bdu-cdbs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bduCdb)))
            .andExpect(status().isBadRequest());

        // Validate the BduCdb in the database
        List<BduCdb> bduCdbList = bduCdbRepository.findAll();
        assertThat(bduCdbList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBduCdbs() throws Exception {
        // Initialize the database
        bduCdbRepository.saveAndFlush(bduCdb);

        // Get all the bduCdbList
        restBduCdbMockMvc.perform(get("/api/bdu-cdbs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bduCdb.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].numEnreg").value(hasItem(DEFAULT_NUM_ENREG)))
            .andExpect(jsonPath("$.[*].dateTraitement").value(hasItem(DEFAULT_DATE_TRAITEMENT.toString())))
            .andExpect(jsonPath("$.[*].typeCredit").value(hasItem(DEFAULT_TYPE_CREDIT)))
            .andExpect(jsonPath("$.[*].objetCredit").value(hasItem(DEFAULT_OBJET_CREDIT)))
            .andExpect(jsonPath("$.[*].montantCreditDemande").value(hasItem(DEFAULT_MONTANT_CREDIT_DEMANDE.doubleValue())))
            .andExpect(jsonPath("$.[*].dureeCreditDemande").value(hasItem(DEFAULT_DUREE_CREDIT_DEMANDE)))
            .andExpect(jsonPath("$.[*].tauxInteretSouhaite").value(hasItem(DEFAULT_TAUX_INTERET_SOUHAITE)))
            .andExpect(jsonPath("$.[*].natureDebiteur").value(hasItem(DEFAULT_NATURE_DEBITEUR)))
            .andExpect(jsonPath("$.[*].paysResidence").value(hasItem(DEFAULT_PAYS_RESIDENCE)))
            .andExpect(jsonPath("$.[*].ville").value(hasItem(DEFAULT_VILLE)))
            .andExpect(jsonPath("$.[*].statutJuridique").value(hasItem(DEFAULT_STATUT_JURIDIQUE)))
            .andExpect(jsonPath("$.[*].sexeDebiteur").value(hasItem(DEFAULT_SEXE_DEBITEUR)))
            .andExpect(jsonPath("$.[*].secteurActivite").value(hasItem(DEFAULT_SECTEUR_ACTIVITE)))
            .andExpect(jsonPath("$.[*].tailleEntreprise").value(hasItem(DEFAULT_TAILLE_ENTREPRISE)))
            .andExpect(jsonPath("$.[*].decision").value(hasItem(DEFAULT_DECISION)))
            .andExpect(jsonPath("$.[*].motifRejet").value(hasItem(DEFAULT_MOTIF_REJET)))
            .andExpect(jsonPath("$.[*].dateCredit").value(hasItem(DEFAULT_DATE_CREDIT.toString())))
            .andExpect(jsonPath("$.[*].montantCreditAccorde").value(hasItem(DEFAULT_MONTANT_CREDIT_ACCORDE.doubleValue())))
            .andExpect(jsonPath("$.[*].dureeCreditAccorde").value(hasItem(DEFAULT_DUREE_CREDIT_ACCORDE.doubleValue())))
            .andExpect(jsonPath("$.[*].periodiciteRemboursement").value(hasItem(DEFAULT_PERIODICITE_REMBOURSEMENT.doubleValue())))
            .andExpect(jsonPath("$.[*].tauxInteretApplique").value(hasItem(DEFAULT_TAUX_INTERET_APPLIQUE)))
            .andExpect(jsonPath("$.[*].montantInteret").value(hasItem(DEFAULT_MONTANT_INTERET.doubleValue())))
            .andExpect(jsonPath("$.[*].montantCharges").value(hasItem(DEFAULT_MONTANT_CHARGES.doubleValue())))
            .andExpect(jsonPath("$.[*].montantCommission").value(hasItem(DEFAULT_MONTANT_COMMISSION.doubleValue())))
            .andExpect(jsonPath("$.[*].autresCommissions").value(hasItem(DEFAULT_AUTRES_COMMISSIONS.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getBduCdb() throws Exception {
        // Initialize the database
        bduCdbRepository.saveAndFlush(bduCdb);

        // Get the bduCdb
        restBduCdbMockMvc.perform(get("/api/bdu-cdbs/{id}", bduCdb.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bduCdb.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.numEnreg").value(DEFAULT_NUM_ENREG))
            .andExpect(jsonPath("$.dateTraitement").value(DEFAULT_DATE_TRAITEMENT.toString()))
            .andExpect(jsonPath("$.typeCredit").value(DEFAULT_TYPE_CREDIT))
            .andExpect(jsonPath("$.objetCredit").value(DEFAULT_OBJET_CREDIT))
            .andExpect(jsonPath("$.montantCreditDemande").value(DEFAULT_MONTANT_CREDIT_DEMANDE.doubleValue()))
            .andExpect(jsonPath("$.dureeCreditDemande").value(DEFAULT_DUREE_CREDIT_DEMANDE))
            .andExpect(jsonPath("$.tauxInteretSouhaite").value(DEFAULT_TAUX_INTERET_SOUHAITE))
            .andExpect(jsonPath("$.natureDebiteur").value(DEFAULT_NATURE_DEBITEUR))
            .andExpect(jsonPath("$.paysResidence").value(DEFAULT_PAYS_RESIDENCE))
            .andExpect(jsonPath("$.ville").value(DEFAULT_VILLE))
            .andExpect(jsonPath("$.statutJuridique").value(DEFAULT_STATUT_JURIDIQUE))
            .andExpect(jsonPath("$.sexeDebiteur").value(DEFAULT_SEXE_DEBITEUR))
            .andExpect(jsonPath("$.secteurActivite").value(DEFAULT_SECTEUR_ACTIVITE))
            .andExpect(jsonPath("$.tailleEntreprise").value(DEFAULT_TAILLE_ENTREPRISE))
            .andExpect(jsonPath("$.decision").value(DEFAULT_DECISION))
            .andExpect(jsonPath("$.motifRejet").value(DEFAULT_MOTIF_REJET))
            .andExpect(jsonPath("$.dateCredit").value(DEFAULT_DATE_CREDIT.toString()))
            .andExpect(jsonPath("$.montantCreditAccorde").value(DEFAULT_MONTANT_CREDIT_ACCORDE.doubleValue()))
            .andExpect(jsonPath("$.dureeCreditAccorde").value(DEFAULT_DUREE_CREDIT_ACCORDE.doubleValue()))
            .andExpect(jsonPath("$.periodiciteRemboursement").value(DEFAULT_PERIODICITE_REMBOURSEMENT.doubleValue()))
            .andExpect(jsonPath("$.tauxInteretApplique").value(DEFAULT_TAUX_INTERET_APPLIQUE))
            .andExpect(jsonPath("$.montantInteret").value(DEFAULT_MONTANT_INTERET.doubleValue()))
            .andExpect(jsonPath("$.montantCharges").value(DEFAULT_MONTANT_CHARGES.doubleValue()))
            .andExpect(jsonPath("$.montantCommission").value(DEFAULT_MONTANT_COMMISSION.doubleValue()))
            .andExpect(jsonPath("$.autresCommissions").value(DEFAULT_AUTRES_COMMISSIONS.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingBduCdb() throws Exception {
        // Get the bduCdb
        restBduCdbMockMvc.perform(get("/api/bdu-cdbs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBduCdb() throws Exception {
        // Initialize the database
        bduCdbService.save(bduCdb);

        int databaseSizeBeforeUpdate = bduCdbRepository.findAll().size();

        // Update the bduCdb
        BduCdb updatedBduCdb = bduCdbRepository.findById(bduCdb.getId()).get();
        // Disconnect from session so that the updates on updatedBduCdb are not directly saved in db
        em.detach(updatedBduCdb);
        updatedBduCdb
            .code(UPDATED_CODE)
            .numEnreg(UPDATED_NUM_ENREG)
            .dateTraitement(UPDATED_DATE_TRAITEMENT)
            .typeCredit(UPDATED_TYPE_CREDIT)
            .objetCredit(UPDATED_OBJET_CREDIT)
            .montantCreditDemande(UPDATED_MONTANT_CREDIT_DEMANDE)
            .dureeCreditDemande(UPDATED_DUREE_CREDIT_DEMANDE)
            .tauxInteretSouhaite(UPDATED_TAUX_INTERET_SOUHAITE)
            .natureDebiteur(UPDATED_NATURE_DEBITEUR)
            .paysResidence(UPDATED_PAYS_RESIDENCE)
            .ville(UPDATED_VILLE)
            .statutJuridique(UPDATED_STATUT_JURIDIQUE)
            .sexeDebiteur(UPDATED_SEXE_DEBITEUR)
            .secteurActivite(UPDATED_SECTEUR_ACTIVITE)
            .tailleEntreprise(UPDATED_TAILLE_ENTREPRISE)
            .decision(UPDATED_DECISION)
            .motifRejet(UPDATED_MOTIF_REJET)
            .dateCredit(UPDATED_DATE_CREDIT)
            .montantCreditAccorde(UPDATED_MONTANT_CREDIT_ACCORDE)
            .dureeCreditAccorde(UPDATED_DUREE_CREDIT_ACCORDE)
            .periodiciteRemboursement(UPDATED_PERIODICITE_REMBOURSEMENT)
            .tauxInteretApplique(UPDATED_TAUX_INTERET_APPLIQUE)
            .montantInteret(UPDATED_MONTANT_INTERET)
            .montantCharges(UPDATED_MONTANT_CHARGES)
            .montantCommission(UPDATED_MONTANT_COMMISSION)
            .autresCommissions(UPDATED_AUTRES_COMMISSIONS);

        restBduCdbMockMvc.perform(put("/api/bdu-cdbs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBduCdb)))
            .andExpect(status().isOk());

        // Validate the BduCdb in the database
        List<BduCdb> bduCdbList = bduCdbRepository.findAll();
        assertThat(bduCdbList).hasSize(databaseSizeBeforeUpdate);
        BduCdb testBduCdb = bduCdbList.get(bduCdbList.size() - 1);
        assertThat(testBduCdb.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testBduCdb.getNumEnreg()).isEqualTo(UPDATED_NUM_ENREG);
        assertThat(testBduCdb.getDateTraitement()).isEqualTo(UPDATED_DATE_TRAITEMENT);
        assertThat(testBduCdb.getTypeCredit()).isEqualTo(UPDATED_TYPE_CREDIT);
        assertThat(testBduCdb.getObjetCredit()).isEqualTo(UPDATED_OBJET_CREDIT);
        assertThat(testBduCdb.getMontantCreditDemande()).isEqualTo(UPDATED_MONTANT_CREDIT_DEMANDE);
        assertThat(testBduCdb.getDureeCreditDemande()).isEqualTo(UPDATED_DUREE_CREDIT_DEMANDE);
        assertThat(testBduCdb.getTauxInteretSouhaite()).isEqualTo(UPDATED_TAUX_INTERET_SOUHAITE);
        assertThat(testBduCdb.getNatureDebiteur()).isEqualTo(UPDATED_NATURE_DEBITEUR);
        assertThat(testBduCdb.getPaysResidence()).isEqualTo(UPDATED_PAYS_RESIDENCE);
        assertThat(testBduCdb.getVille()).isEqualTo(UPDATED_VILLE);
        assertThat(testBduCdb.getStatutJuridique()).isEqualTo(UPDATED_STATUT_JURIDIQUE);
        assertThat(testBduCdb.getSexeDebiteur()).isEqualTo(UPDATED_SEXE_DEBITEUR);
        assertThat(testBduCdb.getSecteurActivite()).isEqualTo(UPDATED_SECTEUR_ACTIVITE);
        assertThat(testBduCdb.getTailleEntreprise()).isEqualTo(UPDATED_TAILLE_ENTREPRISE);
        assertThat(testBduCdb.getDecision()).isEqualTo(UPDATED_DECISION);
        assertThat(testBduCdb.getMotifRejet()).isEqualTo(UPDATED_MOTIF_REJET);
        assertThat(testBduCdb.getDateCredit()).isEqualTo(UPDATED_DATE_CREDIT);
        assertThat(testBduCdb.getMontantCreditAccorde()).isEqualTo(UPDATED_MONTANT_CREDIT_ACCORDE);
        assertThat(testBduCdb.getDureeCreditAccorde()).isEqualTo(UPDATED_DUREE_CREDIT_ACCORDE);
        assertThat(testBduCdb.getPeriodiciteRemboursement()).isEqualTo(UPDATED_PERIODICITE_REMBOURSEMENT);
        assertThat(testBduCdb.getTauxInteretApplique()).isEqualTo(UPDATED_TAUX_INTERET_APPLIQUE);
        assertThat(testBduCdb.getMontantInteret()).isEqualTo(UPDATED_MONTANT_INTERET);
        assertThat(testBduCdb.getMontantCharges()).isEqualTo(UPDATED_MONTANT_CHARGES);
        assertThat(testBduCdb.getMontantCommission()).isEqualTo(UPDATED_MONTANT_COMMISSION);
        assertThat(testBduCdb.getAutresCommissions()).isEqualTo(UPDATED_AUTRES_COMMISSIONS);
    }

    @Test
    @Transactional
    public void updateNonExistingBduCdb() throws Exception {
        int databaseSizeBeforeUpdate = bduCdbRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBduCdbMockMvc.perform(put("/api/bdu-cdbs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bduCdb)))
            .andExpect(status().isBadRequest());

        // Validate the BduCdb in the database
        List<BduCdb> bduCdbList = bduCdbRepository.findAll();
        assertThat(bduCdbList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBduCdb() throws Exception {
        // Initialize the database
        bduCdbService.save(bduCdb);

        int databaseSizeBeforeDelete = bduCdbRepository.findAll().size();

        // Delete the bduCdb
        restBduCdbMockMvc.perform(delete("/api/bdu-cdbs/{id}", bduCdb.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BduCdb> bduCdbList = bduCdbRepository.findAll();
        assertThat(bduCdbList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
