package org.delivery.api.domain.order.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import org.delivery.db.order.UserOrderEntity;
import org.delivery.db.order.enums.UserOrderStatus;

@Getter
public class UserOrderResponse {

  private Long id;

  private UserOrderStatus status;

  private BigDecimal amount;

  private LocalDateTime orderedAt;

  private LocalDateTime acceptedAt;

  private LocalDateTime cookingStartedAt;

  private LocalDateTime deliveryStartedAt;

  private LocalDateTime receivedAt;

  public static UserOrderResponse from(UserOrderEntity entity) {
    UserOrderResponse response = new UserOrderResponse();
    response.id = entity.getId();
    response.status = entity.getStatus();
    response.amount = entity.getAmount();
    response.orderedAt = entity.getOrderedAt();
    response.acceptedAt = entity.getAcceptedAt();
    response.cookingStartedAt = entity.getCookingStartedAt();
    response.deliveryStartedAt = entity.getDeliveryStartedAt();
    response.receivedAt = entity.getReceivedAt();
    return response;
  }
}
