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
    Boolean saveUser(UsersRequestVo usersRequestVo);

    Boolean save(Users user);

    void saveSocial(SocialAuth social);

    String findUserEmail(String email);

    void updatePassword(Map<String, String> map);

    String duplicationUserEmail(String email);

    UsersResponseVo findUserByEmail(String email);

    List<UsersResponseVo> selectUser(String email);

    int updateUserInfo(UsersRequestVo usersRequestVo);

    int updateUserPhotoInfo(UsersRequestVo usersRequestVo);

    UsersResponseVo userInfo(int user_id);

    List<StoreResponseVo> selectMyStoreList(int user_id);

    List<BoardVo> selectMyPostList(int user_id);

    List<CommentResponseVo> selectMyCommentList(int user_id);

    Users findByEmail(String email);

    List<BoardLikeVo> selectMyLikePostList(int user_id);

    Users findById(int id);

    int countingPosts(int user_id);

    int countingComments(int user_id);

    int countingLikes(int user_id);
}
