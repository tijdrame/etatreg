package com.emard.api.web.rest;

import com.emard.api.domain.Bp2Com;
import com.emard.api.service.Bp2ComService;
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
 * REST controller for managing {@link com.emard.api.domain.Bp2Com}.
 */
@RestController
@RequestMapping("/api")
public class Bp2ComResource {

    private final Logger log = LoggerFactory.getLogger(Bp2ComResource.class);

    private static final String ENTITY_NAME = "bp2Com";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Bp2ComService bp2ComService;

    public Bp2ComResource(Bp2ComService bp2ComService) {
        this.bp2ComService = bp2ComService;
    }

    /**
     * {@code POST  /bp-2-coms} : Create a new bp2Com.
     *
     * @param bp2Com the bp2Com to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bp2Com, or with status {@code 400 (Bad Request)} if the bp2Com has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bp-2-coms")
    public ResponseEntity<Bp2Com> createBp2Com(@RequestBody Bp2Com bp2Com) throws URISyntaxException {
        log.debug("REST request to save Bp2Com : {}", bp2Com);
        if (bp2Com.getId() != null) {
            throw new BadRequestAlertException("A new bp2Com cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bp2Com result = bp2ComService.save(bp2Com);
        return ResponseEntity.created(new URI("/api/bp-2-coms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bp-2-coms} : Updates an existing bp2Com.
     *
     * @param bp2Com the bp2Com to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bp2Com,
     * or with status {@code 400 (Bad Request)} if the bp2Com is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bp2Com couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bp-2-coms")
    public ResponseEntity<Bp2Com> updateBp2Com(@RequestBody Bp2Com bp2Com) throws URISyntaxException {
        log.debug("REST request to update Bp2Com : {}", bp2Com);
        if (bp2Com.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bp2Com result = bp2ComService.save(bp2Com);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bp2Com.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bp-2-coms} : get all the bp2Coms.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bp2Coms in body.
     */
    @GetMapping("/bp-2-coms")
    public ResponseEntity<List<Bp2Com>> getAllBp2Coms(Pageable pageable) {
        log.debug("REST request to get a page of Bp2Coms");
        Page<Bp2Com> page = bp2ComService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bp-2-coms/:id} : get the "id" bp2Com.
     *
     * @param id the id of the bp2Com to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bp2Com, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bp-2-coms/{id}")
    public ResponseEntity<Bp2Com> getBp2Com(@PathVariable Long id) {
        log.debug("REST request to get Bp2Com : {}", id);
        Optional<Bp2Com> bp2Com = bp2ComService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bp2Com);
    }

    /**
     * {@code DELETE  /bp-2-coms/:id} : delete the "id" bp2Com.
     *
     * @param id the id of the bp2Com to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bp-2-coms/{id}")
    public ResponseEntity<Void> deleteBp2Com(@PathVariable Long id) {
        log.debug("REST request to delete Bp2Com : {}", id);
        bp2ComService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
