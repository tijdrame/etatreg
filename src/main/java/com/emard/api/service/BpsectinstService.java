package com.emard.api.service;

import com.emard.api.domain.Bpsectinst;
import com.emard.api.repository.BpsectinstRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bpsectinst}.
 */
@Service
@Transactional
public class BpsectinstService {

    private final Logger log = LoggerFactory.getLogger(BpsectinstService.class);

    private final BpsectinstRepository bpsectinstRepository;

    public BpsectinstService(BpsectinstRepository bpsectinstRepository) {
        this.bpsectinstRepository = bpsectinstRepository;
    }

    /**
     * Save a bpsectinst.
     *
     * @param bpsectinst the entity to save.
     * @return the persisted entity.
     */
    public Bpsectinst save(Bpsectinst bpsectinst) {
        log.debug("Request to save Bpsectinst : {}", bpsectinst);
        return bpsectinstRepository.save(bpsectinst);
    }

    /**
     * Get all the bpsectinsts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Bpsectinst> findAll(Pageable pageable) {
        log.debug("Request to get all Bpsectinsts");
        return bpsectinstRepository.findAll(pageable);
    }


    /**
     * Get one bpsectinst by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Bpsectinst> findOne(Long id) {
        log.debug("Request to get Bpsectinst : {}", id);
        return bpsectinstRepository.findById(id);
    }

    /**
     * Delete the bpsectinst by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bpsectinst : {}", id);
        bpsectinstRepository.deleteById(id);
    }
}
