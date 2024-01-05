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

	/**
	 * 주어진 조건에 따라 카테고리 목록을 조회합니다.
	 *
	 * @param where 조회 조건
	 * @return 카테고리 목록
	 */
	public List<CategoryListVo> selectCategory(String where) {
		return categoryRepository.selectCategory(where);
	}

	/**
	 * 모든 카테고리 목록을 조회합니다.
	 *
	 * @return 모든 카테고리 목록
	 */
	public List<CategoryListVo> categoryList() {
		return selectCategory("");
	}

	/**
	 * 한식 카테고리 목록을 조회합니다.
	 *
	 * @return 한식 카테고리 목록
	 */
	public List<CategoryListVo> categoryKor() {
		return selectCategory("WHERE category='한식'");
	}

	/**
	 * 양식 카테고리 목록을 조회합니다.
	 *
	 * @return 양식 카테고리 목록
	 */
	public List<CategoryListVo> categoryWest() {
		return selectCategory("WHERE category='양식'");
	}

	/**
	 * 중식 카테고리 목록을 조회합니다.
	 *
	 * @return 중식 카테고리 목록
	 */
	public List<CategoryListVo> categoryChi() {
		return selectCategory("WHERE category='중식'");
	}

	/**
	 * 분식 카테고리 목록을 조회합니다.
	 *
	 * @return 분식 카테고리 목록
	 */
	public List<CategoryListVo> categorySnack() {
		return selectCategory("WHERE category='분식'");
	}

	/**
	 * 일식 카테고리 목록을 조회합니다.
	 *
	 * @return 일식 카테고리 목록
	 */
	public List<CategoryListVo> categoryJpn() {
		return selectCategory("WHERE category='일식'");
	}

	/**
	 * 카페 카테고리 목록을 조회합니다.
	 *
	 * @return 카페 카테고리 목록
	 */
	public List<CategoryListVo> categoryCafe() {
		return selectCategory("WHERE category='기타'");
	}

	/**
	 * 카테고리 이미지를 업데이트하고 그 결과를 반환합니다.
	 *
	 * @param categoryRequestVo 카테고리 이미지 업데이트에 필요한 정보를 담은 객체
	 * @return 이미지 업데이트 결과 (성공 시 1, 실패 시 -1)
	 */
	public int updateCateFile(CategoryRequestVo categoryRequestVo) {
		MultipartFile file = categoryRequestVo.getPhotoFile();

		// 카테고리 이미지가 존재하고 비어있지 않은 경우
		if (file != null && !file.isEmpty()) {
			FileInfo fileInfo = fileUpload.fileUpload(file);
			categoryRequestVo.setPhotoPath(fileInfo.getPhotoPath());
			categoryRequestVo.setPhoto(fileInfo.getPhotoName());
		} else {
			// 카테고리 이미지가 존재하지 않거나 비어있는 경우 기본 이미지로 설정
			categoryRequestVo.setPhotoPath("/resources/static/image/김치찌개.jpg");
			categoryRequestVo.setPhoto("김치찌개.jpg");
		}

		int result = -1;
		result = categoryRepository.updateUploadImage(categoryRequestVo);
		return result;
	}
}

