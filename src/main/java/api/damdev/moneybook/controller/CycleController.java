package api.damdev.moneybook.controller;

import api.damdev.moneybook.dto.CycleParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/api/moneybook/cycle")
public class CycleController {

    @PostMapping
    public ResponseEntity addCycleData(@RequestBody CycleParam param) {


        return ResponseEntity.ok().build();
    }
}
