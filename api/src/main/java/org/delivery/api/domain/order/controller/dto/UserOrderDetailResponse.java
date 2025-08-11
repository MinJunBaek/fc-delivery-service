package org.delivery.api.domain.order.controller.dto;

import java.util.List;
import lombok.Getter;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;

@Getter
public class UserOrderDetailResponse {
  private UserOrderResponse userOrderResponse;
  private StoreResponse storeResponse;
  private List<StoreMenuResponse> storeMenuResponseList;

  public static UserOrderDetailResponse of(
      UserOrderResponse userOrderResponse,
      StoreResponse storeResponse,
      List<StoreMenuResponse> storeMenuResponseList
  ) {
    UserOrderDetailResponse userOrderDetailResponse = new UserOrderDetailResponse();
    userOrderDetailResponse.userOrderResponse = userOrderResponse;
    userOrderDetailResponse.storeResponse = storeResponse;
    userOrderDetailResponse.storeMenuResponseList = storeMenuResponseList;
    return userOrderDetailResponse;
  }
}
