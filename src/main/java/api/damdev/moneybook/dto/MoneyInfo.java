package api.damdev.moneybook.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import api.damdev.moneybook.common.type.MoneyType;
import lombok.Data;

/**
 * Author : zenic
 * Created : 29/12/2018
 */
@Data
public class MoneyInfo {

  private String userSeqId;

 @Enumerated(EnumType.STRING)
 private MoneyType moneyType;

  private String category;

  private String reservation;

  private String money;
  
}
