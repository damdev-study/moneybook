package api.damdev.moneybook.repository;

import api.damdev.moneybook.common.type.MoneyType;
import api.damdev.moneybook.domain.Cycle;
import api.damdev.moneybook.repository.dsl.CycleRepoCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CycleRepo extends JpaRepository<Cycle, String>, CycleRepoCustom {
    Page<Cycle> findByCycleNameContains(String cycleName, Pageable pageable);

    Page<Cycle> findByMoneyType(MoneyType moneyType, Pageable pageable);

}
