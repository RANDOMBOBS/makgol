package com.org.makgol.users.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.stores.vo.StoreResponseVo;
import com.org.makgol.users.repository.UsersRepository;
import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserDao {
		
	private final JdbcTemplate jdbcTemplate;
	private final UsersRepository usersRepository;


	// 사용자 정보 조회 (로그인)
	public UsersResponseVo selectUser(String email) {
		List<UsersResponseVo> list = null;
		list = usersRepository.selectUser(email);
		return list.size()>0 ? list.get(0) : null;
	}

	public int updateUserInfo(UsersRequestVo usersRequestVo){
		int result = -1;
		result = usersRepository.updateUserInfo(usersRequestVo);
		return result;
	}

	public List<StoreResponseVo> selectMyStoreList(int user_id){
		List<StoreResponseVo> storeVos = null;
		storeVos = usersRepository.selectMyStoreList(user_id);
		return storeVos;
	}

	public List<BoardVo> selectMyPostList(int user_id){
		List<BoardVo> boardVos = null;
		boardVos = usersRepository.selectMyPostList(user_id);
		return boardVos;
	}

	public List<CommentResponseVo> selectMyCommentList(int user_id){
		List<CommentResponseVo> commentVos = null;
		commentVos = usersRepository.selectMyCommentList(user_id);
		return commentVos;
	}

	public List<BoardVo> selectMyLikePostList(int user_id){
		List<BoardVo> boardVos = null;
		boardVos = usersRepository.selectMyLikePostList(user_id);
		return boardVos;
	}

}
