package br.com.nagata.dev.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "dev-specification", url = "${clients.dev-specification}")
public interface DevSpecificationClient {

  @GetMapping(value = "/api/v1/customer/{id}")
  ResponseEntity<JsonNode> getCustomerById(@PathVariable("id") Long id);
}
