package com.org.makgol.common.config.security;

import com.org.makgol.common.RoleType;
import com.org.makgol.common.jwt.exception.RestAuthenticationEntryPoint;
import com.org.makgol.common.jwt.filter.JwtAuthFilter;
import com.org.makgol.common.jwt.handler.TokenAccessDeniedHandler;
import com.org.makgol.common.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
    private final LogoutHandler logoutService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring().antMatchers("/h2-console/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        //http.cors();
        //http.csrf().disable();

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable() // csrf 설정 해제
                .formLogin().disable() // 소셜로그인만 이용할 것이기 때문에 formLogin 해제
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint()) // 요청이 들어올 시, 인증 헤더를 보내지 않는 경우 401 응답 처리
                .accessDeniedHandler(tokenAccessDeniedHandler)
                .and()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/user/login").permitAll() //회원가입과 로그인을 위한 /api/account/** 로 들어노는 요청은 전부 검증없이 요청을 허용하도록 설정하였다.
                //.antMatchers("/api/account/logout").hasAnyAuthority(RoleType.USER.getCode(), RoleType.ADMIN.getCode())
                //.antMatchers("/", "/user/join", "/user/login").permitAll() //회원가입과 로그인을 위한 /api/account/** 로 들어노는 요청은 전부 검증없이 요청을 허용하도록 설정하였다.
                //.antMatchers("/api/account/logout").hasAnyAuthority(RoleType.USER.getCode(), RoleType.ADMIN.getCode())
                //.antMatchers(HttpMethod.POST, "/api/v1/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .logout(logoutConfig -> { logoutConfig
                        .logoutUrl("/auth/logout")
                        .addLogoutHandler(logoutService)
                        .logoutSuccessHandler(((request, response, authentication) -> SecurityContextHolder.clearContext()));
                });

        return http.build();

    }
}