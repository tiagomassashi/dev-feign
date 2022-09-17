package br.com.nagata.dev.service;

import br.com.nagata.dev.client.DevSpecificationClient;
import br.com.nagata.dev.exception.BusinessException;
import com.fasterxml.jackson.databind.JsonNode;
import feign.FeignException;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeignServiceTest {
  @Mock private DevSpecificationClient specificationClient;
  @InjectMocks private FeignService service;

  @Test
  void testGetCustomerByIdWhenClientReturnOk(@Mock JsonNode mockedResponse)
      throws BusinessException {
    when(specificationClient.getCustomerById(Mockito.anyLong()))
        .thenReturn(ResponseEntity.ok(mockedResponse));
    assertDoesNotThrow(() -> service.getCustomerById(1L), "Successful execution expected");
  }

  @Test
  void testGetCustomerByIdWhenClientReturnNotFound() throws BusinessException {
    when(specificationClient.getCustomerById(Mockito.anyLong()))
        .thenThrow(
            FeignException.errorStatus(
                "getCustomerById",
                Response.builder()
                    .status(404)
                    .reason("customer not found")
                    .request(
                        Request.create(
                            Request.HttpMethod.GET,
                            "/api/v1/feign/1",
                            Collections.emptyMap(),
                            null,
                            null,
                            null))
                    .build()));
    assertThrows(
        BusinessException.class, () -> service.getCustomerById(1L), "Expected BusinessException");
  }

  @Test
  void testGetCustomerByIdWhenClientReturnInternalServerError() throws BusinessException {
    when(specificationClient.getCustomerById(Mockito.anyLong()))
        .thenThrow(
            FeignException.errorStatus(
                "getCustomerById",
                Response.builder()
                    .status(-1)
                    .reason("connection refused")
                    .request(
                        Request.create(
                            Request.HttpMethod.GET,
                            "/api/v1/feign/1",
                            Collections.emptyMap(),
                            null,
                            null,
                            null))
                    .build()));
    assertThrows(
        BusinessException.class, () -> service.getCustomerById(1L), "Expected BusinessException");
  }
}
