package com.emard.api.web.rest;

import com.emard.api.domain.Bp3His;
import com.emard.api.service.Bp3HisService;
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
 * REST controller for managing {@link com.emard.api.domain.Bp3His}.
 */
@RestController
@RequestMapping("/api")
public class Bp3HisResource {

    private final Logger log = LoggerFactory.getLogger(Bp3HisResource.class);

    private static final String ENTITY_NAME = "bp3His";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Bp3HisService bp3HisService;

    public Bp3HisResource(Bp3HisService bp3HisService) {
        this.bp3HisService = bp3HisService;
    }

    /**
     * {@code POST  /bp-3-his} : Create a new bp3His.
     *
     * @param bp3His the bp3His to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bp3His, or with status {@code 400 (Bad Request)} if the bp3His has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bp-3-his")
    public ResponseEntity<Bp3His> createBp3His(@RequestBody Bp3His bp3His) throws URISyntaxException {
        log.debug("REST request to save Bp3His : {}", bp3His);
        if (bp3His.getId() != null) {
            throw new BadRequestAlertException("A new bp3His cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bp3His result = bp3HisService.save(bp3His);
        return ResponseEntity.created(new URI("/api/bp-3-his/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bp-3-his} : Updates an existing bp3His.
     *
     * @param bp3His the bp3His to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bp3His,
     * or with status {@code 400 (Bad Request)} if the bp3His is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bp3His couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bp-3-his")
    public ResponseEntity<Bp3His> updateBp3His(@RequestBody Bp3His bp3His) throws URISyntaxException {
        log.debug("REST request to update Bp3His : {}", bp3His);
        if (bp3His.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bp3His result = bp3HisService.save(bp3His);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bp3His.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bp-3-his} : get all the bp3His.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bp3His in body.
     */
    @GetMapping("/bp-3-his")
    public ResponseEntity<List<Bp3His>> getAllBp3His(Pageable pageable) {
        log.debug("REST request to get a page of Bp3His");
        Page<Bp3His> page = bp3HisService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bp-3-his/:id} : get the "id" bp3His.
     *
     * @param id the id of the bp3His to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bp3His, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bp-3-his/{id}")
    public ResponseEntity<Bp3His> getBp3His(@PathVariable Long id) {
        log.debug("REST request to get Bp3His : {}", id);
        Optional<Bp3His> bp3His = bp3HisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bp3His);
    }

    /**
     * {@code DELETE  /bp-3-his/:id} : delete the "id" bp3His.
     *
     * @param id the id of the bp3His to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bp-3-his/{id}")
    public ResponseEntity<Void> deleteBp3His(@PathVariable Long id) {
        log.debug("REST request to delete Bp3His : {}", id);
        bp3HisService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
