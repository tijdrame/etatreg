package com.emard.api.web.rest;

import com.emard.api.domain.Bpbqe;
import com.emard.api.service.BpbqeService;
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
 * REST controller for managing {@link com.emard.api.domain.Bpbqe}.
 */
@RestController
@RequestMapping("/api")
public class BpbqeResource {

    private final Logger log = LoggerFactory.getLogger(BpbqeResource.class);

    private static final String ENTITY_NAME = "bpbqe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BpbqeService bpbqeService;

    public BpbqeResource(BpbqeService bpbqeService) {
        this.bpbqeService = bpbqeService;
    }

    /**
     * {@code POST  /bpbqes} : Create a new bpbqe.
     *
     * @param bpbqe the bpbqe to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bpbqe, or with status {@code 400 (Bad Request)} if the bpbqe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bpbqes")
    public ResponseEntity<Bpbqe> createBpbqe(@RequestBody Bpbqe bpbqe) throws URISyntaxException {
        log.debug("REST request to save Bpbqe : {}", bpbqe);
        if (bpbqe.getId() != null) {
            throw new BadRequestAlertException("A new bpbqe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bpbqe result = bpbqeService.save(bpbqe);
        return ResponseEntity.created(new URI("/api/bpbqes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bpbqes} : Updates an existing bpbqe.
     *
     * @param bpbqe the bpbqe to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bpbqe,
     * or with status {@code 400 (Bad Request)} if the bpbqe is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bpbqe couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bpbqes")
    public ResponseEntity<Bpbqe> updateBpbqe(@RequestBody Bpbqe bpbqe) throws URISyntaxException {
        log.debug("REST request to update Bpbqe : {}", bpbqe);
        if (bpbqe.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bpbqe result = bpbqeService.save(bpbqe);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bpbqe.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bpbqes} : get all the bpbqes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bpbqes in body.
     */
    @GetMapping("/bpbqes")
    public ResponseEntity<List<Bpbqe>> getAllBpbqes(Pageable pageable) {
        log.debug("REST request to get a page of Bpbqes");
        Page<Bpbqe> page = bpbqeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bpbqes/:id} : get the "id" bpbqe.
     *
     * @param id the id of the bpbqe to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bpbqe, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bpbqes/{id}")
    public ResponseEntity<Bpbqe> getBpbqe(@PathVariable Long id) {
        log.debug("REST request to get Bpbqe : {}", id);
        Optional<Bpbqe> bpbqe = bpbqeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bpbqe);
    }

    /**
     * {@code DELETE  /bpbqes/:id} : delete the "id" bpbqe.
     *
     * @param id the id of the bpbqe to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bpbqes/{id}")
    public ResponseEntity<Void> deleteBpbqe(@PathVariable Long id) {
        log.debug("REST request to delete Bpbqe : {}", id);
        bpbqeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
