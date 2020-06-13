package com.emard.api.service;

import com.emard.api.domain.BankInfos;
import com.emard.api.repository.BankInfosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BankInfos}.
 */
@Service
@Transactional
public class BankInfosService {

    private final Logger log = LoggerFactory.getLogger(BankInfosService.class);

    private final BankInfosRepository bankInfosRepository;

    public BankInfosService(BankInfosRepository bankInfosRepository) {
        this.bankInfosRepository = bankInfosRepository;
    }

    /**
     * Save a bankInfos.
     *
     * @param bankInfos the entity to save.
     * @return the persisted entity.
     */
    public BankInfos save(BankInfos bankInfos) {
        log.debug("Request to save BankInfos : {}", bankInfos);
        return bankInfosRepository.save(bankInfos);
    }

    /**
     * Get all the bankInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BankInfos> findAll(Pageable pageable) {
        log.debug("Request to get all BankInfos");
        return bankInfosRepository.findAll(pageable);
    }


    /**
     * Get one bankInfos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BankInfos> findOne(Long id) {
        log.debug("Request to get BankInfos : {}", id);
        return bankInfosRepository.findById(id);
    }

    /**
     * Delete the bankInfos by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BankInfos : {}", id);
        bankInfosRepository.deleteById(id);
    }
}
