package com.org.makgol.users.repository;

import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.stores.vo.StoreResponseVo;
import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersRepository {

    Boolean saveUser(UsersRequestVo usersRequestVo);
    Boolean findUserEmail(String email);
    Boolean updatePassword(Map<String, String> map);
    String duplicationUserEmail(String email);
    UsersResponseVo findUserByEmail(String email);
    List<UsersResponseVo> selectUser(String email);
    int updateUserInfo(UsersRequestVo usersRequestVo);
    List<StoreResponseVo> selectMyStoreList(int user_id);
    List<BoardVo> selectMyPostList(int user_id);
    List<CommentResponseVo> selectMyCommentList(int user_id);
    List<BoardVo> selectMyLikePostList(int user_id);

}
