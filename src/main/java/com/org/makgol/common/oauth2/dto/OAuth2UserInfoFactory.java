package com.org.makgol.common.oauth2.dto;

import com.org.makgol.common.oauth2.entity.type.AuthProvider;
import com.org.makgol.common.oauth2.exception.OAuth2AuthenticationProcessingException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {

        if (registrationId.equalsIgnoreCase(AuthProvider.kakao.toString())) {
            return new KakaoOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.naver.toString())) {
            return new NaverOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Unsupported Login Type : " + registrationId);
        }
    }

}