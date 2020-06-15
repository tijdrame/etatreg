package com.emard.api.web.rest;

import com.emard.api.domain.BduDepot;
import com.emard.api.service.BduDepotService;
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
 * REST controller for managing {@link com.emard.api.domain.BduDepot}.
 */
@RestController
@RequestMapping("/api")
public class BduDepotResource {

    private final Logger log = LoggerFactory.getLogger(BduDepotResource.class);

    private static final String ENTITY_NAME = "bduDepot";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BduDepotService bduDepotService;

    public BduDepotResource(BduDepotService bduDepotService) {
        this.bduDepotService = bduDepotService;
    }

    /**
     * {@code POST  /bdu-depots} : Create a new bduDepot.
     *
     * @param bduDepot the bduDepot to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bduDepot, or with status {@code 400 (Bad Request)} if the bduDepot has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bdu-depots")
    public ResponseEntity<BduDepot> createBduDepot(@RequestBody BduDepot bduDepot) throws URISyntaxException {
        log.debug("REST request to save BduDepot : {}", bduDepot);
        if (bduDepot.getId() != null) {
            throw new BadRequestAlertException("A new bduDepot cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BduDepot result = bduDepotService.save(bduDepot);
        return ResponseEntity.created(new URI("/api/bdu-depots/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bdu-depots} : Updates an existing bduDepot.
     *
     * @param bduDepot the bduDepot to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bduDepot,
     * or with status {@code 400 (Bad Request)} if the bduDepot is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bduDepot couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bdu-depots")
    public ResponseEntity<BduDepot> updateBduDepot(@RequestBody BduDepot bduDepot) throws URISyntaxException {
        log.debug("REST request to update BduDepot : {}", bduDepot);
        if (bduDepot.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BduDepot result = bduDepotService.save(bduDepot);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bduDepot.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bdu-depots} : get all the bduDepots.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bduDepots in body.
     */
    @GetMapping("/bdu-depots")
    public ResponseEntity<List<BduDepot>> getAllBduDepots(Pageable pageable) {
        log.debug("REST request to get a page of BduDepots");
        Page<BduDepot> page = bduDepotService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bdu-depots/:id} : get the "id" bduDepot.
     *
     * @param id the id of the bduDepot to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bduDepot, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bdu-depots/{id}")
    public ResponseEntity<BduDepot> getBduDepot(@PathVariable Long id) {
        log.debug("REST request to get BduDepot : {}", id);
        Optional<BduDepot> bduDepot = bduDepotService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bduDepot);
    }

    /**
     * {@code DELETE  /bdu-depots/:id} : delete the "id" bduDepot.
     *
     * @param id the id of the bduDepot to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bdu-depots/{id}")
    public ResponseEntity<Void> deleteBduDepot(@PathVariable Long id) {
        log.debug("REST request to delete BduDepot : {}", id);
        bduDepotService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
