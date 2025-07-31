package org.delivery.api.domain.user.controller.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserLoginRequest {

  @NotBlank
  @Email
  private String email;
  @NotBlank
  @Size(min = 8)
  private String password;
}
