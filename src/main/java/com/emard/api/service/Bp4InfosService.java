package com.emard.api.service;

import com.emard.api.domain.Bp4Infos;
import com.emard.api.repository.Bp4InfosRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bp4Infos}.
 */
@Service
@Transactional
public class Bp4InfosService {

    private final Logger log = LoggerFactory.getLogger(Bp4InfosService.class);

    private final Bp4InfosRepository bp4InfosRepository;

    public Bp4InfosService(Bp4InfosRepository bp4InfosRepository) {
        this.bp4InfosRepository = bp4InfosRepository;
    }

    /**
     * Save a bp4Infos.
     *
     * @param bp4Infos the entity to save.
     * @return the persisted entity.
     */
    public Bp4Infos save(Bp4Infos bp4Infos) {
        log.debug("Request to save Bp4Infos : {}", bp4Infos);
        return bp4InfosRepository.save(bp4Infos);
    }

    /**
     * Get all the bp4Infos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Bp4Infos> findAll(Pageable pageable) {
        log.debug("Request to get all Bp4Infos");
        return bp4InfosRepository.findAll(pageable);
    }


    /**
     * Get one bp4Infos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Bp4Infos> findOne(Long id) {
        log.debug("Request to get Bp4Infos : {}", id);
        return bp4InfosRepository.findById(id);
    }

    /**
     * Delete the bp4Infos by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bp4Infos : {}", id);
        bp4InfosRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Object[]> findListEmissionAcquisition(){
        return bp4InfosRepository.findListEmissionAcquisition();
    }
    
    @Transactional(readOnly = false)
    public void viderBp4Infos(String nomFic) {
        log.debug("Request to delete all Bp4Infos by filename");
        bp4InfosRepository.viderBp4Infos(nomFic);
    } 
}
