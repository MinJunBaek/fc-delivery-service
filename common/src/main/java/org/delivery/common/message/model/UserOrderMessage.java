package org.delivery.common.message.model;

import lombok.Getter;

@Getter
public class UserOrderMessage {

  private Long userOrderId;

  public static UserOrderMessage from(Long userOrderId) {
    UserOrderMessage userOrderMessage = new UserOrderMessage();
    userOrderMessage.userOrderId = userOrderId;
    return userOrderMessage;
  }
}