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
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        CycleInfo cycleInfo = setCycle("Insert Cycle");

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
        CycleInfo cycleInfo = setCycle("Update Cycle");

        //데이터 추가 필요
        Cycle result = getCycleSave(cycleInfo);

        mockMvc.perform(put("/api/moneybook/cycle/{id}", result.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(cycleInfo))
        )
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    public void removeCycle() throws Exception {
        CycleInfo cycleInfo = setCycle("Delete Cycle");

        Cycle result = getCycleSave(cycleInfo);

        mockMvc.perform(delete("/api/moneybook/cycle/{id}", result.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(cycleInfo))
        )
        .andDo(print())
        .andExpect(status().isOk());
    }


    @Test
    public void viewListCycle() throws Exception {

        for(int i=0;i<10;i++) {
            CycleInfo cycleInfo = setCycle("List Select Cycle " + i);
            getCycleSave(cycleInfo);
        }

        for(int i=0;i<10;i++) {
            CycleInfo cycleInfo = setCycle("Page Select Cycle " + i);
            getCycleSave(cycleInfo);
        }

        mockMvc.perform(get("/api/moneybook/cycle/list")
//                .param("page", "2")
//                .param("size", "2")
                .param("searchType", "2")
                .param("cycleName", "Page")
                .param("moneyType", MoneyType.INCOME.name())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
        .andDo(print())
        .andExpect(status().isOk());
    }

    private CycleInfo setCycle(String cycleName) {
        CycleInfo cycleInfo = CycleInfo.builder()
                .cycleName(cycleName)
                .moneyType((int)(Math.random() * 2) == 1 ? MoneyType.INCOME : MoneyType.SPENDING)
                .cycleStartDate(LocalDateTime.now())
                .cycleEndDate(LocalDateTime.now())
                .cycleYear(0)
                .cycleMonth(3)
                .cycleDate(0)
                .cycleDayOfWeek(DayOfWeek.SAT)
                .cycleType(CycleType.FIXED)
                .active(ActiveType.ACITVE)
                .build();


        return cycleInfo;
    }

    private Cycle getCycleSave(CycleInfo cycleInfo) {
        Cycle cycle = new Cycle(cycleInfo);
        return cycleRepo.save(cycle);
    }

}