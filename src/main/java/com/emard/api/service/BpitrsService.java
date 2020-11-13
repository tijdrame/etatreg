package com.emard.api.service;

import com.emard.api.domain.Bpitrs;
import com.emard.api.repository.BpitrsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bpitrs}.
 */
@Service
@Transactional
public class BpitrsService {

    private final Logger log = LoggerFactory.getLogger(BpitrsService.class);

    private final BpitrsRepository bpitrsRepository;

    public BpitrsService(BpitrsRepository bpitrsRepository) {
        this.bpitrsRepository = bpitrsRepository;
    }

    /**
     * Save a bpitrs.
     *
     * @param bpitrs the entity to save.
     * @return the persisted entity.
     */
    public Bpitrs save(Bpitrs bpitrs) {
        log.debug("Request to save Bpitrs : {}", bpitrs);
        return bpitrsRepository.save(bpitrs);
    }

    /**
     * Get all the bpitrs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Bpitrs> findAll(Pageable pageable) {
        log.debug("Request to get all Bpitrs");
        return bpitrsRepository.findAll(pageable);
    }


    /**
     * Get one bpitrs by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Bpitrs> findOne(Long id) {
        log.debug("Request to get Bpitrs : {}", id);
        return bpitrsRepository.findById(id);
    }

    /**
     * Delete the bpitrs by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bpitrs : {}", id);
        bpitrsRepository.deleteById(id);
    }
}
