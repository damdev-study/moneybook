package api.damdev.moneybook.repository;

import api.damdev.moneybook.domain.Cycle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CycleRepo extends MongoRepository<Cycle, String> {


}
