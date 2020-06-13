package com.emard.api.web.rest;

import com.emard.api.domain.SecteurActivite;
import com.emard.api.service.SecteurActiviteService;
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
 * REST controller for managing {@link com.emard.api.domain.SecteurActivite}.
 */
@RestController
@RequestMapping("/api")
public class SecteurActiviteResource {

    private final Logger log = LoggerFactory.getLogger(SecteurActiviteResource.class);

    private static final String ENTITY_NAME = "secteurActivite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SecteurActiviteService secteurActiviteService;

    public SecteurActiviteResource(SecteurActiviteService secteurActiviteService) {
        this.secteurActiviteService = secteurActiviteService;
    }

    /**
     * {@code POST  /secteur-activites} : Create a new secteurActivite.
     *
     * @param secteurActivite the secteurActivite to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new secteurActivite, or with status {@code 400 (Bad Request)} if the secteurActivite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/secteur-activites")
    public ResponseEntity<SecteurActivite> createSecteurActivite(@Valid @RequestBody SecteurActivite secteurActivite) throws URISyntaxException {
        log.debug("REST request to save SecteurActivite : {}", secteurActivite);
        if (secteurActivite.getId() != null) {
            throw new BadRequestAlertException("A new secteurActivite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SecteurActivite result = secteurActiviteService.save(secteurActivite);
        return ResponseEntity.created(new URI("/api/secteur-activites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /secteur-activites} : Updates an existing secteurActivite.
     *
     * @param secteurActivite the secteurActivite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated secteurActivite,
     * or with status {@code 400 (Bad Request)} if the secteurActivite is not valid,
     * or with status {@code 500 (Internal Server Error)} if the secteurActivite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/secteur-activites")
    public ResponseEntity<SecteurActivite> updateSecteurActivite(@Valid @RequestBody SecteurActivite secteurActivite) throws URISyntaxException {
        log.debug("REST request to update SecteurActivite : {}", secteurActivite);
        if (secteurActivite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SecteurActivite result = secteurActiviteService.save(secteurActivite);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, secteurActivite.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /secteur-activites} : get all the secteurActivites.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of secteurActivites in body.
     */
    @GetMapping("/secteur-activites")
    public ResponseEntity<List<SecteurActivite>> getAllSecteurActivites(Pageable pageable) {
        log.debug("REST request to get a page of SecteurActivites");
        Page<SecteurActivite> page = secteurActiviteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /secteur-activites/:id} : get the "id" secteurActivite.
     *
     * @param id the id of the secteurActivite to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the secteurActivite, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/secteur-activites/{id}")
    public ResponseEntity<SecteurActivite> getSecteurActivite(@PathVariable Long id) {
        log.debug("REST request to get SecteurActivite : {}", id);
        Optional<SecteurActivite> secteurActivite = secteurActiviteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(secteurActivite);
    }

    /**
     * {@code DELETE  /secteur-activites/:id} : delete the "id" secteurActivite.
     *
     * @param id the id of the secteurActivite to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/secteur-activites/{id}")
    public ResponseEntity<Void> deleteSecteurActivite(@PathVariable Long id) {
        log.debug("REST request to delete SecteurActivite : {}", id);
        secteurActiviteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
