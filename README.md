spring-guava-cache-adapter
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
            <property name="maximumSize" value="1"/>
          </bean>
        </list>
      </property>
    </bean>

For further details please consider the test.

Credits
------

- Oliver Otzen
- Timmo Freudl-Gierke


Licence
-----

