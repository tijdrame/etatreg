package com.emard.api.service;

import com.emard.api.domain.BduCdb;
import com.emard.api.repository.BduCdbRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BduCdb}.
 */
@Service
@Transactional
public class BduCdbService {

    private final Logger log = LoggerFactory.getLogger(BduCdbService.class);

    private final BduCdbRepository bduCdbRepository;

    public BduCdbService(BduCdbRepository bduCdbRepository) {
        this.bduCdbRepository = bduCdbRepository;
    }

    /**
     * Save a bduCdb.
     *
     * @param bduCdb the entity to save.
     * @return the persisted entity.
     */
    public BduCdb save(BduCdb bduCdb) {
        log.debug("Request to save BduCdb : {}", bduCdb);
        return bduCdbRepository.save(bduCdb);
    }

    /**
     * Get all the bduCdbs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BduCdb> findAll(Pageable pageable) {
        log.debug("Request to get all BduCdbs");
        return bduCdbRepository.findAll(pageable);
    }


    /**
     * Get one bduCdb by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BduCdb> findOne(Long id) {
        log.debug("Request to get BduCdb : {}", id);
        return bduCdbRepository.findById(id);
    }

    /**
     * Delete the bduCdb by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BduCdb : {}", id);
        bduCdbRepository.deleteById(id);
    }
}
