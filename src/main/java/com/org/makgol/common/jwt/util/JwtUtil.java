package com.org.makgol.common.jwt.util;

import com.org.makgol.common.config.security.user.UserDetailsServiceImpl;
import com.org.makgol.common.jwt.repository.RefreshTokenRepository;
import com.org.makgol.common.jwt.vo.TokenResponseVo;
import com.org.makgol.common.jwt.vo.TokenVo;
import com.org.makgol.common.oauth2.security.UserPrincipal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final UserDetailsServiceImpl userDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

//    private static final long ACCESS_TIME = 30 * 60 * 1000L;
//    private static final long REFRESH_TIME =  7 * 24 * 60 * 60 * 1000L;

    private static final long ACCESS_TIME = 30 * 60 * 1000L;
    private static final long REFRESH_TIME = 7 * 24 * 60 * 60 * 1000L;
    public static final String ACCESS_TOKEN = "Access_Token";
    public static final String REFRESH_TOKEN = "Refresh_Token";
    public static final String HEADER = "header";
    public static final String COOKIE = "cookie";
    public static final String REVOKED = "revoked";
    public static final String EXPIRED = "expired";


    @Value("${domain.name}")
    private String domainName;

    @Value("${jwt.secret.key}")
    private String secretKey;
    public static Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    // bean으로 등록 되면서 딱 한번 실행이 됩니다.
    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    // header, cookie 토큰을 가져오는 기능
    public String getToken(HttpServletRequest request, String type, String type2) {
        if (type2.equals(HEADER)) {
            return type.equals("Access") ? request.getHeader(ACCESS_TOKEN) : request.getHeader(REFRESH_TOKEN);

        } else {
            String accessToken = "";
            String refreshToken = "";
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    switch (cookie.getName()) {
                        case ACCESS_TOKEN:
                            accessToken = cookie.getValue();
                            continue;
                        case REFRESH_TOKEN:
                            refreshToken = cookie.getValue();
                            continue;

                    }
                }
            }
            return type.equals("Access") ? accessToken : refreshToken;
        }
    }

    // 토큰 생성
    public TokenVo createAllToken(String email) {
        return new TokenVo(createToken(email, "Access"), createToken(email, "Refresh"));
    }


    public TokenVo createSettingToken(String email) {

        String refreshToken = createToken(email, "Refresh");
        saveToken(refreshToken, email);
        Optional<TokenResponseVo> tokenResponseVo = findByToken(refreshToken);
        log.info("tokenResponseVo.get().getDate() --> : {}", tokenResponseVo.get().getDate());
        String accessToken = createAccessToken(email, tokenResponseVo.get().getDate());

        return new TokenVo(accessToken, refreshToken);
    }

    public String formatDate(Date date){
        // 포맷을 원하는 형식으로 설정합니다.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 날짜를 문자열로 변환합니다.

        return dateFormat.format(date);
    }


    public String createToken(String email, String type) {

        Date date = new Date();

        long time = type.equals("Access") ? ACCESS_TIME : REFRESH_TIME;

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(date.getTime() + time))
                //.setExpiration(new Date(System.currentTimeMillis() + time))
                .setIssuedAt(date)
                .signWith(key, signatureAlgorithm)
                .compact();

    }

    public String createAccessToken(String email, String refreshDate) {

        Date date = new Date();

        long time = ACCESS_TIME;

        return Jwts.builder()
                .setSubject(email)
                .claim("refreshDate", refreshDate)
                .setExpiration(new Date(date.getTime() + time))
                //.setExpiration(new Date(System.currentTimeMillis() + time))
                .setIssuedAt(date)
                .signWith(key, signatureAlgorithm)
                .compact();

    }

    // 토큰 검증
    public Boolean tokenValidation(String token) {
        try {
            Jwts.parserBuilder().
                    setSigningKey(key).
                    build().
                    parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    // refreshToken 토큰 검증
    // db에 저장되어 있는 token과 비교
    // db에 저장한다는 것이 jwt token을 사용한다는 강점을 상쇄시킨다.
    // db 보다는 redis를 사용하는 것이 더욱 좋다. (in-memory db기 때문에 조회속도가 빠르고 주기적으로 삭제하는 기능이 기본적으로 존재합니다.)
    public Boolean refreshTokenValidation(String token) {

        // 1차 토큰 검증
        if (!tokenValidation(token)) return false;

        // DB에 저장한 토큰 비교
        //Optional<RefreshToken> refreshToken = refreshTokenRepository.findByUserEmail(getEmailFromToken(token));
        Optional<TokenResponseVo> refreshToken = findTokenByEmail(getEmailFromToken(token));


        return refreshToken.isPresent() && token.equals(refreshToken.get().getToken());
    }


    // 인증 객체 생성
    public Authentication createAuthentication(String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        // spring security 내에서 가지고 있는 객체입니다. (UsernamePasswordAuthenticationToken)

        log.info("userDetails --> : {} ", userDetails.getUsername());
        log.info("userDetails --> : {} ", userDetails.getAuthorities());

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 email 가져오는 기능
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 토큰에서 refreshDate 가져오는 기능
    public String getPayloadRefreshDate(String token) {
        return (String) Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("refreshDate");
    }

    // 악세스 토큰 헤더 설정
    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("Access_Token", accessToken);
    }

    // 리프레시 토큰 헤더 설정
    public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
        response.setHeader("Refresh_Token", refreshToken);
    }


    // 토큰 검색 쿼리
    public Optional<TokenResponseVo> findTokenByEmail(String email) {
        TokenResponseVo tokenResponseVo = refreshTokenRepository.findByEmail(email);
        return Optional.ofNullable(tokenResponseVo);
    }

    public Optional<TokenResponseVo> findByToken(String refreshToken) {
        TokenResponseVo tokenResponseVo = refreshTokenRepository.findByToken(refreshToken);
        return Optional.ofNullable(tokenResponseVo);
    }

    public Optional<TokenResponseVo> findbyEmailandDate(String email, String refreshDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        map.put("date", refreshDate);
        TokenResponseVo tokenResponseVo = refreshTokenRepository.findbyEmailandDate(map);
        return Optional.ofNullable(tokenResponseVo);
    }

    public void saveToken(String token, String email) {

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("email", email);
        map.put("expired", false);
        map.put("revoked", false);

        refreshTokenRepository.save(map);
        //return Optional.ofNullable(tokenResponseVo);
    }

    public void saveTokenUpdate(String email, String type) {

        if (type.equals(REVOKED)) {
            refreshTokenRepository.saveUpdateRevoked(email);

        } else if (type.equals(EXPIRED)) {
            refreshTokenRepository.saveUpdateExpried(email);
        }
    }

    public boolean checkExEndRv(String token) {
        try {
            tokenValidation(token);
            return refreshTokenRepository.checkExEndRv(token);
        } catch (Exception e) {
            return false;
        }
    }


    public void setTokenInCookie(HttpServletResponse response, String token, String type) {

        ResponseCookie cookie = type.equals("Access") ? ResponseCookie.from(JwtUtil.ACCESS_TOKEN, token)
                .domain(domainName)
                .path("/")
                .sameSite("Lax")            //sameSite 모르면 검색!! 중요함!!! 돼지꼬리 떙떙!!
                .httpOnly(true)
                .secure(false)  //https
                .maxAge(2 * 60 * 60)
                .build()
                : ResponseCookie.from(JwtUtil.REFRESH_TOKEN, token)
                .domain(domainName)
                .path("/")
                .sameSite("Lax")            //sameSite 모르면 검색!! 중요함!!! 돼지꼬리 떙떙!!
                .httpOnly(true)
                .secure(false)
                //.maxAge(7 * 24 * 60 * 60)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());


//        Cookie cookie = new Cookie(JwtUtil.REFRESH_TOKEN, refreshToken);
//        cookie.setDomain(domainName);       // 여기서는 localhost로 설정되어 해당 도메인에서만 쿠키가 유효합니다.
//        cookie.setPath("/");                // "/"로 설정되어 해당 도메인 전체에서 쿠키가 유효합니다.
//        cookie.setMaxAge(7 * 24 * 60 * 60); // 1주일간 유지
//        cookie.setHttpOnly(true);           //javascript로 접근이 불가능하게 함
//        cookie.setSecure(false);           //https 일 경우에만 쿠키 전송
//          response.addCookie(cookie);
    }

}