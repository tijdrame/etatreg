package com.emard.api.repository;

import com.emard.api.domain.Bpsectinst;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bpsectinst entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BpsectinstRepository extends JpaRepository<Bpsectinst, Long> {
}
