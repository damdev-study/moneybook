package api.damdev.moneybook.util;

import org.springframework.validation.Errors;

import java.time.LocalDateTime;

public class CommonUtils {

    public static void dateDiff(LocalDateTime startDate, LocalDateTime endDate, Errors errors) {

        if(!endDate.isAfter(startDate)) {
            errors.rejectValue("endDate", "wrongData", "endDate가 startDate 이후 날짜가 아님");
        }
    }
}
