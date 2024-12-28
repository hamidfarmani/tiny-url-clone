package com.hamidfarmani.urlservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableCassandraRepositories(basePackages = "com.hamidfarmani.urlservice.repository")
public class UrlServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(UrlServiceApplication.class, args);
  }

}
