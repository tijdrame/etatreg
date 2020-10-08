package com.emard.api.service;

import com.emard.api.domain.CrpAtr;
import com.emard.api.repository.CrpAtrRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CrpAtr}.
 */
@Service
@Transactional
public class CrpAtrService {

    private final Logger log = LoggerFactory.getLogger(CrpAtrService.class);

    private final CrpAtrRepository crpAtrRepository;

    public CrpAtrService(CrpAtrRepository crpAtrRepository) {
        this.crpAtrRepository = crpAtrRepository;
    }

    /**
     * Save a crpAtr.
     *
     * @param crpAtr the entity to save.
     * @return the persisted entity.
     */
    public CrpAtr save(CrpAtr crpAtr) {
        log.debug("Request to save CrpAtr : {}", crpAtr);
        return crpAtrRepository.save(crpAtr);
    }

    /**
     * Get all the crpAtrs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CrpAtr> findAll(Pageable pageable) {
        log.debug("Request to get all CrpAtrs");
        return crpAtrRepository.findAll(pageable);
    }


    /**
     * Get one crpAtr by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CrpAtr> findOne(Long id) {
        log.debug("Request to get CrpAtr : {}", id);
        return crpAtrRepository.findById(id);
    }

    /**
     * Delete the crpAtr by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CrpAtr : {}", id);
        crpAtrRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<CrpAtr> findByCenr(String cenr) {
        log.debug("Request to get all Bp2Coms");
        return crpAtrRepository.findByCenr(cenr);
    }
    
    @Transactional(readOnly = false)
    public void viderCrpAtr(String nomFic) {
        log.debug("Request to delete all crpatr by filename");
        crpAtrRepository.viderCrpAtr(nomFic);
    } 
}
