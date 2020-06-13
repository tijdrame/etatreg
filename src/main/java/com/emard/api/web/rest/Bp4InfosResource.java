package com.emard.api.web.rest;

import com.emard.api.domain.Bp4Infos;
import com.emard.api.service.Bp4InfosService;
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
 * REST controller for managing {@link com.emard.api.domain.Bp4Infos}.
 */
@RestController
@RequestMapping("/api")
public class Bp4InfosResource {

    private final Logger log = LoggerFactory.getLogger(Bp4InfosResource.class);

    private static final String ENTITY_NAME = "bp4Infos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Bp4InfosService bp4InfosService;

    public Bp4InfosResource(Bp4InfosService bp4InfosService) {
        this.bp4InfosService = bp4InfosService;
    }

    /**
     * {@code POST  /bp-4-infos} : Create a new bp4Infos.
     *
     * @param bp4Infos the bp4Infos to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bp4Infos, or with status {@code 400 (Bad Request)} if the bp4Infos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bp-4-infos")
    public ResponseEntity<Bp4Infos> createBp4Infos(@RequestBody Bp4Infos bp4Infos) throws URISyntaxException {
        log.debug("REST request to save Bp4Infos : {}", bp4Infos);
        if (bp4Infos.getId() != null) {
            throw new BadRequestAlertException("A new bp4Infos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bp4Infos result = bp4InfosService.save(bp4Infos);
        return ResponseEntity.created(new URI("/api/bp-4-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bp-4-infos} : Updates an existing bp4Infos.
     *
     * @param bp4Infos the bp4Infos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bp4Infos,
     * or with status {@code 400 (Bad Request)} if the bp4Infos is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bp4Infos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bp-4-infos")
    public ResponseEntity<Bp4Infos> updateBp4Infos(@RequestBody Bp4Infos bp4Infos) throws URISyntaxException {
        log.debug("REST request to update Bp4Infos : {}", bp4Infos);
        if (bp4Infos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bp4Infos result = bp4InfosService.save(bp4Infos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bp4Infos.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bp-4-infos} : get all the bp4Infos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bp4Infos in body.
     */
    @GetMapping("/bp-4-infos")
    public ResponseEntity<List<Bp4Infos>> getAllBp4Infos(Pageable pageable) {
        log.debug("REST request to get a page of Bp4Infos");
        Page<Bp4Infos> page = bp4InfosService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bp-4-infos/:id} : get the "id" bp4Infos.
     *
     * @param id the id of the bp4Infos to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bp4Infos, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bp-4-infos/{id}")
    public ResponseEntity<Bp4Infos> getBp4Infos(@PathVariable Long id) {
        log.debug("REST request to get Bp4Infos : {}", id);
        Optional<Bp4Infos> bp4Infos = bp4InfosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bp4Infos);
    }

    /**
     * {@code DELETE  /bp-4-infos/:id} : delete the "id" bp4Infos.
     *
     * @param id the id of the bp4Infos to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bp-4-infos/{id}")
    public ResponseEntity<Void> deleteBp4Infos(@PathVariable Long id) {
        log.debug("REST request to delete Bp4Infos : {}", id);
        bp4InfosService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
