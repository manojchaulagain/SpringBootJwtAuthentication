package com.anoush.authentication.controller;

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
public class HealthCheckController {

  @Value("${message.default.welcome}")
  private String welcomeMessage;

  @GetMapping("/monitor")
  public ResponseEntity healthCheck() {
    return ResponseEntity.ok(new HealthCheck());
  }

  @GetMapping("/value")
  public ResponseEntity<?> getValue() {
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
