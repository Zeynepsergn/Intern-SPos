package tr.gov.gib.borc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tr.gov.gib.borc.entity.MukellefBorc;

import java.util.List;

@Repository
public interface MukellefBorcRepository extends JpaRepository<MukellefBorc, Integer> {


    @Query("""
        select m from MukellefBorc m 
                 inner join fetch m.vergi v 
                 inner join fetch m.mukellefKullanici k
                 where v.id= :vergiId and k.tcKn= :tckn
    """)
    List<MukellefBorc> getMukkellefBorcByVergiTur(@Param("vergiId") Integer vergiId, @Param("tckn")String tckn);

    @Query("""
        select m from MukellefBorc m 
                 inner join fetch m.vergi v 
                 inner join fetch m.mukellefKullanici k
                 where k.tcKn= :tckn
    """)
    List<MukellefBorc> getMukkellefBorcByVergiTur(@Param("tckn")String tckn);
}
