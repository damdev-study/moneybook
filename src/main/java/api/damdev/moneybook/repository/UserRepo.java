package api.damdev.moneybook.repository;

import api.damdev.moneybook.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserInfo, String> {

}
