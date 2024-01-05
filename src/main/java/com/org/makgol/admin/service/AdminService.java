package com.org.makgol.admin.service;

import java.util.List;
import java.util.Map;

import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.makgol.admin.dao.AdminDao;

@Service

public class AdminService {
	@Autowired
	AdminDao adminDao;

	/**
	 * 모든 사용자 목록을 가져오는 메서드입니다.
	 *
	 * @return 사용자 응답 객체의 목록을 반환합니다.
	 */
	public List<UsersResponseVo> getUserList() {
		return adminDao.selectAllUserList();
	}

	/**
	 * 사용자 등급을 수정하는 메서드입니다.
	 *
	 * @param userVo 사용자 요청 정보를 담은 객체
	 * @return 수정 결과를 나타내는 정수를 반환합니다.
	 */
	public int modGrade(UsersRequestVo userVo) {
		return adminDao.UpdateGrade(userVo);
	}

	/**
	 * 검색된 사용자 목록을 가져오는 메서드입니다.
	 *
	 * @param map 검색 매개변수를 담은 Map 객체
	 * @return 검색된 사용자 응답 객체의 목록을 반환합니다.
	 */
	public List<UsersResponseVo> getSearchUser(Map<String, String> map){
		return adminDao.selectSearchUserList(map);
	}
}
