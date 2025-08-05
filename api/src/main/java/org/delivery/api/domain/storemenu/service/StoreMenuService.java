package org.delivery.api.domain.storemenu.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.storemenu.StoreMenuRepository;
import org.delivery.db.storemenu.enums.StoreMenuStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreMenuService {
  private final StoreMenuRepository storeMenuRepository;

  // 메뉴 등록
  public StoreMenuEntity register(StoreMenuEntity entity) {
    if (entity == null) {
      throw new ApiException(ErrorCode.NULL_POINT);
    }

    entity.register(StoreMenuStatus.REGISTERED);
    storeMenuRepository.save(entity);

    return entity;
  }

  public StoreMenuEntity getStoreMenuWithThrow(Long id) {
    StoreMenuEntity menuEntity = storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED)
        .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    return menuEntity;
  }

  public List<StoreMenuEntity> getStoreMenuByStoreId(Long storeId) {
    List<StoreMenuEntity> storeMenuEntityList = storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceDesc(storeId, StoreMenuStatus.REGISTERED);
    return storeMenuEntityList;
  }
}
