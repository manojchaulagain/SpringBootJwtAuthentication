package com.anoush.authentication;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAsysncConf {

  @Async
  public void asyncMethodWithVoidReturnType(AtomicInteger x, int y) throws InterruptedException {
    x.getAndAdd(y);
    System.out.println("value of X: " + x);
  }
}
