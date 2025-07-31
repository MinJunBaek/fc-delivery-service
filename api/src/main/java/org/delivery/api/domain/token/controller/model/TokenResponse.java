package org.delivery.api.domain.token.controller.model;

import java.time.LocalDateTime;
import lombok.Getter;
import org.delivery.api.domain.token.model.TokenDto;

@Getter
public class TokenResponse {
  private String accessToken;
  private LocalDateTime accessTokenExpiredAt;
  private String refreshToken;
  private LocalDateTime refreshTokenExpiredAt;

  public static TokenResponse of(TokenDto accessToken, TokenDto refreshToken) {
    TokenResponse tokenResponse = new TokenResponse();
    tokenResponse.accessToken = accessToken.getToken();
    tokenResponse.accessTokenExpiredAt = accessToken.getExpiredAt();
    tokenResponse.refreshToken = refreshToken.getToken();
    tokenResponse.refreshTokenExpiredAt = refreshToken.getExpiredAt();
    return tokenResponse;
  }
}
