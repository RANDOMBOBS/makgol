package com.org.makgol.main.repository;


import com.org.makgol.category.vo.CategoryListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainRepository {

    /**
     * 모든 카테고리를 조회하는 메서드입니다.
     *
     * @return 카테고리 목록
     */
    public List<CategoryListVo> selectAllCategory();
}
