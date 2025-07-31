package org.delivery.api.common.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.ErrorCodeInterface;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Result {
  private Integer resultCode;
  private String resultMessage;
  private String resultDescription;

  public static Result OK() {
    Result result = new Result(ErrorCode.OK.getErrorCode(), ErrorCode.OK.getDescription(), "성공");
    return result;
  }

  public static Result Error(ErrorCodeInterface errorCodeInterface) {
    Result result = new Result(errorCodeInterface.getErrorCode(), errorCodeInterface.getDescription(), "실패");
    return result;
  }

  public static Result Error(ErrorCodeInterface errorCodeInterface, Throwable tx) {
    Result result = new Result(errorCodeInterface.getErrorCode(), errorCodeInterface.getDescription(), tx.getLocalizedMessage()); //getLocalizedMessage은 사용하지않는걸 권장
    return result;
  }

  public static Result Error(ErrorCodeInterface errorCodeInterface, String description) {
    Result result = new Result(errorCodeInterface.getErrorCode(), errorCodeInterface.getDescription(), description);
    return result;
  }
}
