package com.emard.api.repository;

import com.emard.api.domain.ChargeFile;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ChargeFile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChargeFileRepository extends JpaRepository<ChargeFile, Long> {
}
