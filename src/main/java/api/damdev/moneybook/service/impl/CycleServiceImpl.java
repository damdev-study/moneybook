package api.damdev.moneybook.service.impl;

import api.damdev.moneybook.domain.Cycle;
import api.damdev.moneybook.dto.CycleInfo;
import api.damdev.moneybook.repository.CycleRepo;
import api.damdev.moneybook.service.CycleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("cycleService")
public class CycleServiceImpl implements CycleService {

    @Resource
    CycleRepo cycleRepo;

    @Override
    public void addCycle(CycleInfo addInfo) {
        Cycle entity = new Cycle(addInfo);

        cycleRepo.save(entity);
    }
}
