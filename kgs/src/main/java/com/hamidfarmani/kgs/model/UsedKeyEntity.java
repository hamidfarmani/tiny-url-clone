package com.hamidfarmani.kgs.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "used_keys")
public class UsedKeyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 6)
  private String key;

  @Column(nullable = false, updatable = false)
  private LocalDateTime usedAt = LocalDateTime.now();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public LocalDateTime getUsedAt() {
    return usedAt;
  }

  public void setUsedAt(LocalDateTime usedAt) {
    this.usedAt = usedAt;
  }
}
