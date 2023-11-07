package com.org.makgol.main.dao;


import com.org.makgol.category.vo.CategoryListVo;
import com.org.makgol.main.repository.MainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class MainDao {

    private final MainRepository mainRepository;

    public List<CategoryListVo> selectAllCategory() throws DataAccessException {
        List<CategoryListVo> categoryVos = null;
        categoryVos = mainRepository.selectAllCategory();
        return categoryVos;
    }
}
