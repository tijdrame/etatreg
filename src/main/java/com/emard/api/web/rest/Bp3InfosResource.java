package com.emard.api.web.rest;

import com.emard.api.domain.Bp3Infos;
import com.emard.api.service.Bp3InfosService;
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
 * REST controller for managing {@link com.emard.api.domain.Bp3Infos}.
 */
@RestController
@RequestMapping("/api")
public class Bp3InfosResource {

    private final Logger log = LoggerFactory.getLogger(Bp3InfosResource.class);

    private static final String ENTITY_NAME = "bp3Infos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Bp3InfosService bp3InfosService;

    public Bp3InfosResource(Bp3InfosService bp3InfosService) {
        this.bp3InfosService = bp3InfosService;
    }

    /**
     * {@code POST  /bp-3-infos} : Create a new bp3Infos.
     *
     * @param bp3Infos the bp3Infos to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bp3Infos, or with status {@code 400 (Bad Request)} if the bp3Infos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bp-3-infos")
    public ResponseEntity<Bp3Infos> createBp3Infos(@RequestBody Bp3Infos bp3Infos) throws URISyntaxException {
        log.debug("REST request to save Bp3Infos : {}", bp3Infos);
        if (bp3Infos.getId() != null) {
            throw new BadRequestAlertException("A new bp3Infos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bp3Infos result = bp3InfosService.save(bp3Infos);
        return ResponseEntity.created(new URI("/api/bp-3-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bp-3-infos} : Updates an existing bp3Infos.
     *
     * @param bp3Infos the bp3Infos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bp3Infos,
     * or with status {@code 400 (Bad Request)} if the bp3Infos is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bp3Infos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bp-3-infos")
    public ResponseEntity<Bp3Infos> updateBp3Infos(@RequestBody Bp3Infos bp3Infos) throws URISyntaxException {
        log.debug("REST request to update Bp3Infos : {}", bp3Infos);
        if (bp3Infos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bp3Infos result = bp3InfosService.save(bp3Infos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bp3Infos.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bp-3-infos} : get all the bp3Infos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bp3Infos in body.
     */
    @GetMapping("/bp-3-infos")
    public ResponseEntity<List<Bp3Infos>> getAllBp3Infos(Pageable pageable) {
        log.debug("REST request to get a page of Bp3Infos");
        Page<Bp3Infos> page = bp3InfosService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bp-3-infos/:id} : get the "id" bp3Infos.
     *
     * @param id the id of the bp3Infos to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bp3Infos, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bp-3-infos/{id}")
    public ResponseEntity<Bp3Infos> getBp3Infos(@PathVariable Long id) {
        log.debug("REST request to get Bp3Infos : {}", id);
        Optional<Bp3Infos> bp3Infos = bp3InfosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bp3Infos);
    }

    /**
     * {@code DELETE  /bp-3-infos/:id} : delete the "id" bp3Infos.
     *
     * @param id the id of the bp3Infos to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bp-3-infos/{id}")
    public ResponseEntity<Void> deleteBp3Infos(@PathVariable Long id) {
        log.debug("REST request to delete Bp3Infos : {}", id);
        bp3InfosService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
