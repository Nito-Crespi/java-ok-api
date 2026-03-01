package com.example.okapi;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OkController {

  private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_ZONED_DATE_TIME;

  @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
  public String root() {
    ZonedDateTime now = ZonedDateTime.now(); // hora del contenedor (server)
    return "OK - " + FMT.format(now);
  }

  @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
  public java.util.Map<String, String> health() {
    ZonedDateTime now = ZonedDateTime.now();
    return java.util.Map.of(
        "status", "ok",
        "serverTime", FMT.format(now),
        "zoneId", now.getZone().getId());
  }
}