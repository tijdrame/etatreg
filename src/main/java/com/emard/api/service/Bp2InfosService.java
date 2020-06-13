package com.emard.api.service;

import com.emard.api.domain.Bp2Infos;
import com.emard.api.repository.Bp2InfosRepository;
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
public class Bp2InfosService {

    private final Logger log = LoggerFactory.getLogger(Bp2InfosService.class);

    private final Bp2InfosRepository bp2InfosRepository;

    public Bp2InfosService(Bp2InfosRepository bp2InfosRepository) {
        this.bp2InfosRepository = bp2InfosRepository;
    }

    /**
     * Save a bp2Infos.
     *
     * @param bp2Infos the entity to save.
     * @return the persisted entity.
     */
    public Bp2Infos save(Bp2Infos bp2Infos) {
        log.debug("Request to save Bp2Infos : {}", bp2Infos);
        return bp2InfosRepository.save(bp2Infos);
    }

    /**
     * Get all the bp2Infos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Bp2Infos> findAll(Pageable pageable) {
        log.debug("Request to get all Bp2Infos");
        return bp2InfosRepository.findAll(pageable);
    }


    /**
     * Get one bp2Infos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Bp2Infos> findOne(Long id) {
        log.debug("Request to get Bp2Infos : {}", id);
        return bp2InfosRepository.findById(id);
    }

    /**
     * Delete the bp2Infos by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bp2Infos : {}", id);
        bp2InfosRepository.deleteById(id);
    }
}
