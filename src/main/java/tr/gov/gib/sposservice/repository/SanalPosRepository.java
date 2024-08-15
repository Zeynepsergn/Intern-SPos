package tr.gov.gib.sposservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.gov.gib.sposservice.entity.SanalPos;


@Repository
public interface SanalPosRepository extends JpaRepository<SanalPos, String> {

}
