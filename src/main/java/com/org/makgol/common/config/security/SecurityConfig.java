package com.org.makgol.common.config.security;

import com.org.makgol.common.RoleType;
import com.org.makgol.common.jwt.exception.RestAuthenticationEntryPoint;
import com.org.makgol.common.jwt.filter.JwtAuthFilter;
import com.org.makgol.common.jwt.handler.TokenAccessDeniedHandler;
import com.org.makgol.common.jwt.util.JwtUtil;
import com.org.makgol.common.oauth2.security.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
    private final LogoutHandler logoutService;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        //http.cors();
        //http.csrf().disable();

        http
                //.sessionManagement()
                //.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 사용을 막는다.
                //.and()
                .cors()
                .and()
                .csrf()
                .disable() // csrf 설정 해제
                .formLogin().disable() // 소셜로그인만 이용할 것이기 때문에 formLogin 해제
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint()) // 요청이 들어올 시, 인증 헤더를 보내지 않는 경우 401 응답 처리
                .accessDeniedHandler(tokenAccessDeniedHandler)
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/"                     // main page
                        , "/user/login"                     // request login
                        , "/user/loginConfirm"              // request login
                        , "/user/join"                      // request join
                        , "/resources/static/css/**"        // css
                        , "/resources/static/image/**"      // image
                        , "/resources/static/js/modal/**"   // modal js
                        , "/resources/static/js/csv/**"
                        , "/views/**"
                        , "/user/blackList"
                        , "/main/todayMenuList"
                        , "/main/topMenuList"
                        , "/auth/token", "/oauth2/**"
                        , "/board/suggestion/**"
                )
                .permitAll() //회원가입과 로그인을 위한 /api/account/** 로 들어노는 요청은 전부 검증없이 요청을 허용하도록 설정하였다.
                .antMatchers(
                        "/api/account/logout"
                        , "/user/myPage"
                        , "/user/modifyUser"
                        , "/user/modifyUserConfirm"

                ).hasAnyAuthority(RoleType.USER.getCode(), RoleType.ADMIN.getCode())
                //.antMatchers("/", "/user/join", "/user/login").permitAll() //회원가입과 로그인을 위한 /api/account/** 로 들어노는 요청은 전부 검증없이 요청을 허용하도록 설정하였다.
                //.antMatchers("/api/account/logout").hasAnyAuthority(RoleType.USER.getCode(), RoleType.ADMIN.getCode())
                //.antMatchers(HttpMethod.POST, "/api/v1/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .oauth2Login().authorizationEndpoint()
                .baseUri("/oauth2/authorization") // 소셜 로그인 Url
                .authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository()) // 인증 요청을 쿠키에 저장하고 검색
                .and()
                .redirectionEndpoint()
                .baseUri("/*/oauth2/code/*") // 소셜 인증 후 Redirect Url
                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService) // 소셜의 회원 정보를 받아와 가공처리
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler) // 인증 성공 시 Handler
                .failureHandler(oAuth2AuthenticationFailureHandler); // 인증 실패 시 Handler

                http.addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .logout(logoutConfig -> { logoutConfig
                        .logoutUrl("/user/logout")
                        .addLogoutHandler(logoutService)
                        .logoutSuccessHandler(((request, response, authentication) -> SecurityContextHolder.clearContext()));
                });

        return http.build();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

}