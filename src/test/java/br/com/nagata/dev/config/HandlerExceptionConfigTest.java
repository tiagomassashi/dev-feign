package br.com.nagata.dev.config;

import br.com.nagata.dev.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class HandlerExceptionConfigTest {

  private HandlerExceptionConfig exceptionConfig;

  @BeforeEach
  void init() {
    this.exceptionConfig = new HandlerExceptionConfig();
  }

  @Test
  void testHandlerBusinessException(@Mock WebRequest request) {
    ResponseEntity<?> response =
        exceptionConfig.handlerBusinessException(
            new BusinessException(HttpStatus.BAD_REQUEST, "error"), request);
    assertNotNull(response, "Expected response not null");
  }

  @Test
  void testHandlerBusinessExceptionMessageNull(@Mock WebRequest request) {
    ResponseEntity<?> response =
        exceptionConfig.handlerBusinessException(
            new BusinessException(HttpStatus.BAD_REQUEST, null), request);
    assertNotNull(response, "Expected response not null");
  }

  @Test
  void testHandlerException(@Mock WebRequest request) {
    ResponseEntity<?> response = exceptionConfig.handlerException(new Exception("error"), request);
    assertNotNull(response, "Expected response not null");
  }
}
