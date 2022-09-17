package br.com.nagata.dev.service;

import br.com.nagata.dev.client.DevSpecificationClient;
import br.com.nagata.dev.exception.BusinessException;
import com.fasterxml.jackson.databind.JsonNode;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FeignService {
  private final DevSpecificationClient specificationClient;

  @Autowired
  public FeignService(DevSpecificationClient specificationClient) {
    this.specificationClient = specificationClient;
  }

  public JsonNode getCustomerById(Long id) throws BusinessException {
    ResponseEntity<JsonNode> response;

    try {
      response = specificationClient.getCustomerById(id);
    } catch (FeignException e) {
      throw new BusinessException(
          e.status() == -1 ? HttpStatus.BAD_REQUEST : HttpStatus.valueOf(e.status()),
          e.getMessage());
    }
    return response.getBody();
  }
}
