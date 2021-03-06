package api.damdev.moneybook.service.impl;

import api.damdev.moneybook.common.type.ActiveType;
import api.damdev.moneybook.domain.Cycle;
import api.damdev.moneybook.dto.cycle.CycleInfo;
import api.damdev.moneybook.dto.cycle.CycleParam;
import api.damdev.moneybook.repository.CycleRepo;
import api.damdev.moneybook.service.CycleService;
import com.google.common.base.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cycleService")
public class CycleServiceImpl implements CycleService {

    @Resource
    CycleRepo cycleRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Cycle insertCycle(CycleInfo addInfo) {
//        Cycle entity = new Cycle(addInfo);

        Cycle entity = modelMapper.map(addInfo, Cycle.class);

        return cycleRepo.save(entity);
    }

    @Override
    public Cycle updateCycle(CycleInfo addInfo, String id) {
        Cycle entity = modelMapper.map(addInfo, Cycle.class);
        entity.setId(id);

        return cycleRepo.save(entity);
    }

    @Override
    public Cycle deleteCycle(String id) {
        Cycle entity = cycleRepo.findById(id).orElse(new Cycle());

        if(Strings.isNullOrEmpty(entity.getId())) {
            return entity;
        }

        entity.setActive(ActiveType.INACTIVE);

        return cycleRepo.save(entity);
    }

    @Override
    public List<Cycle> findAllCycle() {
        List<Cycle> list = cycleRepo.findAll();

        return list;
    }

    @Override
    public Page<Cycle> findPageCycle(CycleParam cycleParam, Pageable pageable) {
        Page<Cycle> page = cycleRepo.getUserList(cycleParam, pageable);

        return page;
    }

    @Override
    public Cycle findById(String id) {
        Cycle cycle = cycleRepo.findById(id).orElse(new Cycle());

        return cycle;
    }
}
