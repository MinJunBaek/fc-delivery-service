package org.delivery.api.domain.store.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.api.domain.store.converter.StoreConverter;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.enums.StoreCategory;

@Business
@RequiredArgsConstructor
public class StoreBusiness {

  private final StoreService storeService;
  private final StoreConverter storeConverter;

  public StoreResponse register(StoreRegisterRequest request) {
    StoreEntity entity = storeConverter.toEntity(request);
    storeService.register(entity);
    StoreResponse response = storeConverter.toResponse(entity);
    return response;
  }

  public List<StoreResponse> searchCategory(StoreCategory storeCategory) {
    List<StoreEntity> storeList = storeService.searchByCategory(storeCategory);
    List<StoreResponse> responseList = storeList.stream().map(storeConverter::toResponse).toList();
    return responseList;
  }
}
