package org.delivery.storeadmin.domain.user.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.delivery.db.storeuser.enums.StoreUserRole;

@Getter
public class StoreUserRegisterRequest {

  @NotBlank
  private String storeName;
  @NotBlank
  private String email;
  @NotBlank
  private String password;
  @NotNull
  private StoreUserRole role;
}
