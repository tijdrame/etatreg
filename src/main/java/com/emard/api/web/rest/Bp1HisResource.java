package com.emard.api.web.rest;

import com.emard.api.domain.Bp1His;
import com.emard.api.service.Bp1HisService;
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
 * REST controller for managing {@link com.emard.api.domain.Bp1His}.
 */
@RestController
@RequestMapping("/api")
public class Bp1HisResource {

    private final Logger log = LoggerFactory.getLogger(Bp1HisResource.class);

    private static final String ENTITY_NAME = "bp1His";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Bp1HisService bp1HisService;

    public Bp1HisResource(Bp1HisService bp1HisService) {
        this.bp1HisService = bp1HisService;
    }

    /**
     * {@code POST  /bp-1-his} : Create a new bp1His.
     *
     * @param bp1His the bp1His to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bp1His, or with status {@code 400 (Bad Request)} if the bp1His has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bp-1-his")
    public ResponseEntity<Bp1His> createBp1His(@RequestBody Bp1His bp1His) throws URISyntaxException {
        log.debug("REST request to save Bp1His : {}", bp1His);
        if (bp1His.getId() != null) {
            throw new BadRequestAlertException("A new bp1His cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bp1His result = bp1HisService.save(bp1His);
        return ResponseEntity.created(new URI("/api/bp-1-his/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bp-1-his} : Updates an existing bp1His.
     *
     * @param bp1His the bp1His to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bp1His,
     * or with status {@code 400 (Bad Request)} if the bp1His is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bp1His couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bp-1-his")
    public ResponseEntity<Bp1His> updateBp1His(@RequestBody Bp1His bp1His) throws URISyntaxException {
        log.debug("REST request to update Bp1His : {}", bp1His);
        if (bp1His.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bp1His result = bp1HisService.save(bp1His);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bp1His.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bp-1-his} : get all the bp1His.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bp1His in body.
     */
    @GetMapping("/bp-1-his")
    public ResponseEntity<List<Bp1His>> getAllBp1His(Pageable pageable) {
        log.debug("REST request to get a page of Bp1His");
        Page<Bp1His> page = bp1HisService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bp-1-his/:id} : get the "id" bp1His.
     *
     * @param id the id of the bp1His to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bp1His, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bp-1-his/{id}")
    public ResponseEntity<Bp1His> getBp1His(@PathVariable Long id) {
        log.debug("REST request to get Bp1His : {}", id);
        Optional<Bp1His> bp1His = bp1HisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bp1His);
    }

    /**
     * {@code DELETE  /bp-1-his/:id} : delete the "id" bp1His.
     *
     * @param id the id of the bp1His to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bp-1-his/{id}")
    public ResponseEntity<Void> deleteBp1His(@PathVariable Long id) {
        log.debug("REST request to delete Bp1His : {}", id);
        bp1HisService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
