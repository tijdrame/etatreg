package com.emard.api.repository;

import com.emard.api.domain.FilesInfos;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FilesInfos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FilesInfosRepository extends JpaRepository<FilesInfos, Long> {
}
