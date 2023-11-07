package com.org.makgol.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.makgol.admin.dao.AdminDao;
import com.org.makgol.users.vo.UserVo;

@Service

public class AdminService {
	@Autowired
	AdminDao adminDao;
	
	public List<UserVo> getUserList() {
		return adminDao.selectAllUserList();
	}
	
	public int modGrade(UserVo userVo) {
		return adminDao.UpdateGrade(userVo);
	}
}
