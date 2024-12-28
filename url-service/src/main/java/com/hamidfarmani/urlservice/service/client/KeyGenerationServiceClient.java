package com.hamidfarmani.urlservice.service.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kgs", url = "http://localhost:8081/kgs")
public interface KeyGenerationServiceClient {
  @GetMapping("/fetch")
  List<String> fetchKeys(@RequestParam int count);
}
