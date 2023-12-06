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

	public List<UsersResponseVo> getUserList() {
		return adminDao.selectAllUserList();
	}

	public int modGrade(UsersRequestVo userVo) {
		return adminDao.UpdateGrade(userVo);
	}

	public List<UsersResponseVo> getSearchUser(Map<String, String> map){
		return adminDao.selectSearchUserList(map);
	}
}
