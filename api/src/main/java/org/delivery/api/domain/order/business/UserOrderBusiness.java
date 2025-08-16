package org.delivery.api.domain.order.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.order.controller.dto.UserOrderDetailResponse;
import org.delivery.api.domain.order.controller.dto.UserOrderRequest;
import org.delivery.api.domain.order.controller.dto.UserOrderResponse;
import org.delivery.api.domain.order.converter.UserOrderConverter;
import org.delivery.api.domain.order.producer.UserOrderProducer;
import org.delivery.api.domain.order.service.UserOrderService;
import org.delivery.api.domain.ordermenu.converter.UserOrderMenuConverter;
import org.delivery.api.domain.ordermenu.service.UserOrderMenuService;
import org.delivery.api.domain.store.converter.StoreConverter;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;
import org.delivery.api.domain.user.model.User;
import org.delivery.db.order.UserOrderEntity;
import org.delivery.db.ordermenu.UserOrderMenuEntity;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.storemenu.StoreMenuEntity;

@Business
@RequiredArgsConstructor
public class UserOrderBusiness {

  private final UserOrderConverter userOrderConverter;
  private final UserOrderService userOrderService;

  private final StoreConverter storeConverter;
  private final StoreService storeService;

  private final StoreMenuConverter storeMenuConverter;
  private final StoreMenuService storeMenuService;

  private final UserOrderMenuConverter userOrderMenuConverter;
  private final UserOrderMenuService userOrderMenuService;

  private final UserOrderProducer userOrderProducer;

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

    // 비동기로 가맹점에 주문 알리기
    userOrderProducer.sendOrder(newUserOrderEntity);

    return userOrderConverter.toResponse(newUserOrderEntity);
  }

  public List<UserOrderDetailResponse> current(User user) {
    List<UserOrderEntity> userOrderEntityList = userOrderService.current(user.getId());
    // 주문 1건씩 처리
    List<UserOrderDetailResponse> userOrderDetailResponseList = userOrderEntityList.stream().map(userOrderEntity -> {
      // 사용자가 주문 메뉴
      List<UserOrderMenuEntity> userOrderMenuEntityList = userOrderMenuService.getUserOrderMenu(userOrderEntity.getId());
      List<StoreMenuEntity> storeMenuEntityList = userOrderMenuEntityList.stream()
          .map(userOrderMenuEntity -> {
            return storeMenuService.getStoreMenuWithThrow(userOrderMenuEntity.getStoreMenuId());
          })
          .toList();

      // 사용자가 주문한 스토어 TODO 리패토링 필요
      StoreEntity storeEntity = storeService.getStoreWithThrow(storeMenuEntityList.stream().findFirst().get().getStoreId());

      return UserOrderDetailResponse.of(
          userOrderConverter.toResponse(userOrderEntity),
          storeConverter.toResponse(storeEntity),
          storeMenuConverter.toResponse(storeMenuEntityList)
          );
    }).toList();

    return userOrderDetailResponseList;
  }

  public List<UserOrderDetailResponse> history(User user) {
    List<UserOrderEntity> userOrderEntityList = userOrderService.history(user.getId());
    // 주문 1건씩 처리
    List<UserOrderDetailResponse> userOrderDetailResponseList = userOrderEntityList.stream().map(userOrderEntity -> {
      // 사용자가 주문 메뉴
      List<UserOrderMenuEntity> userOrderMenuEntityList = userOrderMenuService.getUserOrderMenu(userOrderEntity.getId());
      List<StoreMenuEntity> storeMenuEntityList = userOrderMenuEntityList.stream()
          .map(userOrderMenuEntity -> {
            return storeMenuService.getStoreMenuWithThrow(userOrderMenuEntity.getStoreMenuId());
          })
          .toList();

      // 사용자가 주문한 스토어 TODO 리패토링 필요
      StoreEntity storeEntity = storeService.getStoreWithThrow(storeMenuEntityList.stream().findFirst().get().getStoreId());

      return UserOrderDetailResponse.of(
          userOrderConverter.toResponse(userOrderEntity),
          storeConverter.toResponse(storeEntity),
          storeMenuConverter.toResponse(storeMenuEntityList)
      );
    }).toList();

    return userOrderDetailResponseList;
  }

  public UserOrderDetailResponse read(User user, Long orderId) {
    UserOrderEntity userOrderEntity = userOrderService.getUserOrderWithThrow(orderId, user.getId());

    // 사용자가 주문한 메뉴
    List<UserOrderMenuEntity> userOrderMenuEntityList = userOrderMenuService.getUserOrderMenu(userOrderEntity.getId());
    List<StoreMenuEntity> storeMenuEntityList = userOrderMenuEntityList.stream()
        .map(userOrderMenuEntity -> {
          return storeMenuService.getStoreMenuWithThrow(userOrderMenuEntity.getStoreMenuId());
        })
        .toList();

    // 사용자가 주문한 스토어 TODO 리패토링 필요
    StoreEntity storeEntity = storeService.getStoreWithThrow(storeMenuEntityList.stream().findFirst().get().getStoreId());

    UserOrderDetailResponse userOrderDetailResponseList = UserOrderDetailResponse.of(
        userOrderConverter.toResponse(userOrderEntity),
        storeConverter.toResponse(storeEntity),
        storeMenuConverter.toResponse(storeMenuEntityList)
    );

    return userOrderDetailResponseList;
  }
}
