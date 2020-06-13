package com.emard.api.web.rest;

import com.emard.api.domain.BankInfos;
import com.emard.api.service.BankInfosService;
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
 * REST controller for managing {@link com.emard.api.domain.BankInfos}.
 */
@RestController
@RequestMapping("/api")
public class BankInfosResource {

    private final Logger log = LoggerFactory.getLogger(BankInfosResource.class);

    private static final String ENTITY_NAME = "bankInfos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BankInfosService bankInfosService;

    public BankInfosResource(BankInfosService bankInfosService) {
        this.bankInfosService = bankInfosService;
    }

    /**
     * {@code POST  /bank-infos} : Create a new bankInfos.
     *
     * @param bankInfos the bankInfos to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bankInfos, or with status {@code 400 (Bad Request)} if the bankInfos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bank-infos")
    public ResponseEntity<BankInfos> createBankInfos(@Valid @RequestBody BankInfos bankInfos) throws URISyntaxException {
        log.debug("REST request to save BankInfos : {}", bankInfos);
        if (bankInfos.getId() != null) {
            throw new BadRequestAlertException("A new bankInfos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BankInfos result = bankInfosService.save(bankInfos);
        return ResponseEntity.created(new URI("/api/bank-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bank-infos} : Updates an existing bankInfos.
     *
     * @param bankInfos the bankInfos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bankInfos,
     * or with status {@code 400 (Bad Request)} if the bankInfos is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bankInfos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bank-infos")
    public ResponseEntity<BankInfos> updateBankInfos(@Valid @RequestBody BankInfos bankInfos) throws URISyntaxException {
        log.debug("REST request to update BankInfos : {}", bankInfos);
        if (bankInfos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BankInfos result = bankInfosService.save(bankInfos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bankInfos.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bank-infos} : get all the bankInfos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bankInfos in body.
     */
    @GetMapping("/bank-infos")
    public ResponseEntity<List<BankInfos>> getAllBankInfos(Pageable pageable) {
        log.debug("REST request to get a page of BankInfos");
        Page<BankInfos> page = bankInfosService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bank-infos/:id} : get the "id" bankInfos.
     *
     * @param id the id of the bankInfos to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bankInfos, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bank-infos/{id}")
    public ResponseEntity<BankInfos> getBankInfos(@PathVariable Long id) {
        log.debug("REST request to get BankInfos : {}", id);
        Optional<BankInfos> bankInfos = bankInfosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bankInfos);
    }

    /**
     * {@code DELETE  /bank-infos/:id} : delete the "id" bankInfos.
     *
     * @param id the id of the bankInfos to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bank-infos/{id}")
    public ResponseEntity<Void> deleteBankInfos(@PathVariable Long id) {
        log.debug("REST request to delete BankInfos : {}", id);
        bankInfosService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
