package api.damdev.moneybook.controller;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;

import api.damdev.moneybook.common.RestDocsConfiguration;
import api.damdev.moneybook.common.type.MoneyType;
import api.damdev.moneybook.domain.History;
import api.damdev.moneybook.dto.MoneyInfo;
import api.damdev.moneybook.repository.MoneyRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
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
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
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
      .changeMoney(10000)
      .category("용돈")
      .build();

    mockMvc.perform(post("/api/moneybook/history")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .accept(MediaTypes.HAL_JSON)
      .content(objectMapper.writeValueAsString(moneyInfo)))
      .andDo(print())
      .andExpect(status().isCreated())
      .andExpect(jsonPath("id").exists())
      .andExpect(jsonPath("_links.self").exists())
      .andDo(document(
        "reg-history",
        links(
          linkWithRel("self").description("link to self"),
          linkWithRel("query-history").description("link to query events"),
          linkWithRel("profile").description("link to profile")
        ),
        requestHeaders(
          headerWithName(HttpHeaders.ACCEPT).description(MediaTypes.HAL_JSON),
          headerWithName(HttpHeaders.CONTENT_TYPE).description(MediaType.APPLICATION_JSON_UTF8)
        ),
        requestFields(
          fieldWithPath("userSeqId").description("유저 시퀀스 ID"),
          fieldWithPath("moneyType").description("입력된 내역의 타입"),
          fieldWithPath("category").description("카테고리"),
          fieldWithPath("changeMoney").description("금액")
        ),
        responseHeaders(
          headerWithName(HttpHeaders.LOCATION).description("location header"),
          headerWithName(HttpHeaders.CONTENT_TYPE).description(MediaTypes.HAL_JSON_UTF8_VALUE)
        ),
        responseFields(
          fieldWithPath("id").description("등록된 내역의 ID"),
          fieldWithPath("userSeqId").description("유저 시퀀스 ID"),
          fieldWithPath("moneyType").description("입력된 내역의 타입"),
          fieldWithPath("category").description("카테고리"),
          fieldWithPath("changeMoney").description("금액"),
          fieldWithPath("activeType").description("내역의 상태"),
          fieldWithPath("totalMoney").description("현재 금액"),
          fieldWithPath("regDate").description("등록일"),
          fieldWithPath("updateDate").description("수정일"),
          fieldWithPath("_links.self.href").description("자기 자신"),
          fieldWithPath("_links.query-history.href").description("등록된 내역 조회 API"),
          fieldWithPath("_links.profile.href").description("프로필")
        )
      ));
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
      .changeMoney(1000)
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
      .andExpect(jsonPath("changeMoney").exists())
      .andExpect(jsonPath("moneyType").exists())
      .andExpect(jsonPath("category").exists());
  }

  @Test
  public void updateHistory404() throws Exception {
    MoneyInfo moneyInfo = MoneyInfo.builder()
      .changeMoney(1000)
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
      .changeMoney(-1000)
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
      .changeMoney(index + 1000)
      .category("카테고리")
      .moneyType(index % 2 == 0 ? MoneyType.INCOME : MoneyType.SPENDING)
      .build();
    return moneyRepo.save(history);
  }

  @Test
  public void findSearcsh() throws Exception {

    for ( int i=0; i<20;i++) {
    	History moneyInfo = new History();
//    moneyInfo.setUserSeqId("2");
    	moneyInfo.setMoneyType(MoneyType.INCOME);
    	moneyInfo.setChangeMoney(new Random().nextInt(10000));
    	moneyInfo.setCategory("커피"+moneyInfo.getChangeMoney());
    	moneyRepo.save(moneyInfo);
    }

    mockMvc.perform(
      get("/api/moneybook/history/list")
        .param("category", "커피1")
        .param("sort", "category,desc")
        .accept(MediaTypes.HAL_JSON)
    )
    .andDo(print())
    .andExpect(status().isOk())
    .andDo(document(
        "historyList", 
        requestHeaders(
          headerWithName(HttpHeaders.ACCEPT).description(MediaTypes.HAL_JSON)
        ),
        requestParameters(
          parameterWithName("userSeqId").description("유저 시퀀스 ID"),
          parameterWithName("moneyType").description("입력된 내역의 타입"),
          parameterWithName("category").description("카테고리"),
          parameterWithName("startDate").description("조회 시작 일자"),
          parameterWithName("endDate").description("조회 종료 일자")
        ),
        responseHeaders(
          headerWithName(HttpHeaders.LOCATION).description("location header"),
          headerWithName(HttpHeaders.CONTENT_TYPE).description(MediaTypes.HAL_JSON_UTF8_VALUE)
        ),
        responseFields(
          fieldWithPath("id").description("등록된 내역의 ID"),
          fieldWithPath("userSeqId").description("유저 시퀀스 ID"),
          fieldWithPath("moneyType").description("입력된 내역의 타입"),
          fieldWithPath("category").description("카테고리"),
          fieldWithPath("activeType").description("내역의 상태"),
          fieldWithPath("regDate").description("등록일"),
          fieldWithPath("updateDate").description("수정일"),
          fieldWithPath("changeMoney").description("금액"),
          fieldWithPath("totalMoney").description("잔액")/*,
          fieldWithPath("_links.self.href").description("자기 자신"),
          fieldWithPath("_links.query-history.href").description("등록된 내역 조회 API"),
          fieldWithPath("_links.profile.href").description("프로필")*/
        )))
    ;
    
  }
}