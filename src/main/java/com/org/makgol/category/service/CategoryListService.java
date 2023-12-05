package com.org.makgol.category.service;

import java.util.List;

import com.org.makgol.category.repository.CategoryRepository;
import com.org.makgol.category.vo.CategoryRequestVo;
import com.org.makgol.util.file.FileInfo;
import com.org.makgol.util.file.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import com.org.makgol.category.dao.CategoryListDao;
import com.org.makgol.category.vo.CategoryListVo;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CategoryListService {



	private final CategoryListDao categoryDao;

	private final FileUpload fileUpload;

	private final CategoryRepository categoryRepository;

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

	public int updateCateFile (CategoryRequestVo categoryRequestVo) {
		MultipartFile file = categoryRequestVo.getPhotoFile();
		if (categoryRequestVo.getPhotoFile() != null && !file.isEmpty()) {
			FileInfo fileInfo = fileUpload.fileUpload(file);
			categoryRequestVo.setPhotoPath(fileInfo.getPhotoPath());
			categoryRequestVo.setPhoto(fileInfo.getPhotoName());
		} else {
			categoryRequestVo.setPhotoPath("/resources/static/image/김치찌개.jpg");
			categoryRequestVo.setPhoto("김치찌개.jpg");
		}
			return categoryDao.updateCateFile(categoryRequestVo);
	}
}
