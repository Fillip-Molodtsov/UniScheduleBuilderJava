package com.springproj.schedulebuilder.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ProjectSimpleCacheManager implements CacheManager, InitializingBean {
    private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<>(16);
    private volatile Set<String> cacheNames = Collections.emptySet();
    private Collection<? extends Cache> caches = Collections.emptySet();

    @Override
    public void afterPropertiesSet() {
        initializeCaches();
    }

    public void initializeCaches() {
        Collection<? extends Cache> caches = loadCaches();

        synchronized (this.cacheMap) {
            this.cacheNames = Collections.emptySet();
            this.cacheMap.clear();
            Set<String> cacheNames = new LinkedHashSet<>(caches.size());
            for (Cache cache : caches) {
                String name = cache.getName();
                this.cacheMap.put(name, cache);
                cacheNames.add(name);
            }
            this.cacheNames = Collections.unmodifiableSet(cacheNames);
        }
    }

    public void setCaches(Collection<? extends Cache> caches) {
        this.caches = caches;
    }

    protected Collection<? extends Cache> loadCaches() {
        return this.caches;
    }

    @Override
    public Cache getCache(String name) {
        return this.cacheMap.get(name);
    }

    @Override
    public Collection<String> getCacheNames() {
        return this.cacheNames;
    }
}