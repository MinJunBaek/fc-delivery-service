package org.delivery.api.domain.ordermenu.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.ordermenu.UserOrderMenuEntity;
import org.delivery.db.ordermenu.UserOrderMenuRepository;
import org.delivery.db.ordermenu.enums.UserOrderMenuStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOrderMenuService {

  private final UserOrderMenuRepository userOrderMenuRepository;

  public List<UserOrderMenuEntity> getUserOrderMenu(Long userOrderId) {
    List<UserOrderMenuEntity> menuEntityList = userOrderMenuRepository.findAllByUserOrderIdAndStatus(userOrderId, UserOrderMenuStatus.REGISTERED);
    return menuEntityList;
  }

  public UserOrderMenuEntity order(UserOrderMenuEntity userOrderMenuEntity) {
    return Optional.ofNullable(userOrderMenuEntity)
        .map(entity -> {
          entity.setStatus(UserOrderMenuStatus.REGISTERED);
          return userOrderMenuRepository.save(entity);
        }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
  }
}
