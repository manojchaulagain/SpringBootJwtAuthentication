package com.anoush.authentication.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/health")
@RefreshScope
@Slf4j
public class HealthCheckController {

  @Value("${message.default.welcome}")
  private String welcomeMessage;

  @GetMapping("/monitor")
  public ResponseEntity healthCheck() {
    log.info("Health Check end point called !!!");
    return ResponseEntity.ok(new HealthCheck());
  }

  @GetMapping("/value")
  public ResponseEntity<?> getValue() {
    log.info("Value requested !!!");
    return ResponseEntity.ok(welcomeMessage);
  }

  class HealthCheck {

    private boolean isLive;
    private String message;
    private String upTime;

    HealthCheck() {
      isLive = true;
      message = "The api is up and running";
      upTime = new Date().toString();
    }

    public boolean isLive() {
      return isLive;
    }

    public String getMessage() {
      return message;
    }

    public String getUpTime() {
      return upTime;
    }
  }
}
