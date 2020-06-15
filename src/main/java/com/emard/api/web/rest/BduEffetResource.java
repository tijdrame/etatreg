package com.emard.api.web.rest;

import com.emard.api.domain.BduEffet;
import com.emard.api.service.BduEffetService;
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
 * REST controller for managing {@link com.emard.api.domain.BduEffet}.
 */
@RestController
@RequestMapping("/api")
public class BduEffetResource {

    private final Logger log = LoggerFactory.getLogger(BduEffetResource.class);

    private static final String ENTITY_NAME = "bduEffet";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BduEffetService bduEffetService;

    public BduEffetResource(BduEffetService bduEffetService) {
        this.bduEffetService = bduEffetService;
    }

    /**
     * {@code POST  /bdu-effets} : Create a new bduEffet.
     *
     * @param bduEffet the bduEffet to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bduEffet, or with status {@code 400 (Bad Request)} if the bduEffet has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bdu-effets")
    public ResponseEntity<BduEffet> createBduEffet(@RequestBody BduEffet bduEffet) throws URISyntaxException {
        log.debug("REST request to save BduEffet : {}", bduEffet);
        if (bduEffet.getId() != null) {
            throw new BadRequestAlertException("A new bduEffet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BduEffet result = bduEffetService.save(bduEffet);
        return ResponseEntity.created(new URI("/api/bdu-effets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bdu-effets} : Updates an existing bduEffet.
     *
     * @param bduEffet the bduEffet to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bduEffet,
     * or with status {@code 400 (Bad Request)} if the bduEffet is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bduEffet couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bdu-effets")
    public ResponseEntity<BduEffet> updateBduEffet(@RequestBody BduEffet bduEffet) throws URISyntaxException {
        log.debug("REST request to update BduEffet : {}", bduEffet);
        if (bduEffet.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BduEffet result = bduEffetService.save(bduEffet);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bduEffet.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bdu-effets} : get all the bduEffets.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bduEffets in body.
     */
    @GetMapping("/bdu-effets")
    public ResponseEntity<List<BduEffet>> getAllBduEffets(Pageable pageable) {
        log.debug("REST request to get a page of BduEffets");
        Page<BduEffet> page = bduEffetService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bdu-effets/:id} : get the "id" bduEffet.
     *
     * @param id the id of the bduEffet to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bduEffet, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bdu-effets/{id}")
    public ResponseEntity<BduEffet> getBduEffet(@PathVariable Long id) {
        log.debug("REST request to get BduEffet : {}", id);
        Optional<BduEffet> bduEffet = bduEffetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bduEffet);
    }

    /**
     * {@code DELETE  /bdu-effets/:id} : delete the "id" bduEffet.
     *
     * @param id the id of the bduEffet to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bdu-effets/{id}")
    public ResponseEntity<Void> deleteBduEffet(@PathVariable Long id) {
        log.debug("REST request to delete BduEffet : {}", id);
        bduEffetService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
