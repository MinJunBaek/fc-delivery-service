package org.delivery.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.UserErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.db.user.UserEntity;
import org.delivery.db.user.UserRepository;
import org.delivery.db.user.enums.UserStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserEntity register(UserEntity entity) {
    if (entity == null) {
      throw new ApiException(ErrorCode.NULL_POINT, "UserEntity Null");
    }
    return userRepository.save(entity);
  }

  public UserEntity login(UserLoginRequest request) {
    UserEntity entity = getUserWithThrow(request.getEmail(), request.getPassword());
    return entity;
  }

  public UserEntity getUserWithThrow(String email, String password) {
    UserEntity entity = userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(email, password, UserStatus.REGISTERED)
        .orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND));
    return entity;
  }

  public UserEntity getUserWithThrow(Long userId) {
    UserEntity entity = userRepository.findFirstByIdAndStatusOrderByIdDesc(userId, UserStatus.REGISTERED)
        .orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND));
    return entity;
  }
}
