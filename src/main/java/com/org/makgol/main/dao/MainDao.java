package com.org.makgol.main.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.org.makgol.category.vo.CategoryListVo;

@Component

public class MainDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<CategoryListVo> selectAllCategory() {
		String sql = "SELECT DISTINCT category FROM stores";
		List <CategoryListVo> categorys = new ArrayList<CategoryListVo>();
		try {
		RowMapper<CategoryListVo> rowMapper = BeanPropertyRowMapper.newInstance(CategoryListVo.class);
		categorys = jdbcTemplate.query(sql, rowMapper);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return categorys;
	}
}
