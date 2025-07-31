package org.delivery.api.domain.token.business;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.converter.TokenConverter;
import org.delivery.api.domain.token.model.TokenDto;
import org.delivery.api.domain.token.service.TokenService;
import org.delivery.db.user.UserEntity;

@RequiredArgsConstructor
@Business
public class TokenBusiness {

  private final TokenService tokenService;
  private final TokenConverter tokenConverter;

  /**
   * 1. User Entity User ID 추출
   * 2. access, refresh token 발행
   * 3. converter -> token respones로 변경
  * */

  public TokenResponse issueToken(UserEntity entity) {
    if (entity == null) {
      throw new ApiException(ErrorCode.NULL_POINT);
    }

    Long userId = entity.getId();
    TokenDto accessToken = tokenService.issueAccessToken(userId);
    TokenDto refreshToken = tokenService.issueRefreshToken(userId);
    TokenResponse tokenResponse = tokenConverter.toResponse(accessToken, refreshToken);

    return tokenResponse;
//    return Optional.ofNullable(entity).map(userEntity -> {
//      return userEntity.getId();
//    }).map(userId -> {
//          TokenDto accessToken = tokenService.issueAccessToken(userId);
//          TokenDto refreshToken = tokenService.issueRefreshToken(userId);
//          return tokenConverter.toResponse(accessToken, refreshToken);
//        })
//        .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
  }

  public Long validationAccessToken(String accessToken) {
    Long userId = tokenService.validationToken(accessToken);
    return userId;
  }
}
