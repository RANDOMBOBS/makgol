package com.org.makgol.category.repository;

import com.org.makgol.category.vo.CategoryListVo;
import com.org.makgol.category.vo.CategoryRequestVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryRepository {

    int updateUploadImage(CategoryRequestVo categoryRequestVo);


    List<CategoryListVo> selectCategory(String where);
}

