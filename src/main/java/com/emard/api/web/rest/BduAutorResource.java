package com.emard.api.web.rest;

import com.emard.api.domain.BduAutor;
import com.emard.api.service.BduAutorService;
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
 * REST controller for managing {@link com.emard.api.domain.BduAutor}.
 */
@RestController
@RequestMapping("/api")
public class BduAutorResource {

    private final Logger log = LoggerFactory.getLogger(BduAutorResource.class);

    private static final String ENTITY_NAME = "bduAutor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BduAutorService bduAutorService;

    public BduAutorResource(BduAutorService bduAutorService) {
        this.bduAutorService = bduAutorService;
    }

    /**
     * {@code POST  /bdu-autors} : Create a new bduAutor.
     *
     * @param bduAutor the bduAutor to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bduAutor, or with status {@code 400 (Bad Request)} if the bduAutor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bdu-autors")
    public ResponseEntity<BduAutor> createBduAutor(@RequestBody BduAutor bduAutor) throws URISyntaxException {
        log.debug("REST request to save BduAutor : {}", bduAutor);
        if (bduAutor.getId() != null) {
            throw new BadRequestAlertException("A new bduAutor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BduAutor result = bduAutorService.save(bduAutor);
        return ResponseEntity.created(new URI("/api/bdu-autors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bdu-autors} : Updates an existing bduAutor.
     *
     * @param bduAutor the bduAutor to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bduAutor,
     * or with status {@code 400 (Bad Request)} if the bduAutor is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bduAutor couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bdu-autors")
    public ResponseEntity<BduAutor> updateBduAutor(@RequestBody BduAutor bduAutor) throws URISyntaxException {
        log.debug("REST request to update BduAutor : {}", bduAutor);
        if (bduAutor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BduAutor result = bduAutorService.save(bduAutor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bduAutor.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bdu-autors} : get all the bduAutors.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bduAutors in body.
     */
    @GetMapping("/bdu-autors")
    public ResponseEntity<List<BduAutor>> getAllBduAutors(Pageable pageable) {
        log.debug("REST request to get a page of BduAutors");
        Page<BduAutor> page = bduAutorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bdu-autors/:id} : get the "id" bduAutor.
     *
     * @param id the id of the bduAutor to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bduAutor, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bdu-autors/{id}")
    public ResponseEntity<BduAutor> getBduAutor(@PathVariable Long id) {
        log.debug("REST request to get BduAutor : {}", id);
        Optional<BduAutor> bduAutor = bduAutorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bduAutor);
    }

    /**
     * {@code DELETE  /bdu-autors/:id} : delete the "id" bduAutor.
     *
     * @param id the id of the bduAutor to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bdu-autors/{id}")
    public ResponseEntity<Void> deleteBduAutor(@PathVariable Long id) {
        log.debug("REST request to delete BduAutor : {}", id);
        bduAutorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
