package com.org.makgol.common.oauth2.security;

import com.org.makgol.common.oauth2.util.CookieUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.org.makgol.common.oauth2.security.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    // OAuth2 로그인 실패 핸들러
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        // 실패 시 리다이렉트할 URL을 설정
        String targetUrl = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue)
                .orElse(("/"));

        // 실패 메시지를 출력 (예제에서는 콘솔에 출력)
        log.error("OAuth2 Authentication Failed: {}", exception.getLocalizedMessage());

        // 실패 시 리다이렉트 URL에 토큰 및 에러 메시지를 추가하여 구성
        targetUrl = UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", "")
                .queryParam("error", exception.getLocalizedMessage())
                .build().toUriString();

        // 인증 요청 쿠키 삭제
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);

        // 지정된 URL로 리다이렉트
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

}
