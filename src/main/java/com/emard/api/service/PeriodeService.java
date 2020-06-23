package com.emard.api.service;

import com.emard.api.domain.Periode;
import com.emard.api.repository.PeriodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Periode}.
 */
@Service
@Transactional
public class PeriodeService {

    private final Logger log = LoggerFactory.getLogger(PeriodeService.class);

    private final PeriodeRepository periodeRepository;

    public PeriodeService(PeriodeRepository periodeRepository) {
        this.periodeRepository = periodeRepository;
    }

    /**
     * Save a periode.
     *
     * @param periode the entity to save.
     * @return the persisted entity.
     */
    public Periode save(Periode periode) {
        log.debug("Request to save Periode : {}", periode);
        return periodeRepository.save(periode);
    }

    /**
     * Get all the periodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Periode> findAll() {
        log.debug("Request to get all Periodes");
        return periodeRepository.findAll();
    }


    /**
     * Get one periode by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Periode> findOne(Long id) {
        log.debug("Request to get Periode : {}", id);
        return periodeRepository.findById(id);
    }

    /**
     * Delete the periode by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Periode : {}", id);
        periodeRepository.deleteById(id);
    }
}
