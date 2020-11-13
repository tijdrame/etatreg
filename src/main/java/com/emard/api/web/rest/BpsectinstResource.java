package com.emard.api.web.rest;

import com.emard.api.domain.Bpsectinst;
import com.emard.api.service.BpsectinstService;
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
 * REST controller for managing {@link com.emard.api.domain.Bpsectinst}.
 */
@RestController
@RequestMapping("/api")
public class BpsectinstResource {

    private final Logger log = LoggerFactory.getLogger(BpsectinstResource.class);

    private static final String ENTITY_NAME = "bpsectinst";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BpsectinstService bpsectinstService;

    public BpsectinstResource(BpsectinstService bpsectinstService) {
        this.bpsectinstService = bpsectinstService;
    }

    /**
     * {@code POST  /bpsectinsts} : Create a new bpsectinst.
     *
     * @param bpsectinst the bpsectinst to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bpsectinst, or with status {@code 400 (Bad Request)} if the bpsectinst has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bpsectinsts")
    public ResponseEntity<Bpsectinst> createBpsectinst(@RequestBody Bpsectinst bpsectinst) throws URISyntaxException {
        log.debug("REST request to save Bpsectinst : {}", bpsectinst);
        if (bpsectinst.getId() != null) {
            throw new BadRequestAlertException("A new bpsectinst cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bpsectinst result = bpsectinstService.save(bpsectinst);
        return ResponseEntity.created(new URI("/api/bpsectinsts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bpsectinsts} : Updates an existing bpsectinst.
     *
     * @param bpsectinst the bpsectinst to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bpsectinst,
     * or with status {@code 400 (Bad Request)} if the bpsectinst is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bpsectinst couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bpsectinsts")
    public ResponseEntity<Bpsectinst> updateBpsectinst(@RequestBody Bpsectinst bpsectinst) throws URISyntaxException {
        log.debug("REST request to update Bpsectinst : {}", bpsectinst);
        if (bpsectinst.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bpsectinst result = bpsectinstService.save(bpsectinst);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bpsectinst.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bpsectinsts} : get all the bpsectinsts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bpsectinsts in body.
     */
    @GetMapping("/bpsectinsts")
    public ResponseEntity<List<Bpsectinst>> getAllBpsectinsts(Pageable pageable) {
        log.debug("REST request to get a page of Bpsectinsts");
        Page<Bpsectinst> page = bpsectinstService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bpsectinsts/:id} : get the "id" bpsectinst.
     *
     * @param id the id of the bpsectinst to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bpsectinst, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bpsectinsts/{id}")
    public ResponseEntity<Bpsectinst> getBpsectinst(@PathVariable Long id) {
        log.debug("REST request to get Bpsectinst : {}", id);
        Optional<Bpsectinst> bpsectinst = bpsectinstService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bpsectinst);
    }

    /**
     * {@code DELETE  /bpsectinsts/:id} : delete the "id" bpsectinst.
     *
     * @param id the id of the bpsectinst to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bpsectinsts/{id}")
    public ResponseEntity<Void> deleteBpsectinst(@PathVariable Long id) {
        log.debug("REST request to delete Bpsectinst : {}", id);
        bpsectinstService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
