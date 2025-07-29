package org.delivery.api.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

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

    // TODO header 검증 (추후에 추가)

    return true; // 일단 통과
  }
}
