package com.org.makgol.admin.repository;

import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminRepository {
    List<UsersResponseVo> selectAllUserList();

    int UpdateGrade(UsersRequestVo usersRequestVo);

    List<UsersResponseVo> selectSearchUserList(Map<String, String> map);
}
