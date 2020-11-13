package com.emard.api.service;

import com.emard.api.domain.Bpdevise;
import com.emard.api.repository.BpdeviseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bpdevise}.
 */
@Service
@Transactional
public class BpdeviseService {

    private final Logger log = LoggerFactory.getLogger(BpdeviseService.class);

    private final BpdeviseRepository bpdeviseRepository;

    public BpdeviseService(BpdeviseRepository bpdeviseRepository) {
        this.bpdeviseRepository = bpdeviseRepository;
    }

    /**
     * Save a bpdevise.
     *
     * @param bpdevise the entity to save.
     * @return the persisted entity.
     */
    public Bpdevise save(Bpdevise bpdevise) {
        log.debug("Request to save Bpdevise : {}", bpdevise);
        return bpdeviseRepository.save(bpdevise);
    }

    /**
     * Get all the bpdevises.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Bpdevise> findAll(Pageable pageable) {
        log.debug("Request to get all Bpdevises");
        return bpdeviseRepository.findAll(pageable);
    }


    /**
     * Get one bpdevise by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Bpdevise> findOne(Long id) {
        log.debug("Request to get Bpdevise : {}", id);
        return bpdeviseRepository.findById(id);
    }

    /**
     * Delete the bpdevise by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bpdevise : {}", id);
        bpdeviseRepository.deleteById(id);
    }
}
