package com.emard.api.service;

import com.emard.api.domain.SecteurActivite;
import com.emard.api.repository.SecteurActiviteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SecteurActivite}.
 */
@Service
@Transactional
public class SecteurActiviteService {

    private final Logger log = LoggerFactory.getLogger(SecteurActiviteService.class);

    private final SecteurActiviteRepository secteurActiviteRepository;

    public SecteurActiviteService(SecteurActiviteRepository secteurActiviteRepository) {
        this.secteurActiviteRepository = secteurActiviteRepository;
    }

    /**
     * Save a secteurActivite.
     *
     * @param secteurActivite the entity to save.
     * @return the persisted entity.
     */
    public SecteurActivite save(SecteurActivite secteurActivite) {
        log.debug("Request to save SecteurActivite : {}", secteurActivite);
        return secteurActiviteRepository.save(secteurActivite);
    }

    /**
     * Get all the secteurActivites.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SecteurActivite> findAll(Pageable pageable) {
        log.debug("Request to get all SecteurActivites");
        return secteurActiviteRepository.findAll(pageable);
    }


    /**
     * Get one secteurActivite by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SecteurActivite> findOne(Long id) {
        log.debug("Request to get SecteurActivite : {}", id);
        return secteurActiviteRepository.findById(id);
    }

    /**
     * Delete the secteurActivite by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SecteurActivite : {}", id);
        secteurActiviteRepository.deleteById(id);
    }
}
