package com.org.makgol.common.jwt.repository;

import com.org.makgol.common.jwt.vo.TokenResponseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
import java.util.Optional;

@Mapper
public interface RefreshTokenRepository {
    TokenResponseVo findTokenByEmail(String emailFromToken);
    void saveToken(Map<String, Object> map);
    void saveTokenUpdateRevoked(String email);
    void saveTokenUpdateExpried(String email);
}
