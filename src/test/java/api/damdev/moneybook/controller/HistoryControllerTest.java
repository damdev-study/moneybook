package api.damdev.moneybook.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import api.damdev.moneybook.common.type.MoneyType;
import api.damdev.moneybook.domain.History;
import api.damdev.moneybook.dto.MoneyInfo;
import api.damdev.moneybook.repository.MoneyRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Author : zenic
 * Created : 2019-01-13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HistoryControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  MoneyRepo moneyRepo;

  @Test
  public void regHistory() throws Exception {
    MoneyInfo moneyInfo = MoneyInfo.builder()
      .userSeqId("1")
      .moneyType(MoneyType.INCOME)
      .money(10000)
      .category("커피")
      .build();

    mockMvc.perform(post("/api/moneybook/history")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .accept(MediaTypes.HAL_JSON)
      .content(objectMapper.writeValueAsString(moneyInfo)))
      .andDo(print())
      .andExpect(status().isCreated())
      .andExpect(jsonPath("id").exists())
      .andExpect(jsonPath("_links.self").exists());
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

  @Test
  public void getHistory() throws Exception {
    History getHistory = generateHistory(10);

    mockMvc.perform(get("/api/moneybook/history/{id}", getHistory.getId()))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("id").exists());
  }

  @Test
  public void getHistory404() throws Exception {
    mockMvc.perform(get("/api/moneybook/history/12345"))
      .andDo(print())
      .andExpect(status().isNotFound());
  }

  @Test
  public void updateHistory() throws Exception {
    // Given
    History getHistory = generateHistory(100);
    MoneyInfo moneyInfo = MoneyInfo.builder()
      .money(1000)
      .moneyType(MoneyType.INCOME)
      .category("카테고리")
      .build();

    // When & Then
    mockMvc.perform(put("/api/moneybook/history/{id}", getHistory.getId())
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(objectMapper.writeValueAsString(moneyInfo))
    )
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("id").exists())
      .andExpect(jsonPath("money").exists())
      .andExpect(jsonPath("moneyType").exists())
      .andExpect(jsonPath("category").exists());
  }

  @Test
  public void updateHistory404() throws Exception {
    MoneyInfo moneyInfo = MoneyInfo.builder()
      .money(1000)
      .moneyType(MoneyType.INCOME)
      .category("카테고리")
      .build();

    mockMvc.perform(put("/api/moneybook/history/12345")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(objectMapper.writeValueAsString(moneyInfo))
    )
      .andDo(print())
      .andExpect(status().isNotFound());
  }

  @Test
  public void updateHistory_Bad_Request_Empty_Input() throws Exception {
    // Given
    History getHistory = generateHistory(100);
    MoneyInfo moneyInfo = new MoneyInfo();

    // When & Then
    mockMvc.perform(put("/api/moneybook/history/{id}", getHistory.getId())
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(objectMapper.writeValueAsString(moneyInfo))
    )
      .andDo(print())
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$[0].field").exists())
      .andExpect(jsonPath("$[0].objectName").exists())
      .andExpect(jsonPath("$[0].code").exists())
      .andExpect(jsonPath("$[0].defaultMessage").exists());
  }

  @Test
  public void updateHistory_Bad_Request_Wrong_Input() throws Exception {
    // Given
    History getHistory = generateHistory(100);
    MoneyInfo moneyInfo = MoneyInfo.builder()
      .money(-1000)
      .moneyType(MoneyType.INCOME)
      .category("카테고리")
      .build();

    // When & Then
    mockMvc.perform(put("/api/moneybook/history/{id}", getHistory.getId())
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(objectMapper.writeValueAsString(moneyInfo))
    )
      .andDo(print())
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$[0].field").exists())
      .andExpect(jsonPath("$[0].objectName").exists())
      .andExpect(jsonPath("$[0].code").exists())
      .andExpect(jsonPath("$[0].defaultMessage").exists());
  }

  @Test
  public void deleteHistory() throws Exception {
    History history = generateHistory(200);

    mockMvc.perform(delete("/api/moneybook/history/{id}", history.getId()))
      .andDo(print())
      .andExpect(status().isOk());
  }

  @Test
  public void deleteHistory_Bad_Request_Not_Found() throws Exception {
    mockMvc.perform(delete("/api/moneybook/history/12345"))
      .andDo(print())
      .andExpect(status().isNotFound());
  }

  private History generateHistory(int index) {
    History history = History.builder()
      .money(index + 1000)
      .category("카테고리")
      .moneyType(index % 2 == 0 ? MoneyType.INCOME : MoneyType.SPENDING)
      .build();
    return moneyRepo.save(history);
  }
}