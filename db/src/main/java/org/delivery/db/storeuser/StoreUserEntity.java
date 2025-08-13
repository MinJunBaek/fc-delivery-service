package org.delivery.db.storeuser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.delivery.db.BaseEntity;
import org.delivery.db.storeuser.enums.StoreUserRole;
import org.delivery.db.storeuser.enums.StoreUserStatus;

@Getter
@EqualsAndHashCode(callSuper = true)
@Entity(name = "store_users")
public class StoreUserEntity extends BaseEntity {

  @Column(nullable = false)
  private Long storeId;
  @Column(length = 100, nullable = false)
  private String email;
  @Column(length = 100, nullable = false)
  private String password;
  @Column(length = 50, nullable = false)
  @Enumerated(EnumType.STRING)
  private StoreUserStatus status;
  @Column(length = 50, nullable = false)
  @Enumerated(EnumType.STRING)
  private StoreUserRole role;
  private LocalDateTime registeredAt;
  private LocalDateTime unRegisteredAt;
  private LocalDateTime lastLoginAt;

}
