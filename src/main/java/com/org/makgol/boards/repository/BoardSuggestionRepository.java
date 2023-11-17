package com.org.makgol.boards.repository;

import com.org.makgol.boards.vo.BoardCreateRequestVo;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentRequestVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.util.file.FileInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardSuggestionRepository {

    List<BoardVo> selectAllSuggestionBoard();

    int insertSuggestionBoard(BoardCreateRequestVo boardCreateRequestVo);

    int insertSuggestionBoardImages(Map<String, Object> map);

    List<BoardVo> showDetailSuggestionBoard(int id);

    int updateHit(int b_id);

    int insertComment(CommentRequestVo commentRequestVo);

    List<CommentResponseVo> selectCommentList(int board_id);

    int updateComment(CommentResponseVo commentResponseVo);

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
    int deleteHistoryBoard(List<Integer> idList);
    int deleteHistoryComment(List<Integer> idList);
    int deleteHistoryLike(List<Integer> idList);
    void deleteLikes(List<Integer> boardidList);

}