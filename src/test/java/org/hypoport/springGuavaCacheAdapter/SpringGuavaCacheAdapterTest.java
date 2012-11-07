package org.hypoport.springGuavaCacheAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

@ContextConfiguration(locations = "guava-cache-adapter-test.spring.xml")
public class SpringGuavaCacheAdapterTest extends AbstractTestNGSpringContextTests {

  @Autowired
  Bean bean;

  @Test
  public void cached_method_gets_invoked_only_once() {

    bean.getSomething("KEY");
    bean.getSomething("KEY");
    bean.getSomething("KEY");

    assertThat(bean.getCallCount()).isEqualTo(1);
  }
}
