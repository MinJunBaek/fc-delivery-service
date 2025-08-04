package org.delivery.api.domain.store.controller.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import org.delivery.db.store.enums.StoreCategory;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StoreRegisterRequest {
  @NotBlank
  private String name;
  @NotBlank
  private String address;
  @NotNull
  private StoreCategory storeCategory;
  @NotBlank
  private String thumbnailUrl;
  @NotNull
  private BigDecimal minimumAmount;
  @NotNull
  private BigDecimal minimumDeliveryAmount;
  @NotBlank
  private String phoneNumber;
}
