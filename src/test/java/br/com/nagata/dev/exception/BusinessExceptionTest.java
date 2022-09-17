package br.com.nagata.dev.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class BusinessExceptionTest {

  @Test
  void testConstructorMessage() {
    BusinessException ex = new BusinessException("message");
    assertNotNull(ex, "Expected exception not null");
  }

  @Test
  void testConstructorCodeAndMessage() {
    BusinessException ex = new BusinessException(HttpStatus.BAD_REQUEST, "message");
    assertNotNull(ex, "Expected exception not null");
  }

  @Test
  void testConstructorMessageAndThrowable(@Mock Throwable throwable) {
    BusinessException ex = new BusinessException("message", throwable);
    assertNotNull(ex, "Expected exception not null");
  }

  @Test
  void testConstructorCodeAndMessageAndThrowable(@Mock Throwable throwable) {
    BusinessException ex = new BusinessException(HttpStatus.BAD_REQUEST, "message", throwable);
    assertNotNull(ex, "Expected exception not null");
  }

  @Test
  void testConstructorGet() {
    BusinessException ex = new BusinessException(HttpStatus.BAD_REQUEST, "message");
    HttpStatus code = ex.getCode();
    assertNotNull(code, "Expected code not null");
  }
}
