package api.damdev.moneybook.domain;

import api.damdev.moneybook.common.type.MoneyType;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Author : zenic
 * Created : 24/12/2018
 */
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "THISTORY")
public class History {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

//  @ManyToOne
//  private UserInfo user;

  private MoneyType moneyType;

  private String category;

  private String money;

  @CreationTimestamp
  private LocalDateTime regDate;

  @UpdateTimestamp
  private LocalDateTime updateDate;
}
