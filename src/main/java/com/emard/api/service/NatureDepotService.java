package com.emard.api.service;

import com.emard.api.domain.NatureDepot;
import com.emard.api.repository.NatureDepotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NatureDepot}.
 */
@Service
@Transactional
public class NatureDepotService {

    private final Logger log = LoggerFactory.getLogger(NatureDepotService.class);

    private final NatureDepotRepository natureDepotRepository;

    public NatureDepotService(NatureDepotRepository natureDepotRepository) {
        this.natureDepotRepository = natureDepotRepository;
    }

    /**
     * Save a natureDepot.
     *
     * @param natureDepot the entity to save.
     * @return the persisted entity.
     */
    public NatureDepot save(NatureDepot natureDepot) {
        log.debug("Request to save NatureDepot : {}", natureDepot);
        return natureDepotRepository.save(natureDepot);
    }

    /**
     * Get all the natureDepots.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NatureDepot> findAll(Pageable pageable) {
        log.debug("Request to get all NatureDepots");
        return natureDepotRepository.findAll(pageable);
    }


    /**
     * Get one natureDepot by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NatureDepot> findOne(Long id) {
        log.debug("Request to get NatureDepot : {}", id);
        return natureDepotRepository.findById(id);
    }

    /**
     * Delete the natureDepot by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NatureDepot : {}", id);
        natureDepotRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public String findCodeItrsByCodeInterne (String codeInterne){
        log.debug("Request to get Code Itrs : {}", codeInterne);
        return natureDepotRepository.findCodeItrsByCodeInterne(codeInterne);
    }
}
