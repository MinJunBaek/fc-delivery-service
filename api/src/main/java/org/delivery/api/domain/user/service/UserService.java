package org.delivery.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.user.UserEntity;
import org.delivery.db.user.UserRepository;
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
}
