package api.damdev.moneybook.dto.cycle;

import api.damdev.moneybook.common.type.ActiveType;
import api.damdev.moneybook.common.type.CycleType;
import api.damdev.moneybook.common.type.DayOfWeek;
import api.damdev.moneybook.common.type.MoneyType;
import api.damdev.moneybook.domain.Cycle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CycleView {
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

    public CycleView(Cycle cycle) {
        this.cycleName = cycle.getCycleName();
        this.moneyType = cycle.getMoneyType();
        this.cycleStartDate = cycle.getCycleStartDate();
        this.cycleEndDate = getCycleEndDate();
        this.cycleYear = getCycleYear();
        this.cycleMonth = getCycleMonth();
        this.cycleDate = getCycleDate();
        this.cycleDayOfWeek = getCycleDayOfWeek();
        this.cycleType = getCycleType();
        this.active = getActive();
    }

}
