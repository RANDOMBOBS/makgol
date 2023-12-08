package com.org.makgol.common.oauth2.security;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.org.makgol.common.oauth2.dto.OAuth2UserInfo;
import com.org.makgol.common.oauth2.dto.OAuth2UserInfoFactory;
import com.org.makgol.common.oauth2.entity.SocialAuth;
import com.org.makgol.common.oauth2.entity.type.AuthProvider;
import com.org.makgol.common.oauth2.exception.OAuth2AuthenticationProcessingException;
import com.org.makgol.common.oauth2.infra.UsersRepoUtil;
import com.org.makgol.users.vo.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UsersRepoUtil usersRepoUtil;

    // OAuth2 사용자 로딩
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        log.info("OAuth2User.toString() --> : {}", oAuth2User.toString());
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }

    // OAuth2 사용자 처리
    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        log.info("oAuth2UserRequest.getClientRegistration().getRegistrationId() --> : {}", oAuth2UserRequest.getClientRegistration().getRegistrationId());
        String getRegistrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
                getRegistrationId,
                oAuth2User.getAttributes()
        );

        // 이메일이 비어있으면 예외 발생
        if (StringUtils.isBlank(oAuth2UserInfo.getEmail()))
            throw new OAuth2AuthenticationProcessingException("empty email");

        log.info("oAuth2UserInfo.getEmail() --> : {}", oAuth2UserInfo.getEmail());
        // 이메일을 기준으로 사용자 조회
        Optional<Users> userOptional = usersRepoUtil.findFirstByEmailOrderByIdAsc(oAuth2UserInfo.getEmail());

        Users user;
        if (userOptional.isPresent()) {
            // 이미 가입된 사용자이면서 다른 소셜 프로바이더로 가입된 경우 예외 발생
//            if (!userOptional.get().getSocialAuth().getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId())))
//                throw new OAuth2AuthenticationProcessingException("already sign up other provider");
            log.info("if (userOptional.isPresent()) {");
            user = updateUser(userOptional.get(), oAuth2UserInfo);
            log.info("if (userOptional.isPresent()) {");
        } else {
            // 새로운 사용자 등록
            user = registerUser(oAuth2UserRequest, oAuth2UserInfo);
            log.info("user = registerUser(oAuth2UserRequest, oAuth2UserInfo);");
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    @Transactional
    // 사용자 등록
    private Users registerUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
//        return usersRepoUtil.save(Users.builder()
//                .email(oAuth2UserInfo.getEmail())
//                .name(oAuth2UserInfo.getName())
//                .socialAuth(SocialAuth.builder()
//                        .providerId(oAuth2UserInfo.getId())
//                        .provider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
//                        .email(oAuth2UserInfo.getEmail())
//                        .name(oAuth2UserInfo.getName())
//                        .imageUrl(oAuth2UserInfo.getImageUrl())
//                        .attributes(oAuth2UserInfo.getAttributes().toString())
//                        .ip("127.0.0.1")
//                        .build())
//                .build());

        Users user = usersRepoUtil.save(Users.builder()
                .email(oAuth2UserInfo.getEmail())
                .name(oAuth2UserInfo.getName())
                .photo_path(oAuth2UserInfo.getImageUrl())
                .grade("정회원")
                .photo("social")
                .phone("social")
                .password("social")
                .longitude(127.0309212341660000000000)
                .latitude(37.4924272855457000000000)
                .address("서울 강남구 강남대로 328")
                .build());

        log.info("oAuth2UserInfo.getEmail() --> : {}", oAuth2UserInfo.getEmail());
        log.info("oAuth2UserInfo.getName() --> : {}", oAuth2UserInfo.getName());
        log.info("oAuth2UserInfo.getImageUrl --> : {}", oAuth2UserInfo.getImageUrl());

        usersRepoUtil.saveSocial(SocialAuth.builder()
                        .user_id(user.getId())
                        .provider_id(oAuth2UserInfo.getId())
                        .provider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
                        .email(oAuth2UserInfo.getEmail())
                        .name(oAuth2UserInfo.getName())
                        .image_url(oAuth2UserInfo.getImageUrl())
                        .attributes(oAuth2UserInfo.getAttributes().toString())
                        .ip("127.0.0.1")
                .build());

        return user;
    }

    // 사용자 정보 업데이트
    private Users updateUser(Users user, OAuth2UserInfo oAuth2UserInfo) {

        user.updateSocialAuth(oAuth2UserInfo.getName(), oAuth2UserInfo.getImageUrl(), oAuth2UserInfo.getAttributes());
        //user.getSocialAuth().update(oAuth2UserInfo.getName(), oAuth2UserInfo.getImageUrl(), oAuth2UserInfo.getAttributes());
        return user;
    }

}
