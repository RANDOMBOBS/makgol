package com.org.makgol.common.jwt.repository;

import com.org.makgol.common.jwt.vo.TokenResponseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface RefreshTokenRepository {
    TokenResponseVo findByUserEmail(String emailFromToken);
}
