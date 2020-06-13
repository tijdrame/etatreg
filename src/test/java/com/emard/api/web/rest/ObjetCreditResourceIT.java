package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.ObjetCredit;
import com.emard.api.repository.ObjetCreditRepository;
import com.emard.api.service.ObjetCreditService;

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
 * Integration tests for the {@link ObjetCreditResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ObjetCreditResourceIT {

    private static final String DEFAULT_CODE_BDU = "AAAAAAAAAA";
    private static final String UPDATED_CODE_BDU = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_CB = "AAAAAAAAAA";
    private static final String UPDATED_CODE_CB = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ObjetCreditRepository objetCreditRepository;

    @Autowired
    private ObjetCreditService objetCreditService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restObjetCreditMockMvc;

    private ObjetCredit objetCredit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ObjetCredit createEntity(EntityManager em) {
        ObjetCredit objetCredit = new ObjetCredit()
            .codeBdu(DEFAULT_CODE_BDU)
            .codeCb(DEFAULT_CODE_CB)
            .description(DEFAULT_DESCRIPTION);
        return objetCredit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ObjetCredit createUpdatedEntity(EntityManager em) {
        ObjetCredit objetCredit = new ObjetCredit()
            .codeBdu(UPDATED_CODE_BDU)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);
        return objetCredit;
    }

    @BeforeEach
    public void initTest() {
        objetCredit = createEntity(em);
    }

    @Test
    @Transactional
    public void createObjetCredit() throws Exception {
        int databaseSizeBeforeCreate = objetCreditRepository.findAll().size();
        // Create the ObjetCredit
        restObjetCreditMockMvc.perform(post("/api/objet-credits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(objetCredit)))
            .andExpect(status().isCreated());

        // Validate the ObjetCredit in the database
        List<ObjetCredit> objetCreditList = objetCreditRepository.findAll();
        assertThat(objetCreditList).hasSize(databaseSizeBeforeCreate + 1);
        ObjetCredit testObjetCredit = objetCreditList.get(objetCreditList.size() - 1);
        assertThat(testObjetCredit.getCodeBdu()).isEqualTo(DEFAULT_CODE_BDU);
        assertThat(testObjetCredit.getCodeCb()).isEqualTo(DEFAULT_CODE_CB);
        assertThat(testObjetCredit.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createObjetCreditWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = objetCreditRepository.findAll().size();

        // Create the ObjetCredit with an existing ID
        objetCredit.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restObjetCreditMockMvc.perform(post("/api/objet-credits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(objetCredit)))
            .andExpect(status().isBadRequest());

        // Validate the ObjetCredit in the database
        List<ObjetCredit> objetCreditList = objetCreditRepository.findAll();
        assertThat(objetCreditList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeBduIsRequired() throws Exception {
        int databaseSizeBeforeTest = objetCreditRepository.findAll().size();
        // set the field null
        objetCredit.setCodeBdu(null);

        // Create the ObjetCredit, which fails.


        restObjetCreditMockMvc.perform(post("/api/objet-credits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(objetCredit)))
            .andExpect(status().isBadRequest());

        List<ObjetCredit> objetCreditList = objetCreditRepository.findAll();
        assertThat(objetCreditList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeCbIsRequired() throws Exception {
        int databaseSizeBeforeTest = objetCreditRepository.findAll().size();
        // set the field null
        objetCredit.setCodeCb(null);

        // Create the ObjetCredit, which fails.


        restObjetCreditMockMvc.perform(post("/api/objet-credits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(objetCredit)))
            .andExpect(status().isBadRequest());

        List<ObjetCredit> objetCreditList = objetCreditRepository.findAll();
        assertThat(objetCreditList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllObjetCredits() throws Exception {
        // Initialize the database
        objetCreditRepository.saveAndFlush(objetCredit);

        // Get all the objetCreditList
        restObjetCreditMockMvc.perform(get("/api/objet-credits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(objetCredit.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeBdu").value(hasItem(DEFAULT_CODE_BDU)))
            .andExpect(jsonPath("$.[*].codeCb").value(hasItem(DEFAULT_CODE_CB)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getObjetCredit() throws Exception {
        // Initialize the database
        objetCreditRepository.saveAndFlush(objetCredit);

        // Get the objetCredit
        restObjetCreditMockMvc.perform(get("/api/objet-credits/{id}", objetCredit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(objetCredit.getId().intValue()))
            .andExpect(jsonPath("$.codeBdu").value(DEFAULT_CODE_BDU))
            .andExpect(jsonPath("$.codeCb").value(DEFAULT_CODE_CB))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingObjetCredit() throws Exception {
        // Get the objetCredit
        restObjetCreditMockMvc.perform(get("/api/objet-credits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateObjetCredit() throws Exception {
        // Initialize the database
        objetCreditService.save(objetCredit);

        int databaseSizeBeforeUpdate = objetCreditRepository.findAll().size();

        // Update the objetCredit
        ObjetCredit updatedObjetCredit = objetCreditRepository.findById(objetCredit.getId()).get();
        // Disconnect from session so that the updates on updatedObjetCredit are not directly saved in db
        em.detach(updatedObjetCredit);
        updatedObjetCredit
            .codeBdu(UPDATED_CODE_BDU)
            .codeCb(UPDATED_CODE_CB)
            .description(UPDATED_DESCRIPTION);

        restObjetCreditMockMvc.perform(put("/api/objet-credits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedObjetCredit)))
            .andExpect(status().isOk());

        // Validate the ObjetCredit in the database
        List<ObjetCredit> objetCreditList = objetCreditRepository.findAll();
        assertThat(objetCreditList).hasSize(databaseSizeBeforeUpdate);
        ObjetCredit testObjetCredit = objetCreditList.get(objetCreditList.size() - 1);
        assertThat(testObjetCredit.getCodeBdu()).isEqualTo(UPDATED_CODE_BDU);
        assertThat(testObjetCredit.getCodeCb()).isEqualTo(UPDATED_CODE_CB);
        assertThat(testObjetCredit.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingObjetCredit() throws Exception {
        int databaseSizeBeforeUpdate = objetCreditRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObjetCreditMockMvc.perform(put("/api/objet-credits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(objetCredit)))
            .andExpect(status().isBadRequest());

        // Validate the ObjetCredit in the database
        List<ObjetCredit> objetCreditList = objetCreditRepository.findAll();
        assertThat(objetCreditList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteObjetCredit() throws Exception {
        // Initialize the database
        objetCreditService.save(objetCredit);

        int databaseSizeBeforeDelete = objetCreditRepository.findAll().size();

        // Delete the objetCredit
        restObjetCreditMockMvc.perform(delete("/api/objet-credits/{id}", objetCredit.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ObjetCredit> objetCreditList = objetCreditRepository.findAll();
        assertThat(objetCreditList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
