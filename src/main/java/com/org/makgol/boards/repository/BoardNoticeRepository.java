package com.org.makgol.boards.repository;

import com.org.makgol.boards.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardNoticeRepository {


    List<BoardVo> selectNotice();

    List<BoardVo> selectSearchNotice(String searchWord);

    int insertNotice(BoardVo boardVo);

    List<BoardVo> selectDetailNotice(int b_id);
}
