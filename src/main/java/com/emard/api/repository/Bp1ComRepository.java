package com.emard.api.repository;

import com.emard.api.domain.Bp1Com;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bp1Com entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Bp1ComRepository extends JpaRepository<Bp1Com, Long> {
    @Modifying
    @Query("delete Bp1Com where nom_fic like :nomFic")     
    public void viderBp1Com(@Param("nomFic") String nomFic);
    
    @Query("select sum(od.sde) from Bp1Com od where od.sde > 0")     
    public Double findSumSoldeDebutBySoldeDebutPos();
    
   @Query("select sum(od.sde) from Bp1Com od where od.sde < 0")     
    public Double findSumSoldeDebutBySoldeDebutNeg();
    
    @Query("select sum(od.sdeFin) from Bp1Com od where od.sdeFin > 0")     
    public Double findSumSoldeFinBySoldeFinPos();
    
    @Query("select sum(od.sdeFin) from Bp1Com od where od.sdeFin < 0")     
    public Double findSumSoldeFinBySoldeFinNeg();
}
