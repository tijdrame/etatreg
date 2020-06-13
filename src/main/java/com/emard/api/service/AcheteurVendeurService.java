package com.emard.api.service;

import com.emard.api.domain.AcheteurVendeur;
import com.emard.api.repository.AcheteurVendeurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AcheteurVendeur}.
 */
@Service
@Transactional
public class AcheteurVendeurService {

    private final Logger log = LoggerFactory.getLogger(AcheteurVendeurService.class);

    private final AcheteurVendeurRepository acheteurVendeurRepository;

    public AcheteurVendeurService(AcheteurVendeurRepository acheteurVendeurRepository) {
        this.acheteurVendeurRepository = acheteurVendeurRepository;
    }

    /**
     * Save a acheteurVendeur.
     *
     * @param acheteurVendeur the entity to save.
     * @return the persisted entity.
     */
    public AcheteurVendeur save(AcheteurVendeur acheteurVendeur) {
        log.debug("Request to save AcheteurVendeur : {}", acheteurVendeur);
        return acheteurVendeurRepository.save(acheteurVendeur);
    }

    /**
     * Get all the acheteurVendeurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AcheteurVendeur> findAll(Pageable pageable) {
        log.debug("Request to get all AcheteurVendeurs");
        return acheteurVendeurRepository.findAll(pageable);
    }


    /**
     * Get one acheteurVendeur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AcheteurVendeur> findOne(Long id) {
        log.debug("Request to get AcheteurVendeur : {}", id);
        return acheteurVendeurRepository.findById(id);
    }

    /**
     * Delete the acheteurVendeur by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AcheteurVendeur : {}", id);
        acheteurVendeurRepository.deleteById(id);
    }
}
