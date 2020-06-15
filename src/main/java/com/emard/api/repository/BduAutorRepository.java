package com.emard.api.repository;

import com.emard.api.domain.BduAutor;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BduAutor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BduAutorRepository extends JpaRepository<BduAutor, Long> {
}
