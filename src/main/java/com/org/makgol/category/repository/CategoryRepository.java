package com.org.makgol.category.repository;

import com.org.makgol.category.vo.CategoryListVo;
import com.org.makgol.category.vo.CategoryRequestVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryRepository {

    /**
     * 주어진 조건에 따라 카테고리 목록을 조회합니다.
     *
     * @param where 조회 조건
     * @return 카테고리 목록
     */
    List<CategoryListVo> selectCategory(String where);

    /**
     * 카테고리 이미지를 업로드하여 업데이트합니다.
     *
     * @param categoryRequestVo 업로드할 카테고리 이미지 정보를 담고 있는 객체
     * @return 업데이트 결과를 나타내는 정수 값
     */
    int updateUploadImage(CategoryRequestVo categoryRequestVo);
}

