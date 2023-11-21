package com.org.makgol.users.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.stores.vo.StoreResponseVo;
import com.org.makgol.users.repository.UsersRepository;
import com.org.makgol.users.vo.UsersResponseVo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.org.makgol.users.vo.UsersRequestVo;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserDao {
		
	private final JdbcTemplate jdbcTemplate;
	private final UsersRepository usersRepository;
	

	//db insert 쿼리 전송
	public boolean insertAuthNumber(int auth_number) {
		boolean result = false;
		String sql = "insert into auth_email(auth_number, date) values (?,now()) ";
		try {
			result = (jdbcTemplate.update(sql, auth_number) > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	} // insertAuthNumber_END
	
	
	
	public boolean checkNumber(int auth_number) {
		String sql = "DELETE FROM auth_email WHERE auth_number = ? AND date >= NOW() - INTERVAL 3 MINUTE";
		
	    try {
	    	Boolean result = (jdbcTemplate.update(sql, auth_number) > 0);
	        return result;
	        
	    } catch (EmptyResultDataAccessException e) {
	        return false; // auth_number not found
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false; // Handle other exceptions
	    }
	}

	public Boolean createUser(UsersRequestVo usersRequestVo) {
		Boolean result = false;
		result = usersRepository.insertUser(usersRequestVo);

		return result;
	}



	public Boolean findUserEmail(String userEmail) {
		Boolean result = false;
		String sql = "SELECT email FROM user WHERE email = ?";
		
		try {
			result = userEmail.equals(jdbcTemplate.queryForObject(sql, String.class, userEmail));
		}catch (Exception e) {e.getStackTrace();}
		
		return result;
	}



	//updatePassword
	public Boolean updatePassword(String newPassword, String userEmail) {
		Boolean result = false;
		
		String sql = "UPDATE users SET password = ? WHERE email = ?";
		
		try {
			result = (jdbcTemplate.update(sql, newPassword, userEmail) > 0);
		}catch (Exception e) {e.getStackTrace();}
		
		return result;
	} // updatePassword_END
	
	// find x, y
	public UsersResponseVo findXY(UsersRequestVo usersRequestVo) {
	    // 사용자 이메일 주소 가져오기
	    String userEmail = usersRequestVo.getEmail();

	    String sql = "SELECT * FROM users WHERE email = ?";

		UsersResponseVo usersResponseVo = new UsersResponseVo();

	    try {
	        // queryForObject 메서드를 사용하여 사용자 정보를 조회합니다.
	        // 사용자 정보는 UsersRequestVo 클래스의 객체로 반환됩니다.
	        // 쿼리 결과가 없을 경우 DataAccessException이 발생할 수 있으므로 try-catch 블록으로 처리합니다.
			usersResponseVo = jdbcTemplate.queryForObject(sql, new Object[] { userEmail }, new UsersResponseVoRowMapper());


	    } catch (EmptyResultDataAccessException e) {
	        // 사용자를 찾지 못한 경우 예외 처리할 수 있습니다.
	        // 예를 들어, 로깅 또는 다른 작업을 수행할 수 있습니다.
	    }

	    return usersResponseVo;
	}

	// RowMapper 클래스를 정의하여 ResultSet에서 데이터를 매핑합니다.
	private static final class UsersResponseVoRowMapper implements RowMapper<UsersResponseVo> {
	    public UsersResponseVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			UsersResponseVo usersResponseVo = new UsersResponseVo();
			usersResponseVo.setName(rs.getString("name"));
			usersResponseVo.setEmail(rs.getString("email"));
			usersResponseVo.setPassword(rs.getString("password"));
			usersResponseVo.setPhone(rs.getString("phone"));
			usersResponseVo.setLatitude(Double.parseDouble(rs.getString("latitude")));
			usersResponseVo.setLongitude(Double.parseDouble(rs.getString("longitude")));
	        // 필요한 다른 사용자 속성들을 추가로 매핑하실 수 있습니다.

	        return usersResponseVo;
	    }
	}


	// 사용자 정보 조회 (로그인)
	public UsersRequestVo selectUser(String email) {
		List<UsersRequestVo> list = null;
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
