package com.org.makgol.common.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Configuration
public class CommonBeanFactory {

    @Bean
    public ResponseEntity<String> responseEntity() {
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }

}
