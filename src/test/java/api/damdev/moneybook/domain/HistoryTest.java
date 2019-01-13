package api.damdev.moneybook.domain;

import org.junit.Test;

/**
 * Author : zenic
 * Created : 2019-01-13
 */
public class HistoryTest {

  @Test
  public void builder() {
    History history = History.builder()
      .money("")
      .build();
  }
}