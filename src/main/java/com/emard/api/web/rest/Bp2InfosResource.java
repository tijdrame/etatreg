package com.emard.api.web.rest;

import com.emard.api.domain.Bp2Infos;
import com.emard.api.service.Bp2InfosService;
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
 * REST controller for managing {@link com.emard.api.domain.Bp2Infos}.
 */
@RestController
@RequestMapping("/api")
public class Bp2InfosResource {

    private final Logger log = LoggerFactory.getLogger(Bp2InfosResource.class);

    private static final String ENTITY_NAME = "bp2Infos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Bp2InfosService bp2InfosService;

    public Bp2InfosResource(Bp2InfosService bp2InfosService) {
        this.bp2InfosService = bp2InfosService;
    }

    /**
     * {@code POST  /bp-2-infos} : Create a new bp2Infos.
     *
     * @param bp2Infos the bp2Infos to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bp2Infos, or with status {@code 400 (Bad Request)} if the bp2Infos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bp-2-infos")
    public ResponseEntity<Bp2Infos> createBp2Infos(@RequestBody Bp2Infos bp2Infos) throws URISyntaxException {
        log.debug("REST request to save Bp2Infos : {}", bp2Infos);
        if (bp2Infos.getId() != null) {
            throw new BadRequestAlertException("A new bp2Infos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bp2Infos result = bp2InfosService.save(bp2Infos);
        return ResponseEntity.created(new URI("/api/bp-2-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bp-2-infos} : Updates an existing bp2Infos.
     *
     * @param bp2Infos the bp2Infos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bp2Infos,
     * or with status {@code 400 (Bad Request)} if the bp2Infos is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bp2Infos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bp-2-infos")
    public ResponseEntity<Bp2Infos> updateBp2Infos(@RequestBody Bp2Infos bp2Infos) throws URISyntaxException {
        log.debug("REST request to update Bp2Infos : {}", bp2Infos);
        if (bp2Infos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bp2Infos result = bp2InfosService.save(bp2Infos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bp2Infos.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bp-2-infos} : get all the bp2Infos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bp2Infos in body.
     */
    @GetMapping("/bp-2-infos")
    public ResponseEntity<List<Bp2Infos>> getAllBp2Infos(Pageable pageable) {
        log.debug("REST request to get a page of Bp2Infos");
        Page<Bp2Infos> page = bp2InfosService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bp-2-infos/:id} : get the "id" bp2Infos.
     *
     * @param id the id of the bp2Infos to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bp2Infos, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bp-2-infos/{id}")
    public ResponseEntity<Bp2Infos> getBp2Infos(@PathVariable Long id) {
        log.debug("REST request to get Bp2Infos : {}", id);
        Optional<Bp2Infos> bp2Infos = bp2InfosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bp2Infos);
    }

    /**
     * {@code DELETE  /bp-2-infos/:id} : delete the "id" bp2Infos.
     *
     * @param id the id of the bp2Infos to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bp-2-infos/{id}")
    public ResponseEntity<Void> deleteBp2Infos(@PathVariable Long id) {
        log.debug("REST request to delete Bp2Infos : {}", id);
        bp2InfosService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
