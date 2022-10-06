package com.example.note.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.note.constant.CacheConst.COFFEE_CACHE_NAME;
import static com.example.note.constant.PropertiesConst.CACHE_COFFEE_CACHE_TIME_TO_LIVE;

@Configuration
@EnableCaching
public class CacheConfig {

    @Autowired
    private Environment env;

    @Bean
    public CacheManager cacheManager() {
        // 因使用CaffeineCacheManager 無法為每個快取客製化配置，因而使用SimpleCacheManager
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(allCaches());

        return cacheManager;
    }

    @Bean
    public List<Cache> allCaches() {
        return Arrays.asList(coffeeCache());
    }

    @Bean
    public Cache coffeeCache() {
        return createExpireAfterWriteCacheByNameAndIntervalAndTimeUnit(
                COFFEE_CACHE_NAME,
                Integer.parseInt(env.getRequiredProperty(CACHE_COFFEE_CACHE_TIME_TO_LIVE)),
                TimeUnit.SECONDS
        );
    }

    private Cache createExpireAfterWriteCacheByNameAndIntervalAndTimeUnit(
            String cacheName, int timeInterval, TimeUnit timeUnit) {
        return new CaffeineCache(
                cacheName,
                Caffeine.newBuilder()
                        .expireAfterWrite(timeInterval, timeUnit)
                        .build()
        );
    }

}
