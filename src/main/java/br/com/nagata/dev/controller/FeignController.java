package br.com.nagata.dev.controller;

import br.com.nagata.dev.exception.BusinessException;
import br.com.nagata.dev.service.FeignService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/feign")
public class FeignController {
  private final FeignService feignService;

  @Autowired
  public FeignController(FeignService feignService) {
    this.feignService = feignService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<JsonNode> getCustomerById(@PathVariable Long id) throws BusinessException {
    return ResponseEntity.ok(feignService.getCustomerById(id));
  }
}
