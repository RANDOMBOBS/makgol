package com.org.makgol.boards.repository;

import com.org.makgol.boards.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardSuggestionRepository {
    List<BoardVo> selectAllSuggestionBoard();

    int insertSuggestionBoard();

    BoardVo showDetailSuggestionBoard();
}