package com.org.makgol.util.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class MailConfig {
    //Properties pt = new Properties();

    private final MailProperties mailProperties;

    @Bean
    public JavaMailSender javaMailSender() {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(this.mailProperties.getHost());
        javaMailSender.setUsername(this.mailProperties.getAccount());
        javaMailSender.setPassword(this.mailProperties.getPassword());
        javaMailSender.setPort(this.mailProperties.getPort());

        // 메일 옵션 설정
        Properties prop = new Properties();
        prop.put("mail.smtp.socketFactory.port", this.mailProperties.getPort());
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        //prop.put("mail.smtp.starttls.required", true);
        //prop.put("mail.smtp.socketFactory.fallback", false);
        //prop.put("mail.smtp.socketFactory.class", this.mailProperties.getSocketFactoryClass());

        javaMailSender.setJavaMailProperties(prop);
        javaMailSender.setDefaultEncoding("UTF-8");
        return javaMailSender;
    }
}
