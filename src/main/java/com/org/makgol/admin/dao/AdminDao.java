package com.org.makgol.admin.dao;

import com.org.makgol.admin.repository.AdminRepository;
import com.org.makgol.users.vo.UsersRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminDao {


    private final AdminRepository adminRepository;

    public List<UsersRequestVo> selectAllUserList() {
        List<UsersRequestVo> userVos = null;
        userVos = adminRepository.selectAllUserList();
        return userVos;
    }

    public int UpdateGrade(UsersRequestVo usersRequestVo) {
        int result = -1;
        result = adminRepository.UpdateGrade(usersRequestVo);
        return result;
    }
}
