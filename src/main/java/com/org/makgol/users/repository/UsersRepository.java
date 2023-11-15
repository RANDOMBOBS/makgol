package com.org.makgol.users.repository;

import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.stores.vo.StoreResponseVo;
import com.org.makgol.users.vo.UsersRequestVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersRepository {

    Boolean insertUser(UsersRequestVo usersRequestVo);
    String duplicationUserEmail(String email);
    List<UsersRequestVo> selectUser(String email);
    int updateUserInfo(UsersRequestVo usersRequestVo);
    List<StoreResponseVo> selectMyStoreList(int user_id);

    List<BoardVo> selectMyPostList(int user_id);
}
