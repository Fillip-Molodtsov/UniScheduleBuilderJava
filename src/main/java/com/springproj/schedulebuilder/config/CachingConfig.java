package com.springproj.schedulebuilder.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
    public CacheManager cacheManager() {
        ProjectSimpleCacheManager cacheManager = new ProjectSimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("intervals"),
                new ConcurrentMapCache("days")));
        return cacheManager;
    }
}