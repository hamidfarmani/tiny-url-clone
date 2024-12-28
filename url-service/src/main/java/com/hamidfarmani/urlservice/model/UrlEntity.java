package com.hamidfarmani.urlservice.model;


import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("url_entity")
public class UrlEntity {

  @PrimaryKey
  private String shortUrl;
  private String originalUrl;
  private String apiDevKey;
  private String userName;
  private String expireDate;
  private String customAlias;

  public UrlEntity() {
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }

  public void setOriginalUrl(String originalUrl) {
    this.originalUrl = originalUrl;
  }

  public String getApiDevKey() {
    return apiDevKey;
  }

  public void setApiDevKey(String apiDevKey) {
    this.apiDevKey = apiDevKey;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getExpireDate() {
    return expireDate;
  }

  public void setExpireDate(String expireDate) {
    this.expireDate = expireDate;
  }

  public String getCustomAlias() {
    return customAlias;
  }

  public void setCustomAlias(String customAlias) {
    this.customAlias = customAlias;
  }
}
