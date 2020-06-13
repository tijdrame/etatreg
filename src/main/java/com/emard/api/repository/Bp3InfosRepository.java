package com.emard.api.repository;

import com.emard.api.domain.Bp3Infos;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bp3Infos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Bp3InfosRepository extends JpaRepository<Bp3Infos, Long> {
}
