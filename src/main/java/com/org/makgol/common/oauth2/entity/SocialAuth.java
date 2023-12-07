package com.org.makgol.common.oauth2.entity;

import com.org.makgol.common.oauth2.entity.type.AuthProvider;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SocialAuth {
    private String providerId;

    private AuthProvider provider;
    private String email;
    private String name;
    private String imageUrl;
    private String attributes;
    private String ip;

    public void update(String name, String imageUrl, Map<String, Object> attributes) {

        this.name = name;
        this.imageUrl = imageUrl;
        this.attributes = attributes.toString();
    }



}
