package com.org.makgol.main.service;

import java.util.List;

import com.org.makgol.users.vo.UserXy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.makgol.category.vo.CategoryListVo;
import com.org.makgol.main.dao.MainDao;

@Service

public class MainService {
	@Autowired
	MainDao mainDao;

	
	public List<CategoryListVo> getAllCategory() {
		return mainDao.selectAllCategory();
	}

	public List<CategoryListVo> todayMenuList(String category) {
		return mainDao.selectTodayMenu(category);
	}

	public List<CategoryListVo> todayMenuList(UserXy userXy) {
		return mainDao.selectTodayMenu(userXy);
	}

	public List<CategoryListVo> topMenuList() {
		return mainDao.selectTopMenu();
	}
}
