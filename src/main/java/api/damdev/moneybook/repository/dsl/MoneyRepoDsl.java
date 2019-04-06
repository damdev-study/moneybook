package api.damdev.moneybook.repository.dsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import api.damdev.moneybook.domain.History;
import api.damdev.moneybook.dto.MoneyParam;

public interface MoneyRepoDsl {
	Page<History> getList(MoneyParam param, Pageable pageable);
}
