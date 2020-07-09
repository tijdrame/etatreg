package com.emard.api.repository;

import com.emard.api.domain.Bp1Infos;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bp2Infos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Bp1InfosRepository extends JpaRepository<Bp1Infos, Long> {
}
