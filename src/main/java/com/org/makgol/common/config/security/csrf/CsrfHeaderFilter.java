package com.org.makgol.common.config.security.csrf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@PropertySource("classpath:/application.properties")
public class CsrfHeaderFilter extends OncePerRequestFilter {
    @Value("${domain.address}")
    private String domainAddress;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // GET 메서드가 아닌 경우에만 Referer 검증 수행
        if (!"GET".equals(request.getMethod())) {
            log.info("equals(request.getMethod())) {");
            CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

            if (csrf != null) {
                // Referer 헤더 가져오기
                log.info("Referer --> : {}", request.getHeader("Referer"));
                String headerValue = request.getHeader("Referer");

                // Referer 검증: 허용된 도메인 중 하나인 경우만 허용
                if (!isAllowedDomain(headerValue)) {
                    // CSRF 토큰이 유효하지 않으면 HTTP 403 Forbidden 에러 반환
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid Referer");
                    return;
                }
            }
        }

        // GET 메서드인 경우에도 또는 검증이 통과한 경우에는 다음 필터로 이동
        filterChain.doFilter(request, response);
    }

    // 허용된 도메인인지 확인
    private boolean isAllowedDomain(String referer) {

        if (referer != null && referer.contains(domainAddress)) {
            // "http://13.209.84.0/"가 포함되어 있다면 처리
            return true;
        } else {
            // "http://13.209.84.0/"가 포함되어 있지 않다면 처리
            return false;
        }
    }
}