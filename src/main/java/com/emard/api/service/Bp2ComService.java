package com.emard.api.service;

import com.emard.api.domain.Bp2Com;
import com.emard.api.repository.Bp2ComRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bp2Com}.
 */
@Service
@Transactional
public class Bp2ComService {

    private final Logger log = LoggerFactory.getLogger(Bp2ComService.class);

    private final Bp2ComRepository bp2ComRepository;

    public Bp2ComService(Bp2ComRepository bp2ComRepository) {
        this.bp2ComRepository = bp2ComRepository;
    }

    /**
     * Save a bp2Com.
     *
     * @param bp2Com the entity to save.
     * @return the persisted entity.
     */
    public Bp2Com save(Bp2Com bp2Com) {
        log.debug("Request to save Bp2Com : {}", bp2Com);
        return bp2ComRepository.save(bp2Com);
    }

    /**
     * Get all the bp2Coms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Bp2Com> findAll(Pageable pageable) {
        log.debug("Request to get all Bp2Coms");
        return bp2ComRepository.findAll(pageable);
    }


    /**
     * Get one bp2Com by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Bp2Com> findOne(Long id) {
        log.debug("Request to get Bp2Com : {}", id);
        return bp2ComRepository.findById(id);
    }

    /**
     * Delete the bp2Com by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bp2Com : {}", id);
        bp2ComRepository.deleteById(id);
    }
    
        /**
     * Get all the bp2Coms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Bp2Com> findByDev(String dev) {
        log.debug("Request to get all Bp2Coms");
        return bp2ComRepository.findByDev(dev);
    }
    
        @Transactional(readOnly = true)
    public List<Bp2Com> findLikeCha(String cha, String devise) {
        log.debug("Request to get all Bp2Coms");
        return bp2ComRepository.findLikeCha(cha, devise);
    }
    
    @Transactional(readOnly = true)
    public List<Bp2Com> findLikeChaAndSdeNeg(String cha, String devise) {
        log.debug("Request to get all Bp2Coms");
        return bp2ComRepository.findLikeChaAndSdeNeg(cha,devise);
    }
    
    @Transactional(readOnly = true)
    public List<Bp2Com> findLikeChaAndSdePos(String cha, String devise) {
        log.debug("Request to get all Bp2Coms");
        return bp2ComRepository.findLikeChaAndSdePos(cha, devise);
    }
    
        @Transactional(readOnly = true)
    public List<Bp2Com> findLikeCha1OrCha2(String cha1, String cha2, String devise) {
        log.debug("Request to get all Bp2Coms");
        return bp2ComRepository.findLikeCha1OrCha2(cha1,cha2,devise);
    } 
    
    @Transactional(readOnly = true)
    public List<Bp2Com> findLikeCha1OrCha2AndSdeNeg(String cha1, String cha2,String devise) {
        log.debug("Request to get all Bp2Coms");
        return bp2ComRepository.findLikeCha1OrCha2AndSdeNeg(cha1,cha2, devise);
    } 
    
    @Transactional(readOnly = true)
    public List<Bp2Com> findByChapitres(List<String> chapitres, String devise) {
        log.debug("Request to get all Bp2Coms");
        return bp2ComRepository.findByChapitres(chapitres, devise);
    } 
}
