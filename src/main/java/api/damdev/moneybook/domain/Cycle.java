package api.damdev.moneybook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="TCYCLE")
public class Cycle {

    @Id
    @Generated
    private int id;
    private String cycleName;

    @CreationTimestamp
    private LocalDateTime regDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    private LocalDateTime cycleStartDate;
    private LocalDateTime cycleEndDate;

    private int cycleYear;
    private int cycleMonth;
    private int cycleDate;
    private int cycleDayOfWeek;

}
