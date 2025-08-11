package org.delivery.api.domain.storemenu.converter;

import java.util.List;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.db.storemenu.StoreMenuEntity;

@Converter
public class StoreMenuConverter {

  public StoreMenuEntity toEntity(StoreMenuRegisterRequest request) {
    if (request == null) {
      throw new ApiException(ErrorCode.NULL_POINT);
    }
    StoreMenuEntity entity = StoreMenuEntity.of(request.getStoreId(), request.getName(), request.getAmount(), request.getThumbnailUrl());
    return entity;
  }

  public StoreMenuResponse toResponse(StoreMenuEntity entity) {
    if (entity == null) {
      throw new ApiException(ErrorCode.NULL_POINT);
    }
    StoreMenuResponse response = StoreMenuResponse.from(entity);
    return response;
  }

  public List<StoreMenuResponse> toResponse(List<StoreMenuEntity> storeMenuEntityList) {
    List<StoreMenuResponse> storeMenuResponseList = storeMenuEntityList.stream().map(storeMenuEntity -> toResponse(storeMenuEntity)).toList();
    return storeMenuResponseList;
  }
}
