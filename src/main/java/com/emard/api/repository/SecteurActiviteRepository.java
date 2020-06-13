package com.emard.api.repository;

import com.emard.api.domain.SecteurActivite;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SecteurActivite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecteurActiviteRepository extends JpaRepository<SecteurActivite, Long> {
}
