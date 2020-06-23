package com.emard.api.repository;

import com.emard.api.domain.Periode;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Periode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PeriodeRepository extends JpaRepository<Periode, Long> {
}
