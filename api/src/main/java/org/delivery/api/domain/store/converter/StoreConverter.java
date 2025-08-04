package org.delivery.api.domain.store.converter;

import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.db.store.StoreEntity;

@Converter
public class StoreConverter {

  public StoreEntity toEntity(StoreRegisterRequest request) {
    if (request == null) {
      throw new ApiException(ErrorCode.NULL_POINT);
    }

    StoreEntity entity = StoreEntity.of(
        request.getName(),
        request.getAddress(),
        request.getStoreCategory(),
        request.getThumbnailUrl(),
        request.getMinimumAmount(),
        request.getMinimumDeliveryAmount(),
        request.getPhoneNumber());
    return entity;
  }

  public StoreResponse toResponse(StoreEntity entity) {
    if (entity == null) {
      throw new ApiException(ErrorCode.NULL_POINT);
    }
    StoreResponse response = StoreResponse.from(entity);
    return response;
  }
}
