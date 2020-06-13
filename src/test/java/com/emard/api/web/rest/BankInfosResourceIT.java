package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.BankInfos;
import com.emard.api.repository.BankInfosRepository;
import com.emard.api.service.BankInfosService;

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
 * Integration tests for the {@link BankInfosResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BankInfosResourceIT {

    private static final String DEFAULT_CODE_ID = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SIGLE = "AAAAAAAAAA";
    private static final String UPDATED_SIGLE = "BBBBBBBBBB";

    private static final String DEFAULT_RAISON_SOCIALE = "AAAAAAAAAA";
    private static final String UPDATED_RAISON_SOCIALE = "BBBBBBBBBB";

    @Autowired
    private BankInfosRepository bankInfosRepository;

    @Autowired
    private BankInfosService bankInfosService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBankInfosMockMvc;

    private BankInfos bankInfos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BankInfos createEntity(EntityManager em) {
        BankInfos bankInfos = new BankInfos()
            .codeId(DEFAULT_CODE_ID)
            .sigle(DEFAULT_SIGLE)
            .raisonSociale(DEFAULT_RAISON_SOCIALE);
        return bankInfos;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BankInfos createUpdatedEntity(EntityManager em) {
        BankInfos bankInfos = new BankInfos()
            .codeId(UPDATED_CODE_ID)
            .sigle(UPDATED_SIGLE)
            .raisonSociale(UPDATED_RAISON_SOCIALE);
        return bankInfos;
    }

    @BeforeEach
    public void initTest() {
        bankInfos = createEntity(em);
    }

    @Test
    @Transactional
    public void createBankInfos() throws Exception {
        int databaseSizeBeforeCreate = bankInfosRepository.findAll().size();
        // Create the BankInfos
        restBankInfosMockMvc.perform(post("/api/bank-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankInfos)))
            .andExpect(status().isCreated());

        // Validate the BankInfos in the database
        List<BankInfos> bankInfosList = bankInfosRepository.findAll();
        assertThat(bankInfosList).hasSize(databaseSizeBeforeCreate + 1);
        BankInfos testBankInfos = bankInfosList.get(bankInfosList.size() - 1);
        assertThat(testBankInfos.getCodeId()).isEqualTo(DEFAULT_CODE_ID);
        assertThat(testBankInfos.getSigle()).isEqualTo(DEFAULT_SIGLE);
        assertThat(testBankInfos.getRaisonSociale()).isEqualTo(DEFAULT_RAISON_SOCIALE);
    }

    @Test
    @Transactional
    public void createBankInfosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bankInfosRepository.findAll().size();

        // Create the BankInfos with an existing ID
        bankInfos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBankInfosMockMvc.perform(post("/api/bank-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankInfos)))
            .andExpect(status().isBadRequest());

        // Validate the BankInfos in the database
        List<BankInfos> bankInfosList = bankInfosRepository.findAll();
        assertThat(bankInfosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankInfosRepository.findAll().size();
        // set the field null
        bankInfos.setCodeId(null);

        // Create the BankInfos, which fails.


        restBankInfosMockMvc.perform(post("/api/bank-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankInfos)))
            .andExpect(status().isBadRequest());

        List<BankInfos> bankInfosList = bankInfosRepository.findAll();
        assertThat(bankInfosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRaisonSocialeIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankInfosRepository.findAll().size();
        // set the field null
        bankInfos.setRaisonSociale(null);

        // Create the BankInfos, which fails.


        restBankInfosMockMvc.perform(post("/api/bank-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankInfos)))
            .andExpect(status().isBadRequest());

        List<BankInfos> bankInfosList = bankInfosRepository.findAll();
        assertThat(bankInfosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBankInfos() throws Exception {
        // Initialize the database
        bankInfosRepository.saveAndFlush(bankInfos);

        // Get all the bankInfosList
        restBankInfosMockMvc.perform(get("/api/bank-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bankInfos.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeId").value(hasItem(DEFAULT_CODE_ID)))
            .andExpect(jsonPath("$.[*].sigle").value(hasItem(DEFAULT_SIGLE)))
            .andExpect(jsonPath("$.[*].raisonSociale").value(hasItem(DEFAULT_RAISON_SOCIALE)));
    }
    
    @Test
    @Transactional
    public void getBankInfos() throws Exception {
        // Initialize the database
        bankInfosRepository.saveAndFlush(bankInfos);

        // Get the bankInfos
        restBankInfosMockMvc.perform(get("/api/bank-infos/{id}", bankInfos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bankInfos.getId().intValue()))
            .andExpect(jsonPath("$.codeId").value(DEFAULT_CODE_ID))
            .andExpect(jsonPath("$.sigle").value(DEFAULT_SIGLE))
            .andExpect(jsonPath("$.raisonSociale").value(DEFAULT_RAISON_SOCIALE));
    }
    @Test
    @Transactional
    public void getNonExistingBankInfos() throws Exception {
        // Get the bankInfos
        restBankInfosMockMvc.perform(get("/api/bank-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBankInfos() throws Exception {
        // Initialize the database
        bankInfosService.save(bankInfos);

        int databaseSizeBeforeUpdate = bankInfosRepository.findAll().size();

        // Update the bankInfos
        BankInfos updatedBankInfos = bankInfosRepository.findById(bankInfos.getId()).get();
        // Disconnect from session so that the updates on updatedBankInfos are not directly saved in db
        em.detach(updatedBankInfos);
        updatedBankInfos
            .codeId(UPDATED_CODE_ID)
            .sigle(UPDATED_SIGLE)
            .raisonSociale(UPDATED_RAISON_SOCIALE);

        restBankInfosMockMvc.perform(put("/api/bank-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBankInfos)))
            .andExpect(status().isOk());

        // Validate the BankInfos in the database
        List<BankInfos> bankInfosList = bankInfosRepository.findAll();
        assertThat(bankInfosList).hasSize(databaseSizeBeforeUpdate);
        BankInfos testBankInfos = bankInfosList.get(bankInfosList.size() - 1);
        assertThat(testBankInfos.getCodeId()).isEqualTo(UPDATED_CODE_ID);
        assertThat(testBankInfos.getSigle()).isEqualTo(UPDATED_SIGLE);
        assertThat(testBankInfos.getRaisonSociale()).isEqualTo(UPDATED_RAISON_SOCIALE);
    }

    @Test
    @Transactional
    public void updateNonExistingBankInfos() throws Exception {
        int databaseSizeBeforeUpdate = bankInfosRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBankInfosMockMvc.perform(put("/api/bank-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankInfos)))
            .andExpect(status().isBadRequest());

        // Validate the BankInfos in the database
        List<BankInfos> bankInfosList = bankInfosRepository.findAll();
        assertThat(bankInfosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBankInfos() throws Exception {
        // Initialize the database
        bankInfosService.save(bankInfos);

        int databaseSizeBeforeDelete = bankInfosRepository.findAll().size();

        // Delete the bankInfos
        restBankInfosMockMvc.perform(delete("/api/bank-infos/{id}", bankInfos.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BankInfos> bankInfosList = bankInfosRepository.findAll();
        assertThat(bankInfosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
