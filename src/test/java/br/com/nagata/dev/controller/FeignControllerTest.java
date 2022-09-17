package br.com.nagata.dev.controller;

import br.com.nagata.dev.exception.BusinessException;
import br.com.nagata.dev.service.FeignService;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeignControllerTest {
  @Mock private FeignService service;
  @InjectMocks private FeignController controller;

  @Test
  void testGetCustomerById(@Mock JsonNode serviceResponse) throws BusinessException {
    when(service.getCustomerById(Mockito.anyLong())).thenReturn(serviceResponse);
    ResponseEntity<?> response = controller.getCustomerById(1L);
    assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected return code 200");
  }
}
