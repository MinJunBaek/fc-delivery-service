package org.delivery.api.config.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.interceptor.AuthorizationInterceptor;
import org.delivery.api.resolver.UserSessionResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final AuthorizationInterceptor authorizationInterceptor;
  private final UserSessionResolver userSessionResolver;

  // 검증제외 API
  private final List<String> OPEN_API = List.of(
      "/open-api/**"
  );

  // 기본적으로 해주어야할 값
  private final List<String> DEFAULT_EXCLUDE = List.of(
      "/",
      "favicon.ico",
      "/error"
  );

  // Swagger
  private final List<String> SWAGGER = List.of(
      "/swagger-ui.html",
      // "/swagger-ui.index.html",
      "/swagger-ui/**",
      "/v3/api-docs/**"
  );

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 인증을 절차를 거치지 않고 제공해야하는 API도 있음(회원가입, 약관, 등급이 없는 글 조회 등등)
    // exclude 를 하지 않으면 authorizationInterceptor에 모든것을 인증절차를 밟기 때문에 제외를 지정해줘야한다. (.excludePathPatterns() 메소드 활용)
    // 하지만 일일히 지정하지 않고 분류를 통해서 간단하게 지정하는게 좋다.
    // "/open-api"/**는 검증하지 않고 나머지들은 다 검증 -> OPEN_API로 해결
    // 기본적으로 빼줘야 하는 주소들이 있음
    registry.addInterceptor(authorizationInterceptor)
        .excludePathPatterns(OPEN_API)
        .excludePathPatterns(DEFAULT_EXCLUDE)
        .excludePathPatterns(SWAGGER);
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(userSessionResolver);
  }
}
