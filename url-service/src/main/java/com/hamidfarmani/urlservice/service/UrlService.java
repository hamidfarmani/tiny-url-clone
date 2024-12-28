package com.hamidfarmani.urlservice.service;

import com.hamidfarmani.urlservice.repository.UrlRepository;
import com.hamidfarmani.urlservice.model.UrlEntity;
import com.hamidfarmani.urlservice.service.client.KeyGenerationServiceClient;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

  private final KeyGenerationServiceClient keyGenerationServiceClient;
  private final UrlRepository urlRepository;
  private final BlockingQueue<String> localKeyPool = new LinkedBlockingQueue<>();

  private final UrlRepository repository;

  public UrlService(KeyGenerationServiceClient keyGenerationServiceClient,
      UrlRepository urlRepository, UrlRepository repository) {
    this.keyGenerationServiceClient = keyGenerationServiceClient;
    this.urlRepository = urlRepository;
    this.repository = repository;
  }


  public String createShortUrl(String originalUrl, String customAlias, String apiDevKey, String userName, String expireDate) {
    String shortUrl = (customAlias != null) ? customAlias : getKeyFromLocalPool();

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


  private String getKeyFromLocalPool() {
    String key = localKeyPool.poll();
    if (key == null) {
      replenishLocalPool();
      throw new RuntimeException("No keys available!");
    }
    return key;
  }

  private void replenishLocalPool() {
    List<String> keys = keyGenerationServiceClient.fetchKeys(100);
    localKeyPool.addAll(keys);
  }

}
