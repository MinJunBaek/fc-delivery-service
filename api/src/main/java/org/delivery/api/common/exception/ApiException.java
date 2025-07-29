package org.delivery.api.common.exception;

import lombok.Getter;
import org.delivery.api.common.error.ErrorCodeInterface;
@Getter
public class ApiException extends RuntimeException implements ApiExceptionInterface {
  private final ErrorCodeInterface errorCodeInterface;
  private final String errorCodeDescription;

  public ApiException(ErrorCodeInterface errorCodeInterface) {
    super(errorCodeInterface.getDescription());
    this.errorCodeInterface = errorCodeInterface;
    this.errorCodeDescription = errorCodeInterface.getDescription();
  }

  public ApiException(ErrorCodeInterface errorCodeInterface, String errorDescription) {
    super(errorDescription);
    this.errorCodeInterface = errorCodeInterface;
    this.errorCodeDescription = errorDescription;
  }

  public ApiException(ErrorCodeInterface errorCodeInterface, Throwable tx) {
    super(tx);
    this.errorCodeInterface = errorCodeInterface;
    this.errorCodeDescription = errorCodeInterface.getDescription();
  }

  public ApiException(ErrorCodeInterface errorCodeInterface, String errorDescription,Throwable tx) {
    super(tx);
    this.errorCodeInterface = errorCodeInterface;
    this.errorCodeDescription = errorDescription;
  }
}