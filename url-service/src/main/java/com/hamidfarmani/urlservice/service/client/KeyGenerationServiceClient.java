package com.hamidfarmani.urlservice.service.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gateway-service")
public interface KeyGenerationServiceClient {
  @GetMapping("/kgs/fetch")
  List<String> fetchKeys(@RequestParam int count);
}
