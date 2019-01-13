package api.damdev.moneybook.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

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
