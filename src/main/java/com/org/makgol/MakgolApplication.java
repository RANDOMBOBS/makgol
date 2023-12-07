package com.org.makgol;

import com.org.makgol.common.config.security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication
public class MakgolApplication {

    public static void main(String[] args) {
        SpringApplication.run(MakgolApplication.class, args);
    }

}
// yi