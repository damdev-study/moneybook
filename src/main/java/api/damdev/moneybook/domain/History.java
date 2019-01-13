package api.damdev.moneybook.domain;

import api.damdev.moneybook.common.MoneyType;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
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
@Data
@Table(name = "THISTORY")
public class History {

  @Id
  private String id;

  @OneToMany
  private UserInfo user;

  @Enumerated(EnumType.STRING)
  private MoneyType moneyType;

  private String category;

  private String reservation;

  private String money;

  @CreationTimestamp
  private LocalDateTime regDate;

  @UpdateTimestamp
  private LocalDateTime updateDate;
}
