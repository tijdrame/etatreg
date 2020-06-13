package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.Bp4Infos;
import com.emard.api.repository.Bp4InfosRepository;
import com.emard.api.service.Bp4InfosService;

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
 * Integration tests for the {@link Bp4InfosResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class Bp4InfosResourceIT {

    private static final String DEFAULT_CODE_ISO_PAYS = "AAAAAAAAAA";
    private static final String UPDATED_CODE_ISO_PAYS = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE_PAYS = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_PAYS = "BBBBBBBBBB";

    private static final Double DEFAULT_MNTNOS_CARTES = 1D;
    private static final Double UPDATED_MNTNOS_CARTES = 2D;

    private static final Double DEFAULT_MNT_CARTES_ETR = 1D;
    private static final Double UPDATED_MNT_CARTES_ETR = 2D;

    @Autowired
    private Bp4InfosRepository bp4InfosRepository;

    @Autowired
    private Bp4InfosService bp4InfosService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBp4InfosMockMvc;

    private Bp4Infos bp4Infos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bp4Infos createEntity(EntityManager em) {
        Bp4Infos bp4Infos = new Bp4Infos()
            .codeIsoPays(DEFAULT_CODE_ISO_PAYS)
            .libellePays(DEFAULT_LIBELLE_PAYS)
            .mntnosCartes(DEFAULT_MNTNOS_CARTES)
            .mntCartesEtr(DEFAULT_MNT_CARTES_ETR);
        return bp4Infos;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bp4Infos createUpdatedEntity(EntityManager em) {
        Bp4Infos bp4Infos = new Bp4Infos()
            .codeIsoPays(UPDATED_CODE_ISO_PAYS)
            .libellePays(UPDATED_LIBELLE_PAYS)
            .mntnosCartes(UPDATED_MNTNOS_CARTES)
            .mntCartesEtr(UPDATED_MNT_CARTES_ETR);
        return bp4Infos;
    }

    @BeforeEach
    public void initTest() {
        bp4Infos = createEntity(em);
    }

    @Test
    @Transactional
    public void createBp4Infos() throws Exception {
        int databaseSizeBeforeCreate = bp4InfosRepository.findAll().size();
        // Create the Bp4Infos
        restBp4InfosMockMvc.perform(post("/api/bp-4-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp4Infos)))
            .andExpect(status().isCreated());

        // Validate the Bp4Infos in the database
        List<Bp4Infos> bp4InfosList = bp4InfosRepository.findAll();
        assertThat(bp4InfosList).hasSize(databaseSizeBeforeCreate + 1);
        Bp4Infos testBp4Infos = bp4InfosList.get(bp4InfosList.size() - 1);
        assertThat(testBp4Infos.getCodeIsoPays()).isEqualTo(DEFAULT_CODE_ISO_PAYS);
        assertThat(testBp4Infos.getLibellePays()).isEqualTo(DEFAULT_LIBELLE_PAYS);
        assertThat(testBp4Infos.getMntnosCartes()).isEqualTo(DEFAULT_MNTNOS_CARTES);
        assertThat(testBp4Infos.getMntCartesEtr()).isEqualTo(DEFAULT_MNT_CARTES_ETR);
    }

    @Test
    @Transactional
    public void createBp4InfosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bp4InfosRepository.findAll().size();

        // Create the Bp4Infos with an existing ID
        bp4Infos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBp4InfosMockMvc.perform(post("/api/bp-4-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp4Infos)))
            .andExpect(status().isBadRequest());

        // Validate the Bp4Infos in the database
        List<Bp4Infos> bp4InfosList = bp4InfosRepository.findAll();
        assertThat(bp4InfosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBp4Infos() throws Exception {
        // Initialize the database
        bp4InfosRepository.saveAndFlush(bp4Infos);

        // Get all the bp4InfosList
        restBp4InfosMockMvc.perform(get("/api/bp-4-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bp4Infos.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeIsoPays").value(hasItem(DEFAULT_CODE_ISO_PAYS)))
            .andExpect(jsonPath("$.[*].libellePays").value(hasItem(DEFAULT_LIBELLE_PAYS)))
            .andExpect(jsonPath("$.[*].mntnosCartes").value(hasItem(DEFAULT_MNTNOS_CARTES.doubleValue())))
            .andExpect(jsonPath("$.[*].mntCartesEtr").value(hasItem(DEFAULT_MNT_CARTES_ETR.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getBp4Infos() throws Exception {
        // Initialize the database
        bp4InfosRepository.saveAndFlush(bp4Infos);

        // Get the bp4Infos
        restBp4InfosMockMvc.perform(get("/api/bp-4-infos/{id}", bp4Infos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bp4Infos.getId().intValue()))
            .andExpect(jsonPath("$.codeIsoPays").value(DEFAULT_CODE_ISO_PAYS))
            .andExpect(jsonPath("$.libellePays").value(DEFAULT_LIBELLE_PAYS))
            .andExpect(jsonPath("$.mntnosCartes").value(DEFAULT_MNTNOS_CARTES.doubleValue()))
            .andExpect(jsonPath("$.mntCartesEtr").value(DEFAULT_MNT_CARTES_ETR.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingBp4Infos() throws Exception {
        // Get the bp4Infos
        restBp4InfosMockMvc.perform(get("/api/bp-4-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBp4Infos() throws Exception {
        // Initialize the database
        bp4InfosService.save(bp4Infos);

        int databaseSizeBeforeUpdate = bp4InfosRepository.findAll().size();

        // Update the bp4Infos
        Bp4Infos updatedBp4Infos = bp4InfosRepository.findById(bp4Infos.getId()).get();
        // Disconnect from session so that the updates on updatedBp4Infos are not directly saved in db
        em.detach(updatedBp4Infos);
        updatedBp4Infos
            .codeIsoPays(UPDATED_CODE_ISO_PAYS)
            .libellePays(UPDATED_LIBELLE_PAYS)
            .mntnosCartes(UPDATED_MNTNOS_CARTES)
            .mntCartesEtr(UPDATED_MNT_CARTES_ETR);

        restBp4InfosMockMvc.perform(put("/api/bp-4-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBp4Infos)))
            .andExpect(status().isOk());

        // Validate the Bp4Infos in the database
        List<Bp4Infos> bp4InfosList = bp4InfosRepository.findAll();
        assertThat(bp4InfosList).hasSize(databaseSizeBeforeUpdate);
        Bp4Infos testBp4Infos = bp4InfosList.get(bp4InfosList.size() - 1);
        assertThat(testBp4Infos.getCodeIsoPays()).isEqualTo(UPDATED_CODE_ISO_PAYS);
        assertThat(testBp4Infos.getLibellePays()).isEqualTo(UPDATED_LIBELLE_PAYS);
        assertThat(testBp4Infos.getMntnosCartes()).isEqualTo(UPDATED_MNTNOS_CARTES);
        assertThat(testBp4Infos.getMntCartesEtr()).isEqualTo(UPDATED_MNT_CARTES_ETR);
    }

    @Test
    @Transactional
    public void updateNonExistingBp4Infos() throws Exception {
        int databaseSizeBeforeUpdate = bp4InfosRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBp4InfosMockMvc.perform(put("/api/bp-4-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bp4Infos)))
            .andExpect(status().isBadRequest());

        // Validate the Bp4Infos in the database
        List<Bp4Infos> bp4InfosList = bp4InfosRepository.findAll();
        assertThat(bp4InfosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBp4Infos() throws Exception {
        // Initialize the database
        bp4InfosService.save(bp4Infos);

        int databaseSizeBeforeDelete = bp4InfosRepository.findAll().size();

        // Delete the bp4Infos
        restBp4InfosMockMvc.perform(delete("/api/bp-4-infos/{id}", bp4Infos.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bp4Infos> bp4InfosList = bp4InfosRepository.findAll();
        assertThat(bp4InfosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
