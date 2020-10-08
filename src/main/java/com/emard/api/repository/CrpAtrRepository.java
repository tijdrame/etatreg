package com.emard.api.repository;

import com.emard.api.domain.CrpAtr;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CrpAtr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CrpAtrRepository extends JpaRepository<CrpAtr, Long> {
    
    @Query("select od from CrpAtr od where od.cenr =:cenr")     
    List<CrpAtr> findByCenr(@Param("cenr")String cenr);
    
    @Modifying
    @Query("delete CrpAtr where nom_fic like :nomFic")     
    public void viderCrpAtr(@Param("nomFic") String nomFic);
    
}
