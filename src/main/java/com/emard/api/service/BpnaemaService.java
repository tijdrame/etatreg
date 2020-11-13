package com.emard.api.service;

import com.emard.api.domain.Bpnaema;
import com.emard.api.repository.BpnaemaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bpnaema}.
 */
@Service
@Transactional
public class BpnaemaService {

    private final Logger log = LoggerFactory.getLogger(BpnaemaService.class);

    private final BpnaemaRepository bpnaemaRepository;

    public BpnaemaService(BpnaemaRepository bpnaemaRepository) {
        this.bpnaemaRepository = bpnaemaRepository;
    }

    /**
     * Save a bpnaema.
     *
     * @param bpnaema the entity to save.
     * @return the persisted entity.
     */
    public Bpnaema save(Bpnaema bpnaema) {
        log.debug("Request to save Bpnaema : {}", bpnaema);
        return bpnaemaRepository.save(bpnaema);
    }

    /**
     * Get all the bpnaemas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Bpnaema> findAll(Pageable pageable) {
        log.debug("Request to get all Bpnaemas");
        return bpnaemaRepository.findAll(pageable);
    }


    /**
     * Get one bpnaema by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Bpnaema> findOne(Long id) {
        log.debug("Request to get Bpnaema : {}", id);
        return bpnaemaRepository.findById(id);
    }

    /**
     * Delete the bpnaema by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bpnaema : {}", id);
        bpnaemaRepository.deleteById(id);
    }
}
