package api.damdev.moneybook.controller;

import api.damdev.moneybook.dto.CycleInfo;
import api.damdev.moneybook.dto.CycleParam;
import api.damdev.moneybook.service.CycleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/moneybook/cycle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CycleController {

    @Autowired
    CycleService cycleService;

    @PostMapping
    public ResponseEntity addCycleData(@RequestBody CycleInfo info) {
        cycleService.addCycle(info);

        return ResponseEntity.ok().build();
    }
}
