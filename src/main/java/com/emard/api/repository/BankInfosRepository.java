package com.emard.api.repository;

import com.emard.api.domain.BankInfos;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BankInfos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankInfosRepository extends JpaRepository<BankInfos, Long> {
}
