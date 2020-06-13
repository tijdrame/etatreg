package com.emard.api.web.rest;

import com.emard.api.domain.NatureDebiteur;
import com.emard.api.service.NatureDebiteurService;
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
 * REST controller for managing {@link com.emard.api.domain.NatureDebiteur}.
 */
@RestController
@RequestMapping("/api")
public class NatureDebiteurResource {

    private final Logger log = LoggerFactory.getLogger(NatureDebiteurResource.class);

    private static final String ENTITY_NAME = "natureDebiteur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NatureDebiteurService natureDebiteurService;

    public NatureDebiteurResource(NatureDebiteurService natureDebiteurService) {
        this.natureDebiteurService = natureDebiteurService;
    }

    /**
     * {@code POST  /nature-debiteurs} : Create a new natureDebiteur.
     *
     * @param natureDebiteur the natureDebiteur to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new natureDebiteur, or with status {@code 400 (Bad Request)} if the natureDebiteur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nature-debiteurs")
    public ResponseEntity<NatureDebiteur> createNatureDebiteur(@Valid @RequestBody NatureDebiteur natureDebiteur) throws URISyntaxException {
        log.debug("REST request to save NatureDebiteur : {}", natureDebiteur);
        if (natureDebiteur.getId() != null) {
            throw new BadRequestAlertException("A new natureDebiteur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NatureDebiteur result = natureDebiteurService.save(natureDebiteur);
        return ResponseEntity.created(new URI("/api/nature-debiteurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nature-debiteurs} : Updates an existing natureDebiteur.
     *
     * @param natureDebiteur the natureDebiteur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated natureDebiteur,
     * or with status {@code 400 (Bad Request)} if the natureDebiteur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the natureDebiteur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nature-debiteurs")
    public ResponseEntity<NatureDebiteur> updateNatureDebiteur(@Valid @RequestBody NatureDebiteur natureDebiteur) throws URISyntaxException {
        log.debug("REST request to update NatureDebiteur : {}", natureDebiteur);
        if (natureDebiteur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NatureDebiteur result = natureDebiteurService.save(natureDebiteur);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, natureDebiteur.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nature-debiteurs} : get all the natureDebiteurs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of natureDebiteurs in body.
     */
    @GetMapping("/nature-debiteurs")
    public ResponseEntity<List<NatureDebiteur>> getAllNatureDebiteurs(Pageable pageable) {
        log.debug("REST request to get a page of NatureDebiteurs");
        Page<NatureDebiteur> page = natureDebiteurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nature-debiteurs/:id} : get the "id" natureDebiteur.
     *
     * @param id the id of the natureDebiteur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the natureDebiteur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nature-debiteurs/{id}")
    public ResponseEntity<NatureDebiteur> getNatureDebiteur(@PathVariable Long id) {
        log.debug("REST request to get NatureDebiteur : {}", id);
        Optional<NatureDebiteur> natureDebiteur = natureDebiteurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(natureDebiteur);
    }

    /**
     * {@code DELETE  /nature-debiteurs/:id} : delete the "id" natureDebiteur.
     *
     * @param id the id of the natureDebiteur to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nature-debiteurs/{id}")
    public ResponseEntity<Void> deleteNatureDebiteur(@PathVariable Long id) {
        log.debug("REST request to delete NatureDebiteur : {}", id);
        natureDebiteurService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
