package org.delivery.db.store.enums;

public enum StoreStatus {
  REGISTERED("등록"),
  UNREGISTERED("해지"),
  ;

  private String description;

  StoreStatus(String description) {}
}
