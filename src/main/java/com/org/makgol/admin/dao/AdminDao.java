package com.org.makgol.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.org.makgol.users.vo.UserVo;

@Component
public class AdminDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private SqlSession sqlSession;

	public List<UserVo> selectAllUserList() {
		List<UserVo> userVos = new ArrayList<UserVo>();
		userVos = sqlSession.selectList("mapper.admin.selectAllUserList");
		return userVos;
	}

	public int UpdateGrade(UserVo userVo) {
		int result = -1;
		result = sqlSession.update("mapper.admin.UpdateGrade", userVo);
		return result;
	}
}
