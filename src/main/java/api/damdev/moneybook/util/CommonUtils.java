package api.damdev.moneybook.util;

import java.time.LocalDateTime;

public class CommonUtils {

    public static Boolean dateDiff(LocalDateTime startDate, LocalDateTime endDate) {
        return endDate.isAfter(startDate);
    }
}
