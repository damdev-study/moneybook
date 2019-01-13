package api.damdev.moneybook.repository;

import api.damdev.moneybook.domain.History;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Author : zenic
 * Created : 24/12/2018
 */
public interface MoneyRepo extends MongoRepository<History, String> {

}
