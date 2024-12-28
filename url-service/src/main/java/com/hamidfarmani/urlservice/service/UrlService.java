package com.hamidfarmani.urlservice.service;

import com.hamidfarmani.urlservice.repository.UrlRepository;
import com.hamidfarmani.urlservice.model.UrlEntity;
import java.util.Base64;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

  private final UrlRepository repository;

  public UrlService(UrlRepository repository) {
    this.repository = repository;
  }


  public String createShortUrl(String originalUrl, String customAlias, String apiDevKey, String userName, String expireDate) {
    String shortUrl = (customAlias != null) ? customAlias : generateShortUrl();

//    UrlEntity entity = UrlEntity.builder()
//        .apiDevKey(apiDevKey)
//        .originalUrl(originalUrl)
//        .customAlias(customAlias)
//        .userName(userName)
//        .expireDate(expireDate)
//        .build();
    UrlEntity entity = new UrlEntity();
    entity.setApiDevKey(apiDevKey);
    entity.setOriginalUrl(originalUrl);
    entity.setCustomAlias(customAlias);
    entity.setUserName(userName);
    entity.setExpireDate(expireDate);
    entity.setShortUrl(shortUrl);

    UrlEntity save = repository.save(entity);
    System.out.println("save = " + save);

    return shortUrl;
  }

  public UrlEntity getOriginalUrl(String shortUrl) {
    return repository.findByShortUrl(shortUrl);
  }

  public void deleteShortUrl(String shortUrl) {
    repository.deleteByShortUrl(shortUrl);
  }

  private String generateShortUrl() {
    String uuid = UUID.randomUUID().toString();
    return Base64.getUrlEncoder().encodeToString(uuid.getBytes()).substring(0, 6);
  }
}
