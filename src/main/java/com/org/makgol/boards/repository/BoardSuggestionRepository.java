package com.org.makgol.boards.repository;

import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardSuggestionRepository {

    List<BoardVo> selectAllSuggestionBoard();

    int insertSuggestionBoard(BoardVo boardVo);

    List<BoardVo> showDetailSuggestionBoard(int b_id);

    int updateHit(int b_id);

    int insertComment(CommentVo commentVo);

    List<CommentVo> selectCommentList(int board_id);

    int updateComment(CommentVo commentVo);

    int deleteComment(int id);

    List<BoardVo> selectBoard(int b_id);

    int updateBoard(BoardVo boardVo);

    int deleteBoard(int b_id);

    List<BoardVo> selectSearchBoard(Map<String, String> map);

    int selectUserLikeStatus(BoardVo boardVo);

    int insertBoardLike(BoardVo boardVo);

    int deleteBoardLike(BoardVo boardVo);

    int selectLikeCount(int b_id);

    void updateBoardSympathy(Map<String, Integer> map);
}