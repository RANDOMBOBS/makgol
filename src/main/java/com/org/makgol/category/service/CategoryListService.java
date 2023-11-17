package com.org.makgol.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.makgol.category.dao.CategoryListDao;
import com.org.makgol.category.vo.CategoryListVo;

@Service
public class CategoryListService {

	
	@Autowired
	CategoryListDao categoryDao;
	

	
	
	public List<CategoryListVo> categoryList() {
		return categoryDao.selectCategory();
	}
	public List<CategoryListVo> categoryKor() {
		return categoryDao.selectCategoryKor();
	}
	public List<CategoryListVo> categoryWest() {
		return categoryDao.selectCategoryWest();
	}
	public List<CategoryListVo> categoryChi() {
		return categoryDao.selectCategoryChi();
	}
	public List<CategoryListVo> categorySnack() {
		return categoryDao.selectCategorySnack();
	}
	public List<CategoryListVo> categoryJpn() {
		return categoryDao.selectCategoryJpn();
	}
	public List<CategoryListVo> categoryCafe() {
		return categoryDao.selectCategoryCafe();
	}
}
