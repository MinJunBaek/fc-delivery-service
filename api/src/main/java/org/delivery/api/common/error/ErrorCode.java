package org.delivery.api.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode implements ErrorCodeInterface {

  OK(200, 200, "성공"),
  BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400, "잘못된 요청"),
  SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500, "서버에러"),
  NULL_POINT(HttpStatus.I_AM_A_TEAPOT.value(), 512, "Null point");

  private final Integer httpStatusCode;
  private final Integer errorCode;
  private final String description;

  ErrorCode(Integer httpStatusCode, Integer errorCode, String description) {
    this.httpStatusCode = httpStatusCode;
    this.errorCode = errorCode;
    this.description = description;
  }
}
