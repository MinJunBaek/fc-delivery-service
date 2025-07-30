package org.delivery.db.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.delivery.db.BaseEntity;
import org.delivery.db.user.enums.UserStatus;

@EqualsAndHashCode(callSuper = true)
@Getter
@Entity(name = "user") // @Table 어노테이션에 name으로 "user"로 작성해도 동일하게 작동이 된다.
public class UserEntity extends BaseEntity {

  @Column(length = 50, nullable = false)
  private String name;

  @Column(length = 100, nullable = false)
  private String email;

  @Column(length = 100, nullable = false)
  private String password;

  @Column(length = 50, nullable = false)
  @Enumerated(EnumType.STRING)
  private UserStatus status;

  @Column(length = 150, nullable = false)
  private String address;

  private LocalDateTime registered_at;

  private LocalDateTime unregistered_at;

  private LocalDateTime last_login_at;
}
