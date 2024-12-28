package com.hamidfarmani.urlservice.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("url_entity")
@Builder
@Data
public class UrlEntity {

  @PrimaryKey
  private String shortUrl;
  private String originalUrl;
  private String apiDevKey;
  private String userName;
  private String expireDate;
  private String customAlias;
}
