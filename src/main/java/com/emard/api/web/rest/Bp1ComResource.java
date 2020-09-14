package com.emard.api.web.rest;

import com.emard.api.domain.Bp1Com;
import com.emard.api.service.Bp1ComService;
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
 * REST controller for managing {@link com.emard.api.domain.Bp1Com}.
 */
@RestController
@RequestMapping("/api")
public class Bp1ComResource {

    private final Logger log = LoggerFactory.getLogger(Bp1ComResource.class);

    private static final String ENTITY_NAME = "bp1Com";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Bp1ComService bp1ComService;

    public Bp1ComResource(Bp1ComService bp1ComService) {
        this.bp1ComService = bp1ComService;
    }

    /**
     * {@code POST  /bp-1-coms} : Create a new bp1Com.
     *
     * @param bp1Com the bp1Com to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bp1Com, or with status {@code 400 (Bad Request)} if the bp1Com has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bp-1-coms")
    public ResponseEntity<Bp1Com> createBp1Com(@RequestBody Bp1Com bp1Com) throws URISyntaxException {
        log.debug("REST request to save Bp1Com : {}", bp1Com);
        if (bp1Com.getId() != null) {
            throw new BadRequestAlertException("A new bp1Com cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bp1Com result = bp1ComService.save(bp1Com);
        return ResponseEntity.created(new URI("/api/bp-1-coms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bp-1-coms} : Updates an existing bp1Com.
     *
     * @param bp1Com the bp1Com to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bp1Com,
     * or with status {@code 400 (Bad Request)} if the bp1Com is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bp1Com couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bp-1-coms")
    public ResponseEntity<Bp1Com> updateBp1Com(@RequestBody Bp1Com bp1Com) throws URISyntaxException {
        log.debug("REST request to update Bp1Com : {}", bp1Com);
        if (bp1Com.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bp1Com result = bp1ComService.save(bp1Com);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bp1Com.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bp-1-coms} : get all the bp1Coms.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bp1Coms in body.
     */
    @GetMapping("/bp-1-coms")
    public ResponseEntity<List<Bp1Com>> getAllBp1Coms(Pageable pageable) {
        log.debug("REST request to get a page of Bp1Coms");
        Page<Bp1Com> page = bp1ComService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bp-1-coms/:id} : get the "id" bp1Com.
     *
     * @param id the id of the bp1Com to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bp1Com, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bp-1-coms/{id}")
    public ResponseEntity<Bp1Com> getBp1Com(@PathVariable Long id) {
        log.debug("REST request to get Bp1Com : {}", id);
        Optional<Bp1Com> bp1Com = bp1ComService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bp1Com);
    }

    /**
     * {@code DELETE  /bp-1-coms/:id} : delete the "id" bp1Com.
     *
     * @param id the id of the bp1Com to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bp-1-coms/{id}")
    public ResponseEntity<Void> deleteBp1Com(@PathVariable Long id) {
        log.debug("REST request to delete Bp1Com : {}", id);
        bp1ComService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
