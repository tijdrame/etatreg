package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.ChargeFile;
import com.emard.api.repository.ChargeFileRepository;
import com.emard.api.service.ChargeFileService;

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
 * Integration tests for the {@link ChargeFileResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ChargeFileResourceIT {

    private static final String DEFAULT_NOM_FIC = "AAAAAAAAAA";
    private static final String UPDATED_NOM_FIC = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_CHARGE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CHARGE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ChargeFileRepository chargeFileRepository;

    @Autowired
    private ChargeFileService chargeFileService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChargeFileMockMvc;

    private ChargeFile chargeFile;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChargeFile createEntity(EntityManager em) {
        ChargeFile chargeFile = new ChargeFile()
            .nomFic(DEFAULT_NOM_FIC)
            .dateCharge(DEFAULT_DATE_CHARGE);
        return chargeFile;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChargeFile createUpdatedEntity(EntityManager em) {
        ChargeFile chargeFile = new ChargeFile()
            .nomFic(UPDATED_NOM_FIC)
            .dateCharge(UPDATED_DATE_CHARGE);
        return chargeFile;
    }

    @BeforeEach
    public void initTest() {
        chargeFile = createEntity(em);
    }

    @Test
    @Transactional
    public void createChargeFile() throws Exception {
        int databaseSizeBeforeCreate = chargeFileRepository.findAll().size();
        // Create the ChargeFile
        restChargeFileMockMvc.perform(post("/api/charge-files")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chargeFile)))
            .andExpect(status().isCreated());

        // Validate the ChargeFile in the database
        List<ChargeFile> chargeFileList = chargeFileRepository.findAll();
        assertThat(chargeFileList).hasSize(databaseSizeBeforeCreate + 1);
        ChargeFile testChargeFile = chargeFileList.get(chargeFileList.size() - 1);
        assertThat(testChargeFile.getNomFic()).isEqualTo(DEFAULT_NOM_FIC);
        assertThat(testChargeFile.getDateCharge()).isEqualTo(DEFAULT_DATE_CHARGE);
    }

    @Test
    @Transactional
    public void createChargeFileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chargeFileRepository.findAll().size();

        // Create the ChargeFile with an existing ID
        chargeFile.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChargeFileMockMvc.perform(post("/api/charge-files")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chargeFile)))
            .andExpect(status().isBadRequest());

        // Validate the ChargeFile in the database
        List<ChargeFile> chargeFileList = chargeFileRepository.findAll();
        assertThat(chargeFileList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllChargeFiles() throws Exception {
        // Initialize the database
        chargeFileRepository.saveAndFlush(chargeFile);

        // Get all the chargeFileList
        restChargeFileMockMvc.perform(get("/api/charge-files?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chargeFile.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomFic").value(hasItem(DEFAULT_NOM_FIC)))
            .andExpect(jsonPath("$.[*].dateCharge").value(hasItem(DEFAULT_DATE_CHARGE.toString())));
    }
    
    @Test
    @Transactional
    public void getChargeFile() throws Exception {
        // Initialize the database
        chargeFileRepository.saveAndFlush(chargeFile);

        // Get the chargeFile
        restChargeFileMockMvc.perform(get("/api/charge-files/{id}", chargeFile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(chargeFile.getId().intValue()))
            .andExpect(jsonPath("$.nomFic").value(DEFAULT_NOM_FIC))
            .andExpect(jsonPath("$.dateCharge").value(DEFAULT_DATE_CHARGE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingChargeFile() throws Exception {
        // Get the chargeFile
        restChargeFileMockMvc.perform(get("/api/charge-files/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChargeFile() throws Exception {
        // Initialize the database
        chargeFileService.save(chargeFile);

        int databaseSizeBeforeUpdate = chargeFileRepository.findAll().size();

        // Update the chargeFile
        ChargeFile updatedChargeFile = chargeFileRepository.findById(chargeFile.getId()).get();
        // Disconnect from session so that the updates on updatedChargeFile are not directly saved in db
        em.detach(updatedChargeFile);
        updatedChargeFile
            .nomFic(UPDATED_NOM_FIC)
            .dateCharge(UPDATED_DATE_CHARGE);

        restChargeFileMockMvc.perform(put("/api/charge-files")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedChargeFile)))
            .andExpect(status().isOk());

        // Validate the ChargeFile in the database
        List<ChargeFile> chargeFileList = chargeFileRepository.findAll();
        assertThat(chargeFileList).hasSize(databaseSizeBeforeUpdate);
        ChargeFile testChargeFile = chargeFileList.get(chargeFileList.size() - 1);
        assertThat(testChargeFile.getNomFic()).isEqualTo(UPDATED_NOM_FIC);
        assertThat(testChargeFile.getDateCharge()).isEqualTo(UPDATED_DATE_CHARGE);
    }

    @Test
    @Transactional
    public void updateNonExistingChargeFile() throws Exception {
        int databaseSizeBeforeUpdate = chargeFileRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChargeFileMockMvc.perform(put("/api/charge-files")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chargeFile)))
            .andExpect(status().isBadRequest());

        // Validate the ChargeFile in the database
        List<ChargeFile> chargeFileList = chargeFileRepository.findAll();
        assertThat(chargeFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChargeFile() throws Exception {
        // Initialize the database
        chargeFileService.save(chargeFile);

        int databaseSizeBeforeDelete = chargeFileRepository.findAll().size();

        // Delete the chargeFile
        restChargeFileMockMvc.perform(delete("/api/charge-files/{id}", chargeFile.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ChargeFile> chargeFileList = chargeFileRepository.findAll();
        assertThat(chargeFileList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
