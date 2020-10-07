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
    @Query("select od from Bp2Com od where od.cha like ?1% and dev = ?2")
    List<Bp2Com> findLikeCha (String cha, String devise);
    @Query("select od from Bp2Com od where od.cha like ?1% and sde < 0 and dev = ?2")
    List<Bp2Com> findLikeChaAndSdeNeg (String cha, String devise);
    @Query("select od from Bp2Com od where od.cha like ?1% and sde > 0 and dev = ?2")
    List<Bp2Com> findLikeChaAndSdePos (String cha, String devise);
    @Query("select od from Bp2Com od where od.cha like ?1% or od.cha like ?2% and dev = ?2")
    List<Bp2Com> findLikeCha1OrCha2 (String cha1, String cha2, String devise);
    @Query("select od from Bp2Com od where od.cha like ?1% or od.cha like ?2% and sde < 0 and dev = ?3")
    List<Bp2Com> findLikeCha1OrCha2AndSdeNeg (String cha1, String cha2, String devise);
    @Query("select od from Bp2Com od where od.cha IN (:chapitres) and dev = :devise")     
    List<Bp2Com> findByChapitres(@Param("chapitres")List<String> chapitres, @Param("devise")String devise);
    @Modifying
    @Query("delete Bp2Com where nom_fic like :nomFic")     
    public void viderBp2Com(@Param("nomFic") String nomFic);
}
