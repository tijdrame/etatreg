package com.emard.api.service;

import com.emard.api.domain.ObjetCredit;
import com.emard.api.repository.ObjetCreditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ObjetCredit}.
 */
@Service
@Transactional
public class ObjetCreditService {

    private final Logger log = LoggerFactory.getLogger(ObjetCreditService.class);

    private final ObjetCreditRepository objetCreditRepository;

    public ObjetCreditService(ObjetCreditRepository objetCreditRepository) {
        this.objetCreditRepository = objetCreditRepository;
    }

    /**
     * Save a objetCredit.
     *
     * @param objetCredit the entity to save.
     * @return the persisted entity.
     */
    public ObjetCredit save(ObjetCredit objetCredit) {
        log.debug("Request to save ObjetCredit : {}", objetCredit);
        return objetCreditRepository.save(objetCredit);
    }

    /**
     * Get all the objetCredits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ObjetCredit> findAll(Pageable pageable) {
        log.debug("Request to get all ObjetCredits");
        return objetCreditRepository.findAll(pageable);
    }


    /**
     * Get one objetCredit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ObjetCredit> findOne(Long id) {
        log.debug("Request to get ObjetCredit : {}", id);
        return objetCreditRepository.findById(id);
    }

    /**
     * Delete the objetCredit by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ObjetCredit : {}", id);
        objetCreditRepository.deleteById(id);
    }
}
