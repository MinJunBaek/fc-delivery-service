package org.delivery.api.common.error;

import lombok.Getter;

/**
 * User의 경우 1000 번대 에러코드 사용
 */

@Getter
public enum UserErrorCode implements ErrorCodeInterface {

  USER_NOT_FOUND(400, 1404, "사용자를 찾을수 없습니다.");

  private final Integer httpStatusCode;
  private final Integer errorCode;
  private final String description;

  UserErrorCode(Integer httpStatusCode, Integer errorCode, String description) {
    this.httpStatusCode = httpStatusCode;
    this.errorCode = errorCode;
    this.description = description;
  }
}
