package com.emard.api.repository;

import com.emard.api.domain.NatureDebiteur;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NatureDebiteur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NatureDebiteurRepository extends JpaRepository<NatureDebiteur, Long> {
}
