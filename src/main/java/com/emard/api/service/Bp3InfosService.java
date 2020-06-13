package com.emard.api.service;

import com.emard.api.domain.Bp3Infos;
import com.emard.api.repository.Bp3InfosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bp3Infos}.
 */
@Service
@Transactional
public class Bp3InfosService {

    private final Logger log = LoggerFactory.getLogger(Bp3InfosService.class);

    private final Bp3InfosRepository bp3InfosRepository;

    public Bp3InfosService(Bp3InfosRepository bp3InfosRepository) {
        this.bp3InfosRepository = bp3InfosRepository;
    }

    /**
     * Save a bp3Infos.
     *
     * @param bp3Infos the entity to save.
     * @return the persisted entity.
     */
    public Bp3Infos save(Bp3Infos bp3Infos) {
        log.debug("Request to save Bp3Infos : {}", bp3Infos);
        return bp3InfosRepository.save(bp3Infos);
    }

    /**
     * Get all the bp3Infos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Bp3Infos> findAll(Pageable pageable) {
        log.debug("Request to get all Bp3Infos");
        return bp3InfosRepository.findAll(pageable);
    }


    /**
     * Get one bp3Infos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Bp3Infos> findOne(Long id) {
        log.debug("Request to get Bp3Infos : {}", id);
        return bp3InfosRepository.findById(id);
    }

    /**
     * Delete the bp3Infos by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bp3Infos : {}", id);
        bp3InfosRepository.deleteById(id);
    }
}
