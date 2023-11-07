package com.org.makgol.category.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.makgol.category.vo.CategoryListVo;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Component
public class CategoryListDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	


	public List<CategoryListVo> selectCategory(String where) {
		String sql = "SELECT menu FROM category_list " + where;
		List<CategoryListVo> categorys = new ArrayList<CategoryListVo>();
		try {
			RowMapper<CategoryListVo> rowMapper = BeanPropertyRowMapper.newInstance(CategoryListVo.class);
			categorys = jdbcTemplate.query(sql, rowMapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categorys.size() > 0 ? categorys : null;
	}

	public List<CategoryListVo> selectCategory() {
		return selectCategory("");
	}

	public List<CategoryListVo> selectCategoryKor() {
		return selectCategory("WHERE category='한식'");
	}

	public List<CategoryListVo> selectCategoryWest() {
		return selectCategory("WHERE category='양식'");
	}

	public List<CategoryListVo> selectCategoryChi() {
		return selectCategory("WHERE category='중식'");
	}

	public List<CategoryListVo> selectCategorySnack() {
		return selectCategory("WHERE category='분식'");
	}

	public List<CategoryListVo> selectCategoryJpn() {
		return selectCategory("WHERE category='일식'");
	}

	public List<CategoryListVo> selectCategoryCafe() {
		return selectCategory("WHERE category='카페/디저트'");
	}

}
