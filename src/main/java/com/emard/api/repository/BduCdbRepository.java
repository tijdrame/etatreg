package com.emard.api.repository;

import com.emard.api.domain.BduCdb;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BduCdb entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BduCdbRepository extends JpaRepository<BduCdb, Long> {
}
