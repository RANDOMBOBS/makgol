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
	

	
	
	// ī�װ� ����Ʈ (��ü����Ʈ, �ѽ�,���,�߽�,�н�,�Ͻ�,ī��/����Ʈ)
	public List<CategoryListVo> categoryList() {
		return categoryDao.selectCategory();
	}
	//�ѽ� ��ư - ī�װ� 
	public List<CategoryListVo> categoryKor() {
		return categoryDao.selectCategoryKor();
	}
	//��� ��ư - ī�װ�
	public List<CategoryListVo> categoryWest() {
		return categoryDao.selectCategoryWest();
	}
	//�߽� ��ư - ī�װ�
	public List<CategoryListVo> categoryChi() {
		return categoryDao.selectCategoryChi();
	}
	//�н� ��ư - ī�װ�
	public List<CategoryListVo> categorySnack() {
		return categoryDao.selectCategorySnack();
	}
	//�Ͻ� ��ư - ī�װ�
	public List<CategoryListVo> categoryJpn() {
		return categoryDao.selectCategoryJpn();
	}
	//ī��/����Ʈ ��ư - ī�װ�
	public List<CategoryListVo> categoryCafe() {
		return categoryDao.selectCategoryCafe();
	}
}
