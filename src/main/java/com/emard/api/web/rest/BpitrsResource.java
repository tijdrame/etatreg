package com.emard.api.web.rest;

import com.emard.api.domain.Bpitrs;
import com.emard.api.service.BpitrsService;
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
 * REST controller for managing {@link com.emard.api.domain.Bpitrs}.
 */
@RestController
@RequestMapping("/api")
public class BpitrsResource {

    private final Logger log = LoggerFactory.getLogger(BpitrsResource.class);

    private static final String ENTITY_NAME = "bpitrs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BpitrsService bpitrsService;

    public BpitrsResource(BpitrsService bpitrsService) {
        this.bpitrsService = bpitrsService;
    }

    /**
     * {@code POST  /bpitrs} : Create a new bpitrs.
     *
     * @param bpitrs the bpitrs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bpitrs, or with status {@code 400 (Bad Request)} if the bpitrs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bpitrs")
    public ResponseEntity<Bpitrs> createBpitrs(@RequestBody Bpitrs bpitrs) throws URISyntaxException {
        log.debug("REST request to save Bpitrs : {}", bpitrs);
        if (bpitrs.getId() != null) {
            throw new BadRequestAlertException("A new bpitrs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bpitrs result = bpitrsService.save(bpitrs);
        return ResponseEntity.created(new URI("/api/bpitrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bpitrs} : Updates an existing bpitrs.
     *
     * @param bpitrs the bpitrs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bpitrs,
     * or with status {@code 400 (Bad Request)} if the bpitrs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bpitrs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bpitrs")
    public ResponseEntity<Bpitrs> updateBpitrs(@RequestBody Bpitrs bpitrs) throws URISyntaxException {
        log.debug("REST request to update Bpitrs : {}", bpitrs);
        if (bpitrs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bpitrs result = bpitrsService.save(bpitrs);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bpitrs.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bpitrs} : get all the bpitrs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bpitrs in body.
     */
    @GetMapping("/bpitrs")
    public ResponseEntity<List<Bpitrs>> getAllBpitrs(Pageable pageable) {
        log.debug("REST request to get a page of Bpitrs");
        Page<Bpitrs> page = bpitrsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bpitrs/:id} : get the "id" bpitrs.
     *
     * @param id the id of the bpitrs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bpitrs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bpitrs/{id}")
    public ResponseEntity<Bpitrs> getBpitrs(@PathVariable Long id) {
        log.debug("REST request to get Bpitrs : {}", id);
        Optional<Bpitrs> bpitrs = bpitrsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bpitrs);
    }

    /**
     * {@code DELETE  /bpitrs/:id} : delete the "id" bpitrs.
     *
     * @param id the id of the bpitrs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bpitrs/{id}")
    public ResponseEntity<Void> deleteBpitrs(@PathVariable Long id) {
        log.debug("REST request to delete Bpitrs : {}", id);
        bpitrsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
