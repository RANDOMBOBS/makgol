package com.org.makgol.admin.repository;

import com.org.makgol.users.vo.UsersRequestVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminRepository {
    List<UsersRequestVo> selectAllUserList();

    int UpdateGrade(UsersRequestVo usersRequestVo);

}
