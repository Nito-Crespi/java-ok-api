package com.example.okapi;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OkController {

  @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
  public String root() {
    return "OK";
  }

  @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, String> health() {
    return Map.of("status", "ok");
  }
}