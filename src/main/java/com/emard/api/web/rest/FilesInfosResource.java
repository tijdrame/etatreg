package com.emard.api.web.rest;

import com.emard.api.domain.FilesInfos;
import com.emard.api.service.FilesInfosService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.emard.api.domain.FilesInfos}.
 */
@RestController
@RequestMapping("/api")
public class FilesInfosResource {

    private final Logger log = LoggerFactory.getLogger(FilesInfosResource.class);

    private static final String ENTITY_NAME = "filesInfos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FilesInfosService filesInfosService;

    public FilesInfosResource(FilesInfosService filesInfosService) {
        this.filesInfosService = filesInfosService;
    }

    /**
     * {@code POST  /files-infos} : Create a new filesInfos.
     *
     * @param filesInfos the filesInfos to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new filesInfos, or with status {@code 400 (Bad Request)} if the filesInfos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/files-infos")
    public ResponseEntity<FilesInfos> createFilesInfos(@Valid @RequestBody FilesInfos filesInfos) throws URISyntaxException {
        log.debug("REST request to save FilesInfos : {}", filesInfos);
        if (filesInfos.getId() != null) {
            throw new BadRequestAlertException("A new filesInfos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FilesInfos result = filesInfosService.save(filesInfos);
        return ResponseEntity.created(new URI("/api/files-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /files-infos} : Updates an existing filesInfos.
     *
     * @param filesInfos the filesInfos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated filesInfos,
     * or with status {@code 400 (Bad Request)} if the filesInfos is not valid,
     * or with status {@code 500 (Internal Server Error)} if the filesInfos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/files-infos")
    public ResponseEntity<FilesInfos> updateFilesInfos(@Valid @RequestBody FilesInfos filesInfos) throws URISyntaxException {
        log.debug("REST request to update FilesInfos : {}", filesInfos);
        if (filesInfos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FilesInfos result = filesInfosService.save(filesInfos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, filesInfos.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /files-infos} : get all the filesInfos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of filesInfos in body.
     */
    @GetMapping("/files-infos")
    public ResponseEntity<List<FilesInfos>> getAllFilesInfos(Pageable pageable) {
        log.debug("REST request to get a page of FilesInfos");
        Page<FilesInfos> page = filesInfosService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/files-infos-bis")
    public ResponseEntity<List<FilesInfos>> getAllFilesInfos() {
        log.debug("REST request to get a page of FilesInfos");
        List<FilesInfos> page = filesInfosService.findAllBis();
        return ResponseEntity.ok().body(page);
    }

    @GetMapping(path = "/files-infos-code", params = {"code"})
    public ResponseEntity<List<FilesInfos>>findByCodeFile (@RequestParam String code) {
        log.debug("REST request to get a page of FilesInfos");
        List<FilesInfos> page = filesInfosService.findByCodeFile(code);
        //HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping(path = "/files-infos-BPR")
    public ResponseEntity<List<String>>findFileInFolderBPR () {
        log.debug("REST request to get file of BPR");
        List<FilesInfos> list = filesInfosService.findByCodeFile("BPR");
        List<String> page = new ArrayList();
        if(list!=null && !list.isEmpty())
            page = filesInfosService.getAllFileInFolder(list.get(0).getInputPath());
        return ResponseEntity.ok().body(page);
    }
    @GetMapping(path = "/files-infos-CDP")
    public ResponseEntity<List<String>>findFileInFolderCDP () {
        log.debug("REST request to get file of CDP");
        List<FilesInfos> list = filesInfosService.findByCodeFile("CDP");
        List<String> page = new ArrayList();
        if(list!=null && !list.isEmpty())
            page = filesInfosService.getAllFileInFolder(list.get(0).getInputPath());
        return ResponseEntity.ok().body(page);
    }

    /**
     * {@code GET  /files-infos/:id} : get the "id" filesInfos.
     *
     * @param id the id of the filesInfos to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the filesInfos, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/files-infos/{id}")
    public ResponseEntity<FilesInfos> getFilesInfos(@PathVariable Long id) {
        log.debug("REST request to get FilesInfos : {}", id);
        Optional<FilesInfos> filesInfos = filesInfosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(filesInfos);
    }

    /**
     * {@code DELETE  /files-infos/:id} : delete the "id" filesInfos.
     *
     * @param id the id of the filesInfos to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/files-infos/{id}")
    public ResponseEntity<Void> deleteFilesInfos(@PathVariable Long id) {
        log.debug("REST request to delete FilesInfos : {}", id);
        filesInfosService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
