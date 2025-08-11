package org.delivery.api.domain.order.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.order.controller.dto.UserOrderRequest;
import org.delivery.api.domain.order.controller.dto.UserOrderResponse;
import org.delivery.api.domain.order.converter.UserOrderConverter;
import org.delivery.api.domain.order.service.UserOrderService;
import org.delivery.api.domain.ordermenu.converter.UserOrderMenuConverter;
import org.delivery.api.domain.ordermenu.service.UserOrderMenuService;
import org.delivery.api.domain.storemenu.service.StoreMenuService;
import org.delivery.api.domain.user.model.User;
import org.delivery.db.order.UserOrderEntity;
import org.delivery.db.ordermenu.UserOrderMenuEntity;
import org.delivery.db.storemenu.StoreMenuEntity;

@Business
@RequiredArgsConstructor
public class UserOrderBusiness {

  private final UserOrderService userOrderService;
  private final StoreMenuService storeMenuService;
  private final UserOrderConverter userOrderConverter;
  private final UserOrderMenuConverter userOrderMenuConverter;
  private final UserOrderMenuService userOrderMenuService;

  public UserOrderResponse userOrder(User user, UserOrderRequest request) {
    List<StoreMenuEntity> storeMenuEntityList = request.getStoreMenuIdList().stream()
        .map(menu -> storeMenuService.getStoreMenuWithThrow(menu)).toList();

    UserOrderEntity userOrderEntity = userOrderConverter.toEntity(user, storeMenuEntityList);

    UserOrderEntity newUserOrderEntity = userOrderService.order(userOrderEntity);

    List<UserOrderMenuEntity> userOrderMenuEntityList = storeMenuEntityList.stream()
        .map(entity -> {
          var userOrderMenuEntity = userOrderMenuConverter.toEntity(newUserOrderEntity, entity);
          return userOrderMenuEntity;
        }).toList();

    userOrderMenuEntityList.forEach(entity -> {
      userOrderMenuService.order(entity);
    });

    return userOrderConverter.toResponse(newUserOrderEntity);
  }
}
