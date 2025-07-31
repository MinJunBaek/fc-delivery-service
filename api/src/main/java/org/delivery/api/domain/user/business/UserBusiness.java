package org.delivery.api.domain.user.business;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.db.user.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Business
public class UserBusiness {
  private final UserService userService;
  private final UserConverter userConverter;

  public UserResponse register(UserRegisterRequest request) {
    if (request == null) {
      throw new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null");
    }
    // converter를 통해서 dto -> entity로 변환
    UserEntity entity = userConverter.toEntity(request);
    // entity를 service에 넘겨서 저장
    userService.register(entity);
    // 저장된 entity -> dto로 넘겨서 반환
    UserResponse response = userConverter.toResponse(entity);

//    Optional.ofNullable(request)
//        .map(userConverter::toEntity)
//        .map(userService::register)
//        .map(userConverter::toResponse)
//        .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));

    return response;
  }

  public UserResponse login(UserLoginRequest request) {
    if (request == null) {
      throw new ApiException(ErrorCode.NULL_POINT, "UserLoginRequest Null");
    }
    // 가입한 유저 조회
    UserEntity entity = userService.login(request);

    // TODO 토큰 생성

    // Response Dto로 변환하여 반환
    UserResponse userResponse = userConverter.toResponse(entity);
    return userResponse;
  }
}
