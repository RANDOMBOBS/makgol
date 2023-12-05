package com.org.makgol.util.mail;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.mail") // properties 파일의 접두사(prefix) 설정
public class MailProperties {
    private int port;
    private String host;
    private String account;
    private String password;
    private String socketFactoryClass;
}
