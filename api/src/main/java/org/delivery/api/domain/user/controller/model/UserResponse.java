package org.delivery.api.domain.user.controller.model;

import java.time.LocalDateTime;
import lombok.Getter;
import org.delivery.db.user.UserEntity;

@Getter
public class UserResponse {
  private final Long id;
  private final String name;
  private final String email;
  private final LocalDateTime registeredAt;

  private UserResponse(Long id, String name, String email, LocalDateTime registeredAt){
    this.id = id;
    this.name = name;
    this.email = email;
    this.registeredAt = registeredAt;
  }

  public static UserResponse from(UserEntity entity) {
    UserResponse userResponse = new UserResponse(
        entity.getId(),
        entity.getName(),
        entity.getEmail(),
        entity.getRegisteredAt()
    );
    return userResponse;
  }
}
