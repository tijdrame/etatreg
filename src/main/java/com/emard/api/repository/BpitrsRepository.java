package com.emard.api.repository;

import com.emard.api.domain.Bpitrs;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bpitrs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BpitrsRepository extends JpaRepository<Bpitrs, Long> {
}
