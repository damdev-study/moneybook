package api.damdev.moneybook.dto.cycle;

import api.damdev.moneybook.common.type.ActiveType;
import api.damdev.moneybook.common.type.CycleType;
import api.damdev.moneybook.common.type.DayOfWeek;
import api.damdev.moneybook.common.type.MoneyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CycleInfo {
    @NotNull
    private String cycleName;
    @NotNull
    private MoneyType moneyType;
    @NotNull
    private Integer changeMoney;
//    @NotNull
    private String userSeqId;
    private LocalDateTime cycleStartDate;
    private LocalDateTime cycleEndDate;
    private int cycleYear;
    private int cycleMonth;
    private int cycleDate;
    private DayOfWeek cycleDayOfWeek;
    @NotNull
    private CycleType cycleType;
    @NotNull
    private ActiveType active;
}
