package api.damdev.moneybook.controller;

import api.damdev.moneybook.domain.Cycle;
import api.damdev.moneybook.dto.cycle.CycleInfo;
import api.damdev.moneybook.dto.cycle.CycleParam;
import api.damdev.moneybook.service.CycleService;
import api.damdev.moneybook.util.CommonUtils;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/moneybook/cycle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CycleController {

    @Autowired
    CycleService cycleService;

    @PostMapping
    public ResponseEntity addCycleData(@RequestBody @Valid CycleInfo info, Errors errors) {
        //날짜 비교
        CommonUtils.dateDiff(info.getCycleStartDate(), info.getCycleEndDate(), errors);

        //날짜 비교 후 errors 값 노출
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        Cycle cycle = cycleService.insertCycle(info);

        return ResponseEntity.ok(cycle);
    }

    @PutMapping("{cycleId}")
    public ResponseEntity modifyCycleData(@RequestBody @Valid CycleInfo info, @PathVariable("cycleId") String id, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        Cycle cycle = cycleService.updateCycle(info, id);

        return ResponseEntity.ok(cycle);
    }

    @DeleteMapping("{cycleId}")
    public ResponseEntity removeCycle(@PathVariable("cycleId") String id) {
        if(Strings.isNullOrEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }

        Cycle cycle = cycleService.deleteCycle(id);

        if(Strings.isNullOrEmpty(cycle.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("list")
    public ResponseEntity viewListCycleData(Pageable pageable, CycleParam cycleParam) {
        if(Strings.isNullOrEmpty(cycleParam.getUserSeqId())) {
            return ResponseEntity.badRequest().build();
        }

        Page<Cycle> page = cycleService.findPageCycle(cycleParam, pageable);

        return ResponseEntity.ok(page);
    }

    @GetMapping("{cycleId}")
    public ResponseEntity viewCycleDetail(@PathVariable("cycleId") String id) {
        Cycle cycle = cycleService.findById(id);

        if(Strings.isNullOrEmpty(cycle.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(cycle);
    }
}
