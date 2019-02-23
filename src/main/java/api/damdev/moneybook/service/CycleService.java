package api.damdev.moneybook.service;

import api.damdev.moneybook.domain.Cycle;
import api.damdev.moneybook.dto.cycle.CycleInfo;
import api.damdev.moneybook.dto.cycle.CycleParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CycleService {
    Cycle insertCycle(CycleInfo addInfo);

    Cycle updateCycle(CycleInfo addInfo, String id);

    Cycle deleteCycle(String id);

    List<Cycle> findAllCycle();

    Page<Cycle> findPageCycle(CycleParam param, Pageable pageable);
}
