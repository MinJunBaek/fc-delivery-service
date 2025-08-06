package org.delivery.db.user.enums;

public enum UserStatus {

  REGISTERED("등록"),

  UNREGISTERED("해지");

  private String description;

  UserStatus(String description) {
    this.description = description;
  }
}
