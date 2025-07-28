package org.delivery.api.common.api;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCodeInterface;

@Getter
@NoArgsConstructor
public class Api<T> {
  private Result result;

  @Valid
  private T body;

  public static <T> Api<T> OK(T data) {
    Api<T> api = new Api<>();
    api.result = Result.OK();
    api.body = data;
    return api;
  }

  public static Api<Object> ERROR(Result result) {
    Api<Object> api = new Api<>();
    api.result = result;
    return api;
  }

  public static Api<Object> ERROR(ErrorCodeInterface errorCodeInterface) {
    Api<Object> api = new Api<>();
    api.result = Result.Error(errorCodeInterface);
    return api;
  }

  public static Api<Object> ERROR(ErrorCodeInterface errorCodeInterface, Throwable tx) {
    Api<Object> api = new Api<>();
    api.result = Result.Error(errorCodeInterface, tx);
    return api;
  }

  public static Api<Object> ERROR(ErrorCodeInterface errorCodeInterface, String description) {
    Api<Object> api = new Api<>();
    api.result = Result.Error(errorCodeInterface, description);
    return api;
  }
}
