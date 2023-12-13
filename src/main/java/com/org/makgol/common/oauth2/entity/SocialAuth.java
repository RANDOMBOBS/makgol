package com.org.makgol.common.oauth2.entity;

import com.org.makgol.common.oauth2.entity.type.AuthProvider;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SocialAuth {
    private int id;
    private int user_id;
    private String provider_id;

    private AuthProvider provider;
    private String email;
    private String name;
    private String image_url;
    private String attributes;
    private String ip;
    private Date date;

    public void update(String name, String imageUrl, Map<String, Object> attributes) {

        this.name = name;
        this.image_url = imageUrl;
        this.attributes = attributes.toString();
    }



}
