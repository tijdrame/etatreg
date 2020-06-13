package com.emard.api.repository;

import com.emard.api.domain.Bp4Infos;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bp4Infos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Bp4InfosRepository extends JpaRepository<Bp4Infos, Long> {
}
