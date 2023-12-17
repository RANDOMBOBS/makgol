package com.org.makgol.main.dao;


import com.org.makgol.category.vo.CategoryListVo;
import com.org.makgol.main.repository.MainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class MainDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final MainRepository mainRepository;

    public List<CategoryListVo> selectAllCategory() throws DataAccessException {
        List<CategoryListVo> categoryVos = null;
        categoryVos = mainRepository.selectAllCategory();
        return categoryVos;
    }


    // 오늘의 메뉴 (no mybatis)
    public List<CategoryListVo> selectTodayMenu(String where) {
        String sql = "SELECT menu,photo,photoPath FROM menus " + where;
        List<CategoryListVo> categorys = new ArrayList<CategoryListVo>();
        try {
            RowMapper<CategoryListVo> rowMapper = BeanPropertyRowMapper.newInstance(CategoryListVo.class);
            categorys = jdbcTemplate.query(sql, rowMapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorys.size() > 0 ? categorys : null;
    }

    public List<CategoryListVo> selectTodayMenu() {
        return selectTodayMenu("");
    }

    // Top5 메뉴
    public List<CategoryListVo> selectTopMenu() {
        String sql = "SELECT name,photo FROM stores";
        List<CategoryListVo> categorys = new ArrayList<CategoryListVo>();
        try {
            RowMapper<CategoryListVo> rowMapper = BeanPropertyRowMapper.newInstance(CategoryListVo.class);
            categorys = jdbcTemplate.query(sql, rowMapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorys.size() > 0 ? categorys : null;
    }

}
