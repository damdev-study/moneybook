package api.damdev.moneybook.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

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
    
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;
    
}
