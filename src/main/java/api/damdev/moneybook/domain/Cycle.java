package api.damdev.moneybook.domain;

import api.damdev.moneybook.common.type.ActiveType;
import api.damdev.moneybook.common.type.CycleType;
import api.damdev.moneybook.common.type.DayOfWeek;
import api.damdev.moneybook.common.type.MoneyType;
import api.damdev.moneybook.dto.cycle.CycleInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "TCYCLE")
public class Cycle {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String cycleName;

    @ManyToOne
    private UserInfo user;

    private MoneyType moneyType;

    @CreationTimestamp
    private LocalDateTime regDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    private LocalDateTime cycleStartDate;
    private LocalDateTime cycleEndDate;

    private int cycleYear;
    private int cycleMonth;
    private int cycleDate;

    @Enumerated(EnumType.STRING)
    private DayOfWeek cycleDayOfWeek;

    @Enumerated(EnumType.STRING)
    private CycleType cycleType;

    @Enumerated(EnumType.STRING)
    private ActiveType active = ActiveType.ACITVE;

    public Cycle(CycleInfo addInfo) {
       this.cycleName = addInfo.getCycleName();
       this.moneyType = addInfo.getMoneyType();
       this.cycleStartDate = addInfo.getCycleStartDate();
       this.cycleEndDate = addInfo.getCycleEndDate();
       this.cycleYear = addInfo.getCycleYear();
       this.cycleMonth = addInfo.getCycleMonth();
       this.cycleDate = addInfo.getCycleDate();
       this.cycleDayOfWeek = addInfo.getCycleDayOfWeek();
       this.cycleType = addInfo.getCycleType();
       this.active = addInfo.getActive();
    }

    public Cycle(CycleInfo addInfo, String id) {
        this(addInfo);
        this.id = id;
    }

}
