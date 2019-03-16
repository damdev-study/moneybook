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

    @Autowired
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
    public void addCycleDataBadRequest() throws Exception {
        CycleInfo cycleInfo = new CycleInfo();

        mockMvc.perform(post("/api/moneybook/cycle")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(cycleInfo))
        )
        .andDo(print())
        .andExpect(status().isBadRequest());
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
    public void modifyCycleBadRequest() throws Exception {
        CycleInfo cycleInfo = new CycleInfo();

        mockMvc.perform(put("/api/moneybook/cycle/{id}", "nothing")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(cycleInfo))
        )
        .andDo(print())
        .andExpect(status().isBadRequest());
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
    public void removeCycleBadRequest() throws Exception {
        CycleInfo cycleInfo = setCycle("Delete Cycle");

        mockMvc.perform(delete("/api/moneybook/cycle/{id}", "nothing")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(cycleInfo))
        )
        .andDo(print())
        .andExpect(status().isBadRequest());
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
                .param("cycleName", "1")
                .param("moneyType", MoneyType.INCOME.name())
                .param("cycleType", CycleType.UNIT.name())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    public void viewListCycleBadRequest() throws Exception {

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
                .param("cycleName", "Unit") // 존재하지 않는 값
                .param("moneyType", "NOTHING") // 존재하지 않는 값
                .param("cycleType", "NOTHING") // 존재하지 않는 값
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
        .andDo(print())
        .andExpect(status().isBadRequest());
    }

    @Test
    public void viewCycleDetail() throws Exception {
        CycleInfo cycleInfo = setCycle("Select Cycle");

        Cycle result = getCycleSave(cycleInfo);

        mockMvc.perform(get("/api/moneybook/cycle/{id}", result.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(cycleInfo))
        )
        .andDo(print())
        .andExpect(status().isOk());

    }

    @Test
    public void viewCycleDetailBadRequest() throws Exception {
        CycleInfo cycleInfo = setCycle("Select Cycle");

        mockMvc.perform(get("/api/moneybook/cycle/{id}", "nothing")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(cycleInfo))
        )
        .andDo(print())
        .andExpect(status().isBadRequest());

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
                .cycleType((int)(Math.random() * 2) == 1 ? CycleType.FIXED : CycleType.UNIT)
                .active(ActiveType.ACITVE)
                .build();


        return cycleInfo;
    }

    private Cycle getCycleSave(CycleInfo cycleInfo) {
        Cycle cycle = new Cycle(cycleInfo);
        return cycleRepo.save(cycle);
    }

}