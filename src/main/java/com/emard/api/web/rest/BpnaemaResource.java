package com.emard.api.web.rest;

import com.emard.api.domain.Bpnaema;
import com.emard.api.service.BpnaemaService;
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
 * REST controller for managing {@link com.emard.api.domain.Bpnaema}.
 */
@RestController
@RequestMapping("/api")
public class BpnaemaResource {

    private final Logger log = LoggerFactory.getLogger(BpnaemaResource.class);

    private static final String ENTITY_NAME = "bpnaema";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BpnaemaService bpnaemaService;

    public BpnaemaResource(BpnaemaService bpnaemaService) {
        this.bpnaemaService = bpnaemaService;
    }

    /**
     * {@code POST  /bpnaemas} : Create a new bpnaema.
     *
     * @param bpnaema the bpnaema to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bpnaema, or with status {@code 400 (Bad Request)} if the bpnaema has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bpnaemas")
    public ResponseEntity<Bpnaema> createBpnaema(@RequestBody Bpnaema bpnaema) throws URISyntaxException {
        log.debug("REST request to save Bpnaema : {}", bpnaema);
        if (bpnaema.getId() != null) {
            throw new BadRequestAlertException("A new bpnaema cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bpnaema result = bpnaemaService.save(bpnaema);
        return ResponseEntity.created(new URI("/api/bpnaemas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bpnaemas} : Updates an existing bpnaema.
     *
     * @param bpnaema the bpnaema to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bpnaema,
     * or with status {@code 400 (Bad Request)} if the bpnaema is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bpnaema couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bpnaemas")
    public ResponseEntity<Bpnaema> updateBpnaema(@RequestBody Bpnaema bpnaema) throws URISyntaxException {
        log.debug("REST request to update Bpnaema : {}", bpnaema);
        if (bpnaema.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bpnaema result = bpnaemaService.save(bpnaema);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bpnaema.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bpnaemas} : get all the bpnaemas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bpnaemas in body.
     */
    @GetMapping("/bpnaemas")
    public ResponseEntity<List<Bpnaema>> getAllBpnaemas(Pageable pageable) {
        log.debug("REST request to get a page of Bpnaemas");
        Page<Bpnaema> page = bpnaemaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bpnaemas/:id} : get the "id" bpnaema.
     *
     * @param id the id of the bpnaema to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bpnaema, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bpnaemas/{id}")
    public ResponseEntity<Bpnaema> getBpnaema(@PathVariable Long id) {
        log.debug("REST request to get Bpnaema : {}", id);
        Optional<Bpnaema> bpnaema = bpnaemaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bpnaema);
    }

    /**
     * {@code DELETE  /bpnaemas/:id} : delete the "id" bpnaema.
     *
     * @param id the id of the bpnaema to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bpnaemas/{id}")
    public ResponseEntity<Void> deleteBpnaema(@PathVariable Long id) {
        log.debug("REST request to delete Bpnaema : {}", id);
        bpnaemaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
