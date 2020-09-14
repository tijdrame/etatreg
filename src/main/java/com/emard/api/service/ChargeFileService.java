package com.emard.api.service;

import com.emard.api.domain.ChargeFile;
import com.emard.api.repository.ChargeFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ChargeFile}.
 */
@Service
@Transactional
public class ChargeFileService {

    private final Logger log = LoggerFactory.getLogger(ChargeFileService.class);

    private final ChargeFileRepository chargeFileRepository;

    public ChargeFileService(ChargeFileRepository chargeFileRepository) {
        this.chargeFileRepository = chargeFileRepository;
    }

    /**
     * Save a chargeFile.
     *
     * @param chargeFile the entity to save.
     * @return the persisted entity.
     */
    public ChargeFile save(ChargeFile chargeFile) {
        log.debug("Request to save ChargeFile : {}", chargeFile);
        return chargeFileRepository.save(chargeFile);
    }

    /**
     * Get all the chargeFiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ChargeFile> findAll(Pageable pageable) {
        log.debug("Request to get all ChargeFiles");
        return chargeFileRepository.findAll(pageable);
    }


    /**
     * Get one chargeFile by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ChargeFile> findOne(Long id) {
        log.debug("Request to get ChargeFile : {}", id);
        return chargeFileRepository.findById(id);
    }

    /**
     * Delete the chargeFile by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ChargeFile : {}", id);
        chargeFileRepository.deleteById(id);
    }
}
