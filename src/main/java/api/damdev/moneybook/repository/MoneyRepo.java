package api.damdev.moneybook.repository;

import api.damdev.moneybook.domain.History;
import api.damdev.moneybook.repository.dsl.MoneyRepoDsl;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : zenic
 * Created : 24/12/2018
 */
public interface MoneyRepo extends JpaRepository<History, String>, MoneyRepoDsl {

}
