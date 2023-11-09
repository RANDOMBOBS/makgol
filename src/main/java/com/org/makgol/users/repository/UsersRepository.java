package com.org.makgol.users.repository;

import com.org.makgol.users.vo.UsersRequestVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersRepository {

    Boolean insertUser(UsersRequestVo usersRequestVo);
    String duplicationUserEmail(String email);
}
