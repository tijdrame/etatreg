package com.emard.api.service;

import com.emard.api.domain.NatureDebiteur;
import com.emard.api.repository.NatureDebiteurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NatureDebiteur}.
 */
@Service
@Transactional
public class NatureDebiteurService {

    private final Logger log = LoggerFactory.getLogger(NatureDebiteurService.class);

    private final NatureDebiteurRepository natureDebiteurRepository;

    public NatureDebiteurService(NatureDebiteurRepository natureDebiteurRepository) {
        this.natureDebiteurRepository = natureDebiteurRepository;
    }

    /**
     * Save a natureDebiteur.
     *
     * @param natureDebiteur the entity to save.
     * @return the persisted entity.
     */
    public NatureDebiteur save(NatureDebiteur natureDebiteur) {
        log.debug("Request to save NatureDebiteur : {}", natureDebiteur);
        return natureDebiteurRepository.save(natureDebiteur);
    }

    /**
     * Get all the natureDebiteurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NatureDebiteur> findAll(Pageable pageable) {
        log.debug("Request to get all NatureDebiteurs");
        return natureDebiteurRepository.findAll(pageable);
    }


    /**
     * Get one natureDebiteur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NatureDebiteur> findOne(Long id) {
        log.debug("Request to get NatureDebiteur : {}", id);
        return natureDebiteurRepository.findById(id);
    }

    /**
     * Delete the natureDebiteur by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NatureDebiteur : {}", id);
        natureDebiteurRepository.deleteById(id);
    }
}
