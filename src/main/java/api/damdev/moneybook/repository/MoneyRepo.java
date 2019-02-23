package api.damdev.moneybook.repository;

import api.damdev.moneybook.domain.History;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : zenic
 * Created : 24/12/2018
 */
public interface MoneyRepo extends JpaRepository<History, String> {

	Page<History> findByCategory(String category, Pageable page);

}
