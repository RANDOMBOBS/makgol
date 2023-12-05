package com.org.makgol.category.repository;

import com.org.makgol.category.vo.CategoryListVo;
import com.org.makgol.category.vo.CategoryRequestVo;
import com.org.makgol.util.file.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface CategoryRepository {



    int updateUploadImage(CategoryRequestVo categoryRequestVo);
}
