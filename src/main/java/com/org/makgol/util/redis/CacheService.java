package com.org.makgol.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void cacheQueryResult(String cacheKey, Object result, long expireTimeInMinutes) {
        redisTemplate.opsForValue().set(cacheKey, result, expireTimeInMinutes, TimeUnit.MINUTES);
    }

    public Object getCachedQueryResult(String cacheKey) {
        return redisTemplate.opsForValue().get(cacheKey);
    }
}
