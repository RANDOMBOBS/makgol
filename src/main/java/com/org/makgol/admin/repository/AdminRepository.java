package com.org.makgol.admin.repository;

import com.org.makgol.users.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminRepository {
    List<UserVo> selectAllUserList();

    int UpdateGrade(UserVo userVo);

}
