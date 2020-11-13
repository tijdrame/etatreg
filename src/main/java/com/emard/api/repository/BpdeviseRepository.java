package com.emard.api.repository;

import com.emard.api.domain.Bpdevise;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bpdevise entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BpdeviseRepository extends JpaRepository<Bpdevise, Long> {
}
