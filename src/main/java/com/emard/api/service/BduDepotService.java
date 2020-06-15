package com.emard.api.service;

import com.emard.api.domain.BduDepot;
import com.emard.api.repository.BduDepotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BduDepot}.
 */
@Service
@Transactional
public class BduDepotService {

    private final Logger log = LoggerFactory.getLogger(BduDepotService.class);

    private final BduDepotRepository bduDepotRepository;

    public BduDepotService(BduDepotRepository bduDepotRepository) {
        this.bduDepotRepository = bduDepotRepository;
    }

    /**
     * Save a bduDepot.
     *
     * @param bduDepot the entity to save.
     * @return the persisted entity.
     */
    public BduDepot save(BduDepot bduDepot) {
        log.debug("Request to save BduDepot : {}", bduDepot);
        return bduDepotRepository.save(bduDepot);
    }

    /**
     * Get all the bduDepots.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BduDepot> findAll(Pageable pageable) {
        log.debug("Request to get all BduDepots");
        return bduDepotRepository.findAll(pageable);
    }


    /**
     * Get one bduDepot by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BduDepot> findOne(Long id) {
        log.debug("Request to get BduDepot : {}", id);
        return bduDepotRepository.findById(id);
    }

    /**
     * Delete the bduDepot by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BduDepot : {}", id);
        bduDepotRepository.deleteById(id);
    }
}
