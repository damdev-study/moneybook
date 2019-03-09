package api.damdev.moneybook.dto.cycle;

import api.damdev.moneybook.common.type.CycleType;
import api.damdev.moneybook.common.type.DayOfWeek;
import api.damdev.moneybook.common.type.MoneyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CycleParam {
    private int searchType;
    private String cycleName;
    private MoneyType moneyType;
    private LocalDateTime cycleStartDateStart;
    private LocalDateTime cycleStartDateEnd;
    private LocalDateTime cycleEndDateStart;
    private LocalDateTime cycleEndDateEnd;
    private CycleType cycleType;
}
