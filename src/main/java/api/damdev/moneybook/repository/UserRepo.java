package api.damdev.moneybook.repository;

import api.damdev.moneybook.domain.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserInfo, String> {

}
