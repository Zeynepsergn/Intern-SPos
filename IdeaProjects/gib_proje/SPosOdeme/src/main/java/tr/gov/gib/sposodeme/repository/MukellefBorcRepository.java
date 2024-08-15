package tr.gov.gib.sposodeme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.gov.gib.sposodeme.entity.MukellefBorc;

import java.util.List;

public interface MukellefBorcRepository extends JpaRepository<MukellefBorc, Integer> {
    List<MukellefBorc> findByMukellefKullaniciId(Integer mukellefKullaniciId);
}
