package com.org.makgol.boards.dao;

import com.org.makgol.boards.repository.BoardSuggestionRepository;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentRequestVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BoardSuggestionDao {


    private final BoardSuggestionRepository boardSuggestionRepository;

    /**
     * suggestion 게시판 가져오기
     **/
    public List<BoardVo> selectAllSuggestionBoard() throws DataAccessException {
        List<BoardVo> boardVos = null;
        boardVos = boardSuggestionRepository.selectAllSuggestionBoard();
        return boardVos.size() > 0 ? boardVos : null;
    }

    /**
     * suggestion 글 쓰기 폼 제출
     **/
    public int insertSuggestionBoard(BoardVo boardVo) {
        int result = -1;
        result = boardSuggestionRepository.insertSuggestionBoard(boardVo);
        return result;

    }

    /**
     * suggestion 글 상세보기
     **/
    public BoardVo showDetailSuggestionBoard(int b_id) {
        List<BoardVo> boardVo = null;
        boardVo = boardSuggestionRepository.showDetailSuggestionBoard(b_id);
        return boardVo.size() > 0 ? boardVo.get(0) : null;
    }

    /**
     * suggestion 조회수
     **/
    public int updateHit(int b_id) {
        int result = -1;
        result = boardSuggestionRepository.updateHit(b_id);
        return result;
    }

    /**
     * suggestion 댓글 INSERT
     **/
    public int insertComment(CommentRequestVo commentRequestVo) {
        int result = -1;
        result = boardSuggestionRepository.insertComment(commentRequestVo);
        return result;
    }

    /**
     * suggestion 댓글 SELECT
     **/
    public List<CommentResponseVo> selectCommentList(int board_id) {
        List<CommentResponseVo> commentResponseVos = null;
        commentResponseVos = boardSuggestionRepository.selectCommentList(board_id);
        return commentResponseVos.size() > 0 ? commentResponseVos : null;
    }

    /**
     * suggestion 댓글 수정 폼 제출
     **/
    public int updateComment(CommentResponseVo commentResponseVo) {
        int result = -1;
        result = boardSuggestionRepository.updateComment(commentResponseVo);
        return result;
    }

    /**
     * suggestion 댓글 DELETE
     **/
    public int deleteComment(int id) {
        int result = -1;
        result = boardSuggestionRepository.deleteComment(id);
        return result;
    }

    /**
     * suggestion 글 수정버튼
     **/
    public BoardVo selectBoard(int b_id) {
        List<BoardVo> boardVo = null;
        boardVo = boardSuggestionRepository.selectBoard(b_id);
        return boardVo.size() > 0 ? boardVo.get(0) : null;
    }

    /**
     * suggestion 글 수정 폼 제출
     **/
//	실패
    public int updateBoard(BoardVo boardVo) {
        int result = -1;
        result = boardSuggestionRepository.updateBoard(boardVo);
        return result;
    }

    /**
     * suggestion 글 DELETE
     **/
    public int deleteBoard(int b_id) {
        int result = -1;
        result = boardSuggestionRepository.deleteBoard(b_id);
        return result;
    }

    /**
     * suggestion 글 검색
     **/
    public List<BoardVo> selectSearchBoard(String searchOption, String searchWord) {
        List<BoardVo> boardVos = null;
        Map<String, String> map = new HashMap<>();
        map.put("searchOption", searchOption);
        map.put("searchWord", searchWord);
        boardVos = boardSuggestionRepository.selectSearchBoard(map);
        return boardVos.size() > 0 ? boardVos : null;
    }

    public int selectUserLikeStatus(BoardVo boardVo) {
        int status = boardSuggestionRepository.selectUserLikeStatus(boardVo);
        return status;
    }

    public int insertBoardLike(BoardVo boardVo) {
        int result = -1;
        result = boardSuggestionRepository.insertBoardLike(boardVo);
        return result;
    }

    public int deleteBoardLike(BoardVo boardVo) {
        int result = -1;
        result = boardSuggestionRepository.deleteBoardLike(boardVo);
        return result;
    }

    public int selectCountLike(int b_id) {
        int totalLike = boardSuggestionRepository.selectLikeCount(b_id);
        return totalLike;
    }

    public void updateBoardSympathy(Map<String, Integer> map) {
        boardSuggestionRepository.updateBoardSympathy(map);
    }
}

