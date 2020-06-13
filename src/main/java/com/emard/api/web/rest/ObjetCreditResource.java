package com.emard.api.web.rest;

import com.emard.api.domain.ObjetCredit;
import com.emard.api.service.ObjetCreditService;
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
 * REST controller for managing {@link com.emard.api.domain.ObjetCredit}.
 */
@RestController
@RequestMapping("/api")
public class ObjetCreditResource {

    private final Logger log = LoggerFactory.getLogger(ObjetCreditResource.class);

    private static final String ENTITY_NAME = "objetCredit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ObjetCreditService objetCreditService;

    public ObjetCreditResource(ObjetCreditService objetCreditService) {
        this.objetCreditService = objetCreditService;
    }

    /**
     * {@code POST  /objet-credits} : Create a new objetCredit.
     *
     * @param objetCredit the objetCredit to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new objetCredit, or with status {@code 400 (Bad Request)} if the objetCredit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/objet-credits")
    public ResponseEntity<ObjetCredit> createObjetCredit(@Valid @RequestBody ObjetCredit objetCredit) throws URISyntaxException {
        log.debug("REST request to save ObjetCredit : {}", objetCredit);
        if (objetCredit.getId() != null) {
            throw new BadRequestAlertException("A new objetCredit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ObjetCredit result = objetCreditService.save(objetCredit);
        return ResponseEntity.created(new URI("/api/objet-credits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /objet-credits} : Updates an existing objetCredit.
     *
     * @param objetCredit the objetCredit to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated objetCredit,
     * or with status {@code 400 (Bad Request)} if the objetCredit is not valid,
     * or with status {@code 500 (Internal Server Error)} if the objetCredit couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/objet-credits")
    public ResponseEntity<ObjetCredit> updateObjetCredit(@Valid @RequestBody ObjetCredit objetCredit) throws URISyntaxException {
        log.debug("REST request to update ObjetCredit : {}", objetCredit);
        if (objetCredit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ObjetCredit result = objetCreditService.save(objetCredit);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, objetCredit.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /objet-credits} : get all the objetCredits.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of objetCredits in body.
     */
    @GetMapping("/objet-credits")
    public ResponseEntity<List<ObjetCredit>> getAllObjetCredits(Pageable pageable) {
        log.debug("REST request to get a page of ObjetCredits");
        Page<ObjetCredit> page = objetCreditService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /objet-credits/:id} : get the "id" objetCredit.
     *
     * @param id the id of the objetCredit to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the objetCredit, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/objet-credits/{id}")
    public ResponseEntity<ObjetCredit> getObjetCredit(@PathVariable Long id) {
        log.debug("REST request to get ObjetCredit : {}", id);
        Optional<ObjetCredit> objetCredit = objetCreditService.findOne(id);
        return ResponseUtil.wrapOrNotFound(objetCredit);
    }

    /**
     * {@code DELETE  /objet-credits/:id} : delete the "id" objetCredit.
     *
     * @param id the id of the objetCredit to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/objet-credits/{id}")
    public ResponseEntity<Void> deleteObjetCredit(@PathVariable Long id) {
        log.debug("REST request to delete ObjetCredit : {}", id);
        objetCreditService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
