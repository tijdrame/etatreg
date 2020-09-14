package com.emard.api.web.rest;

import com.emard.api.domain.CrpAtr;
import com.emard.api.service.CrpAtrService;
import com.emard.api.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.emard.api.domain.CrpAtr}.
 */
@RestController
@RequestMapping("/api")
public class CrpAtrResource {

    private final Logger log = LoggerFactory.getLogger(CrpAtrResource.class);

    private static final String ENTITY_NAME = "crpAtr";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CrpAtrService crpAtrService;

    public CrpAtrResource(CrpAtrService crpAtrService) {
        this.crpAtrService = crpAtrService;
    }

    /**
     * {@code POST  /crp-atrs} : Create a new crpAtr.
     *
     * @param crpAtr the crpAtr to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new crpAtr, or with status {@code 400 (Bad Request)} if the crpAtr has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/crp-atrs")
    public ResponseEntity<CrpAtr> createCrpAtr(@RequestBody CrpAtr crpAtr) throws URISyntaxException {
        log.debug("REST request to save CrpAtr : {}", crpAtr);
        if (crpAtr.getId() != null) {
            throw new BadRequestAlertException("A new crpAtr cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CrpAtr result = crpAtrService.save(crpAtr);
        return ResponseEntity.created(new URI("/api/crp-atrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /crp-atrs} : Updates an existing crpAtr.
     *
     * @param crpAtr the crpAtr to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated crpAtr,
     * or with status {@code 400 (Bad Request)} if the crpAtr is not valid,
     * or with status {@code 500 (Internal Server Error)} if the crpAtr couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/crp-atrs")
    public ResponseEntity<CrpAtr> updateCrpAtr(@RequestBody CrpAtr crpAtr) throws URISyntaxException {
        log.debug("REST request to update CrpAtr : {}", crpAtr);
        if (crpAtr.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CrpAtr result = crpAtrService.save(crpAtr);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, crpAtr.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /crp-atrs} : get all the crpAtrs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of crpAtrs in body.
     */
    @GetMapping("/crp-atrs")
    public ResponseEntity<List<CrpAtr>> getAllCrpAtrs(Pageable pageable) {
        log.debug("REST request to get a page of CrpAtrs");
        Page<CrpAtr> page = crpAtrService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /crp-atrs/:id} : get the "id" crpAtr.
     *
     * @param id the id of the crpAtr to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the crpAtr, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/crp-atrs/{id}")
    public ResponseEntity<CrpAtr> getCrpAtr(@PathVariable Long id) {
        log.debug("REST request to get CrpAtr : {}", id);
        Optional<CrpAtr> crpAtr = crpAtrService.findOne(id);
        return ResponseUtil.wrapOrNotFound(crpAtr);
    }

    /**
     * {@code DELETE  /crp-atrs/:id} : delete the "id" crpAtr.
     *
     * @param id the id of the crpAtr to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/crp-atrs/{id}")
    public ResponseEntity<Void> deleteCrpAtr(@PathVariable Long id) {
        log.debug("REST request to delete CrpAtr : {}", id);
        crpAtrService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
