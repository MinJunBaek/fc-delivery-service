package org.delivery.storeadmin.domain.user.controller.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.storeuser.StoreUserEntity;
import org.delivery.db.storeuser.enums.StoreUserRole;
import org.delivery.db.storeuser.enums.StoreUserStatus;

@Getter
public class StoreUserResponse {
  private StoreUser storeUser;
  private Store store;

  @Getter
  private static class StoreUser{
    private Long id;
    private String email;
    private StoreUserStatus status;
    private StoreUserRole role;
    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;
    private LocalDateTime lastLoginAt;

    private static StoreUser from(StoreUserEntity storeUserEntity) {
      StoreUser storeUser = new StoreUser();
      storeUser.id = storeUserEntity.getId();
      storeUser.email = storeUserEntity.getEmail();
      storeUser.status = storeUserEntity.getStatus();
      storeUser.role = storeUserEntity.getRole();
      storeUser.registeredAt = storeUserEntity.getRegisteredAt();
      storeUser.unRegisteredAt = storeUserEntity.getUnRegisteredAt();
      storeUser.lastLoginAt = storeUserEntity.getLastLoginAt();
      return storeUser;
    }
  }

  @Getter
  private static class Store {
    private Long storeId;
    private String name;

    private static Store from(StoreEntity storeEntity) {
      Store store = new Store();
      store.storeId = storeEntity.getId();
      store.name = storeEntity.getName();
      return store;
    }
  }

  public static StoreUserResponse of(StoreUserEntity storeUserEntity, StoreEntity storeEntity) {
    StoreUserResponse storeUserResponse = new StoreUserResponse();
    storeUserResponse.storeUser = StoreUserResponse.StoreUser.from(storeUserEntity);
    storeUserResponse.store = StoreUserResponse.Store.from(storeEntity);
    return storeUserResponse;
  }
}
