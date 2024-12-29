package com.hamidfarmani.urlservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableCassandraRepositories(basePackages = "com.hamidfarmani.urlservice.repository")
@EnableFeignClients
@EnableDiscoveryClient
public class UrlServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(UrlServiceApplication.class, args);
  }

}
