package com.emard.api.repository;

import com.emard.api.domain.Bp1Infos;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bp2Infos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Bp1InfosRepository extends JpaRepository<Bp1Infos, Long> {
    
    @Modifying
    @Query("delete Bp1Infos where 1=1")     
    public void viderBp1infos();
}
