package com.org.makgol.admin.repository;

import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminRepository {
    /**
     * 모든 사용자 목록을 조회하는 메서드입니다.
     *
     * @return 사용자 응답 객체의 목록을 반환합니다.
     */
    List<UsersResponseVo> selectAllUserList();

    /**
     * 사용자 등급을 업데이트하는 메서드입니다.
     *
     * @param usersRequestVo 사용자 요청 정보를 담은 객체
     * @return 업데이트 결과를 나타내는 정수를 반환합니다.
     */
    int UpdateGrade(UsersRequestVo usersRequestVo);

    /**
     * 검색된 사용자 목록을 조회하는 메서드입니다.
     *
     * @param map 검색 매개변수를 담은 Map 객체
     * @return 검색된 사용자 응답 객체의 목록을 반환합니다.
     */
    List<UsersResponseVo> selectSearchUserList(Map<String, String> map);
}
