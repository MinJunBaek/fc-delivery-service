package org.delivery.db.storeuser.enums;

public enum StoreUserRole {
  MASTER("마스터"),
  ADMIN("관리자"),
  USER("일반유저")
  ;

  private String description;

  StoreUserRole(String description) {
    this.description = description;
  }
}
