package com.org.makgol.common.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 1시간
    private final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOriginPatterns("http://3.34.140.239") // 외부에서 들어오는 모든 url을 허용
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS") // 허용되는 Method
                .allowedHeaders("*") // 허용되는 헤더
                .allowCredentials(true) // 자격증명 허용
                .maxAge(MAX_AGE_SECS); // 허용 시간
    }

}
