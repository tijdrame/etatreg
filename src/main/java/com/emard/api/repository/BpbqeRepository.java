package com.emard.api.repository;

import com.emard.api.domain.Bpbqe;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bpbqe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BpbqeRepository extends JpaRepository<Bpbqe, Long> {
}
