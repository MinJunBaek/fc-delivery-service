package org.delivery.api.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.TokenErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

  private final TokenBusiness tokenBusiness;

  @Override // pre(사전에 검증한다는 뜻?)
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    log.info("Authorization Interceptor url : {}", request.getRequestURI());

    // Web, chrome의 경우 GET, POST OPTIONS를 통해 해당 메서드를 지원하는지 체크 되면 pass
    if (HttpMethod.OPTIONS.matches(request.getMethod())) {
      return true;
    }

    // 리소스 요청(js. html, png 등등) = pass
    if (handler instanceof ResourceHttpRequestHandler) {
      return true;
    }

    // header 검증
    String accessToken = request.getHeader("authorization-token");
    if (accessToken == null) {
      throw new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND);
    }

    Long userId = tokenBusiness.validationAccessToken(accessToken);

    if (userId != null) {
      RequestAttributes requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
      requestContext.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST);
      return true;
    }

    throw new ApiException(ErrorCode.BAD_REQUEST, "인증실패");
  }
}
