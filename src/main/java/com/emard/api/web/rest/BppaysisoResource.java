package com.emard.api.web.rest;

import com.emard.api.domain.Bppaysiso;
import com.emard.api.service.BppaysisoService;
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
 * REST controller for managing {@link com.emard.api.domain.Bppaysiso}.
 */
@RestController
@RequestMapping("/api")
public class BppaysisoResource {

    private final Logger log = LoggerFactory.getLogger(BppaysisoResource.class);

    private static final String ENTITY_NAME = "bppaysiso";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BppaysisoService bppaysisoService;

    public BppaysisoResource(BppaysisoService bppaysisoService) {
        this.bppaysisoService = bppaysisoService;
    }

    /**
     * {@code POST  /bppaysisos} : Create a new bppaysiso.
     *
     * @param bppaysiso the bppaysiso to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bppaysiso, or with status {@code 400 (Bad Request)} if the bppaysiso has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bppaysisos")
    public ResponseEntity<Bppaysiso> createBppaysiso(@RequestBody Bppaysiso bppaysiso) throws URISyntaxException {
        log.debug("REST request to save Bppaysiso : {}", bppaysiso);
        if (bppaysiso.getId() != null) {
            throw new BadRequestAlertException("A new bppaysiso cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bppaysiso result = bppaysisoService.save(bppaysiso);
        return ResponseEntity.created(new URI("/api/bppaysisos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bppaysisos} : Updates an existing bppaysiso.
     *
     * @param bppaysiso the bppaysiso to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bppaysiso,
     * or with status {@code 400 (Bad Request)} if the bppaysiso is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bppaysiso couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bppaysisos")
    public ResponseEntity<Bppaysiso> updateBppaysiso(@RequestBody Bppaysiso bppaysiso) throws URISyntaxException {
        log.debug("REST request to update Bppaysiso : {}", bppaysiso);
        if (bppaysiso.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bppaysiso result = bppaysisoService.save(bppaysiso);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bppaysiso.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bppaysisos} : get all the bppaysisos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bppaysisos in body.
     */
    @GetMapping("/bppaysisos")
    public ResponseEntity<List<Bppaysiso>> getAllBppaysisos(Pageable pageable) {
        log.debug("REST request to get a page of Bppaysisos");
        Page<Bppaysiso> page = bppaysisoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bppaysisos/:id} : get the "id" bppaysiso.
     *
     * @param id the id of the bppaysiso to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bppaysiso, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bppaysisos/{id}")
    public ResponseEntity<Bppaysiso> getBppaysiso(@PathVariable Long id) {
        log.debug("REST request to get Bppaysiso : {}", id);
        Optional<Bppaysiso> bppaysiso = bppaysisoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bppaysiso);
    }

    /**
     * {@code DELETE  /bppaysisos/:id} : delete the "id" bppaysiso.
     *
     * @param id the id of the bppaysiso to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bppaysisos/{id}")
    public ResponseEntity<Void> deleteBppaysiso(@PathVariable Long id) {
        log.debug("REST request to delete Bppaysiso : {}", id);
        bppaysisoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
