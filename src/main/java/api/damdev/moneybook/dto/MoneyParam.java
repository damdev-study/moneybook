package api.damdev.moneybook.dto;

import java.time.LocalDateTime;

import api.damdev.moneybook.common.type.MoneyType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/**
 * 내역 조회를 위한
 * 
 * @author seonyoung
 */
public class MoneyParam {

	// *필수 : 사용자 id
	private String userSeqId;
	
    private MoneyType moneyType;
    
    private String category;
    
    private LocalDateTime startDate;
    
    private LocalDateTime endDate;
    
}
