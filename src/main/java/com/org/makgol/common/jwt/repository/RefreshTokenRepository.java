package com.org.makgol.common.jwt.repository;

import com.org.makgol.common.jwt.vo.TokenResponseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
import java.util.Optional;

@Mapper
public interface RefreshTokenRepository {
    TokenResponseVo findByEmail(String emailFromToken);
    void save(Map<String, Object> map);
    void saveUpdateRevoked(String email);
    void saveUpdateExpried(String email);
}
