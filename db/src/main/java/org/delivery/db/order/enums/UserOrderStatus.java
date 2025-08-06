package org.delivery.db.order.enums;

public enum UserOrderStatus {

  REGISTERED("등록"),
  UNREGISTERED("해지"),
  ORDER("주문"),
  ACCEPT("주문확인"),
  COOKING("조리시작"),
  DELIVERY("배달시작"),
  RECEIVE("배달완료"),
  ;

  private String description;

  UserOrderStatus (String description) {
    this.description = description;
  }

}
