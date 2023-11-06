package com.org.makgol.user.repository;

import com.org.makgol.user.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRepository {
    @Select("SELECT * FROM users WHERE email = #{email}")
    UserVo findByEmail(String email);
}
