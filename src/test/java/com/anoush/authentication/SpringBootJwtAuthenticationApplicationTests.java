package com.anoush.authentication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
// @SpringBootTest
@ContextConfiguration(classes = SimpleConfiguration.class)
public class SpringBootJwtAuthenticationApplicationTests
    implements ApplicationContextAware, InitializingBean {

  private boolean beanInitialized = false;

  private ApplicationContext applicationContext;

  @Autowired private TestAsysncConf testAsysncConf;

  @Test
  public void contextLoads() {}

  @Override
  public void afterPropertiesSet() throws Exception {
    this.beanInitialized = true;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    this.applicationContext = applicationContext;
  }

  @Test
  public void whenTestStarted_thenContextSet() throws Exception {
    AtomicInteger x = new AtomicInteger(4);
    Thread t =
        new Thread(
            () -> {
              x.getAndAdd(3);
              new Thread(
                      () -> {
                        x.getAndAdd(3);
                      })
                  .start();
            });
    Thread t1 = new Thread(() -> x.getAndAdd(5));
    t.start();
    t1.start();
    t.join();
    t1.join();
    //    testAsysncConf.asyncMethodWithVoidReturnType(x, 3);
    //    testAsysncConf.asyncMethodWithVoidReturnType(x, 5);
    //    testAsysncConf.asyncMethodWithVoidReturnType(x, 3);
    //    testAsysncConf.asyncMethodWithVoidReturnType(x, 5);
    //    testAsysncConf.asyncMethodWithVoidReturnType(x, 3);
    //    testAsysncConf.asyncMethodWithVoidReturnType(x, 5);
    //    testAsysncConf.asyncMethodWithVoidReturnType(x, 3);
    //    testAsysncConf.asyncMethodWithVoidReturnType(x, 5);
    //    Thread.sleep( 1000 );
    assertEquals(15, x.get());
    assertNotNull(
        "The application context should have been set due to ApplicationContextAware semantics.",
        this.applicationContext);
  }

  @Test
  public void whenTestStarted_thenBeanInitialized() throws Exception {
    TimeUnit.SECONDS.sleep(2);

    assertTrue(
        "This test bean should have been initialized due to InitializingBean semantics.",
        this.beanInitialized);
  }
}
