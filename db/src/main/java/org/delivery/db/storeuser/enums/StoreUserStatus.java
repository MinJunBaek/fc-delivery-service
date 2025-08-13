package org.delivery.db.storeuser.enums;

public enum StoreUserStatus {
  REGISTERED("등록"),
  UNREGISTERED("해지")
  ;

  private String description;

  StoreUserStatus(String description) {
    this.description = description;
  }
}
