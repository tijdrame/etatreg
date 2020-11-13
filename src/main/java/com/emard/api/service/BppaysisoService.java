package com.emard.api.service;

import com.emard.api.domain.Bppaysiso;
import com.emard.api.repository.BppaysisoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bppaysiso}.
 */
@Service
@Transactional
public class BppaysisoService {

    private final Logger log = LoggerFactory.getLogger(BppaysisoService.class);

    private final BppaysisoRepository bppaysisoRepository;

    public BppaysisoService(BppaysisoRepository bppaysisoRepository) {
        this.bppaysisoRepository = bppaysisoRepository;
    }

    /**
     * Save a bppaysiso.
     *
     * @param bppaysiso the entity to save.
     * @return the persisted entity.
     */
    public Bppaysiso save(Bppaysiso bppaysiso) {
        log.debug("Request to save Bppaysiso : {}", bppaysiso);
        return bppaysisoRepository.save(bppaysiso);
    }

    /**
     * Get all the bppaysisos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Bppaysiso> findAll(Pageable pageable) {
        log.debug("Request to get all Bppaysisos");
        return bppaysisoRepository.findAll(pageable);
    }


    /**
     * Get one bppaysiso by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Bppaysiso> findOne(Long id) {
        log.debug("Request to get Bppaysiso : {}", id);
        return bppaysisoRepository.findById(id);
    }

    /**
     * Delete the bppaysiso by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bppaysiso : {}", id);
        bppaysisoRepository.deleteById(id);
    }
}
