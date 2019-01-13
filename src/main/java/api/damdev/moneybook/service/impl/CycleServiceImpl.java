package api.damdev.moneybook.service.impl;

import api.damdev.moneybook.domain.Cycle;
import api.damdev.moneybook.dto.cycle.CycleInfo;
import api.damdev.moneybook.repository.CycleRepo;
import api.damdev.moneybook.service.CycleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("cycleService")
public class CycleServiceImpl implements CycleService {

    @Resource
    CycleRepo cycleRepo;

    @Override
    public Cycle insertCycle(CycleInfo addInfo) {
        Cycle entity = new Cycle(addInfo);

        return cycleRepo.save(entity);
    }

    @Override
    public Cycle updateCycle(CycleInfo addInfo, String id) {
        Cycle entity = new Cycle(addInfo, id);

        return cycleRepo.save(entity);
    }
}
