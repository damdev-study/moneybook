package api.damdev.moneybook.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
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

import api.damdev.moneybook.dto.MoneyInfo;

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
public class History extends MoneyInfo{

  @Id
  private String id;

  @OneToMany
  private UserInfo user;

//  private String type;
//  private String category;
//  private String reservation;
//  private String money;

  @CreationTimestamp
  private LocalDateTime regDate;

  @UpdateTimestamp
  private LocalDateTime updateDate;
  
  public void setUser() {
	  setUser(user);
	  setUserSeqId(user.getId());
  }
}
