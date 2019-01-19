package api.damdev.moneybook.controller;

import api.damdev.moneybook.domain.Cycle;
import api.damdev.moneybook.dto.cycle.CycleInfo;
import api.damdev.moneybook.service.CycleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/moneybook/cycle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CycleController {

    @Autowired
    CycleService cycleService;

    @PostMapping
    public ResponseEntity addCycleData(@RequestBody @Valid CycleInfo info, Errors errors) {
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
        Cycle cycle = cycleService.deleteCycle(id);

        return ResponseEntity.ok().build();
    }
}
