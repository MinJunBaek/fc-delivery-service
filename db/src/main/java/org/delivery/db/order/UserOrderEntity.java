package org.delivery.db.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.delivery.db.BaseEntity;
import org.delivery.db.order.enums.UserOrderStatus;
import org.delivery.db.storemenu.StoreMenuEntity;

@Getter
@Entity(name = "user_orders")
@EqualsAndHashCode(callSuper = true)
public class UserOrderEntity extends BaseEntity {
  @Column(nullable = false)
  private Long userId;

  @Enumerated(EnumType.STRING)
  @Column(length = 50, nullable = false)
  private UserOrderStatus status;

  @Column(precision = 11, scale = 4, nullable = false)
  private BigDecimal amount;

  private LocalDateTime orderedAt;

  private LocalDateTime acceptedAt;

  private LocalDateTime cookingStartedAt;

  private LocalDateTime deliveryStartedAt;

  private LocalDateTime receivedAt;

  public void setStatus(UserOrderStatus status) {
    switch(status) {
      case ORDER -> {this.status = status; this.orderedAt = LocalDateTime.now();}
      case ACCEPT -> {this.status = status; this.acceptedAt = LocalDateTime.now();}
      case COOKING -> {this.status = status; this.cookingStartedAt = LocalDateTime.now();}
      case DELIVERY -> {this.status = status; this.deliveryStartedAt = LocalDateTime.now();}
      case RECEIVE -> {this.status = status; this.receivedAt = LocalDateTime.now();}
    }
  }

  public static UserOrderEntity of(Long userId, BigDecimal amount) {
    UserOrderEntity entity = new UserOrderEntity();
    entity.userId = userId;
    entity.amount = amount;
    return entity;
  }
}
