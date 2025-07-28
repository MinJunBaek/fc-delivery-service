package org.delivery.api.common.api;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
