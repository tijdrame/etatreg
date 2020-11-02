package com.emard.api.repository;

import com.emard.api.domain.ObjetCredit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ObjetCredit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ObjetCreditRepository extends JpaRepository<ObjetCredit, Long> {
    @Query("select od.codeCb from ObjetCredit od where od.codeBdu = ?1")
    public String findCodeBceaoByCodeInterne (String codeInterne);
}
