package org.hypoport.springGuavaCacheAdapter;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CachedBeanImpl implements Bean {

  private int callCount = 0;

  @Override
  @Cacheable("testCache")
  public String getSomething(String key) {
    callCount++;
    return key;
  }

  @Override
  public int getCallCount() {
    return callCount;
  }
}
