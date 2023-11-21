package com.org.makgol.boards.repository;

import com.org.makgol.boards.vo.BoardLikeVo;
import com.org.makgol.boards.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardNoticeRepository {


    List<BoardVo> selectNotice();

    List<BoardVo> selectSearchNotice(String searchWord);

    int insertNotice(BoardVo boardVo);

    List<BoardVo> selectDetailNotice(int b_id);

    List<BoardVo> selectModNotice(int b_id);

    int updateNotice(BoardVo boardVo);

    void updateNoticeHit(int b_id);

    int deleteNotice(int b_id);

    int selectLikeStatus (BoardLikeVo boardLikeVo);

    int selectLikeCount (BoardLikeVo boardLikeVo);

    int insertLikeCount (BoardLikeVo boardLikeVo);

    int DeleteLikeCount (BoardLikeVo boardLikeVo);
}
