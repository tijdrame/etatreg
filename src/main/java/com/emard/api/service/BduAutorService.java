package com.emard.api.service;

import com.emard.api.domain.BduAutor;
import com.emard.api.repository.BduAutorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BduAutor}.
 */
@Service
@Transactional
public class BduAutorService {

    private final Logger log = LoggerFactory.getLogger(BduAutorService.class);

    private final BduAutorRepository bduAutorRepository;

    public BduAutorService(BduAutorRepository bduAutorRepository) {
        this.bduAutorRepository = bduAutorRepository;
    }

    /**
     * Save a bduAutor.
     *
     * @param bduAutor the entity to save.
     * @return the persisted entity.
     */
    public BduAutor save(BduAutor bduAutor) {
        log.debug("Request to save BduAutor : {}", bduAutor);
        return bduAutorRepository.save(bduAutor);
    }

    /**
     * Get all the bduAutors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BduAutor> findAll(Pageable pageable) {
        log.debug("Request to get all BduAutors");
        return bduAutorRepository.findAll(pageable);
    }


    /**
     * Get one bduAutor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BduAutor> findOne(Long id) {
        log.debug("Request to get BduAutor : {}", id);
        return bduAutorRepository.findById(id);
    }

    /**
     * Delete the bduAutor by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BduAutor : {}", id);
        bduAutorRepository.deleteById(id);
    }
}
