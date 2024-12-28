package com.hamidfarmani.urlservice.repository;

import com.hamidfarmani.urlservice.model.UrlEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends CassandraRepository<UrlEntity, String> {

  UrlEntity findByShortUrl(String shortUrl);

  void deleteByShortUrl(String shortUrl);

}
