package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.FilesInfos;
import com.emard.api.repository.FilesInfosRepository;
import com.emard.api.service.FilesInfosService;

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
 * Integration tests for the {@link FilesInfosResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FilesInfosResourceIT {

    private static final String DEFAULT_CODE_FILE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_FILE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_INPUT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_INPUT_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_OUTPUT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_OUTPUT_PATH = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_DERNIER_CHARGEMENT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_DERNIER_CHARGEMENT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_DERNIER_DECHARGEMENT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_DERNIER_DECHARGEMENT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private FilesInfosRepository filesInfosRepository;

    @Autowired
    private FilesInfosService filesInfosService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFilesInfosMockMvc;

    private FilesInfos filesInfos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FilesInfos createEntity(EntityManager em) {
        FilesInfos filesInfos = new FilesInfos()
            .codeFile(DEFAULT_CODE_FILE)
            .description(DEFAULT_DESCRIPTION)
            .inputPath(DEFAULT_INPUT_PATH)
            .outputPath(DEFAULT_OUTPUT_PATH)
            .dateDernierChargement(DEFAULT_DATE_DERNIER_CHARGEMENT)
            .dateDernierDechargement(DEFAULT_DATE_DERNIER_DECHARGEMENT);
        return filesInfos;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FilesInfos createUpdatedEntity(EntityManager em) {
        FilesInfos filesInfos = new FilesInfos()
            .codeFile(UPDATED_CODE_FILE)
            .description(UPDATED_DESCRIPTION)
            .inputPath(UPDATED_INPUT_PATH)
            .outputPath(UPDATED_OUTPUT_PATH)
            .dateDernierChargement(UPDATED_DATE_DERNIER_CHARGEMENT)
            .dateDernierDechargement(UPDATED_DATE_DERNIER_DECHARGEMENT);
        return filesInfos;
    }

    @BeforeEach
    public void initTest() {
        filesInfos = createEntity(em);
    }

    @Test
    @Transactional
    public void createFilesInfos() throws Exception {
        int databaseSizeBeforeCreate = filesInfosRepository.findAll().size();
        // Create the FilesInfos
        restFilesInfosMockMvc.perform(post("/api/files-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(filesInfos)))
            .andExpect(status().isCreated());

        // Validate the FilesInfos in the database
        List<FilesInfos> filesInfosList = filesInfosRepository.findAll();
        assertThat(filesInfosList).hasSize(databaseSizeBeforeCreate + 1);
        FilesInfos testFilesInfos = filesInfosList.get(filesInfosList.size() - 1);
        assertThat(testFilesInfos.getCodeFile()).isEqualTo(DEFAULT_CODE_FILE);
        assertThat(testFilesInfos.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testFilesInfos.getInputPath()).isEqualTo(DEFAULT_INPUT_PATH);
        assertThat(testFilesInfos.getOutputPath()).isEqualTo(DEFAULT_OUTPUT_PATH);
        assertThat(testFilesInfos.getDateDernierChargement()).isEqualTo(DEFAULT_DATE_DERNIER_CHARGEMENT);
        assertThat(testFilesInfos.getDateDernierDechargement()).isEqualTo(DEFAULT_DATE_DERNIER_DECHARGEMENT);
    }

    @Test
    @Transactional
    public void createFilesInfosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = filesInfosRepository.findAll().size();

        // Create the FilesInfos with an existing ID
        filesInfos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFilesInfosMockMvc.perform(post("/api/files-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(filesInfos)))
            .andExpect(status().isBadRequest());

        // Validate the FilesInfos in the database
        List<FilesInfos> filesInfosList = filesInfosRepository.findAll();
        assertThat(filesInfosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeFileIsRequired() throws Exception {
        int databaseSizeBeforeTest = filesInfosRepository.findAll().size();
        // set the field null
        filesInfos.setCodeFile(null);

        // Create the FilesInfos, which fails.


        restFilesInfosMockMvc.perform(post("/api/files-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(filesInfos)))
            .andExpect(status().isBadRequest());

        List<FilesInfos> filesInfosList = filesInfosRepository.findAll();
        assertThat(filesInfosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFilesInfos() throws Exception {
        // Initialize the database
        filesInfosRepository.saveAndFlush(filesInfos);

        // Get all the filesInfosList
        restFilesInfosMockMvc.perform(get("/api/files-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(filesInfos.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeFile").value(hasItem(DEFAULT_CODE_FILE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].inputPath").value(hasItem(DEFAULT_INPUT_PATH)))
            .andExpect(jsonPath("$.[*].outputPath").value(hasItem(DEFAULT_OUTPUT_PATH)))
            .andExpect(jsonPath("$.[*].dateDernierChargement").value(hasItem(DEFAULT_DATE_DERNIER_CHARGEMENT.toString())))
            .andExpect(jsonPath("$.[*].dateDernierDechargement").value(hasItem(DEFAULT_DATE_DERNIER_DECHARGEMENT.toString())));
    }
    
    @Test
    @Transactional
    public void getFilesInfos() throws Exception {
        // Initialize the database
        filesInfosRepository.saveAndFlush(filesInfos);

        // Get the filesInfos
        restFilesInfosMockMvc.perform(get("/api/files-infos/{id}", filesInfos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(filesInfos.getId().intValue()))
            .andExpect(jsonPath("$.codeFile").value(DEFAULT_CODE_FILE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.inputPath").value(DEFAULT_INPUT_PATH))
            .andExpect(jsonPath("$.outputPath").value(DEFAULT_OUTPUT_PATH))
            .andExpect(jsonPath("$.dateDernierChargement").value(DEFAULT_DATE_DERNIER_CHARGEMENT.toString()))
            .andExpect(jsonPath("$.dateDernierDechargement").value(DEFAULT_DATE_DERNIER_DECHARGEMENT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingFilesInfos() throws Exception {
        // Get the filesInfos
        restFilesInfosMockMvc.perform(get("/api/files-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFilesInfos() throws Exception {
        // Initialize the database
        filesInfosService.save(filesInfos);

        int databaseSizeBeforeUpdate = filesInfosRepository.findAll().size();

        // Update the filesInfos
        FilesInfos updatedFilesInfos = filesInfosRepository.findById(filesInfos.getId()).get();
        // Disconnect from session so that the updates on updatedFilesInfos are not directly saved in db
        em.detach(updatedFilesInfos);
        updatedFilesInfos
            .codeFile(UPDATED_CODE_FILE)
            .description(UPDATED_DESCRIPTION)
            .inputPath(UPDATED_INPUT_PATH)
            .outputPath(UPDATED_OUTPUT_PATH)
            .dateDernierChargement(UPDATED_DATE_DERNIER_CHARGEMENT)
            .dateDernierDechargement(UPDATED_DATE_DERNIER_DECHARGEMENT);

        restFilesInfosMockMvc.perform(put("/api/files-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFilesInfos)))
            .andExpect(status().isOk());

        // Validate the FilesInfos in the database
        List<FilesInfos> filesInfosList = filesInfosRepository.findAll();
        assertThat(filesInfosList).hasSize(databaseSizeBeforeUpdate);
        FilesInfos testFilesInfos = filesInfosList.get(filesInfosList.size() - 1);
        assertThat(testFilesInfos.getCodeFile()).isEqualTo(UPDATED_CODE_FILE);
        assertThat(testFilesInfos.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testFilesInfos.getInputPath()).isEqualTo(UPDATED_INPUT_PATH);
        assertThat(testFilesInfos.getOutputPath()).isEqualTo(UPDATED_OUTPUT_PATH);
        assertThat(testFilesInfos.getDateDernierChargement()).isEqualTo(UPDATED_DATE_DERNIER_CHARGEMENT);
        assertThat(testFilesInfos.getDateDernierDechargement()).isEqualTo(UPDATED_DATE_DERNIER_DECHARGEMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingFilesInfos() throws Exception {
        int databaseSizeBeforeUpdate = filesInfosRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFilesInfosMockMvc.perform(put("/api/files-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(filesInfos)))
            .andExpect(status().isBadRequest());

        // Validate the FilesInfos in the database
        List<FilesInfos> filesInfosList = filesInfosRepository.findAll();
        assertThat(filesInfosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFilesInfos() throws Exception {
        // Initialize the database
        filesInfosService.save(filesInfos);

        int databaseSizeBeforeDelete = filesInfosRepository.findAll().size();

        // Delete the filesInfos
        restFilesInfosMockMvc.perform(delete("/api/files-infos/{id}", filesInfos.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FilesInfos> filesInfosList = filesInfosRepository.findAll();
        assertThat(filesInfosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
