package com.hamidfarmani.urlservice.service;

import com.hamidfarmani.urlservice.repository.UrlRepository;
import com.hamidfarmani.urlservice.model.UrlEntity;
import com.hamidfarmani.urlservice.service.client.KeyGenerationServiceClient;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UrlService {

  private static final Integer POOL_SIZE = 1000;
  private static final Integer BUFFER_SIZE = 500;
  private static final Integer ASYNC_REPLENISH_THRESHOLD = 500;

  private final BlockingQueue<String> localKeyPool = new LinkedBlockingQueue<>(POOL_SIZE);

  private final KeyGenerationServiceClient keyGenerationServiceClient;
  private final UrlRepository urlRepository;

  public UrlService(KeyGenerationServiceClient keyGenerationServiceClient, UrlRepository repository) {
    this.keyGenerationServiceClient = keyGenerationServiceClient;
    this.urlRepository = repository;
    replenishLocalPool();
  }


  public UrlEntity createShortUrl(String originalUrl, String customAlias, String apiDevKey, String userName, String expireDate) {
    String shortUrl = (customAlias != null) ? customAlias : getKeyFromLocalPool();

    UrlEntity entity = UrlEntity.builder()
        .shortUrl(shortUrl)
        .apiDevKey(apiDevKey)
        .originalUrl(originalUrl)
        .customAlias(customAlias)
        .userName(userName)
        .expireDate(expireDate)
        .build();


    UrlEntity urlEntity = urlRepository.save(entity);
    log.info("Short URL created: {}", urlEntity);

    return urlEntity;
  }


  private String getKeyFromLocalPool() {
    String key = localKeyPool.poll();
    if (key == null) {
      log.warn("Local key pool is empty. Replenishing...");
      replenishLocalPool();
      key = localKeyPool.poll();
      if (key == null) {
        throw new RuntimeException("No keys available!");
      }
    }

    if(localKeyPool.size() < BUFFER_SIZE) {
      log.info("Local key pool is running low. Replenishing asynchronously...");
      replenishLocalPoolAsync();
    }

    return key;
  }

  private void replenishLocalPool() {
    List<String> keys = keyGenerationServiceClient.fetchKeys(ASYNC_REPLENISH_THRESHOLD);
    localKeyPool.addAll(keys);
  }

  private void replenishLocalPoolAsync(){
    Thread.startVirtualThread(this::replenishLocalPool);
  }

}
