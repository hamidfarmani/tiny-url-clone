package com.hamidfarmani.urlservice.model;


import lombok.Data;

@Data
public class CreateURLRequest {
  public String apiDevKey;
  public String originalUrl;
  public String customAlias;
  public String userName;
  public String expireDate;

}