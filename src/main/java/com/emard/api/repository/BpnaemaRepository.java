package com.emard.api.repository;

import com.emard.api.domain.Bpnaema;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bpnaema entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BpnaemaRepository extends JpaRepository<Bpnaema, Long> {
}
