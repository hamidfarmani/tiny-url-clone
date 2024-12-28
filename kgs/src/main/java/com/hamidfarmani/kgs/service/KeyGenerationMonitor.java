package com.hamidfarmani.kgs.service;

import com.hamidfarmani.kgs.repository.UnusedKeyRepository;
import com.hamidfarmani.kgs.service.job.KeyGeneratorJob;
import org.springframework.stereotype.Service;

@Service
public class KeyGenerationMonitor {

  private final UnusedKeyRepository unusedKeyRepository;
  private final KeyGeneratorJob keyGeneratorJob;

  public KeyGenerationMonitor(UnusedKeyRepository unusedKeyRepository, KeyGeneratorJob keyGeneratorJob) {
    this.unusedKeyRepository = unusedKeyRepository;
    this.keyGeneratorJob = keyGeneratorJob;
  }

  public Long getUnusedKeyCount() {
    return unusedKeyRepository.count();
  }

  public void checkAndGenerateKeys() {
    long unusedKeyCount = unusedKeyRepository.count();
    long threshold = 720_000;

    if (unusedKeyCount < threshold) {
      keyGeneratorJob.generateKeys();
    }
  }
}
