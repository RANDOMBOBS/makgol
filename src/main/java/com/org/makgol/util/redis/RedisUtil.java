package com.org.makgol.util.redis;

import java.time.Duration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RedisUtil {
 

	private final RedisTemplate<String, String> redisTemplate;
	

	public String getData(String key) {
		// key를 통해 value 리턴
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		return valueOperations.get(key);
		
	}
	
		public void setData(String key, String value) {
			ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
			valueOperations.set(key, value);
			
		}

	// 유효 시간 동안 (key, value) 저장
	public Boolean setDataExpire(String key, String value, long duration) {
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		Duration expireDuration = Duration.ofSeconds(duration);
		valueOperations.set(key, value, expireDuration);
		return true;
	}
	
	// 삭제
	public void deleteData(String key) {
		redisTemplate.delete(key);
		
	}
}
