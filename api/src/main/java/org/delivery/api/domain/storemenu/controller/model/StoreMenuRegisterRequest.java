package org.delivery.api.domain.storemenu.controller.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StoreMenuRegisterRequest {
  @NotNull
  private Long storeId;
  @NotBlank
  private String name;
  @NotNull
  private BigDecimal amount;
  @NotBlank
  private String thumbnailUrl;
}
