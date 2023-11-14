package com.org.makgol.boards.service;

import com.org.makgol.boards.dao.BoardSuggestionDao;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentRequestVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service()
public class BoardSuggestionService {

    @Autowired
    BoardSuggestionDao boardDao;

    /**
     * suggestion 게시판 가져오기
     **/
    public List<BoardVo> getSuggestionBoard() {
        return boardDao.selectAllSuggestionBoard();
    }

    /**
     * suggestion 글 쓰기 폼 제출
     **/
    public int createBoardConfirm(BoardVo boardVo) {
        return boardDao.insertSuggestionBoard(boardVo);
    }

    /**
     * suggestion 글 상세보기
     **/
    public BoardVo readSuggestionBoard(int b_id) {
        return boardDao.showDetailSuggestionBoard(b_id);
    }

    /**
     * suggestion 조회수
     **/
    public int addHit(int b_id) {
        return boardDao.updateHit(b_id);
    }

    /**
     * suggestion 댓글 INSERT
     **/
    public int addComment(CommentRequestVo commentRequestVo) {

        return boardDao.insertComment(commentRequestVo);
    }

    /**
     * suggestion 댓글 SELECT
     **/
    public List<CommentResponseVo> getCommentList(int board_id) {
        return boardDao.selectCommentList(board_id);
    }

    /**
     * suggestion 댓글 수정 폼 제출
     **/
    public int modifyCommentConfirm(CommentResponseVo commentResponseVo) {
        return boardDao.updateComment(commentResponseVo);
    }

    /**
     * suggestion 댓글 DELETE
     **/
    public int delComment(int id) {
        return boardDao.deleteComment(id);
    }

    /**
     * suggestion 글 수정
     **/
    public BoardVo modifyBoard(int b_id) {
        return boardDao.selectBoard(b_id);
    }

    /**
     * suggestion 글 수정 폼 제출
     **/
    public int modifyBoardConfirm(BoardVo boardVo) {
        return boardDao.updateBoard(boardVo);
    }

    /**
     * suggestion 글 DELETE
     **/
    public int deleteBoard(int b_id) {
        return boardDao.deleteBoard(b_id);
    }

    /**
     * suggestion 글 검색
     **/
    public List<BoardVo> searchBoard(String searchOption, String searchWord) {
        return boardDao.selectSearchBoard(searchOption, searchWord);
    }

    public int userLikeStatus(BoardVo boardVo) {
        return boardDao.selectUserLikeStatus(boardVo);
    }

    public int addLikeBoard(BoardVo boardVo) {
        return boardDao.insertBoardLike(boardVo);
    }

    public int removeLikeBoard(BoardVo boardVo) {
        return boardDao.deleteBoardLike(boardVo);

    }

    public int countLike(int b_id) {
        return boardDao.selectCountLike(b_id);
    }

    public void addBoardSympathy(Map<String, Integer> map) {
        boardDao.updateBoardSympathy(map);
    }

}
