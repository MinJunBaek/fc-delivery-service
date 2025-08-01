package org.delivery.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.db.user.UserEntity;

@RequiredArgsConstructor
@Business
public class UserBusiness {
  private final UserService userService;
  private final UserConverter userConverter;
  private final TokenBusiness tokenBusiness;

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

  public TokenResponse login(UserLoginRequest request) {
    if (request == null) {
      throw new ApiException(ErrorCode.NULL_POINT, "UserLoginRequest Null");
    }
    // 가입한 유저 조회
    UserEntity entity = userService.login(request);

    // 토큰 생성
    TokenResponse tokenResponse = tokenBusiness.issueToken(entity);
    return tokenResponse;
  }

  public UserResponse me(Long userId) {
    UserEntity userEntity = userService.getUserWithThrow(userId);

    UserResponse response = userConverter.toResponse(userEntity);
    return response;
  }
}
