package com.org.makgol.common.oauth2.util;

import com.org.makgol.common.config.security.AppProperties;
import com.org.makgol.common.jwt.util.JwtUtil;
import com.org.makgol.common.oauth2.exception.OAuth2AuthenticationProcessingException;
import com.org.makgol.common.oauth2.security.UserPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@AllArgsConstructor
@Service
public class TokenProvider {

    private AppProperties appProperties;


    public String creatToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationTime());

        log.info("userPrincipal.getUsername() --> : {}", userPrincipal.getUsername());

        return Jwts.builder()
            .setSubject(userPrincipal.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(JwtUtil.key, SignatureAlgorithm.HS256)
            .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(appProperties.getAuth().getTokenSecret())
            .parseClaimsJws(token)
            .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) { // 유효하지 않은 JWT 서명
            throw new OAuth2AuthenticationProcessingException("not valid jwt signature");
        } catch (MalformedJwtException e) { // 유효하지 않은 JWT
            throw new OAuth2AuthenticationProcessingException("not valid jwt");
        } catch (ExpiredJwtException e) { // 만료된 JWT
            throw new OAuth2AuthenticationProcessingException("expired jwt");
        } catch (UnsupportedJwtException e) { // 지원하지 않는 JWT
            throw new OAuth2AuthenticationProcessingException("unsupported jwt");
        } catch (IllegalArgumentException e) { // 빈값
            throw new OAuth2AuthenticationProcessingException("empty jwt");
        }
    }

}