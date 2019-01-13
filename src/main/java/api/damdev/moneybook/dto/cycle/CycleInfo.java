package api.damdev.moneybook.dto.cycle;

import api.damdev.moneybook.common.type.ActiveType;
import api.damdev.moneybook.common.type.CycleType;
import api.damdev.moneybook.common.type.DayOfWeek;
import api.damdev.moneybook.common.type.MoneyType;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CycleInfo {

    private String cycleName;
    private MoneyType moneyType;
    private LocalDateTime cycleStartDate;
    private LocalDateTime cycleEndDate;
    private int cycleYear;
    private int cycleMonth;
    private int cycleDate;
    private DayOfWeek cycleDayOfWeek;
    private CycleType cycleType;
    private ActiveType active;
}
