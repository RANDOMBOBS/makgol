package com.org.makgol.common.oauth2.security;

import com.org.makgol.common.config.security.AppProperties;
import com.org.makgol.common.jwt.util.JwtUtil;
import com.org.makgol.common.jwt.vo.TokenVo;
import com.org.makgol.common.oauth2.exception.BadRequestException;
import com.org.makgol.common.oauth2.util.CookieUtils;
import com.org.makgol.common.oauth2.util.TokenProvider;
import com.org.makgol.users.repository.UsersRepository;
import com.org.makgol.users.service.UsersService;
import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import com.org.makgol.util.cookie.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static com.org.makgol.common.oauth2.security.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final CookieUtil    cookieUtil;
    private final JwtUtil       jwtUtil;
    private final AppProperties appProperties;
    private final UsersRepository usersRepository;

    private final ServletContext servletContext;

    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    // OAuth2 로그인 성공 핸들러
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("request --> : {}", request.getHeader("Referer"));
        String targetUrl = determineTargetUrl(request, response, authentication);
        if (response.isCommitted()) {
            logger.debug("response has already been committed. unable to redirect to " + targetUrl);
            return;
        }
        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    @Override
    // 타겟 URL 결정
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue);

        // 리다이렉트 URI가 존재하면서 인가된 URI가 아닌 경우 예외 발생
        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get()))
            throw new BadRequestException("unauthorized Redirect URI");

        String targetUri = redirectUri.orElse(getDefaultTargetUrl());

        //String token = tokenProvider.creatToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        log.info("userPrincipal.getEmail() --> {}", userPrincipal.getEmail());
        String email = userPrincipal.getEmail();

        // 1. 기존에 있던 리프레쉬 토큰을 취소 시킨다.
        jwtUtil.saveTokenUpdate(email, JwtUtil.REVOKED);
        // 2. 리프레쉬를 만들고 date 값을 가져와 access 토큰에 주입 시켜 연관관계를 형성 시킨다.
        TokenVo token = jwtUtil.createSettingToken(email);
        // 3. 악세스 토큰을 쿠키에 담아 클라이언트에게 전송 시킨다.
        jwtUtil.setTokenInCookie(response, token.getAccessToken(), "Access");

        UsersResponseVo usersResponseVo = usersRepository.findUserByEmail(email);

        log.info("usersResponseVo --> {} :", usersResponseVo.toString());

        cookieUtil.saveCookies(response, usersResponseVo);

        servletContext.setAttribute("loginedUserVo", usersResponseVo);

        String urlString = request.getHeader("Referer");

        log.info("urlString --> : {} ", urlString);
        String path;
        try {
            URI uri = new URI(urlString);
            path = uri.getPath(); // 경로 부분을 얻어옴
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return UriComponentsBuilder.fromUriString(urlString)
                //.queryParam("error", "")
                //.queryParam("token", token.getAccessToken())
                .build().toUriString();
    }

    // 인증 속성 지우기
    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    // 인가된 리다이렉트 URI 확인
    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);
        return appProperties.getOauth2().getAuthorizedRedirectUris()
                .stream()
                .anyMatch(authorizedRedirectUri -> {
                    URI authorizedURI = URI.create(authorizedRedirectUri);
                    if (authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost()) && authorizedURI.getPort() == clientRedirectUri.getPort()) {
                        return true;
                    }
                    return false;
                });
    }

}
