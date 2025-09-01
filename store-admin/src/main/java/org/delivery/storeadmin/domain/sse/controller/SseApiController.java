package org.delivery.storeadmin.domain.sse.controller;

import io.swagger.v3.oas.annotations.Parameter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sse")
public class SseApiController {

  private static final Map<String, SseEmitter> userConnection = new ConcurrentHashMap<>();

  @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public ResponseBodyEmitter connect(@Parameter(hidden = true) @AuthenticationPrincipal UserSession userSession) {
    log.info("login user {}", userSession);
    SseEmitter emitter = new SseEmitter(1000L * 60);

    userConnection.put(userSession.getUserId().toString(), emitter);

    // 클라이언트와 타임아웃이 발생할 때
    emitter.onTimeout(() -> {
      log.info("on time out");
      emitter.complete();
    });
    // 클라이언트와 연결이 종료되었을 때
    emitter.onCompletion(() -> {
      log.info("on completion");
      userConnection.remove(userSession.getUserId().toString());
    });

    // 최초 연결시 응답 전송
    SseEventBuilder event = SseEmitter.event().name("onopen");

    try {
      emitter.send(event);
    } catch (IOException e) {
      emitter.completeWithError(e);
    }

    return emitter;
  }

  @GetMapping("/push-event")
  public void pushEvent(@Parameter(hidden = true) @AuthenticationPrincipal UserSession userSession) {
    SseEmitter emitter = userConnection.get(userSession.getUserId().toString());
    SseEventBuilder event = SseEmitter.event().data("hello"); // onmessage

    try {
      emitter.send(event);
    } catch (IOException e) {
      emitter.completeWithError(e);
    }
  }
}
