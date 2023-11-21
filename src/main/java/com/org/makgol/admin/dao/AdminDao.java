package com.org.makgol.admin.dao;

import java.util.ArrayList;
import java.util.List;

import com.org.makgol.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.org.makgol.users.vo.UsersRequestVo;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminDao {

    private final AdminRepository adminRepository;

    public List<UsersRequestVo> selectAllUserList() {
        List<UsersRequestVo> userVos = new ArrayList<UsersRequestVo>();
        userVos = adminRepository.selectAllUserList();
        return userVos;
    }

    public int UpdateGrade(UsersRequestVo userVo) {
        int result = -1;
        result = adminRepository.UpdateGrade(userVo);
        return result;
    }
}
