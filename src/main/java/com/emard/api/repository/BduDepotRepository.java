package com.emard.api.repository;

import com.emard.api.domain.BduDepot;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BduDepot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BduDepotRepository extends JpaRepository<BduDepot, Long> {
}
