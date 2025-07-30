package org.delivery.api.domain.user.controller.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRegisterRequest {
  @NotBlank
  private String name;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String address;

  @NotBlank
  @Size(min = 8)
  private String password;
}
