package com.emard.api.repository;

import com.emard.api.domain.Bppaysiso;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bppaysiso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BppaysisoRepository extends JpaRepository<Bppaysiso, Long> {
}
