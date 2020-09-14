package com.emard.api.repository;

import com.emard.api.domain.Bp1Com;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bp1Com entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Bp1ComRepository extends JpaRepository<Bp1Com, Long> {
}
