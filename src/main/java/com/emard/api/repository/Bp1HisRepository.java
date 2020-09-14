package com.emard.api.repository;

import com.emard.api.domain.Bp1His;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bp1His entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Bp1HisRepository extends JpaRepository<Bp1His, Long> {
}
