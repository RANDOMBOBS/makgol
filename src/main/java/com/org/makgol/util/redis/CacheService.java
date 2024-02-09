package com.org.makgol.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void cacheQueryResult(String cacheKey, Object result) {
        redisTemplate.opsForValue().set(cacheKey, result);
    }

    public Object getCachedQueryResult(String cacheKey) {
        return redisTemplate.opsForValue().get(cacheKey);
    }
}
