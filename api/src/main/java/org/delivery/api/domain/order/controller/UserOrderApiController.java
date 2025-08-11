package org.delivery.api.domain.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.order.business.UserOrderBusiness;
import org.delivery.api.domain.order.controller.dto.UserOrderRequest;
import org.delivery.api.domain.order.controller.dto.UserOrderResponse;
import org.delivery.api.domain.user.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user-order")
public class UserOrderApiController {
  private final UserOrderBusiness userOrderBusiness;

  // 사용자 주문
  @PostMapping("")
  public Api<UserOrderResponse> userOrder(@UserSession User user, @RequestBody @Valid Api<UserOrderRequest> userOrderRequest) {
    UserOrderResponse response = userOrderBusiness.userOrder(user, userOrderRequest.getBody());
    return Api.OK(response);
  }
}
