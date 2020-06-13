package com.emard.api.repository;

import com.emard.api.domain.NatureDepot;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NatureDepot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NatureDepotRepository extends JpaRepository<NatureDepot, Long> {
}
