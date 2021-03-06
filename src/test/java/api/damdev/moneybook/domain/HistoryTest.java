package api.damdev.moneybook.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Author : zenic
 * Created : 2019-01-13
 */
public class HistoryTest {

  @Test
  public void builder() {
    History history = History.builder()
      .changeMoney(10000)
      .build();

    assertThat(history.getId()).isNull();
    assertThat(history.getChangeMoney()).isNotNull();
  }

  @Test
  public void javaBean() {
    History history = new History();
    history.setChangeMoney(10000);

    assertThat(history.getChangeMoney()).isNotNull();
  }
}