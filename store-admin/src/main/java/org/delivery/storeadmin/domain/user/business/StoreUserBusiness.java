package org.delivery.storeadmin.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreStatus;
import org.delivery.db.storeuser.StoreUserEntity;
import org.delivery.storeadmin.common.annotation.Business;
import org.delivery.storeadmin.domain.user.controller.dto.StoreUserRegisterRequest;
import org.delivery.storeadmin.domain.user.controller.dto.StoreUserResponse;
import org.delivery.storeadmin.domain.user.converter.StoreUserConverter;
import org.delivery.storeadmin.domain.user.service.StoreUserService;

@Business
@RequiredArgsConstructor
public class StoreUserBusiness {

  private final StoreUserConverter storeUserConverter;
  private final StoreUserService storeUserService;

  // TODO 임시방편으로 StoreService를 불러오지 못해서 StoreRepository를 불러옴
  private final StoreRepository storeRepository;

  public StoreUserResponse register(StoreUserRegisterRequest request) {

    StoreEntity storeEntity = storeRepository.findFirstByNameAndStatus(request.getStoreName(), StoreStatus.REGISTERED)
        .orElseThrow(() -> new IllegalArgumentException("가게 이름으로 조회한 결과 : 가게가 없습니다."));

    StoreUserEntity storeUserEntity = storeUserConverter.toEntity(request, storeEntity);

    storeUserService.register(storeUserEntity);

    StoreUserResponse response = storeUserConverter.toResponse(storeUserEntity, storeEntity);
    return response;
  }
}
