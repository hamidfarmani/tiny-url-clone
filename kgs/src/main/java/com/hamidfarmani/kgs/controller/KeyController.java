package com.hamidfarmani.kgs.controller;

import com.hamidfarmani.kgs.service.KeyGenerationMonitor;
import com.hamidfarmani.kgs.service.KeyService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kgs")
public class KeyController {

  private final KeyService keyService;
  private final KeyGenerationMonitor keyGenerationMonitor;

  public KeyController(KeyService keyService, KeyGenerationMonitor keyGenerationMonitor) {
    this.keyService = keyService;
    this.keyGenerationMonitor = keyGenerationMonitor;
  }

  @GetMapping("/fetch")
  public List<String> fetchKeys(@RequestParam int count) {
    return keyService.getKeys(count);
  }

  @GetMapping("/monitor/unused-keys/check")
  public Long monitorUnusedKeysCount() {
    return keyGenerationMonitor.getUnusedKeyCount();
  }

  @GetMapping("/monitor/unused-keys/generate")
  public void checkAndGenerateKeys() {
    keyGenerationMonitor.checkAndGenerateKeys();
  }
}
