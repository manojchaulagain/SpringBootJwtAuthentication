package com.anoush.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class SimpleConfiguration {

  @Bean
  public TestAsysncConf testAsysncConf() {
    return new TestAsysncConf();
  }
}
