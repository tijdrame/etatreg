package com.emard.api.repository;

import com.emard.api.domain.Bp3His;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bp3His entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Bp3HisRepository extends JpaRepository<Bp3His, Long> {
    
    
    @Query("select sum(od.mon) from Bp3His od where od.nat = ?1 and od.dev = ?2 and od.mon>0")     
    public Double findSumMonByDevAndNatAndMonPos(String nat, String devise);
    
    @Query("select sum(od.mon) from Bp3His od where od.nat = ?1 and od.dev = ?2 and od.mon<0")     
    public Double findSumMonByDevAndNatAndMonNeg(String nat, String devise);
}
