package api.damdev.moneybook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/api/moneybook/cycle")
public class CycleController {

    @PostMapping
    public ResponseEntity addCycleData() {


        return ResponseEntity.ok().build();
    }
}
