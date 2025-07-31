package org.delivery.api.domain.token.model;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class TokenDto {

  private String token;
  private LocalDateTime expiredAt;

  public static TokenDto of(String jwtToken, LocalDateTime expiredLocalDateTime) {
    TokenDto tokenDto = new TokenDto();
    tokenDto.token = jwtToken;
    tokenDto.expiredAt = expiredLocalDateTime;
    return tokenDto;
  }
}
