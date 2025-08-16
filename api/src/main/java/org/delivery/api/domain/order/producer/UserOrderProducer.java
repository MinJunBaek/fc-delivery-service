package org.delivery.api.domain.order.producer;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.rabbitmq.Producer;
import org.delivery.common.message.model.UserOrderMessage;
import org.delivery.db.order.UserOrderEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOrderProducer {

  private final Producer producer;

  private static final String EXCHANGE = "delivery.exchange";
  private static final String ROUTE_KEY = "delivery.key";

  public void sendOrder(UserOrderEntity userOrderEntity) {
    sendOrder(userOrderEntity.getUserId());
  }

  public void sendOrder(Long userOrderId) {
    UserOrderMessage userOrderMessage = UserOrderMessage.from(userOrderId);
    producer.producer(EXCHANGE, ROUTE_KEY, userOrderMessage);
  }
}
