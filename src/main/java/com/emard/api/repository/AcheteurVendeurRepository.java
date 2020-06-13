package com.emard.api.repository;

import com.emard.api.domain.AcheteurVendeur;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AcheteurVendeur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcheteurVendeurRepository extends JpaRepository<AcheteurVendeur, Long> {
}
