package com.emard.api.service;

import com.emard.api.domain.BduEffet;
import com.emard.api.repository.BduEffetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BduEffet}.
 */
@Service
@Transactional
public class BduEffetService {

    private final Logger log = LoggerFactory.getLogger(BduEffetService.class);

    private final BduEffetRepository bduEffetRepository;

    public BduEffetService(BduEffetRepository bduEffetRepository) {
        this.bduEffetRepository = bduEffetRepository;
    }

    /**
     * Save a bduEffet.
     *
     * @param bduEffet the entity to save.
     * @return the persisted entity.
     */
    public BduEffet save(BduEffet bduEffet) {
        log.debug("Request to save BduEffet : {}", bduEffet);
        return bduEffetRepository.save(bduEffet);
    }

    /**
     * Get all the bduEffets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BduEffet> findAll(Pageable pageable) {
        log.debug("Request to get all BduEffets");
        return bduEffetRepository.findAll(pageable);
    }


    /**
     * Get one bduEffet by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BduEffet> findOne(Long id) {
        log.debug("Request to get BduEffet : {}", id);
        return bduEffetRepository.findById(id);
    }

    /**
     * Delete the bduEffet by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BduEffet : {}", id);
        bduEffetRepository.deleteById(id);
    }
}
