package com.hamidfarmani.kgs.service;

import com.hamidfarmani.kgs.model.UsedKeyEntity;
import com.hamidfarmani.kgs.repository.UnusedKeyRepository;
import com.hamidfarmani.kgs.repository.UsedKeyRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class KeyService {

  private final UnusedKeyRepository unusedKeyRepository;
  private final UsedKeyRepository usedKeyRepository;




  public KeyService(UnusedKeyRepository unusedKeyRepository, UsedKeyRepository usedKeyRepository) {
    this.unusedKeyRepository = unusedKeyRepository;
    this.usedKeyRepository = usedKeyRepository;
  }

  @Transactional
  public void saveKeysWithIgnoreConflict(List<String> keys) {
    for (String key : keys) {
      unusedKeyRepository.insertIgnoreConflict(key, LocalDateTime.now());
    }
  }

  @Transactional
  public List<String> getKeys(int count) {
    List<String> keys = unusedKeyRepository.fetchKeys(count);
    if (!keys.isEmpty()) {
      unusedKeyRepository.deleteKeys(keys);

      List<UsedKeyEntity> usedKeys = keys.stream()
          .map(key -> {
            UsedKeyEntity entity = new UsedKeyEntity();
            entity.setKey(key);
            return entity;
          })
          .toList();

      usedKeyRepository.saveAll(usedKeys);
    }
    return keys;
  }
}
