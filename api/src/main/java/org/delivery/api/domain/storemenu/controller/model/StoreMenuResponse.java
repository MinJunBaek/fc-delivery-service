package org.delivery.api.domain.storemenu.controller.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.math.BigDecimal;
import lombok.Getter;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.storemenu.enums.StoreMenuStatus;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StoreMenuResponse {
  private Long id;
  private Long StoreId;
  private String name;
  private BigDecimal amount;
  private StoreMenuStatus status;
  private String thumbnailUrl;
  private int likeCount;
  private int sequence;

  public static StoreMenuResponse from(StoreMenuEntity entity) {
    StoreMenuResponse response = new StoreMenuResponse();
    response.id = entity.getId();
    response.StoreId = entity.getStoreId();
    response.name = entity.getName();
    response.amount = entity.getAmount();
    response.status = entity.getStatus();
    response.thumbnailUrl = entity.getThumbnailUrl();
    response.likeCount = entity.getLikeCount();
    response.sequence = entity.getSequence();
    return response;
  }
}