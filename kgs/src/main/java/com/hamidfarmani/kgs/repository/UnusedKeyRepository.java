package com.hamidfarmani.kgs.repository;

import com.hamidfarmani.kgs.model.UnusedKeyEntity;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.repository.query.Param;

public interface UnusedKeyRepository extends JpaRepository<UnusedKeyEntity, Long> {

  @Query(value = "SELECT key FROM unused_keys LIMIT :count", nativeQuery = true)
  List<String> fetchKeys(int count);

  @Modifying
  @Query(value = "DELETE FROM unused_keys WHERE key IN (:keys)", nativeQuery = true)
  void deleteKeys(List<String> keys);

  @Modifying
  @Query(value = "INSERT INTO unused_keys (key, created_at) VALUES (:key, :createdAt) ON CONFLICT (key) DO NOTHING", nativeQuery = true)
  void insertIgnoreConflict(@Param("key") String key, @Param("createdAt") LocalDateTime createdAt);

}
