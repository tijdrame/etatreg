package com.emard.api.repository;

import com.emard.api.domain.CrpAtr;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CrpAtr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CrpAtrRepository extends JpaRepository<CrpAtr, Long> {
}
