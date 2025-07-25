package org.delivery.api.account.model;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountMeResponse {
  private String email;
  private String name;
  private LocalDateTime registeredAt;

  public static AccountMeResponse of(String email, String name) {
    AccountMeResponse accountMeResponse = new AccountMeResponse();
    accountMeResponse.email = email;
    accountMeResponse.name = name;
    accountMeResponse.registeredAt = LocalDateTime.now();
    return accountMeResponse;
  }
}
