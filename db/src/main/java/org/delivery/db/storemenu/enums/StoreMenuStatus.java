package org.delivery.db.storemenu.enums;

public enum StoreMenuStatus {
  REGISTERED("등록"),
  UNREGISTERED("해지"),
  ;

  private String description;

  StoreMenuStatus (String description) {
    this.description = description;
  }
}
