package com.emard.api.web.rest;

import com.emard.api.domain.ChargeFile;
import com.emard.api.service.ChargeFileService;
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
 * REST controller for managing {@link com.emard.api.domain.ChargeFile}.
 */
@RestController
@RequestMapping("/api")
public class ChargeFileResource {

    private final Logger log = LoggerFactory.getLogger(ChargeFileResource.class);

    private static final String ENTITY_NAME = "chargeFile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChargeFileService chargeFileService;

    public ChargeFileResource(ChargeFileService chargeFileService) {
        this.chargeFileService = chargeFileService;
    }

    /**
     * {@code POST  /charge-files} : Create a new chargeFile.
     *
     * @param chargeFile the chargeFile to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chargeFile, or with status {@code 400 (Bad Request)} if the chargeFile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/charge-files")
    public ResponseEntity<ChargeFile> createChargeFile(@RequestBody ChargeFile chargeFile) throws URISyntaxException {
        log.debug("REST request to save ChargeFile : {}", chargeFile);
        if (chargeFile.getId() != null) {
            throw new BadRequestAlertException("A new chargeFile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChargeFile result = chargeFileService.save(chargeFile);
        return ResponseEntity.created(new URI("/api/charge-files/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /charge-files} : Updates an existing chargeFile.
     *
     * @param chargeFile the chargeFile to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chargeFile,
     * or with status {@code 400 (Bad Request)} if the chargeFile is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chargeFile couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/charge-files")
    public ResponseEntity<ChargeFile> updateChargeFile(@RequestBody ChargeFile chargeFile) throws URISyntaxException {
        log.debug("REST request to update ChargeFile : {}", chargeFile);
        if (chargeFile.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChargeFile result = chargeFileService.save(chargeFile);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, chargeFile.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /charge-files} : get all the chargeFiles.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chargeFiles in body.
     */
    @GetMapping("/charge-files")
    public ResponseEntity<List<ChargeFile>> getAllChargeFiles(Pageable pageable) {
        log.debug("REST request to get a page of ChargeFiles");
        Page<ChargeFile> page = chargeFileService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /charge-files/:id} : get the "id" chargeFile.
     *
     * @param id the id of the chargeFile to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chargeFile, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/charge-files/{id}")
    public ResponseEntity<ChargeFile> getChargeFile(@PathVariable Long id) {
        log.debug("REST request to get ChargeFile : {}", id);
        Optional<ChargeFile> chargeFile = chargeFileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chargeFile);
    }

    /**
     * {@code DELETE  /charge-files/:id} : delete the "id" chargeFile.
     *
     * @param id the id of the chargeFile to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/charge-files/{id}")
    public ResponseEntity<Void> deleteChargeFile(@PathVariable Long id) {
        log.debug("REST request to delete ChargeFile : {}", id);
        chargeFileService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
