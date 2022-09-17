package br.com.nagata.dev.config;

import br.com.nagata.dev.exception.BusinessException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class HandlerExceptionConfig {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<?> handlerBusinessException(BusinessException ex, WebRequest request) {
    log.error("handlerBusinessException");
    log.trace("handlerBusinessException WebRequest: {}", request);
    log.error("handlerBusinessException StackTrace: {}", ex.getMessage());

    return formatMessage(ex.getCode(), ex);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handlerException(Exception ex, WebRequest request) {
    log.error("handlerBusinessException");
    log.trace("handlerBusinessException WebRequest: {}", request);
    log.error("handlerBusinessException StackTrace: {}", ex.getMessage());

    return formatMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex);
  }

  private ResponseEntity<?> formatMessage(HttpStatus code, Exception ex) {
    ObjectNode json = new ObjectNode(JsonNodeFactory.instance);
    json.put("timestamp", LocalDateTime.now().toString());
    json.put("status", code.value());
    json.put("error", code.getReasonPhrase());
    json.put("message", StringUtils.hasLength(ex.getMessage()) ? ex.getMessage() : "");

    return ResponseEntity.status(code).body(json);
  }
}
