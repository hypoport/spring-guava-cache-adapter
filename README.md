Spring Guava Cache Adapter
==========================


Description
------
An adapter to get the guava cache work together with the spring framework.

Usage
-----

Activate the @Cachable annotation and declare a cacheManager containing your guava cache.

**Please note:** Use the xml "name" attribute for the bean to set the cache name.

    <cache:annotation-driven/>

    <bean id="cacheManager" class="org.springframework.cache.support.SimpleCacheManager">
      <property name="caches">
        <list>
          <bean name="yourCache" class="org.hypoport.springGuavaCacheAdapter.SpringGuavaCacheAdapter">
          </bean>
        </list>
      </property>
    </bean>

You can than annotate the method to cache of your spring bean with @Cacheable.

    @Cacheable("yourCache")
    public String getSomethingCached(String key) {...}


For further details please consider the test.

Contributors
------

- Oliver Otzen
- [Timmo Freudl-Gierke](https://github.com/timmo)


License
-----
     Copyright 2012 HYPOPORT AG

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
