package com.emard.api.repository;

import com.emard.api.domain.Bp2Com;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bp2Com entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Bp2ComRepository extends JpaRepository<Bp2Com, Long> {
    @Query("select od from Bp2Com od where od.dev = ?1")
    List<Bp2Com> findByDev (String devise);
    @Query("select sum(od.sde) from Bp2Com od where od.cha like ?1% and dev = ?2")
    Double findLikeCha (String cha, String devise);
    @Query("select sum(od.sde) from Bp2Com od where od.cha like ?1% and od.sde < 0 and od.dev = ?2")
    Double findLikeChaAndSdeNeg (String cha, String devise);
    @Query("select sum(od.sde) from Bp2Com od where od.cha like ?1% and od.sde > 0 and od.dev = ?2")
    Double findLikeChaAndSdePos (String cha, String devise);
    @Query("select sum(od.sde) from Bp2Com od where od.cha like ?1% and od.dev = ?3 or od.cha like ?2% and od.dev = ?3")
    Double findLikeCha1OrCha2 (String cha1, String cha2, String devise);
    @Query("select sum(od.sde) from Bp2Com od where od.cha like ?1% and od.sde < 0 and od.dev = ?3 or od.cha like ?2% and od.sde < 0 and od.dev = ?3")
    Double findLikeCha1OrCha2AndSdeNeg (String cha1, String cha2, String devise);
    @Query("select sum(od.sde) from Bp2Com od where substr(od.cha,1,4) IN (:chapitres) and od.dev = :devise")     
    Double findByChapitres(@Param("chapitres")List<String> chapitres, @Param("devise")String devise);
    @Query("select sum(od.sde) from Bp2Com od where substr(od.cha,1,4) IN (:chapitres) and od.dev = :devise and od.sde<0")     
    Double findByChapitresAndSdeNeg(@Param("chapitres")List<String> chapitres, @Param("devise")String devise);
    @Query("select sum(od.sde) from Bp2Com od where substr(od.cha,1,4) IN (:chapitres) and od.dev = :devise and od.sde>0")     
    Double findByChapitresAndSdePos(@Param("chapitres")List<String> chapitres, @Param("devise")String devise);
    @Query("select sum(od.sde) from Bp2Com od where substr(od.cha,1,4) IN (:chapitres4) and od.dev = :devise and od.sde<0 or substr(od.cha,1,5) IN (:chapitres5) and od.dev = :devise and od.sde<0")     
    Double findByChapitres4And5AndSdeNeg(@Param("chapitres4")List<String> chapitres4, @Param("chapitres5")List<String> chapitres5, @Param("devise")String devise);
    @Query("select sum(od.sde) from Bp2Com od where substr(od.cha,1,4) IN (:chapitres4) and od.dev = :devise and od.sde>0 or substr(od.cha,1,5) IN (:chapitres5) and od.dev = :devise and od.sde>0")     
    Double findByChapitres4And5AndSdePos(@Param("chapitres4")List<String> chapitres4, @Param("chapitres5")List<String> chapitres5, @Param("devise")String devise);
    @Modifying
    @Query("delete Bp2Com where nom_fic like :nomFic")     
    public void viderBp2Com(@Param("nomFic") String nomFic);
}
