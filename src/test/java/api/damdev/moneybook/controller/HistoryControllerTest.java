package api.damdev.moneybook.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import api.damdev.moneybook.common.type.MoneyType;
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
    MoneyInfo moneyInfo = new MoneyInfo();
    moneyInfo.setUserSeqId("1");
    moneyInfo.setMoneyType(MoneyType.INCOME);
    moneyInfo.setCategory("커피");
    moneyInfo.setMoney("10000");
//      MoneyInfo.builder()
//      .userSeqId("1")
//      .moneyType(MoneyType.INCOME)
//      .money("10000")
//      .category("커피")
//      .build();

    mockMvc.perform(post("/api/moneybook/history")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .accept(MediaTypes.HAL_JSON)
      .content(objectMapper.writeValueAsString(moneyInfo)))
      .andDo(print())
      .andExpect(status().isCreated());
  }
}