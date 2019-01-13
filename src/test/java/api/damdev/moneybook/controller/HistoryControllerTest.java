package api.damdev.moneybook.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import api.damdev.moneybook.common.type.MoneyType;
import api.damdev.moneybook.domain.History;
import api.damdev.moneybook.dto.MoneyInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Author : zenic
 * Created : 2019-01-13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HistoryControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  public void regHistory() throws Exception {
    MoneyInfo moneyInfo = MoneyInfo.builder()
      .userSeqId("1")
      .moneyType(MoneyType.INCOME)
      .money("10000")
      .category("커피")
      .build();

    mockMvc.perform(post("/api/moneybook/history")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .accept(MediaTypes.HAL_JSON)
      .content(objectMapper.writeValueAsString(moneyInfo)))
      .andDo(print())
      .andExpect(status().isCreated());
  }

  @Test
  public void regHistory_Bad_Request_Empty_Input() throws Exception {
    MoneyInfo moneyInfo = new MoneyInfo();

    mockMvc.perform(post("/api/moneybook/history")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .accept(MediaTypes.HAL_JSON)
      .content(objectMapper.writeValueAsString(moneyInfo)))
      .andDo(print())
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$[0].field").exists())
      .andExpect(jsonPath("$[0].objectName").exists())
      .andExpect(jsonPath("$[0].code").exists())
      .andExpect(jsonPath("$[0].defaultMessage").exists());
  }

  @Test
  public void regHistory_Bad_Request_Wrong_Input() throws Exception {
    History history = History.builder()
      .id("1234")
      .build();

    mockMvc.perform(post("/api/moneybook/history")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .accept(MediaTypes.HAL_JSON)
      .content(objectMapper.writeValueAsString(history)))
      .andDo(print())
      .andExpect(status().isBadRequest());
  }
}