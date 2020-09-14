package com.emard.api.repository;

import com.emard.api.domain.Bp2Com;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bp2Com entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Bp2ComRepository extends JpaRepository<Bp2Com, Long> {
}
