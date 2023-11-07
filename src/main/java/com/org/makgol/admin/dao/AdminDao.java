package com.org.makgol.admin.dao;

import com.org.makgol.admin.repository.AdminRepository;
import com.org.makgol.users.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminDao {


    private final AdminRepository adminRepository;

    public List<UserVo> selectAllUserList() {
        List<UserVo> userVos = null;
        userVos = adminRepository.selectAllUserList();
        return userVos;
    }

    public int UpdateGrade(UserVo userVo) {
        int result = -1;
        result = adminRepository.UpdateGrade(userVo);
        return result;
    }
}
