package com.org.makgol.stores.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;

@Configuration
public class BeanFactory {

	/**
	 * RestTemplate을 생성하는 빈 메서드입니다.
	 *
	 * @return RestTemplate 객체
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * HttpHeaders를 생성하는 빈 메서드입니다.
	 *
	 * @return HttpHeaders 객체
	 */
	@Bean
	public HttpHeaders headers() {
		return new HttpHeaders();
	}

	/**
	 * ObjectMapper를 생성하는 빈 메서드입니다. Deserialization 기능 중 알려지지 않은 속성에 대한 처리를 설정합니다.
	 *
	 * @return ObjectMapper 객체
	 */
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
}
