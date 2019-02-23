package api.damdev.moneybook.repository.dsl;

import api.damdev.moneybook.domain.Cycle;
import api.damdev.moneybook.dto.cycle.CycleParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CycleRepoCustom {
    Page<Cycle> getUserList(CycleParam cycleParam, Pageable pageable);
}
