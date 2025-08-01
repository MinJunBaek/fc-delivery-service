package org.delivery.api.domain.user.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import org.delivery.db.user.UserEntity;
import org.delivery.db.user.enums.UserStatus;

@Getter
@ToString
public class User {
  private Long id;

  private String name;

  private String email;

  private String password;

  private UserStatus status;

  private String address;

  private LocalDateTime registeredAt;

  private LocalDateTime unRegisteredAt;

  private LocalDateTime lastLoginAt;

  public static User from(UserEntity userEntity) {
    User entity = new User();
    entity.id = userEntity.getId();
    entity.name = userEntity.getName();
    entity.email = userEntity.getEmail();
    entity.password = userEntity.getPassword();
    entity.status = userEntity.getStatus();
    entity.address = userEntity.getAddress();
    entity.registeredAt = userEntity.getRegisteredAt();
    entity.unRegisteredAt = userEntity.getUnRegisteredAt();
    entity.lastLoginAt = userEntity.getLastLoginAt();
    return entity;
  }
}
