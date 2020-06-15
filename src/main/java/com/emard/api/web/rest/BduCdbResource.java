package com.emard.api.web.rest;

import com.emard.api.domain.BduCdb;
import com.emard.api.service.BduCdbService;
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
 * REST controller for managing {@link com.emard.api.domain.BduCdb}.
 */
@RestController
@RequestMapping("/api")
public class BduCdbResource {

    private final Logger log = LoggerFactory.getLogger(BduCdbResource.class);

    private static final String ENTITY_NAME = "bduCdb";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BduCdbService bduCdbService;

    public BduCdbResource(BduCdbService bduCdbService) {
        this.bduCdbService = bduCdbService;
    }

    /**
     * {@code POST  /bdu-cdbs} : Create a new bduCdb.
     *
     * @param bduCdb the bduCdb to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bduCdb, or with status {@code 400 (Bad Request)} if the bduCdb has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bdu-cdbs")
    public ResponseEntity<BduCdb> createBduCdb(@RequestBody BduCdb bduCdb) throws URISyntaxException {
        log.debug("REST request to save BduCdb : {}", bduCdb);
        if (bduCdb.getId() != null) {
            throw new BadRequestAlertException("A new bduCdb cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BduCdb result = bduCdbService.save(bduCdb);
        return ResponseEntity.created(new URI("/api/bdu-cdbs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bdu-cdbs} : Updates an existing bduCdb.
     *
     * @param bduCdb the bduCdb to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bduCdb,
     * or with status {@code 400 (Bad Request)} if the bduCdb is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bduCdb couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bdu-cdbs")
    public ResponseEntity<BduCdb> updateBduCdb(@RequestBody BduCdb bduCdb) throws URISyntaxException {
        log.debug("REST request to update BduCdb : {}", bduCdb);
        if (bduCdb.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BduCdb result = bduCdbService.save(bduCdb);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bduCdb.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bdu-cdbs} : get all the bduCdbs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bduCdbs in body.
     */
    @GetMapping("/bdu-cdbs")
    public ResponseEntity<List<BduCdb>> getAllBduCdbs(Pageable pageable) {
        log.debug("REST request to get a page of BduCdbs");
        Page<BduCdb> page = bduCdbService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bdu-cdbs/:id} : get the "id" bduCdb.
     *
     * @param id the id of the bduCdb to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bduCdb, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bdu-cdbs/{id}")
    public ResponseEntity<BduCdb> getBduCdb(@PathVariable Long id) {
        log.debug("REST request to get BduCdb : {}", id);
        Optional<BduCdb> bduCdb = bduCdbService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bduCdb);
    }

    /**
     * {@code DELETE  /bdu-cdbs/:id} : delete the "id" bduCdb.
     *
     * @param id the id of the bduCdb to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bdu-cdbs/{id}")
    public ResponseEntity<Void> deleteBduCdb(@PathVariable Long id) {
        log.debug("REST request to delete BduCdb : {}", id);
        bduCdbService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
