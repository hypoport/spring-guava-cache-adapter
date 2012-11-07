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

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CachedBeanImpl implements Bean {

  private int callCount = 0;

  @Override
  @Cacheable("testCache")
  public String getSomethingCached(String key) {
    callCount++;
    return key;
  }

  @Override
  public int getCallCount() {
    return callCount;
  }

  @Override
  public String getSomething(String key) {
    callCount++;
    return key;
  }

  @Override
  public void resetCallCount() {
    callCount = 0;
  }
}
