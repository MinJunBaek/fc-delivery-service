package org.delivery.db.ordermenu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.delivery.db.BaseEntity;
import org.delivery.db.ordermenu.enums.UserOrderMenuStatus;

@Getter
@Entity(name = "user_order_menus")
@EqualsAndHashCode(callSuper = true)
public class UserOrderMenuEntity extends BaseEntity {

  @Column(nullable = false)
  private Long userOrderId;

  @Column(nullable = false)
  private Long storeMenuId;

  @Column(length = 50, nullable = false)
  @Enumerated(EnumType.STRING)
  private UserOrderMenuStatus status;
}
