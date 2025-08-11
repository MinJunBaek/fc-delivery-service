package org.delivery.api.domain.ordermenu.converter;

import org.delivery.api.common.annotation.Converter;
import org.delivery.db.order.UserOrderEntity;
import org.delivery.db.ordermenu.UserOrderMenuEntity;
import org.delivery.db.storemenu.StoreMenuEntity;

@Converter
public class UserOrderMenuConverter {

  public UserOrderMenuEntity toEntity(UserOrderEntity userOrderEntity, StoreMenuEntity storeMenuEntity) {
    return UserOrderMenuEntity.of(userOrderEntity.getUserId(), storeMenuEntity.getStoreId());
  }
}
