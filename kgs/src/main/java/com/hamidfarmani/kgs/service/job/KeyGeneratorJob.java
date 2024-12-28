package com.hamidfarmani.kgs.service.job;

import com.hamidfarmani.kgs.model.UnusedKeyEntity;
import com.hamidfarmani.kgs.repository.UnusedKeyRepository;
import com.hamidfarmani.kgs.service.KeyService;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyGeneratorJob {

  private final UnusedKeyRepository unusedKeyRepository;
  private final KeyService  keyService;

  public KeyGeneratorJob(UnusedKeyRepository unusedKeyRepository, KeyService keyService) {
    this.unusedKeyRepository = unusedKeyRepository;
    this.keyService = keyService;
  }

  @Scheduled(fixedRate = 6 * 60 * 60 * 1000) // Every 6 hours
  public void generateKeys() {
    long unusedKeyCount = unusedKeyRepository.count();
    long threshold = 720_000; // 20% of (1k URLs per second * 3600 seconds = 3.6M URLs per hour)

    if (unusedKeyCount < threshold) {
      int batchSize = 100_000; // Generate 1M keys per batch
      List<String> keys = new ArrayList<>();

      for (int i = 0; i < batchSize; i++) {
        String key = generateShortKey();
        keys.add(key);
      }

      keyService.saveKeysWithIgnoreConflict(keys);
      System.out.println("Generated and attempted to save " + batchSize + " keys.");
    } else {
      System.out.println("Key count is sufficient, no new keys generated.");
    }
  }


  private String generateShortKey() {
    String uuid = UUID.randomUUID().toString();
    return Base64.getUrlEncoder().encodeToString(uuid.getBytes()).substring(0, 6);
  }
}
