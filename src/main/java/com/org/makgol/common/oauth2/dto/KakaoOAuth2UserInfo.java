package com.org.makgol.common.oauth2.dto;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
@Slf4j
public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    private Long id;

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super((Map<String, Object>) attributes.get("kakao_account"));
        this.id = Long.parseLong(String.valueOf(attributes.get("id")));
    }

    @Override
    public String getId() {
        return String.valueOf(this.id);
    }

    @Override
    public String getName() {
        return (String) ((Map<String, Object>) attributes.get("profile")).get("nickname");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) ((Map<String, Object>) attributes.get("profile")).get("thumbnail_image_url");
    }

}
