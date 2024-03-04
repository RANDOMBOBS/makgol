package com.org.makgol.users.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.org.makgol.boards.vo.BoardLikeVo;
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
	private final UsersRepository usersRepository;


	/**
	 * 사용자 정보를 업데이트하는 메서드입니다.
	 *
	 * @param usersRequestVo 업데이트할 사용자 정보를 담은 객체
	 * @return 업데이트 결과를 나타내는 정수값 (-1: 실패, 0 이상: 성공)
	 */
	public int updateUserInfo(UsersRequestVo usersRequestVo){
		int result = -1;
		// 사용자 정보 업데이트 수행
		result = usersRepository.updateUserInfo(usersRequestVo);
		return result;
	}

	/**
	 * 사용자 프로필 사진 정보를 업데이트하는 메서드입니다.
	 *
	 * @param usersRequestVo 업데이트할 사용자 정보를 담은 객체
	 * @return 업데이트 결과를 나타내는 정수값 (-1: 실패, 0 이상: 성공)
	 */
	public int updateUserPhotoInfo(UsersRequestVo usersRequestVo){
		int result = -1;
		// 사용자 프로필 사진 정보 업데이트 수행
		result = usersRepository.updateUserPhotoInfo(usersRequestVo);
		return result;
	}

	/**
	 * 사용자가 등록한 매장 목록을 조회하는 메서드입니다.
	 *
	 * @param user_id 사용자 식별 ID
	 * @return 사용자가 등록한 매장 목록
	 */
	public List<StoreResponseVo> selectMyStoreList(int user_id){
		List<StoreResponseVo> storeVos = null;
		// 사용자가 등록한 매장 목록 조회 수행
		storeVos = usersRepository.selectMyStoreList(user_id);
		return storeVos;
	}

	/**
	 * 사용자가 작성한 게시물 목록을 조회하는 메서드입니다.
	 *
	 * @param user_id 사용자 식별 ID
	 * @return 사용자가 작성한 게시물 목록
	 */
	public List<BoardVo> selectMyPostList(int user_id){
		List<BoardVo> boardVos = null;
		// 사용자가 작성한 게시물 목록 조회 수행
		boardVos = usersRepository.selectMyPostList(user_id);
		return boardVos;
	}

	/**
	 * 사용자가 작성한 댓글 목록을 조회하는 메서드입니다.
	 *
	 * @param user_id 사용자 식별 ID
	 * @return 사용자가 작성한 댓글 목록
	 */
	public List<CommentResponseVo> selectMyCommentList(int user_id){
		List<CommentResponseVo> commentVos = null;
		// 사용자가 작성한 댓글 목록 조회 수행
		commentVos = usersRepository.selectMyCommentList(user_id);
		return commentVos;
	}

	/**
	 * 사용자가 좋아요한 게시물 목록을 조회하는 메서드입니다.
	 *
	 * @param user_id 사용자 식별 ID
	 * @return 사용자가 좋아요한 게시물 목록
	 */
	public List<BoardLikeVo> selectMyLikePostList(int user_id){
		List<BoardLikeVo> boardVos = null;
		// 사용자가 좋아요한 게시물 목록 조회 수행
		boardVos = usersRepository.selectMyLikePostList(user_id);
		return boardVos;
	}


}
