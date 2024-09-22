package org.cantainercraft.micro.users.configuration;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration(proxyBeanMethods = false)
public class CacheConfig {
    @Bean
    public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManager(){
        return cacheManager -> cacheManager.setAllowNullValues(false);
    }
}
