package com.emard.api.repository;

import com.emard.api.domain.Bp2Infos;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bp2Infos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Bp2InfosRepository extends JpaRepository<Bp2Infos, Long> {
}
