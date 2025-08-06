package org.delivery.db.order.enums;

public enum UserOrderStatus {

  REGISTERED("등록"),
  UNREGISTERED("해지"),
  ;

  private String description;

  UserOrderStatus (String description) {
    this.description = description;
  }

}
