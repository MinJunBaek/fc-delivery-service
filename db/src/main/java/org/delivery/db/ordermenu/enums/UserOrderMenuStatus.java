package org.delivery.db.ordermenu.enums;

public enum UserOrderMenuStatus {

  REGISTERED("등록"),
  UNREGISTERED("해지"),
  ;

  private String description;

  UserOrderMenuStatus(String description) {
    this.description = description;
  }
}
