package com.org.makgol.common.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.makgol.common.GlobalResDto;
import com.org.makgol.common.exception.CustomException;
import com.org.makgol.common.exception.ErrorCode;
import com.org.makgol.common.jwt.util.JwtUtil;
import com.org.makgol.common.jwt.vo.TokenResponseVo;
import com.org.makgol.users.service.UsersService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UsersService usersService;


    @Override
    // HTTP 요청이 오면 WAS(tomcat)가 HttpServletRequest, HttpServletResponse 객체를 만들어 줍니다.
    // 만든 인자 값을 받아옵니다.
    // 요청이 들어오면 diFilterInternal 이 딱 한번 실행된다.
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // WebSecurityConfig 에서 보았던 UsernamePasswordAuthenticationFilter 보다 먼저 동작을 하게 됩니다.

        // Access / Refresh 헤더에서 토큰을 가져옴.
        String accessToken = jwtUtil.getToken(request, "Access", jwtUtil.COOKIE);
        log.info("accessToken --> : {}", accessToken);

        String email = jwtUtil.getEmailFromToken(accessToken);
        Optional<TokenResponseVo> tokenResponseVo = Optional.of(new TokenResponseVo());
        try{
            tokenResponseVo = jwtUtil.findbyEmailandDate(email, jwtUtil.getPayloadRefreshDate(accessToken));
        } catch (Exception e){}
        try {

            if (accessToken != null) {
                // 어세스 토큰값이 유효하다면 setAuthentication를 통해
                // security context에 인증 정보저장
                if (jwtUtil.tokenValidation(accessToken) && jwtUtil.checkExEndRv(tokenResponseVo.get().getToken())) {
                    setAuthentication(jwtUtil.getEmailFromToken(accessToken));
                }
                // 어세스 토큰이 만료된 상황 && 리프레시 토큰 또한 존재하는 상황
                else if (tokenResponseVo.isPresent()) {
                    // 리프레시 토큰 검증 && 리프레시 토큰 DB에서  토큰 존재유무 확인
                    boolean isRefreshToken = jwtUtil.refreshTokenValidation(tokenResponseVo.get().getToken());

                    // 리프레시 토큰이 유효하고 리프레시 토큰이 DB와 비교했을때 똑같다면
                    if (isRefreshToken) {
                        // 리프레시 토큰으로 아이디 정보 가져오기
                        String loginId = jwtUtil.getEmailFromToken(tokenResponseVo.get().getToken());
                        // 새로운 어세스 토큰 발급
                        String newAccessToken = jwtUtil.createAccessToken(loginId, tokenResponseVo.get().getData());
                        // 헤더에 어세스 토큰 추가 -> cookie로 변경
                        // jwtUtil.setHeaderAccessToken(response, newAccessToken);
                        usersService.setTokenInCookie(response, newAccessToken, "Access");
                        // Security context에 인증 정보 넣기
                        setAuthentication(jwtUtil.getEmailFromToken(newAccessToken));
                    } else {
                        jwtExceptionHandler(response, "RefreshToken Expired", HttpStatus.BAD_REQUEST);
                        jwtUtil.saveTokenUpdate(tokenResponseVo.get().getToken(), JwtUtil.EXPIRED);
                        return;
                    }
                }
            }
        } catch (SignatureException e) {
            log.info("잘못된 JWT 서명입니다.");
            request.setAttribute("exception", ErrorCode.WRONG_TYPE_SIGNATURE.getCode());
        } catch (MalformedJwtException e) {
            log.info("유효하지 않은 구성의 JWT 토큰입니다.");
            request.setAttribute("exception", ErrorCode.WRONG_TYPE_TOKEN.getCode());
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            request.setAttribute("exception", ErrorCode.EXPIRED_ACCESS_TOKEN.getCode());
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 형식이나 구성의 JWT 토큰입니다.");
            request.setAttribute("exception", ErrorCode.WRONG_TYPE_TOKEN.getCode());
        } catch (IllegalArgumentException e) {
            log.info(e.toString().split(":")[1].trim());
            request.setAttribute("exception", ErrorCode.INVALID_ACCESS_TOKEN.getCode());
        } catch (CustomException e) {
            System.out.println("에러가 발생했을 때, request에 attribute를 세팅하고 RestAuthenticationEntryPoint로 request를 넘겨준다.");
            log.info("Access Token이 존재하지 않습니다.");
            request.setAttribute("exception", ErrorCode.ACCESS_TOKEN_NOT_EXIST.getCode());
        }

        filterChain.doFilter(request, response);
    }

    // SecurityContext 에 Authentication 객체를 저장합니다.
    public void setAuthentication(String email) {
        Authentication authentication = jwtUtil.createAuthentication(email);
        // security가 만들어주는 securityContextHolder 그 안에 authentication을 넣어줍니다.
        // security가 securitycontextholder에서 인증 객체를 확인하는데
        // jwtAuthfilter에서 authentication을 넣어주면 UsernamePasswordAuthenticationFilter 내부에서 인증이 된 것을 확인하고 추가적인 작업을 진행하지 않습니다.
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    // Jwt 예외처리
    public void jwtExceptionHandler(HttpServletResponse response, String msg, HttpStatus status) {
        response.setStatus(status.value());
        response.setContentType("application/json");
        try {
            String json = new ObjectMapper().writeValueAsString(new GlobalResDto(msg, status.value()));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}