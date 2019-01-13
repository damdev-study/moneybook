package api.damdev.moneybook.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CycleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void addCycleData() throws Exception {
        mockMvc.perform(post("/api/moneybook/cycle"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}