package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.SecteurActivite;
import com.emard.api.repository.SecteurActiviteRepository;
import com.emard.api.service.SecteurActiviteService;

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
 * Integration tests for the {@link SecteurActiviteResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SecteurActiviteResourceIT {

    private static final String DEFAULT_CODE_BDU = "AAAAAAAAAA";
    private static final String UPDATED_CODE_BDU = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_CB = "AAAAAAAAAA";
    private static final String UPDATED_CODE_CB = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private SecteurActiviteRepository secteurActiviteRepository;

    @Autowired
    private SecteurActiviteService secteurActiviteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSecteurActiviteMockMvc;

    private SecteurActivite secteurActivite;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SecteurActivite createEntity(EntityManager em) {
        SecteurActivite secteurActivite = new SecteurActivite()
            .codeBdu(DEFAULT_CODE_BDU)
            .codeCb(DEFAULT_CODE_CB)
            .description(DEFAULT_DESCRIPTION);
        return secteurActivite;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SecteurActivite createUpdatedEntity(EntityManager em) {
        SecteurActivite secteurActivite = new SecteurActivite()
            .codeBdu(UPDATED_CODE_BDU)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);
        return secteurActivite;
    }

    @BeforeEach
    public void initTest() {
        secteurActivite = createEntity(em);
    }

    @Test
    @Transactional
    public void createSecteurActivite() throws Exception {
        int databaseSizeBeforeCreate = secteurActiviteRepository.findAll().size();
        // Create the SecteurActivite
        restSecteurActiviteMockMvc.perform(post("/api/secteur-activites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(secteurActivite)))
            .andExpect(status().isCreated());

        // Validate the SecteurActivite in the database
        List<SecteurActivite> secteurActiviteList = secteurActiviteRepository.findAll();
        assertThat(secteurActiviteList).hasSize(databaseSizeBeforeCreate + 1);
        SecteurActivite testSecteurActivite = secteurActiviteList.get(secteurActiviteList.size() - 1);
        assertThat(testSecteurActivite.getCodeBdu()).isEqualTo(DEFAULT_CODE_BDU);
        assertThat(testSecteurActivite.getCodeCb()).isEqualTo(DEFAULT_CODE_CB);
        assertThat(testSecteurActivite.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createSecteurActiviteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = secteurActiviteRepository.findAll().size();

        // Create the SecteurActivite with an existing ID
        secteurActivite.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSecteurActiviteMockMvc.perform(post("/api/secteur-activites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(secteurActivite)))
            .andExpect(status().isBadRequest());

        // Validate the SecteurActivite in the database
        List<SecteurActivite> secteurActiviteList = secteurActiviteRepository.findAll();
        assertThat(secteurActiviteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeBduIsRequired() throws Exception {
        int databaseSizeBeforeTest = secteurActiviteRepository.findAll().size();
        // set the field null
        secteurActivite.setCodeBdu(null);

        // Create the SecteurActivite, which fails.


        restSecteurActiviteMockMvc.perform(post("/api/secteur-activites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(secteurActivite)))
            .andExpect(status().isBadRequest());

        List<SecteurActivite> secteurActiviteList = secteurActiviteRepository.findAll();
        assertThat(secteurActiviteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeCbIsRequired() throws Exception {
        int databaseSizeBeforeTest = secteurActiviteRepository.findAll().size();
        // set the field null
        secteurActivite.setCodeCb(null);

        // Create the SecteurActivite, which fails.


        restSecteurActiviteMockMvc.perform(post("/api/secteur-activites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(secteurActivite)))
            .andExpect(status().isBadRequest());

        List<SecteurActivite> secteurActiviteList = secteurActiviteRepository.findAll();
        assertThat(secteurActiviteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSecteurActivites() throws Exception {
        // Initialize the database
        secteurActiviteRepository.saveAndFlush(secteurActivite);

        // Get all the secteurActiviteList
        restSecteurActiviteMockMvc.perform(get("/api/secteur-activites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(secteurActivite.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeBdu").value(hasItem(DEFAULT_CODE_BDU)))
            .andExpect(jsonPath("$.[*].codeCb").value(hasItem(DEFAULT_CODE_CB)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getSecteurActivite() throws Exception {
        // Initialize the database
        secteurActiviteRepository.saveAndFlush(secteurActivite);

        // Get the secteurActivite
        restSecteurActiviteMockMvc.perform(get("/api/secteur-activites/{id}", secteurActivite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(secteurActivite.getId().intValue()))
            .andExpect(jsonPath("$.codeBdu").value(DEFAULT_CODE_BDU))
            .andExpect(jsonPath("$.codeCb").value(DEFAULT_CODE_CB))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingSecteurActivite() throws Exception {
        // Get the secteurActivite
        restSecteurActiviteMockMvc.perform(get("/api/secteur-activites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSecteurActivite() throws Exception {
        // Initialize the database
        secteurActiviteService.save(secteurActivite);

        int databaseSizeBeforeUpdate = secteurActiviteRepository.findAll().size();

        // Update the secteurActivite
        SecteurActivite updatedSecteurActivite = secteurActiviteRepository.findById(secteurActivite.getId()).get();
        // Disconnect from session so that the updates on updatedSecteurActivite are not directly saved in db
        em.detach(updatedSecteurActivite);
        updatedSecteurActivite
            .codeBdu(UPDATED_CODE_BDU)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);

        restSecteurActiviteMockMvc.perform(put("/api/secteur-activites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSecteurActivite)))
            .andExpect(status().isOk());

        // Validate the SecteurActivite in the database
        List<SecteurActivite> secteurActiviteList = secteurActiviteRepository.findAll();
        assertThat(secteurActiviteList).hasSize(databaseSizeBeforeUpdate);
        SecteurActivite testSecteurActivite = secteurActiviteList.get(secteurActiviteList.size() - 1);
        assertThat(testSecteurActivite.getCodeBdu()).isEqualTo(UPDATED_CODE_BDU);
        assertThat(testSecteurActivite.getCodeCb()).isEqualTo(UPDATED_CODE_CB);
        assertThat(testSecteurActivite.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingSecteurActivite() throws Exception {
        int databaseSizeBeforeUpdate = secteurActiviteRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecteurActiviteMockMvc.perform(put("/api/secteur-activites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(secteurActivite)))
            .andExpect(status().isBadRequest());

        // Validate the SecteurActivite in the database
        List<SecteurActivite> secteurActiviteList = secteurActiviteRepository.findAll();
        assertThat(secteurActiviteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSecteurActivite() throws Exception {
        // Initialize the database
        secteurActiviteService.save(secteurActivite);

        int databaseSizeBeforeDelete = secteurActiviteRepository.findAll().size();

        // Delete the secteurActivite
        restSecteurActiviteMockMvc.perform(delete("/api/secteur-activites/{id}", secteurActivite.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SecteurActivite> secteurActiviteList = secteurActiviteRepository.findAll();
        assertThat(secteurActiviteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
