package org.delivery.api.domain.order.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.order.UserOrderEntity;
import org.delivery.db.order.UserOrderRepository;
import org.delivery.db.order.enums.UserOrderStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOrderService {

  private final UserOrderRepository userOrderRepository;

  // 해당 유저의 주문 가져오기
  public List<UserOrderEntity> getUserOrderList(Long userId) {
    return userOrderRepository.findAllByUserIdAndStatusOrderByIdDesc(userId, UserOrderStatus.REGISTERED);
  }

  public List<UserOrderEntity> getUserOrderList(Long userId, List<UserOrderStatus> statusList) {
    return userOrderRepository.findAllByUserIdAndStatusInOrderByIdDesc(userId, statusList);
  }

  // 특정 주문
  public UserOrderEntity getUserOrderWithThrow(Long id, Long userId) {
    return userOrderRepository.findFirstByIdAndStatusAndUserId(id, UserOrderStatus.REGISTERED, userId)
        .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
  }

  public UserOrderEntity getUserOrderWithOutStatusWithThrow(Long id, Long userId) {
    return userOrderRepository.findFirstByIdAndUserId(id, userId)
        .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
  }

  // 주문
  public UserOrderEntity order(UserOrderEntity entity) {
    return Optional.ofNullable(entity)
        .map(
            (order) -> {order.setStatus(UserOrderStatus.ORDER);
            return userOrderRepository.save(order);
            }
        )
        .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
  }

  // 주문확인
  public UserOrderEntity accept(UserOrderEntity entity) {
    return Optional.ofNullable(entity)
        .map(
            (order) -> {order.setStatus(UserOrderStatus.ACCEPT);
              return userOrderRepository.save(order);
            }
        )
        .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
  }

  // 조리시작
  public UserOrderEntity cooking(UserOrderEntity entity) {
    return Optional.ofNullable(entity)
        .map(
            (order) -> {order.setStatus(UserOrderStatus.COOKING);
              return userOrderRepository.save(order);
            }
        )
        .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
  }

  // 배달시작
  public UserOrderEntity delivery(UserOrderEntity entity) {
    return Optional.ofNullable(entity)
        .map(
            (order) -> {order.setStatus(UserOrderStatus.DELIVERY);
              return userOrderRepository.save(order);
            }
        )
        .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
  }

  // 배달완료
  public UserOrderEntity receive(UserOrderEntity entity) {
    return Optional.ofNullable(entity)
        .map(
            (order) -> {order.setStatus(UserOrderStatus.RECEIVE);
              return userOrderRepository.save(order);
            }
        )
        .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
  }

  // 현재 진행중인 주문 내역
  public List<UserOrderEntity> current(Long userId) {
    return getUserOrderList(userId, List.of(
        UserOrderStatus.ORDER,
        UserOrderStatus.ACCEPT,
        UserOrderStatus.COOKING,
        UserOrderStatus.DELIVERY
    ));
  }

  // 과거 주문 내역
  public List<UserOrderEntity> history(Long userId) {
    return getUserOrderList(userId, List.of(
        UserOrderStatus.RECEIVE
    ));
  }
}
