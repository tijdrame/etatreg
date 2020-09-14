package com.emard.api.service;

import com.emard.api.domain.Bp1Com;
import com.emard.api.repository.Bp1ComRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bp1Com}.
 */
@Service
@Transactional
public class Bp1ComService {

    private final Logger log = LoggerFactory.getLogger(Bp1ComService.class);

    private final Bp1ComRepository bp1ComRepository;

    public Bp1ComService(Bp1ComRepository bp1ComRepository) {
        this.bp1ComRepository = bp1ComRepository;
    }

    /**
     * Save a bp1Com.
     *
     * @param bp1Com the entity to save.
     * @return the persisted entity.
     */
    public Bp1Com save(Bp1Com bp1Com) {
        log.debug("Request to save Bp1Com : {}", bp1Com);
        return bp1ComRepository.save(bp1Com);
    }

    /**
     * Get all the bp1Coms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Bp1Com> findAll(Pageable pageable) {
        log.debug("Request to get all Bp1Coms");
        return bp1ComRepository.findAll(pageable);
    }


    /**
     * Get one bp1Com by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Bp1Com> findOne(Long id) {
        log.debug("Request to get Bp1Com : {}", id);
        return bp1ComRepository.findById(id);
    }

    /**
     * Delete the bp1Com by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bp1Com : {}", id);
        bp1ComRepository.deleteById(id);
    }
}
