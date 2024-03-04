package com.org.makgol.users.repository;

import com.org.makgol.boards.vo.BoardLikeVo;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.common.oauth2.entity.SocialAuth;
import com.org.makgol.stores.vo.StoreResponseVo;
import com.org.makgol.users.vo.Users;
import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersRepository {
    /**
     * 사용자 정보를 저장하는 메서드입니다.
     *
     * @param usersRequestVo 사용자 정보를 담은 객체
     * @return 저장 결과를 나타내는 부울 값 (true: 성공, false: 실패)
     */
    Boolean saveUser(UsersRequestVo usersRequestVo);

    /**
     * 사용자 정보를 저장하는 메서드입니다.
     *
     * @param user 저장할 사용자 객체
     * @return 저장 결과를 나타내는 부울 값 (true: 성공, false: 실패)
     */
    Boolean save(Users user);

    /**
     * 소셜 로그인 정보를 저장하는 메서드입니다.
     *
     * @param social 소셜 로그인 정보를 담은 객체
     */
    void saveSocial(SocialAuth social);

    /**
     * 이메일로 사용자를 검색하는 메서드입니다.
     *
     * @param email 검색할 사용자의 이메일
     * @return 검색된 사용자의 이메일
     */
    String findUserEmail(String email);

    /**
     * 사용자의 비밀번호를 업데이트하는 메서드입니다.
     *
     * @param map 업데이트할 비밀번호와 사용자 이메일이 담긴 맵
     */
    void updatePassword(Map<String, String> map);

    /**
     * 이메일 중복 여부를 확인하는 메서드입니다.
     *
     * @param email 확인할 이메일
     * @return 중복 여부를 나타내는 이메일
     */
    String duplicationUserEmail(String email);

    /**
     * 이메일로 사용자를 검색하여 정보를 반환하는 메서드입니다.
     *
     * @param email 검색할 사용자의 이메일
     * @return 검색된 사용자의 정보를 담은 객체
     */
    UsersResponseVo findUserByEmail(String email);

    /**
     * 사용자 정보를 업데이트하는 메서드입니다.
     *
     * @param usersRequestVo 업데이트할 사용자 정보를 담은 객체
     * @return 업데이트 결과를 나타내는 정수값
     */
    int updateUserInfo(UsersRequestVo usersRequestVo);

    /**
     * 사용자 프로필 사진 정보를 업데이트하는 메서드입니다.
     *
     * @param usersRequestVo 업데이트할 사용자 정보를 담은 객체
     * @return 업데이트 결과를 나타내는 정수값
     */
    int updateUserPhotoInfo(UsersRequestVo usersRequestVo);

    /**
     * 사용자의 정보를 조회하는 메서드입니다.
     *
     * @param user_id 조회할 사용자의 식별 ID
     * @return 조회된 사용자의 정보를 담은 객체
     */
    UsersResponseVo userInfo(int user_id);

    /**
     * 사용자가 등록한 매장 목록을 조회하는 메서드입니다.
     *
     * @param user_id 사용자 식별 ID
     * @return 사용자가 등록한 매장 목록
     */
    List<StoreResponseVo> selectMyStoreList(int user_id);

    /**
     * 사용자가 작성한 게시물 목록을 조회하는 메서드입니다.
     *
     * @param user_id 사용자 식별 ID
     * @return 사용자가 작성한 게시물 목록
     */
    List<BoardVo> selectMyPostList(int user_id);

    /**
     * 사용자가 작성한 댓글 목록을 조회하는 메서드입니다.
     *
     * @param user_id 사용자 식별 ID
     * @return 사용자가 작성한 댓글 목록
     */
    List<CommentResponseVo> selectMyCommentList(int user_id);

    /**
     * 이메일로 사용자를 검색하여 반환하는 메서드입니다.
     *
     * @param email 검색할 사용자의 이메일
     * @return 검색된 사용자 객체
     */
    Users findByEmail(String email);

    /**
     * 사용자가 좋아요한 게시물 목록을 조회하는 메서드입니다.
     *
     * @param user_id 사용자 식별 ID
     * @return 사용자가 좋아요한 게시물 목록
     */
    List<BoardLikeVo> selectMyLikePostList(int user_id);

    /**
     * 사용자의 식별 ID로 정보를 조회하는 메서드입니다.
     *
     * @param id 사용자의 식별 ID
     * @return 조회된 사용자 정보를 담은 객체
     */
    Users findById(int id);

    /**
     * 사용자가 작성한 게시물 수를 카운트하는 메서드입니다.
     *
     * @param user_id 사용자 식별 ID
     * @return 게시물 수를 나타내는 정수값
     */
    int countingPosts(int user_id);

    /**
     * 사용자가 작성한 댓글 수를 카운트하는 메서드입니다.
     *
     * @param user_id 사용자 식별 ID
     * @return 댓글 수를 나타내는 정수값
     */
    int countingComments(int user_id);

}
