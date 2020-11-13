package com.emard.api.web.rest;

import com.emard.api.domain.Bpdevise;
import com.emard.api.service.BpdeviseService;
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
 * REST controller for managing {@link com.emard.api.domain.Bpdevise}.
 */
@RestController
@RequestMapping("/api")
public class BpdeviseResource {

    private final Logger log = LoggerFactory.getLogger(BpdeviseResource.class);

    private static final String ENTITY_NAME = "bpdevise";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BpdeviseService bpdeviseService;

    public BpdeviseResource(BpdeviseService bpdeviseService) {
        this.bpdeviseService = bpdeviseService;
    }

    /**
     * {@code POST  /bpdevises} : Create a new bpdevise.
     *
     * @param bpdevise the bpdevise to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bpdevise, or with status {@code 400 (Bad Request)} if the bpdevise has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bpdevises")
    public ResponseEntity<Bpdevise> createBpdevise(@RequestBody Bpdevise bpdevise) throws URISyntaxException {
        log.debug("REST request to save Bpdevise : {}", bpdevise);
        if (bpdevise.getId() != null) {
            throw new BadRequestAlertException("A new bpdevise cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bpdevise result = bpdeviseService.save(bpdevise);
        return ResponseEntity.created(new URI("/api/bpdevises/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bpdevises} : Updates an existing bpdevise.
     *
     * @param bpdevise the bpdevise to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bpdevise,
     * or with status {@code 400 (Bad Request)} if the bpdevise is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bpdevise couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bpdevises")
    public ResponseEntity<Bpdevise> updateBpdevise(@RequestBody Bpdevise bpdevise) throws URISyntaxException {
        log.debug("REST request to update Bpdevise : {}", bpdevise);
        if (bpdevise.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bpdevise result = bpdeviseService.save(bpdevise);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bpdevise.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bpdevises} : get all the bpdevises.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bpdevises in body.
     */
    @GetMapping("/bpdevises")
    public ResponseEntity<List<Bpdevise>> getAllBpdevises(Pageable pageable) {
        log.debug("REST request to get a page of Bpdevises");
        Page<Bpdevise> page = bpdeviseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bpdevises/:id} : get the "id" bpdevise.
     *
     * @param id the id of the bpdevise to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bpdevise, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bpdevises/{id}")
    public ResponseEntity<Bpdevise> getBpdevise(@PathVariable Long id) {
        log.debug("REST request to get Bpdevise : {}", id);
        Optional<Bpdevise> bpdevise = bpdeviseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bpdevise);
    }

    /**
     * {@code DELETE  /bpdevises/:id} : delete the "id" bpdevise.
     *
     * @param id the id of the bpdevise to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bpdevises/{id}")
    public ResponseEntity<Void> deleteBpdevise(@PathVariable Long id) {
        log.debug("REST request to delete Bpdevise : {}", id);
        bpdeviseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
