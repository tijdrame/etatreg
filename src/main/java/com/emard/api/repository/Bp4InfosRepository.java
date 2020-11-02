package com.emard.api.repository;

import com.emard.api.domain.Bp4Infos;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bp4Infos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Bp4InfosRepository extends JpaRepository<Bp4Infos, Long> {
    
    
    @Query("select od.codeIsoPays as codeIsoPays, od.libellePays as libellePays, sum(od.mntnosCartes) as mntnosCartes, sum(od.mntCartesEtr) as mntCartesEtr FROM Bp4Infos od where od.codeIsoPays!='' group by od.codeIsoPays")
    List<Object[]> findListEmissionAcquisition();
    
    @Modifying
    @Query("delete Bp4Infos where nom_fic like :nomFic")     
    public void viderBp4Infos(@Param("nomFic") String nomFic);
}
