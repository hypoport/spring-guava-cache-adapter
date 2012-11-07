/**
 *  Copyright 2012 HYPOPORT AG
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.hypoport.springGuavaCacheAdapter;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SpringGuavaCacheAdapter implements Cache, BeanNameAware, InitializingBean {

  private String name;
  private com.google.common.cache.Cache<Object, Optional<Object>> cache;
  private Long maximumSize;
  private Long expireAfterAccessInSeconds;
  private Long expireAfterWriteInSeconds;

  @Override
  public void afterPropertiesSet() throws Exception {
    assertRequiredName();
    createGuavaCache();
  }

  private void assertRequiredName() {
    if (name == null || name.length() == 0) {
      throw new IllegalArgumentException("name required");
    }
  }

  private void createGuavaCache() {
    CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();
    if (maximumSize != null) {
      builder.maximumSize(maximumSize);
    }
    if (expireAfterAccessInSeconds != null) {
      builder.expireAfterAccess(expireAfterAccessInSeconds, SECONDS);
    }
    if (expireAfterWriteInSeconds != null) {
      builder.expireAfterWrite(expireAfterWriteInSeconds, SECONDS);
    }
    cache = builder.build();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public com.google.common.cache.Cache<Object, Optional<Object>> getNativeCache() {
    return cache;
  }

  @Override
  public ValueWrapper get(Object key) {
    Optional<Object> value = cache.getIfPresent(key);
    return (value != null ? new SimpleValueWrapper(value.orNull()) : null);
  }

  @Override
  public void put(Object key, Object value) {
    cache.put(key, Optional.fromNullable(value));
  }

  @Override
  public void evict(Object key) {
    cache.invalidate(key);
  }

  @Override
  public void clear() {
    cache.invalidateAll();
  }

  @Override
  public void setBeanName(String name) {
    this.name = name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setMaximumSize(long maximumSize) {
    this.maximumSize = maximumSize;
  }

  public void setExpireAfterAccessInSeconds(long expireAfterAccessInSeconds) {
    this.expireAfterAccessInSeconds = expireAfterAccessInSeconds;
  }

  public void setExpireAfterWriteInSeconds(long expireAfterWriteInSeconds) {
    this.expireAfterWriteInSeconds = expireAfterWriteInSeconds;
  }
}
