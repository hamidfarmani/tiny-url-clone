package com.hamidfarmani.kgs.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "unused_keys")
public class UnusedKeyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 6)
  private String key;

  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt = LocalDateTime.now();


  public UnusedKeyEntity() {
  }

  public UnusedKeyEntity(String key) {
    this.key = key;
    this.createdAt = LocalDateTime.now();
  }

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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
