package com.emard.api.service;

import com.emard.api.domain.Bp1Infos;
import com.emard.api.repository.Bp1InfosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bp2Infos}.
 */
@Service
@Transactional
public class Bp1InfosService {

    private final Logger log = LoggerFactory.getLogger(Bp1InfosService.class);

    private final Bp1InfosRepository bp1InfosRepository;

    public Bp1InfosService(Bp1InfosRepository bp1InfosRepository) {
        this.bp1InfosRepository = bp1InfosRepository;
    }

    /**
     * Save a bp2Infos.
     *
     * @param bp2Infos the entity to save.
     * @return the persisted entity.
     */
    public Bp1Infos save(Bp1Infos bp2Infos) {
        log.debug("Request to save Bp2Infos : {}", bp2Infos);
        return bp1InfosRepository.save(bp2Infos);
    }

    /**
     * Get all the bp2Infos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Bp1Infos> findAll(Pageable pageable) {
        log.debug("Request to get all Bp2Infos");
        return bp1InfosRepository.findAll(pageable);
    }


    /**
     * Get one bp2Infos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Bp1Infos> findOne(Long id) {
        log.debug("Request to get Bp1Infos : {}", id);
        return bp1InfosRepository.findById(id);
    }

    /**
     * Delete the bp2Infos by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bp1Infos : {}", id);
        bp1InfosRepository.deleteById(id);
    }
    
    @Transactional(readOnly = false)
    public void viderBp1Infos() {
        log.debug("Request to delete all Bp1Infos by filename");
        bp1InfosRepository.viderBp1infos();
    } 
}
