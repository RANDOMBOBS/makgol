package com.org.makgol.admin.repository;

import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminRepository {
    List<UsersResponseVo> selectAllUserList();

    int UpdateGrade(UsersRequestVo usersRequestVo);

}
