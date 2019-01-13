package api.damdev.moneybook.repository;

import api.damdev.moneybook.domain.Cycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CycleRepo extends JpaRepository<Cycle, String> {

}
