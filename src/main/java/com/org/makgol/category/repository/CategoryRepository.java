package com.org.makgol.category.repository;

import com.org.makgol.category.vo.CategoryRequestVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryRepository {

    int updateUploadImage (CategoryRequestVo categoryRequestVo);


}
