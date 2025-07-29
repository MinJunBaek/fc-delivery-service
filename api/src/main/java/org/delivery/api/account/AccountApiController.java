package org.delivery.api.account;

import lombok.RequiredArgsConstructor;
import org.delivery.api.account.model.AccountMeResponse;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.account.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountApiController {

  private final AccountRepository accountRepository;

  @GetMapping("/me")
  public Api<AccountMeResponse> me() {

//    try {
//      String str = "안녕하세요";
//      Integer age = Integer.parseInt(str);
//    } catch (Exception e) {
//      throw new ApiException(ErrorCode.SERVER_ERROR, "사용자 Me 호출시 에러 발생", e);
//    }

    return Api.OK(AccountMeResponse.of("Lee@gmail.com", "홍길동"));
  }
}
