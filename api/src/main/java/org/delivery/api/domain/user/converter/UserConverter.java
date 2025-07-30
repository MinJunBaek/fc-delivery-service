package org.delivery.api.domain.user.converter;

import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.db.user.UserEntity;

@Converter
public class UserConverter {

  public UserEntity toEntity(UserRegisterRequest request) {

    if (request == null) {
      throw new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null");
    }

    return UserEntity.of(request.getName(), request.getEmail(), request.getAddress(), request.getPassword());
  }

  public UserResponse toResponse(UserEntity entity) {
    if (entity == null) {
      throw new ApiException(ErrorCode.NULL_POINT, "UserEntity Null");
    }
    return UserResponse.from(entity);
  }
}
