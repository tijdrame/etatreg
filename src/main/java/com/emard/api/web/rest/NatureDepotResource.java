package com.emard.api.web.rest;

import com.emard.api.domain.NatureDepot;
import com.emard.api.service.NatureDepotService;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.emard.api.domain.NatureDepot}.
 */
@RestController
@RequestMapping("/api")
public class NatureDepotResource {

    private final Logger log = LoggerFactory.getLogger(NatureDepotResource.class);

    private static final String ENTITY_NAME = "natureDepot";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NatureDepotService natureDepotService;

    public NatureDepotResource(NatureDepotService natureDepotService) {
        this.natureDepotService = natureDepotService;
    }

    /**
     * {@code POST  /nature-depots} : Create a new natureDepot.
     *
     * @param natureDepot the natureDepot to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new natureDepot, or with status {@code 400 (Bad Request)} if the natureDepot has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nature-depots")
    public ResponseEntity<NatureDepot> createNatureDepot(@Valid @RequestBody NatureDepot natureDepot) throws URISyntaxException {
        log.debug("REST request to save NatureDepot : {}", natureDepot);
        if (natureDepot.getId() != null) {
            throw new BadRequestAlertException("A new natureDepot cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NatureDepot result = natureDepotService.save(natureDepot);
        return ResponseEntity.created(new URI("/api/nature-depots/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nature-depots} : Updates an existing natureDepot.
     *
     * @param natureDepot the natureDepot to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated natureDepot,
     * or with status {@code 400 (Bad Request)} if the natureDepot is not valid,
     * or with status {@code 500 (Internal Server Error)} if the natureDepot couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nature-depots")
    public ResponseEntity<NatureDepot> updateNatureDepot(@Valid @RequestBody NatureDepot natureDepot) throws URISyntaxException {
        log.debug("REST request to update NatureDepot : {}", natureDepot);
        if (natureDepot.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NatureDepot result = natureDepotService.save(natureDepot);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, natureDepot.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nature-depots} : get all the natureDepots.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of natureDepots in body.
     */
    @GetMapping("/nature-depots")
    public ResponseEntity<List<NatureDepot>> getAllNatureDepots(Pageable pageable) {
        log.debug("REST request to get a page of NatureDepots");
        Page<NatureDepot> page = natureDepotService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nature-depots/:id} : get the "id" natureDepot.
     *
     * @param id the id of the natureDepot to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the natureDepot, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nature-depots/{id}")
    public ResponseEntity<NatureDepot> getNatureDepot(@PathVariable Long id) {
        log.debug("REST request to get NatureDepot : {}", id);
        Optional<NatureDepot> natureDepot = natureDepotService.findOne(id);
        return ResponseUtil.wrapOrNotFound(natureDepot);
    }

    /**
     * {@code DELETE  /nature-depots/:id} : delete the "id" natureDepot.
     *
     * @param id the id of the natureDepot to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nature-depots/{id}")
    public ResponseEntity<Void> deleteNatureDepot(@PathVariable Long id) {
        log.debug("REST request to delete NatureDepot : {}", id);
        natureDepotService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
