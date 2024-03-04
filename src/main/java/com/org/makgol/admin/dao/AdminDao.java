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

    /**
     * 모든 사용자 목록을 조회하는 메서드입니다.
     *
     * @return 사용자 응답 객체의 목록을 반환합니다.
     */
    public List<UsersResponseVo> selectAllUserList() {
        List<UsersResponseVo> userVos = new ArrayList<UsersResponseVo>();
        userVos = adminRepository.selectAllUserList();
        return userVos;
    }

    /**
     * 사용자 등급을 업데이트하는 메서드입니다.
     *
     * @param userVo 사용자 요청 정보를 담은 객체
     * @return 업데이트 결과를 나타내는 정수를 반환합니다.
     */
    public int UpdateGrade(UsersRequestVo userVo) {
        int result = -1;
        result = adminRepository.UpdateGrade(userVo);
        return result;
    }

    /**
     * 검색된 사용자 목록을 조회하는 메서드입니다.
     *
     * @param map 검색 매개변수를 담은 Map 객체
     * @return 검색된 사용자 응답 객체의 목록을 반환합니다.
     */
    public List<UsersResponseVo> selectSearchUserList(Map<String, String> map){
        List<UsersResponseVo> userVos = adminRepository.selectSearchUserList(map);
        return userVos;
    }
}
