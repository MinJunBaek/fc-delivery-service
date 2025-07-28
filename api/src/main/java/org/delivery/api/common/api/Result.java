package org.delivery.api.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Result {
  private Integer resultCode;
  private String resultMessage;
  private String resultDescription;

  public static Result OK() {
    Result result = new Result(200, "OK", "성공");
    return result;
  }
}
