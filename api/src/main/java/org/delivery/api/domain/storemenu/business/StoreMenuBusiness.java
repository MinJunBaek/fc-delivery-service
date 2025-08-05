package org.delivery.api.domain.storemenu.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;
import org.delivery.db.storemenu.StoreMenuEntity;

@RequiredArgsConstructor
@Business
public class StoreMenuBusiness {

  private final StoreMenuService storeMenuService;
  private final StoreMenuConverter storeMenuConverter;

  public StoreMenuResponse register(StoreMenuRegisterRequest request) {
    StoreMenuEntity entity = storeMenuConverter.toEntity(request);
    StoreMenuEntity newEntity = storeMenuService.register(entity);
    StoreMenuResponse response = storeMenuConverter.toResponse(newEntity);
    return response;
  }

  public List<StoreMenuResponse> search(Long storeId) {
    List<StoreMenuEntity> list = storeMenuService.getStoreMenuByStoreId(storeId);
    List<StoreMenuResponse> responseList = list.stream().map(storeMenuConverter::toResponse).toList();
    return responseList;
  }
}
