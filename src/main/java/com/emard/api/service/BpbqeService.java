package com.emard.api.service;

import com.emard.api.domain.Bpbqe;
import com.emard.api.repository.BpbqeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bpbqe}.
 */
@Service
@Transactional
public class BpbqeService {

    private final Logger log = LoggerFactory.getLogger(BpbqeService.class);

    private final BpbqeRepository bpbqeRepository;

    public BpbqeService(BpbqeRepository bpbqeRepository) {
        this.bpbqeRepository = bpbqeRepository;
    }

    /**
     * Save a bpbqe.
     *
     * @param bpbqe the entity to save.
     * @return the persisted entity.
     */
    public Bpbqe save(Bpbqe bpbqe) {
        log.debug("Request to save Bpbqe : {}", bpbqe);
        return bpbqeRepository.save(bpbqe);
    }

    /**
     * Get all the bpbqes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Bpbqe> findAll(Pageable pageable) {
        log.debug("Request to get all Bpbqes");
        return bpbqeRepository.findAll(pageable);
    }


    /**
     * Get one bpbqe by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Bpbqe> findOne(Long id) {
        log.debug("Request to get Bpbqe : {}", id);
        return bpbqeRepository.findById(id);
    }

    /**
     * Delete the bpbqe by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bpbqe : {}", id);
        bpbqeRepository.deleteById(id);
    }
}
