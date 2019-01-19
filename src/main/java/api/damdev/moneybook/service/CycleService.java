package api.damdev.moneybook.service;

import api.damdev.moneybook.domain.Cycle;
import api.damdev.moneybook.dto.cycle.CycleInfo;

import java.util.List;

public interface CycleService {
    Cycle insertCycle(CycleInfo addInfo);

    Cycle updateCycle(CycleInfo addInfo, String id);

    Cycle deleteCycle(String id);

    List<Cycle> findAllCycle();
}
