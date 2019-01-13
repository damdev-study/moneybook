package api.damdev.moneybook.dto;

import api.damdev.moneybook.common.type.MoneyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : zenic
 * Created : 29/12/2018
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MoneyInfo {

  private String userSeqId;

  private MoneyType moneyType;

  private String category;

  private String money;
}
