package com.emard.api.web.rest;

import com.emard.api.EtatregApp;
import com.emard.api.domain.CrpAtr;
import com.emard.api.repository.CrpAtrRepository;
import com.emard.api.service.CrpAtrService;

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
 * Integration tests for the {@link CrpAtrResource} REST controller.
 */
@SpringBootTest(classes = EtatregApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CrpAtrResourceIT {

    private static final String DEFAULT_CENR = "AAAAAAAAAA";
    private static final String UPDATED_CENR = "BBBBBBBBBB";

    private static final String DEFAULT_REFINT = "AAAAAAAAAA";
    private static final String UPDATED_REFINT = "BBBBBBBBBB";

    private static final String DEFAULT_NUM = "AAAAAAAAAA";
    private static final String UPDATED_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_NOBOR = "AAAAAAAAAA";
    private static final String UPDATED_NOBOR = "BBBBBBBBBB";

    private static final String DEFAULT_TYPENR = "AAAAAAAAAA";
    private static final String UPDATED_TYPENR = "BBBBBBBBBB";

    private static final String DEFAULT_ID_ATR = "AAAAAAAAAA";
    private static final String UPDATED_ID_ATR = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DEM_ATR = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DEM_ATR = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NUM_ATR = "AAAAAAAAAA";
    private static final String UPDATED_NUM_ATR = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_RES = "AAAAAAAAAA";
    private static final String UPDATED_NOM_RES = "BBBBBBBBBB";

    private static final String DEFAULT_CPAY_ISO = "AAAAAAAAAA";
    private static final String UPDATED_CPAY_ISO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATOPE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATOPE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REGT = "AAAAAAAAAA";
    private static final String UPDATED_REGT = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_ETR = "AAAAAAAAAA";
    private static final String UPDATED_NOM_ETR = "BBBBBBBBBB";

    private static final String DEFAULT_NOCLI = "AAAAAAAAAA";
    private static final String UPDATED_NOCLI = "BBBBBBBBBB";

    private static final String DEFAULT_CAT_RES = "AAAAAAAAAA";
    private static final String UPDATED_CAT_RES = "BBBBBBBBBB";

    private static final String DEFAULT_CECO = "AAAAAAAAAA";
    private static final String UPDATED_CECO = "BBBBBBBBBB";

    private static final String DEFAULT_CPAY_ETG = "AAAAAAAAAA";
    private static final String UPDATED_CPAY_ETG = "BBBBBBBBBB";

    private static final String DEFAULT_NATCPT = "AAAAAAAAAA";
    private static final String UPDATED_NATCPT = "BBBBBBBBBB";

    private static final String DEFAULT_SENS = "AAAAAAAAAA";
    private static final String UPDATED_SENS = "BBBBBBBBBB";

    private static final String DEFAULT_DEVN = "AAAAAAAAAA";
    private static final String UPDATED_DEVN = "BBBBBBBBBB";

    private static final Integer DEFAULT_MDEV = 1;
    private static final Integer UPDATED_MDEV = 2;

    private static final Integer DEFAULT_TAUX = 1;
    private static final Integer UPDATED_TAUX = 2;

    private static final Integer DEFAULT_MNAT = 1;
    private static final Integer UPDATED_MNAT = 2;

    private static final String DEFAULT_ETAB = "AAAAAAAAAA";
    private static final String UPDATED_ETAB = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_FIC = "AAAAAAAAAA";
    private static final String UPDATED_NOM_FIC = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_ARRETE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ARRETE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private CrpAtrRepository crpAtrRepository;

    @Autowired
    private CrpAtrService crpAtrService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCrpAtrMockMvc;

    private CrpAtr crpAtr;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CrpAtr createEntity(EntityManager em) {
        CrpAtr crpAtr = new CrpAtr()
            .cenr(DEFAULT_CENR)
            .refint(DEFAULT_REFINT)
            .num(DEFAULT_NUM)
            .nobor(DEFAULT_NOBOR)
            .typenr(DEFAULT_TYPENR)
            .idAtr(DEFAULT_ID_ATR)
            .demAtr(DEFAULT_DEM_ATR)
            .numAtr(DEFAULT_NUM_ATR)
            .nomRes(DEFAULT_NOM_RES)
            .cpayIso(DEFAULT_CPAY_ISO)
            .datope(DEFAULT_DATOPE)
            .regt(DEFAULT_REGT)
            .nomEtr(DEFAULT_NOM_ETR)
            .nocli(DEFAULT_NOCLI)
            .catRes(DEFAULT_CAT_RES)
            .ceco(DEFAULT_CECO)
            .cpayEtg(DEFAULT_CPAY_ETG)
            .natcpt(DEFAULT_NATCPT)
            .sens(DEFAULT_SENS)
            .devn(DEFAULT_DEVN)
            .mdev(DEFAULT_MDEV)
            .taux(DEFAULT_TAUX)
            .mnat(DEFAULT_MNAT)
            .etab(DEFAULT_ETAB)
            .nomFic(DEFAULT_NOM_FIC)
            .dateArrete(DEFAULT_DATE_ARRETE);
        return crpAtr;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CrpAtr createUpdatedEntity(EntityManager em) {
        CrpAtr crpAtr = new CrpAtr()
            .cenr(UPDATED_CENR)
            .refint(UPDATED_REFINT)
            .num(UPDATED_NUM)
            .nobor(UPDATED_NOBOR)
            .typenr(UPDATED_TYPENR)
            .idAtr(UPDATED_ID_ATR)
            .demAtr(UPDATED_DEM_ATR)
            .numAtr(UPDATED_NUM_ATR)
            .nomRes(UPDATED_NOM_RES)
            .cpayIso(UPDATED_CPAY_ISO)
            .datope(UPDATED_DATOPE)
            .regt(UPDATED_REGT)
            .nomEtr(UPDATED_NOM_ETR)
            .nocli(UPDATED_NOCLI)
            .catRes(UPDATED_CAT_RES)
            .ceco(UPDATED_CECO)
            .cpayEtg(UPDATED_CPAY_ETG)
            .natcpt(UPDATED_NATCPT)
            .sens(UPDATED_SENS)
            .devn(UPDATED_DEVN)
            .mdev(UPDATED_MDEV)
            .taux(UPDATED_TAUX)
            .mnat(UPDATED_MNAT)
            .etab(UPDATED_ETAB)
            .nomFic(UPDATED_NOM_FIC)
            .dateArrete(UPDATED_DATE_ARRETE);
        return crpAtr;
    }

    @BeforeEach
    public void initTest() {
        crpAtr = createEntity(em);
    }

    @Test
    @Transactional
    public void createCrpAtr() throws Exception {
        int databaseSizeBeforeCreate = crpAtrRepository.findAll().size();
        // Create the CrpAtr
        restCrpAtrMockMvc.perform(post("/api/crp-atrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(crpAtr)))
            .andExpect(status().isCreated());

        // Validate the CrpAtr in the database
        List<CrpAtr> crpAtrList = crpAtrRepository.findAll();
        assertThat(crpAtrList).hasSize(databaseSizeBeforeCreate + 1);
        CrpAtr testCrpAtr = crpAtrList.get(crpAtrList.size() - 1);
        assertThat(testCrpAtr.getCenr()).isEqualTo(DEFAULT_CENR);
        assertThat(testCrpAtr.getRefint()).isEqualTo(DEFAULT_REFINT);
        assertThat(testCrpAtr.getNum()).isEqualTo(DEFAULT_NUM);
        assertThat(testCrpAtr.getNobor()).isEqualTo(DEFAULT_NOBOR);
        assertThat(testCrpAtr.getTypenr()).isEqualTo(DEFAULT_TYPENR);
        assertThat(testCrpAtr.getIdAtr()).isEqualTo(DEFAULT_ID_ATR);
        assertThat(testCrpAtr.getDemAtr()).isEqualTo(DEFAULT_DEM_ATR);
        assertThat(testCrpAtr.getNumAtr()).isEqualTo(DEFAULT_NUM_ATR);
        assertThat(testCrpAtr.getNomRes()).isEqualTo(DEFAULT_NOM_RES);
        assertThat(testCrpAtr.getCpayIso()).isEqualTo(DEFAULT_CPAY_ISO);
        assertThat(testCrpAtr.getDatope()).isEqualTo(DEFAULT_DATOPE);
        assertThat(testCrpAtr.getRegt()).isEqualTo(DEFAULT_REGT);
        assertThat(testCrpAtr.getNomEtr()).isEqualTo(DEFAULT_NOM_ETR);
        assertThat(testCrpAtr.getNocli()).isEqualTo(DEFAULT_NOCLI);
        assertThat(testCrpAtr.getCatRes()).isEqualTo(DEFAULT_CAT_RES);
        assertThat(testCrpAtr.getCeco()).isEqualTo(DEFAULT_CECO);
        assertThat(testCrpAtr.getCpayEtg()).isEqualTo(DEFAULT_CPAY_ETG);
        assertThat(testCrpAtr.getNatcpt()).isEqualTo(DEFAULT_NATCPT);
        assertThat(testCrpAtr.getSens()).isEqualTo(DEFAULT_SENS);
        assertThat(testCrpAtr.getDevn()).isEqualTo(DEFAULT_DEVN);
        assertThat(testCrpAtr.getMdev()).isEqualTo(DEFAULT_MDEV);
        assertThat(testCrpAtr.getTaux()).isEqualTo(DEFAULT_TAUX);
        assertThat(testCrpAtr.getMnat()).isEqualTo(DEFAULT_MNAT);
        assertThat(testCrpAtr.getEtab()).isEqualTo(DEFAULT_ETAB);
        assertThat(testCrpAtr.getNomFic()).isEqualTo(DEFAULT_NOM_FIC);
        assertThat(testCrpAtr.getDateArrete()).isEqualTo(DEFAULT_DATE_ARRETE);
    }

    @Test
    @Transactional
    public void createCrpAtrWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = crpAtrRepository.findAll().size();

        // Create the CrpAtr with an existing ID
        crpAtr.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCrpAtrMockMvc.perform(post("/api/crp-atrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(crpAtr)))
            .andExpect(status().isBadRequest());

        // Validate the CrpAtr in the database
        List<CrpAtr> crpAtrList = crpAtrRepository.findAll();
        assertThat(crpAtrList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCrpAtrs() throws Exception {
        // Initialize the database
        crpAtrRepository.saveAndFlush(crpAtr);

        // Get all the crpAtrList
        restCrpAtrMockMvc.perform(get("/api/crp-atrs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(crpAtr.getId().intValue())))
            .andExpect(jsonPath("$.[*].cenr").value(hasItem(DEFAULT_CENR)))
            .andExpect(jsonPath("$.[*].refint").value(hasItem(DEFAULT_REFINT)))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM)))
            .andExpect(jsonPath("$.[*].nobor").value(hasItem(DEFAULT_NOBOR)))
            .andExpect(jsonPath("$.[*].typenr").value(hasItem(DEFAULT_TYPENR)))
            .andExpect(jsonPath("$.[*].idAtr").value(hasItem(DEFAULT_ID_ATR)))
            .andExpect(jsonPath("$.[*].demAtr").value(hasItem(DEFAULT_DEM_ATR.toString())))
            .andExpect(jsonPath("$.[*].numAtr").value(hasItem(DEFAULT_NUM_ATR)))
            .andExpect(jsonPath("$.[*].nomRes").value(hasItem(DEFAULT_NOM_RES)))
            .andExpect(jsonPath("$.[*].cpayIso").value(hasItem(DEFAULT_CPAY_ISO)))
            .andExpect(jsonPath("$.[*].datope").value(hasItem(DEFAULT_DATOPE.toString())))
            .andExpect(jsonPath("$.[*].regt").value(hasItem(DEFAULT_REGT)))
            .andExpect(jsonPath("$.[*].nomEtr").value(hasItem(DEFAULT_NOM_ETR)))
            .andExpect(jsonPath("$.[*].nocli").value(hasItem(DEFAULT_NOCLI)))
            .andExpect(jsonPath("$.[*].catRes").value(hasItem(DEFAULT_CAT_RES)))
            .andExpect(jsonPath("$.[*].ceco").value(hasItem(DEFAULT_CECO)))
            .andExpect(jsonPath("$.[*].cpayEtg").value(hasItem(DEFAULT_CPAY_ETG)))
            .andExpect(jsonPath("$.[*].natcpt").value(hasItem(DEFAULT_NATCPT)))
            .andExpect(jsonPath("$.[*].sens").value(hasItem(DEFAULT_SENS)))
            .andExpect(jsonPath("$.[*].devn").value(hasItem(DEFAULT_DEVN)))
            .andExpect(jsonPath("$.[*].mdev").value(hasItem(DEFAULT_MDEV)))
            .andExpect(jsonPath("$.[*].taux").value(hasItem(DEFAULT_TAUX)))
            .andExpect(jsonPath("$.[*].mnat").value(hasItem(DEFAULT_MNAT)))
            .andExpect(jsonPath("$.[*].etab").value(hasItem(DEFAULT_ETAB)))
            .andExpect(jsonPath("$.[*].nomFic").value(hasItem(DEFAULT_NOM_FIC)))
            .andExpect(jsonPath("$.[*].dateArrete").value(hasItem(DEFAULT_DATE_ARRETE.toString())));
    }
    
    @Test
    @Transactional
    public void getCrpAtr() throws Exception {
        // Initialize the database
        crpAtrRepository.saveAndFlush(crpAtr);

        // Get the crpAtr
        restCrpAtrMockMvc.perform(get("/api/crp-atrs/{id}", crpAtr.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(crpAtr.getId().intValue()))
            .andExpect(jsonPath("$.cenr").value(DEFAULT_CENR))
            .andExpect(jsonPath("$.refint").value(DEFAULT_REFINT))
            .andExpect(jsonPath("$.num").value(DEFAULT_NUM))
            .andExpect(jsonPath("$.nobor").value(DEFAULT_NOBOR))
            .andExpect(jsonPath("$.typenr").value(DEFAULT_TYPENR))
            .andExpect(jsonPath("$.idAtr").value(DEFAULT_ID_ATR))
            .andExpect(jsonPath("$.demAtr").value(DEFAULT_DEM_ATR.toString()))
            .andExpect(jsonPath("$.numAtr").value(DEFAULT_NUM_ATR))
            .andExpect(jsonPath("$.nomRes").value(DEFAULT_NOM_RES))
            .andExpect(jsonPath("$.cpayIso").value(DEFAULT_CPAY_ISO))
            .andExpect(jsonPath("$.datope").value(DEFAULT_DATOPE.toString()))
            .andExpect(jsonPath("$.regt").value(DEFAULT_REGT))
            .andExpect(jsonPath("$.nomEtr").value(DEFAULT_NOM_ETR))
            .andExpect(jsonPath("$.nocli").value(DEFAULT_NOCLI))
            .andExpect(jsonPath("$.catRes").value(DEFAULT_CAT_RES))
            .andExpect(jsonPath("$.ceco").value(DEFAULT_CECO))
            .andExpect(jsonPath("$.cpayEtg").value(DEFAULT_CPAY_ETG))
            .andExpect(jsonPath("$.natcpt").value(DEFAULT_NATCPT))
            .andExpect(jsonPath("$.sens").value(DEFAULT_SENS))
            .andExpect(jsonPath("$.devn").value(DEFAULT_DEVN))
            .andExpect(jsonPath("$.mdev").value(DEFAULT_MDEV))
            .andExpect(jsonPath("$.taux").value(DEFAULT_TAUX))
            .andExpect(jsonPath("$.mnat").value(DEFAULT_MNAT))
            .andExpect(jsonPath("$.etab").value(DEFAULT_ETAB))
            .andExpect(jsonPath("$.nomFic").value(DEFAULT_NOM_FIC))
            .andExpect(jsonPath("$.dateArrete").value(DEFAULT_DATE_ARRETE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCrpAtr() throws Exception {
        // Get the crpAtr
        restCrpAtrMockMvc.perform(get("/api/crp-atrs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCrpAtr() throws Exception {
        // Initialize the database
        crpAtrService.save(crpAtr);

        int databaseSizeBeforeUpdate = crpAtrRepository.findAll().size();

        // Update the crpAtr
        CrpAtr updatedCrpAtr = crpAtrRepository.findById(crpAtr.getId()).get();
        // Disconnect from session so that the updates on updatedCrpAtr are not directly saved in db
        em.detach(updatedCrpAtr);
        updatedCrpAtr
            .cenr(UPDATED_CENR)
            .refint(UPDATED_REFINT)
            .num(UPDATED_NUM)
            .nobor(UPDATED_NOBOR)
            .typenr(UPDATED_TYPENR)
            .idAtr(UPDATED_ID_ATR)
            .demAtr(UPDATED_DEM_ATR)
            .numAtr(UPDATED_NUM_ATR)
            .nomRes(UPDATED_NOM_RES)
            .cpayIso(UPDATED_CPAY_ISO)
            .datope(UPDATED_DATOPE)
            .regt(UPDATED_REGT)
            .nomEtr(UPDATED_NOM_ETR)
            .nocli(UPDATED_NOCLI)
            .catRes(UPDATED_CAT_RES)
            .ceco(UPDATED_CECO)
            .cpayEtg(UPDATED_CPAY_ETG)
            .natcpt(UPDATED_NATCPT)
            .sens(UPDATED_SENS)
            .devn(UPDATED_DEVN)
            .mdev(UPDATED_MDEV)
            .taux(UPDATED_TAUX)
            .mnat(UPDATED_MNAT)
            .etab(UPDATED_ETAB)
            .nomFic(UPDATED_NOM_FIC)
            .dateArrete(UPDATED_DATE_ARRETE);

        restCrpAtrMockMvc.perform(put("/api/crp-atrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCrpAtr)))
            .andExpect(status().isOk());

        // Validate the CrpAtr in the database
        List<CrpAtr> crpAtrList = crpAtrRepository.findAll();
        assertThat(crpAtrList).hasSize(databaseSizeBeforeUpdate);
        CrpAtr testCrpAtr = crpAtrList.get(crpAtrList.size() - 1);
        assertThat(testCrpAtr.getCenr()).isEqualTo(UPDATED_CENR);
        assertThat(testCrpAtr.getRefint()).isEqualTo(UPDATED_REFINT);
        assertThat(testCrpAtr.getNum()).isEqualTo(UPDATED_NUM);
        assertThat(testCrpAtr.getNobor()).isEqualTo(UPDATED_NOBOR);
        assertThat(testCrpAtr.getTypenr()).isEqualTo(UPDATED_TYPENR);
        assertThat(testCrpAtr.getIdAtr()).isEqualTo(UPDATED_ID_ATR);
        assertThat(testCrpAtr.getDemAtr()).isEqualTo(UPDATED_DEM_ATR);
        assertThat(testCrpAtr.getNumAtr()).isEqualTo(UPDATED_NUM_ATR);
        assertThat(testCrpAtr.getNomRes()).isEqualTo(UPDATED_NOM_RES);
        assertThat(testCrpAtr.getCpayIso()).isEqualTo(UPDATED_CPAY_ISO);
        assertThat(testCrpAtr.getDatope()).isEqualTo(UPDATED_DATOPE);
        assertThat(testCrpAtr.getRegt()).isEqualTo(UPDATED_REGT);
        assertThat(testCrpAtr.getNomEtr()).isEqualTo(UPDATED_NOM_ETR);
        assertThat(testCrpAtr.getNocli()).isEqualTo(UPDATED_NOCLI);
        assertThat(testCrpAtr.getCatRes()).isEqualTo(UPDATED_CAT_RES);
        assertThat(testCrpAtr.getCeco()).isEqualTo(UPDATED_CECO);
        assertThat(testCrpAtr.getCpayEtg()).isEqualTo(UPDATED_CPAY_ETG);
        assertThat(testCrpAtr.getNatcpt()).isEqualTo(UPDATED_NATCPT);
        assertThat(testCrpAtr.getSens()).isEqualTo(UPDATED_SENS);
        assertThat(testCrpAtr.getDevn()).isEqualTo(UPDATED_DEVN);
        assertThat(testCrpAtr.getMdev()).isEqualTo(UPDATED_MDEV);
        assertThat(testCrpAtr.getTaux()).isEqualTo(UPDATED_TAUX);
        assertThat(testCrpAtr.getMnat()).isEqualTo(UPDATED_MNAT);
        assertThat(testCrpAtr.getEtab()).isEqualTo(UPDATED_ETAB);
        assertThat(testCrpAtr.getNomFic()).isEqualTo(UPDATED_NOM_FIC);
        assertThat(testCrpAtr.getDateArrete()).isEqualTo(UPDATED_DATE_ARRETE);
    }

    @Test
    @Transactional
    public void updateNonExistingCrpAtr() throws Exception {
        int databaseSizeBeforeUpdate = crpAtrRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCrpAtrMockMvc.perform(put("/api/crp-atrs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(crpAtr)))
            .andExpect(status().isBadRequest());

        // Validate the CrpAtr in the database
        List<CrpAtr> crpAtrList = crpAtrRepository.findAll();
        assertThat(crpAtrList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCrpAtr() throws Exception {
        // Initialize the database
        crpAtrService.save(crpAtr);

        int databaseSizeBeforeDelete = crpAtrRepository.findAll().size();

        // Delete the crpAtr
        restCrpAtrMockMvc.perform(delete("/api/crp-atrs/{id}", crpAtr.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CrpAtr> crpAtrList = crpAtrRepository.findAll();
        assertThat(crpAtrList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
