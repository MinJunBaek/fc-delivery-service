package org.delivery.api.domain.ordermenu.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
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
}
