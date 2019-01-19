package api.damdev.moneybook.controller;

import api.damdev.moneybook.common.type.ActiveType;
import api.damdev.moneybook.common.type.CycleType;
import api.damdev.moneybook.common.type.DayOfWeek;
import api.damdev.moneybook.common.type.MoneyType;
import api.damdev.moneybook.domain.Cycle;
import api.damdev.moneybook.dto.cycle.CycleInfo;
import api.damdev.moneybook.repository.CycleRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CycleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Resource
    CycleRepo cycleRepo;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void addCycleData() throws Exception {
        CycleInfo cycleInfo = CycleInfo.builder()
                .cycleName("First Cycle")
                .moneyType(MoneyType.INCOME)
                .cycleStartDate(LocalDateTime.now())
                .cycleEndDate(LocalDateTime.now())
                .cycleYear(0)
                .cycleMonth(3)
                .cycleDate(0)
                .cycleDayOfWeek(DayOfWeek.SAT)
                .cycleType(CycleType.FIXED)
                .active(ActiveType.ACITVE)
                .build();

        mockMvc.perform(post("/api/moneybook/cycle")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(cycleInfo))
        )
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    public void modifyCycle() throws Exception {
        CycleInfo cycleInfo = CycleInfo.builder()
                .cycleName("Second Cycle")
                .moneyType(MoneyType.INCOME)
                .cycleStartDate(LocalDateTime.now())
                .cycleEndDate(LocalDateTime.now())
                .cycleYear(0)
                .cycleMonth(3)
                .cycleDate(0)
                .cycleDayOfWeek(DayOfWeek.SAT)
                .cycleType(CycleType.FIXED)
                .active(ActiveType.ACITVE)
                .build();
        Cycle cycle = new Cycle(cycleInfo);

        Cycle result = cycleRepo.save(cycle);

        mockMvc.perform(put("/api/moneybook/cycle/"+result.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(cycleInfo))
        )
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    public void viewListCycle() throws Exception {
        mockMvc.perform(put("/api/moneybook/cycle/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
        .andDo(print())
        .andExpect(status().isOk());
    }
}