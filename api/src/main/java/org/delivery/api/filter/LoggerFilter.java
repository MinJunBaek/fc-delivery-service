package org.delivery.api.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
@Component
public class LoggerFilter implements Filter {


  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {

    ContentCachingRequestWrapper req = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
    ContentCachingResponseWrapper res = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
    // 실행전은 헤더정보와 바디정보를 찍어주는게 좋다.
    filterChain.doFilter(req, res); // doFilter를 기준으로 실행전 실행후 로그를 나뉘게 된다.

    // request 정보
    Enumeration<String> headerNames = req.getHeaderNames();
    StringBuilder requestHeaderValues = new StringBuilder();

    headerNames.asIterator().forEachRemaining(headerKey -> {
      String headerValue = req.getHeader(headerKey);
      // authorization-token : ???, user-agent : ???
      requestHeaderValues.append("[").append(headerKey).append(" : ").append(headerValue).append("] ");
    });

    String requestBody = new String(req.getContentAsByteArray());
    String uri = req.getRequestURI();
    String method = req.getMethod();

    log.info(">>>>> uri : {}, method : {}, header : {} , body {}", uri, method, requestHeaderValues, requestBody);


    // response 정보
    StringBuilder responseHeaderValues = new StringBuilder();
    res.getHeaderNames().forEach(headerKey -> {
      String headerValue = res.getHeader(headerKey);
      responseHeaderValues.append("[").append(headerKey).append(" : ").append(headerValue).append("] ");
    });

    String responseBody = new String(res.getContentAsByteArray());

    log.info("<<<<< uri : {}, method : {}, header : {} , body {}", uri, method, responseHeaderValues, responseBody);

    res.copyBodyToResponse(); // Response Body를 한번 읽었기 때문에 반환값이 없을수 있어서 다시 copy해서 반환함
  }
}
