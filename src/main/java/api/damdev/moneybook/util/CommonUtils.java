package api.damdev.moneybook.util;

import api.damdev.moneybook.common.type.MoneyType;
import api.damdev.moneybook.domain.History;
import api.damdev.moneybook.dto.MoneyInfo;
import java.time.LocalDateTime;
import org.springframework.validation.Errors;

public class CommonUtils {

  public static void dateDiff(LocalDateTime startDate, LocalDateTime endDate, Errors errors) {

    if (!endDate.isAfter(startDate)) {
      errors.rejectValue("endDate", "wrongData", "endDate가 startDate 이후 날짜가 아님");
    }
  }

  public static void checkTotalMoney(MoneyInfo moneyInfo, History history, Errors errors) {
    if (moneyInfo.getMoneyType().equals(MoneyType.SPENDING) && moneyInfo.getChangeMoney() > history
      .getTotalMoney()) {
      errors.rejectValue("changeMoney", "wrongvalue", "잔액 부족");
    }
  }
}
