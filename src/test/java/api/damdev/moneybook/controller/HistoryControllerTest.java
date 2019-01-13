package api.damdev.moneybook.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Author : zenic
 * Created : 2019-01-13
 */
@SpringBootTest
@AutoConfigureMockMvc
public class HistoryControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  public void regHistory() throws Exception {
    mockMvc.perform(post(""))
      .andDo(print())
      .andExpect(status().isOk());
  }
}