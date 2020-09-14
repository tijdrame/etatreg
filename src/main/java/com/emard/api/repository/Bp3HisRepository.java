package com.emard.api.repository;

import com.emard.api.domain.Bp3His;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bp3His entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Bp3HisRepository extends JpaRepository<Bp3His, Long> {
}
