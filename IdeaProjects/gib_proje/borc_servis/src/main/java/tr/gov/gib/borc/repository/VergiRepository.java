package tr.gov.gib.borc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tr.gov.gib.borc.entity.Vergi;

import java.util.List;

@Repository
public interface VergiRepository extends JpaRepository<Vergi, Integer> {

    @Query("""
        select v from Vergi v inner join fetch VergiOdemeTur t on v.id=t.vergi.id where v.vergiDurum=1
    """)
    List<Vergi> findAllActiveVergi();
}
