package com.org.makgol.category.service;

import java.util.List;

import com.org.makgol.category.repository.CategoryRepository;
import com.org.makgol.category.vo.CategoryRequestVo;
import com.org.makgol.util.file.FileInfo;
import com.org.makgol.util.file.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.org.makgol.category.vo.CategoryListVo;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CategoryListService {

	private final FileUpload fileUpload;

	private final CategoryRepository categoryRepository;

	public List<CategoryListVo> selectCategory(String where) {
		return categoryRepository.selectCategory(where);
	}
	public List<CategoryListVo> categoryList() {
		return selectCategory("");
	}
	public List<CategoryListVo> categoryKor() {
		return selectCategory("WHERE category='한식'");
	}
	public List<CategoryListVo> categoryWest() {
		return selectCategory("WHERE category='양식'");
	}
	public List<CategoryListVo> categoryChi() {
		return selectCategory("WHERE category='중식'");
	}
	public List<CategoryListVo> categorySnack() {
		return selectCategory("WHERE category='분식'");
	}
	public List<CategoryListVo> categoryJpn() {
		return selectCategory("WHERE category='일식'");
	}
	public List<CategoryListVo> categoryCafe() {
		return selectCategory("WHERE category='카페'");
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
		int result = -1;
		result = categoryRepository.updateUploadImage(categoryRequestVo);
		return result;
	}
}

