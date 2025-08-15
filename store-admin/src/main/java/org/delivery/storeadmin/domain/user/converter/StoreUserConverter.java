package org.delivery.storeadmin.domain.user.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.storeuser.StoreUserEntity;
import org.delivery.storeadmin.common.annotation.Converter;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.user.controller.dto.StoreUserRegisterRequest;
import org.delivery.storeadmin.domain.user.controller.dto.StoreUserResponse;

@Converter
@RequiredArgsConstructor
public class StoreUserConverter {

  public StoreUserEntity toEntity(StoreUserRegisterRequest request, StoreEntity storeEntity) {
    StoreUserEntity response = StoreUserEntity.of(storeEntity.getId(), request.getEmail(), request.getPassword(), request.getRole());
    return response;
  }

  public StoreUserResponse toResponse(StoreUserEntity storeUserEntity, StoreEntity storeEntity) {
    StoreUserResponse storeUserResponse = StoreUserResponse.of(storeUserEntity, storeEntity);
    return storeUserResponse;
  }

  public StoreUserResponse toResponse(UserSession userSession) {
    StoreUserResponse storeUserResponse = StoreUserResponse.from(userSession);
    return storeUserResponse;
  }
}
