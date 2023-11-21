package com.org.makgol.util.mail;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("application.mail")
public class MailProperties {

    // SMTP 서버
    private String host;

    // 계정
    private String account;

    // 비밀번호
    private String password;

    // 포트번호
    private int port;

    // 메일연결자
    private String supplier;

    // 발신자 메일
    private String fromMail;

    // tls 설정
    private String socketFactoryClass;
}
