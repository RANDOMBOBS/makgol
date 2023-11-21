package com.org.makgol.main.repository;


import com.org.makgol.category.vo.CategoryListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainRepository {
    List<CategoryListVo> selectAllCategory();
}
