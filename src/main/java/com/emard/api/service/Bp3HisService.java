package com.emard.api.service;

import com.emard.api.domain.Bp3His;
import com.emard.api.repository.Bp3HisRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bp3His}.
 */
@Service
@Transactional
public class Bp3HisService {

    private final Logger log = LoggerFactory.getLogger(Bp3HisService.class);

    private final Bp3HisRepository bp3HisRepository;

    public Bp3HisService(Bp3HisRepository bp3HisRepository) {
        this.bp3HisRepository = bp3HisRepository;
    }

    /**
     * Save a bp3His.
     *
     * @param bp3His the entity to save.
     * @return the persisted entity.
     */
    public Bp3His save(Bp3His bp3His) {
        log.debug("Request to save Bp3His : {}", bp3His);
        return bp3HisRepository.save(bp3His);
    }

    /**
     * Get all the bp3His.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Bp3His> findAll(Pageable pageable) {
        log.debug("Request to get all Bp3His");
        return bp3HisRepository.findAll(pageable);
    }


    /**
     * Get one bp3His by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Bp3His> findOne(Long id) {
        log.debug("Request to get Bp3His : {}", id);
        return bp3HisRepository.findById(id);
    }

    /**
     * Delete the bp3His by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bp3His : {}", id);
        bp3HisRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public Double findSumMonByDevAndNatAndMonPos(String nat, String devise) {
        log.debug("Request to get all Bp2Coms");
        return bp3HisRepository.findSumMonByDevAndNatAndMonPos(nat,devise);
    } 
    
    @Transactional(readOnly = true)
    public Double findSumMonByDevAndNatAndMonNeg(String nat, String devise) {
        log.debug("Request to get all Bp2Coms");
        return bp3HisRepository.findSumMonByDevAndNatAndMonNeg(nat,devise);
    } 

}
