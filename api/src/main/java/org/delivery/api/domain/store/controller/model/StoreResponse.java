package org.delivery.api.domain.store.controller.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.math.BigDecimal;
import lombok.Getter;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.enums.StoreCategory;
import org.delivery.db.store.enums.StoreStatus;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StoreResponse {
  private Long id;
  private String name;
  private StoreStatus storeStatus;
  private StoreCategory storeCategory;
  private double star;
  private String thumbnailUrl;
  private BigDecimal minimumAmount;
  private BigDecimal minimumDeliveryAmount;
  private String phoneNumber;

  public static StoreResponse from(StoreEntity entity) {
    StoreResponse response = new StoreResponse();
    response.id = entity.getId();
    response.name = entity.getName();
    response.storeStatus = entity.getStatus();
    response.storeCategory = entity.getCategory();
    response.star = entity.getStar();
    response.thumbnailUrl = entity.getThumbnailUrl();
    response.minimumAmount = entity.getMinimumAmount();
    response.minimumDeliveryAmount = entity.getMinimumDeliveryAmount();
    response.phoneNumber = entity.getPhoneNumber();
    return response;
  }
}
