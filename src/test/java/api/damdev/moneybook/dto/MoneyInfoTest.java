package api.damdev.moneybook.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Author : zenic
 * Created : 2019-01-13
 */
public class MoneyInfoTest {

  @Test
  public void builder() {
    MoneyInfo history = MoneyInfo.builder()
      .money("10000")
      .build();

    assertThat(history.getUserSeqId()).isNull();
    assertThat(history.getMoney()).isNotNull();
  }

  @Test
  public void javaBean() {
    MoneyInfo history = new MoneyInfo();
    history.setMoney("10000");

    assertThat(history.getUserSeqId()).isNull();
    assertThat(history.getMoney()).isNotNull();
  }
}