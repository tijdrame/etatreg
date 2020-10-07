package com.emard.api.service;

import com.emard.api.domain.Bp1His;
import com.emard.api.repository.Bp1HisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bp1His}.
 */
@Service
@Transactional
public class Bp1HisService {

    private final Logger log = LoggerFactory.getLogger(Bp1HisService.class);

    private final Bp1HisRepository bp1HisRepository;

    public Bp1HisService(Bp1HisRepository bp1HisRepository) {
        this.bp1HisRepository = bp1HisRepository;
    }

    /**
     * Save a bp1His.
     *
     * @param bp1His the entity to save.
     * @return the persisted entity.
     */
    public Bp1His save(Bp1His bp1His) {
        log.debug("Request to save Bp1His : {}", bp1His);
        return bp1HisRepository.save(bp1His);
    }

    /**
     * Get all the bp1His.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Bp1His> findAll(Pageable pageable) {
        log.debug("Request to get all Bp1His");
        return bp1HisRepository.findAll(pageable);
    }


    /**
     * Get one bp1His by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Bp1His> findOne(Long id) {
        log.debug("Request to get Bp1His : {}", id);
        return bp1HisRepository.findById(id);
    }

    /**
     * Delete the bp1His by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bp1His : {}", id);
        bp1HisRepository.deleteById(id);
    }
    
     @Transactional(readOnly = false)
    public void viderBp1His(String nomFic) {
        log.debug("Request to delete all Bp1His by filename");
        bp1HisRepository.viderBp1His(nomFic);
    } 
}
