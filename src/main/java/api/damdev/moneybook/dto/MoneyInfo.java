package api.damdev.moneybook.dto;

import api.damdev.moneybook.domain.History;
import lombok.Data;

/**
 * Author : zenic
 * Created : 29/12/2018
 */
@Data
public class MoneyInfo {

  private String userSeqId;

  private String type;

  private String category;

  private String reservation;

  private String money;
  
  public MoneyInfo() {
	  
  }
  
  public MoneyInfo(History history) {
	  userSeqId = history.getUser().getId();
	  type = history.getType();
	  
	  
	  
	  
  }
}
