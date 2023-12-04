package com.org.makgol.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.org.makgol.admin.repository.AdminRepository;
import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminDao {

    private final AdminRepository adminRepository;

    public List<UsersResponseVo> selectAllUserList() {
        List<UsersResponseVo> userVos = new ArrayList<UsersResponseVo>();
        userVos = adminRepository.selectAllUserList();
        return userVos;
    }

    public int UpdateGrade(UsersRequestVo userVo) {
        int result = -1;
        result = adminRepository.UpdateGrade(userVo);
        return result;
    }

    public List<UsersResponseVo> selectSearchUserList(Map<String, String> map){
        List<UsersResponseVo> userVos = adminRepository.selectSearchUserList(map);
        return userVos;
    }
}
