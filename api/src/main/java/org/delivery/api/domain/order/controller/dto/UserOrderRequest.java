package org.delivery.api.domain.order.controller.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;

@Getter
public class UserOrderRequest {

  @NotNull
  private List<Long> storeMenuIdList;
}
