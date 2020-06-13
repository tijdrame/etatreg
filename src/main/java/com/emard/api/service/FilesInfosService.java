package com.emard.api.service;

import com.emard.api.domain.FilesInfos;
import com.emard.api.repository.FilesInfosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FilesInfos}.
 */
@Service
@Transactional
public class FilesInfosService {

    private final Logger log = LoggerFactory.getLogger(FilesInfosService.class);

    private final FilesInfosRepository filesInfosRepository;

    public FilesInfosService(FilesInfosRepository filesInfosRepository) {
        this.filesInfosRepository = filesInfosRepository;
    }

    /**
     * Save a filesInfos.
     *
     * @param filesInfos the entity to save.
     * @return the persisted entity.
     */
    public FilesInfos save(FilesInfos filesInfos) {
        log.debug("Request to save FilesInfos : {}", filesInfos);
        return filesInfosRepository.save(filesInfos);
    }

    /**
     * Get all the filesInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FilesInfos> findAll(Pageable pageable) {
        log.debug("Request to get all FilesInfos");
        return filesInfosRepository.findAll(pageable);
    }


    /**
     * Get one filesInfos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FilesInfos> findOne(Long id) {
        log.debug("Request to get FilesInfos : {}", id);
        return filesInfosRepository.findById(id);
    }

    /**
     * Delete the filesInfos by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FilesInfos : {}", id);
        filesInfosRepository.deleteById(id);
    }
}
