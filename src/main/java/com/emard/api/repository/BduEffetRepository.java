package com.emard.api.repository;

import com.emard.api.domain.BduEffet;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BduEffet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BduEffetRepository extends JpaRepository<BduEffet, Long> {
}
