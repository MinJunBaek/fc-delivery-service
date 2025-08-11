package org.delivery.api.domain.order.converter;

import java.math.BigDecimal;
import java.util.List;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.domain.order.controller.dto.UserOrderResponse;
import org.delivery.api.domain.user.model.User;
import org.delivery.db.order.UserOrderEntity;
import org.delivery.db.storemenu.StoreMenuEntity;

@Converter
public class UserOrderConverter {

  public UserOrderEntity toEntity(User user, List<StoreMenuEntity> storeMenuEntity) {
    BigDecimal totalAmount = storeMenuEntity.stream()
        .map(menuEntity -> menuEntity.getAmount())
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    return UserOrderEntity.of(user.getId(), totalAmount);
  }

  public UserOrderResponse toResponse(UserOrderEntity entity) {
    return UserOrderResponse.from(entity);
  }
}
