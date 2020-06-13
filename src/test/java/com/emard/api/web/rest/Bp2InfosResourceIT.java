package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.Bp2Infos;
import com.emard.api.repository.Bp2InfosRepository;
import com.emard.api.service.Bp2InfosService;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link Bp2InfosResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class Bp2InfosResourceIT {

    private static final String DEFAULT_CODE_ISO_DEVISE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ISO_DEVISE = "BBBBBBBBBB";

    private static final Double DEFAULT_ACTIF_BILLET_ET_RCAI = 1D;
    private static final Double UPDATED_ACTIF_BILLET_ET_RCAI = 2D;

    private static final Double DEFAULT_ACTIF_MAISON_MERE = 1D;
    private static final Double UPDATED_ACTIF_MAISON_MERE = 2D;

    private static final Double DEFAULT_ACTIF_AU_TRESOR = 1D;
    private static final Double UPDATED_ACTIF_AU_TRESOR = 2D;

    private static final Double DEFAULT_ACTIF_CLIENT_DEB = 1D;
    private static final Double UPDATED_ACTIF_CLIENT_DEB = 2D;

    private static final Double DEFAULT_ACTIF_EFFES_CPTE = 1D;
    private static final Double UPDATED_ACTIF_EFFES_CPTE = 2D;

    private static final Double DEFAULT_ACTIF_EFFET_ENC = 1D;
    private static final Double UPDATED_ACTIF_EFFET_ENC = 2D;

    private static final Double DEFAULT_PASSIF_MAISON_MERE = 1D;
    private static final Double UPDATED_PASSIF_MAISON_MERE = 2D;

    private static final Double DEFAULT_PASSIF_AU_TRESOR = 1D;
    private static final Double UPDATED_PASSIF_AU_TRESOR = 2D;

    private static final Double DEFAULT_PASSIF_CLI_CPTE_CH = 1D;
    private static final Double UPDATED_PASSIF_CLI_CPTE_CH = 2D;

    private static final Double DEFAULT_PASSIF_CPT_APRES_ENC = 1D;
    private static final Double UPDATED_PASSIF_CPT_APRES_ENC = 2D;

    private static final Instant DEFAULT_DATE_CHARGEMENT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CHARGEMENT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_DECHARGEMENT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_DECHARGEMENT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_PASSIF_CLI_CPT_VUE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PASSIF_CLI_CPT_VUE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private Bp2InfosRepository bp2InfosRepository;

    @Autowired
    private Bp2InfosService bp2InfosService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBp2InfosMockMvc;

    private Bp2Infos bp2Infos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bp2Infos createEntity(EntityManager em) {
        Bp2Infos bp2Infos = new Bp2Infos()
            .codeIsoDevise(DEFAULT_CODE_ISO_DEVISE)
            .actifBilletEtRcai(DEFAULT_ACTIF_BILLET_ET_RCAI)
            .actifMaisonMere(DEFAULT_ACTIF_MAISON_MERE)
            .actifAuTresor(DEFAULT_ACTIF_AU_TRESOR)
            .actifClientDeb(DEFAULT_ACTIF_CLIENT_DEB)
            .actifEffesCpte(DEFAULT_ACTIF_EFFES_CPTE)
            .actifEffetEnc(DEFAULT_ACTIF_EFFET_ENC)
            .passifMaisonMere(DEFAULT_PASSIF_MAISON_MERE)
            .passifAuTresor(DEFAULT_PASSIF_AU_TRESOR)
            .passifCliCpteCh(DEFAULT_PASSIF_CLI_CPTE_CH)
            .passifCptApresEnc(DEFAULT_PASSIF_CPT_APRES_ENC)
            .dateChargement(DEFAULT_DATE_CHARGEMENT)
            .dateDechargement(DEFAULT_DATE_DECHARGEMENT)
            .passifCliCptVue(DEFAULT_PASSIF_CLI_CPT_VUE);
        return bp2Infos;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bp2Infos createUpdatedEntity(EntityManager em) {
        Bp2Infos bp2Infos = new Bp2Infos()
            .codeIsoDevise(UPDATED_CODE_ISO_DEVISE)
            .actifBilletEtRcai(UPDATED_ACTIF_BILLET_ET_RCAI)
            .actifMaisonMere(UPDATED_ACTIF_MAISON_MERE)
            .actifAuTresor(UPDATED_ACTIF_AU_TRESOR)
            .actifClientDeb(UPDATED_ACTIF_CLIENT_DEB)
            .actifEffesCpte(UPDATED_ACTIF_EFFES_CPTE)
            .actifEffetEnc(UPDATED_ACTIF_EFFET_ENC)
            .passifMaisonMere(UPDATED_PASSIF_MAISON_MERE)
            .passifAuTresor(UPDATED_PASSIF_AU_TRESOR)
            .passifCliCpteCh(UPDATED_PASSIF_CLI_CPTE_CH)
            .passifCptApresEnc(UPDATED_PASSIF_CPT_APRES_ENC)
            .dateChargement(UPDATED_DATE_CHARGEMENT)
            .dateDechargement(UPDATED_DATE_DECHARGEMENT)
            .passifCliCptVue(UPDATED_PASSIF_CLI_CPT_VUE);
        return bp2Infos;
    }

    @BeforeEach
    public void initTest() {
        bp2Infos = createEntity(em);
    }

    @Test
    @Transactional
    public void createBp2Infos() throws Exception {
        int databaseSizeBeforeCreate = bp2InfosRepository.findAll().size();
        // Create the Bp2Infos
        restBp2InfosMockMvc.perform(post("/api/bp-2-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp2Infos)))
            .andExpect(status().isCreated());

        // Validate the Bp2Infos in the database
        List<Bp2Infos> bp2InfosList = bp2InfosRepository.findAll();
        assertThat(bp2InfosList).hasSize(databaseSizeBeforeCreate + 1);
        Bp2Infos testBp2Infos = bp2InfosList.get(bp2InfosList.size() - 1);
        assertThat(testBp2Infos.getCodeIsoDevise()).isEqualTo(DEFAULT_CODE_ISO_DEVISE);
        assertThat(testBp2Infos.getActifBilletEtRcai()).isEqualTo(DEFAULT_ACTIF_BILLET_ET_RCAI);
        assertThat(testBp2Infos.getActifMaisonMere()).isEqualTo(DEFAULT_ACTIF_MAISON_MERE);
        assertThat(testBp2Infos.getActifAuTresor()).isEqualTo(DEFAULT_ACTIF_AU_TRESOR);
        assertThat(testBp2Infos.getActifClientDeb()).isEqualTo(DEFAULT_ACTIF_CLIENT_DEB);
        assertThat(testBp2Infos.getActifEffesCpte()).isEqualTo(DEFAULT_ACTIF_EFFES_CPTE);
        assertThat(testBp2Infos.getActifEffetEnc()).isEqualTo(DEFAULT_ACTIF_EFFET_ENC);
        assertThat(testBp2Infos.getPassifMaisonMere()).isEqualTo(DEFAULT_PASSIF_MAISON_MERE);
        assertThat(testBp2Infos.getPassifAuTresor()).isEqualTo(DEFAULT_PASSIF_AU_TRESOR);
        assertThat(testBp2Infos.getPassifCliCpteCh()).isEqualTo(DEFAULT_PASSIF_CLI_CPTE_CH);
        assertThat(testBp2Infos.getPassifCptApresEnc()).isEqualTo(DEFAULT_PASSIF_CPT_APRES_ENC);
        assertThat(testBp2Infos.getDateChargement()).isEqualTo(DEFAULT_DATE_CHARGEMENT);
        assertThat(testBp2Infos.getDateDechargement()).isEqualTo(DEFAULT_DATE_DECHARGEMENT);
        assertThat(testBp2Infos.getPassifCliCptVue()).isEqualTo(DEFAULT_PASSIF_CLI_CPT_VUE);
    }

    @Test
    @Transactional
    public void createBp2InfosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bp2InfosRepository.findAll().size();

        // Create the Bp2Infos with an existing ID
        bp2Infos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBp2InfosMockMvc.perform(post("/api/bp-2-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp2Infos)))
            .andExpect(status().isBadRequest());

        // Validate the Bp2Infos in the database
        List<Bp2Infos> bp2InfosList = bp2InfosRepository.findAll();
        assertThat(bp2InfosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBp2Infos() throws Exception {
        // Initialize the database
        bp2InfosRepository.saveAndFlush(bp2Infos);

        // Get all the bp2InfosList
        restBp2InfosMockMvc.perform(get("/api/bp-2-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bp2Infos.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeIsoDevise").value(hasItem(DEFAULT_CODE_ISO_DEVISE)))
            .andExpect(jsonPath("$.[*].actifBilletEtRcai").value(hasItem(DEFAULT_ACTIF_BILLET_ET_RCAI.doubleValue())))
            .andExpect(jsonPath("$.[*].actifMaisonMere").value(hasItem(DEFAULT_ACTIF_MAISON_MERE.doubleValue())))
            .andExpect(jsonPath("$.[*].actifAuTresor").value(hasItem(DEFAULT_ACTIF_AU_TRESOR.doubleValue())))
            .andExpect(jsonPath("$.[*].actifClientDeb").value(hasItem(DEFAULT_ACTIF_CLIENT_DEB.doubleValue())))
            .andExpect(jsonPath("$.[*].actifEffesCpte").value(hasItem(DEFAULT_ACTIF_EFFES_CPTE.doubleValue())))
            .andExpect(jsonPath("$.[*].actifEffetEnc").value(hasItem(DEFAULT_ACTIF_EFFET_ENC.doubleValue())))
            .andExpect(jsonPath("$.[*].passifMaisonMere").value(hasItem(DEFAULT_PASSIF_MAISON_MERE.doubleValue())))
            .andExpect(jsonPath("$.[*].passifAuTresor").value(hasItem(DEFAULT_PASSIF_AU_TRESOR.doubleValue())))
            .andExpect(jsonPath("$.[*].passifCliCpteCh").value(hasItem(DEFAULT_PASSIF_CLI_CPTE_CH.doubleValue())))
            .andExpect(jsonPath("$.[*].passifCptApresEnc").value(hasItem(DEFAULT_PASSIF_CPT_APRES_ENC.doubleValue())))
            .andExpect(jsonPath("$.[*].dateChargement").value(hasItem(DEFAULT_DATE_CHARGEMENT.toString())))
            .andExpect(jsonPath("$.[*].dateDechargement").value(hasItem(DEFAULT_DATE_DECHARGEMENT.toString())))
            .andExpect(jsonPath("$.[*].passifCliCptVue").value(hasItem(DEFAULT_PASSIF_CLI_CPT_VUE.toString())));
    }
    
    @Test
    @Transactional
    public void getBp2Infos() throws Exception {
        // Initialize the database
        bp2InfosRepository.saveAndFlush(bp2Infos);

        // Get the bp2Infos
        restBp2InfosMockMvc.perform(get("/api/bp-2-infos/{id}", bp2Infos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bp2Infos.getId().intValue()))
            .andExpect(jsonPath("$.codeIsoDevise").value(DEFAULT_CODE_ISO_DEVISE))
            .andExpect(jsonPath("$.actifBilletEtRcai").value(DEFAULT_ACTIF_BILLET_ET_RCAI.doubleValue()))
            .andExpect(jsonPath("$.actifMaisonMere").value(DEFAULT_ACTIF_MAISON_MERE.doubleValue()))
            .andExpect(jsonPath("$.actifAuTresor").value(DEFAULT_ACTIF_AU_TRESOR.doubleValue()))
            .andExpect(jsonPath("$.actifClientDeb").value(DEFAULT_ACTIF_CLIENT_DEB.doubleValue()))
            .andExpect(jsonPath("$.actifEffesCpte").value(DEFAULT_ACTIF_EFFES_CPTE.doubleValue()))
            .andExpect(jsonPath("$.actifEffetEnc").value(DEFAULT_ACTIF_EFFET_ENC.doubleValue()))
            .andExpect(jsonPath("$.passifMaisonMere").value(DEFAULT_PASSIF_MAISON_MERE.doubleValue()))
            .andExpect(jsonPath("$.passifAuTresor").value(DEFAULT_PASSIF_AU_TRESOR.doubleValue()))
            .andExpect(jsonPath("$.passifCliCpteCh").value(DEFAULT_PASSIF_CLI_CPTE_CH.doubleValue()))
            .andExpect(jsonPath("$.passifCptApresEnc").value(DEFAULT_PASSIF_CPT_APRES_ENC.doubleValue()))
            .andExpect(jsonPath("$.dateChargement").value(DEFAULT_DATE_CHARGEMENT.toString()))
            .andExpect(jsonPath("$.dateDechargement").value(DEFAULT_DATE_DECHARGEMENT.toString()))
            .andExpect(jsonPath("$.passifCliCptVue").value(DEFAULT_PASSIF_CLI_CPT_VUE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingBp2Infos() throws Exception {
        // Get the bp2Infos
        restBp2InfosMockMvc.perform(get("/api/bp-2-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBp2Infos() throws Exception {
        // Initialize the database
        bp2InfosService.save(bp2Infos);

        int databaseSizeBeforeUpdate = bp2InfosRepository.findAll().size();

        // Update the bp2Infos
        Bp2Infos updatedBp2Infos = bp2InfosRepository.findById(bp2Infos.getId()).get();
        // Disconnect from session so that the updates on updatedBp2Infos are not directly saved in db
        em.detach(updatedBp2Infos);
        updatedBp2Infos
            .codeIsoDevise(UPDATED_CODE_ISO_DEVISE)
            .actifBilletEtRcai(UPDATED_ACTIF_BILLET_ET_RCAI)
            .actifMaisonMere(UPDATED_ACTIF_MAISON_MERE)
            .actifAuTresor(UPDATED_ACTIF_AU_TRESOR)
            .actifClientDeb(UPDATED_ACTIF_CLIENT_DEB)
            .actifEffesCpte(UPDATED_ACTIF_EFFES_CPTE)
            .actifEffetEnc(UPDATED_ACTIF_EFFET_ENC)
            .passifMaisonMere(UPDATED_PASSIF_MAISON_MERE)
            .passifAuTresor(UPDATED_PASSIF_AU_TRESOR)
            .passifCliCpteCh(UPDATED_PASSIF_CLI_CPTE_CH)
            .passifCptApresEnc(UPDATED_PASSIF_CPT_APRES_ENC)
            .dateChargement(UPDATED_DATE_CHARGEMENT)
            .dateDechargement(UPDATED_DATE_DECHARGEMENT)
            .passifCliCptVue(UPDATED_PASSIF_CLI_CPT_VUE);

        restBp2InfosMockMvc.perform(put("/api/bp-2-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBp2Infos)))
            .andExpect(status().isOk());

        // Validate the Bp2Infos in the database
        List<Bp2Infos> bp2InfosList = bp2InfosRepository.findAll();
        assertThat(bp2InfosList).hasSize(databaseSizeBeforeUpdate);
        Bp2Infos testBp2Infos = bp2InfosList.get(bp2InfosList.size() - 1);
        assertThat(testBp2Infos.getCodeIsoDevise()).isEqualTo(UPDATED_CODE_ISO_DEVISE);
        assertThat(testBp2Infos.getActifBilletEtRcai()).isEqualTo(UPDATED_ACTIF_BILLET_ET_RCAI);
        assertThat(testBp2Infos.getActifMaisonMere()).isEqualTo(UPDATED_ACTIF_MAISON_MERE);
        assertThat(testBp2Infos.getActifAuTresor()).isEqualTo(UPDATED_ACTIF_AU_TRESOR);
        assertThat(testBp2Infos.getActifClientDeb()).isEqualTo(UPDATED_ACTIF_CLIENT_DEB);
        assertThat(testBp2Infos.getActifEffesCpte()).isEqualTo(UPDATED_ACTIF_EFFES_CPTE);
        assertThat(testBp2Infos.getActifEffetEnc()).isEqualTo(UPDATED_ACTIF_EFFET_ENC);
        assertThat(testBp2Infos.getPassifMaisonMere()).isEqualTo(UPDATED_PASSIF_MAISON_MERE);
        assertThat(testBp2Infos.getPassifAuTresor()).isEqualTo(UPDATED_PASSIF_AU_TRESOR);
        assertThat(testBp2Infos.getPassifCliCpteCh()).isEqualTo(UPDATED_PASSIF_CLI_CPTE_CH);
        assertThat(testBp2Infos.getPassifCptApresEnc()).isEqualTo(UPDATED_PASSIF_CPT_APRES_ENC);
        assertThat(testBp2Infos.getDateChargement()).isEqualTo(UPDATED_DATE_CHARGEMENT);
        assertThat(testBp2Infos.getDateDechargement()).isEqualTo(UPDATED_DATE_DECHARGEMENT);
        assertThat(testBp2Infos.getPassifCliCptVue()).isEqualTo(UPDATED_PASSIF_CLI_CPT_VUE);
    }

    @Test
    @Transactional
    public void updateNonExistingBp2Infos() throws Exception {
        int databaseSizeBeforeUpdate = bp2InfosRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBp2InfosMockMvc.perform(put("/api/bp-2-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp2Infos)))
            .andExpect(status().isBadRequest());

        // Validate the Bp2Infos in the database
        List<Bp2Infos> bp2InfosList = bp2InfosRepository.findAll();
        assertThat(bp2InfosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBp2Infos() throws Exception {
        // Initialize the database
        bp2InfosService.save(bp2Infos);

        int databaseSizeBeforeDelete = bp2InfosRepository.findAll().size();

        // Delete the bp2Infos
        restBp2InfosMockMvc.perform(delete("/api/bp-2-infos/{id}", bp2Infos.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bp2Infos> bp2InfosList = bp2InfosRepository.findAll();
        assertThat(bp2InfosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
