package api.damdev.moneybook.dto;

import api.damdev.moneybook.common.type.MoneyType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : zenic
 * Created : 29/12/2018
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MoneyInfo {

  private String userSeqId;

  @NotNull
  private MoneyType moneyType;

  @NotNull
  private String category;

  @Min(0)
  private int changeMoney;
}
