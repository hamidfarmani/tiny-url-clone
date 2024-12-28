package com.hamidfarmani.urlservice.controller;

import com.hamidfarmani.urlservice.model.CreateURLRequest;
import com.hamidfarmani.urlservice.model.UrlEntity;
import com.hamidfarmani.urlservice.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UrlController {

  private final UrlService urlService;

  public UrlController(UrlService urlService) {
    this.urlService = urlService;
  }


  @PostMapping("/url")
  public ResponseEntity createURL(@RequestBody CreateURLRequest request) {
    if (request.apiDevKey == null || request.originalUrl == null) {
      return ResponseEntity.badRequest().body("API developer key and original URL are required.");
    }

    UrlEntity result = urlService.createShortUrl(request.originalUrl, request.customAlias,
        request.apiDevKey, request.userName, request.expireDate);

    return ResponseEntity.ok(result);
  }

  @GetMapping("/url")
  public ResponseEntity getURL() {
    return
    ResponseEntity.ok("getURL");
  }

}
