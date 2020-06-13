package com.emard.api.web.rest;

import com.emard.api.domain.AcheteurVendeur;
import com.emard.api.service.AcheteurVendeurService;
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
 * REST controller for managing {@link com.emard.api.domain.AcheteurVendeur}.
 */
@RestController
@RequestMapping("/api")
public class AcheteurVendeurResource {

    private final Logger log = LoggerFactory.getLogger(AcheteurVendeurResource.class);

    private static final String ENTITY_NAME = "acheteurVendeur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AcheteurVendeurService acheteurVendeurService;

    public AcheteurVendeurResource(AcheteurVendeurService acheteurVendeurService) {
        this.acheteurVendeurService = acheteurVendeurService;
    }

    /**
     * {@code POST  /acheteur-vendeurs} : Create a new acheteurVendeur.
     *
     * @param acheteurVendeur the acheteurVendeur to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new acheteurVendeur, or with status {@code 400 (Bad Request)} if the acheteurVendeur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/acheteur-vendeurs")
    public ResponseEntity<AcheteurVendeur> createAcheteurVendeur(@Valid @RequestBody AcheteurVendeur acheteurVendeur) throws URISyntaxException {
        log.debug("REST request to save AcheteurVendeur : {}", acheteurVendeur);
        if (acheteurVendeur.getId() != null) {
            throw new BadRequestAlertException("A new acheteurVendeur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AcheteurVendeur result = acheteurVendeurService.save(acheteurVendeur);
        return ResponseEntity.created(new URI("/api/acheteur-vendeurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /acheteur-vendeurs} : Updates an existing acheteurVendeur.
     *
     * @param acheteurVendeur the acheteurVendeur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated acheteurVendeur,
     * or with status {@code 400 (Bad Request)} if the acheteurVendeur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the acheteurVendeur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/acheteur-vendeurs")
    public ResponseEntity<AcheteurVendeur> updateAcheteurVendeur(@Valid @RequestBody AcheteurVendeur acheteurVendeur) throws URISyntaxException {
        log.debug("REST request to update AcheteurVendeur : {}", acheteurVendeur);
        if (acheteurVendeur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AcheteurVendeur result = acheteurVendeurService.save(acheteurVendeur);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, acheteurVendeur.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /acheteur-vendeurs} : get all the acheteurVendeurs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of acheteurVendeurs in body.
     */
    @GetMapping("/acheteur-vendeurs")
    public ResponseEntity<List<AcheteurVendeur>> getAllAcheteurVendeurs(Pageable pageable) {
        log.debug("REST request to get a page of AcheteurVendeurs");
        Page<AcheteurVendeur> page = acheteurVendeurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /acheteur-vendeurs/:id} : get the "id" acheteurVendeur.
     *
     * @param id the id of the acheteurVendeur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the acheteurVendeur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/acheteur-vendeurs/{id}")
    public ResponseEntity<AcheteurVendeur> getAcheteurVendeur(@PathVariable Long id) {
        log.debug("REST request to get AcheteurVendeur : {}", id);
        Optional<AcheteurVendeur> acheteurVendeur = acheteurVendeurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(acheteurVendeur);
    }

    /**
     * {@code DELETE  /acheteur-vendeurs/:id} : delete the "id" acheteurVendeur.
     *
     * @param id the id of the acheteurVendeur to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/acheteur-vendeurs/{id}")
    public ResponseEntity<Void> deleteAcheteurVendeur(@PathVariable Long id) {
        log.debug("REST request to delete AcheteurVendeur : {}", id);
        acheteurVendeurService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
