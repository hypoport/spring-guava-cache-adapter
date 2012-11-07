package org.hypoport.springGuavaCacheAdapter;

public interface Bean {

  String getSomething(String key);

  int getCallCount();
}
