package com.org.makgol.boards.dao;

import com.org.makgol.boards.repository.BoardSuggestionRepository;
import com.org.makgol.boards.vo.BoardCreateRequestVo;
import com.org.makgol.boards.vo.BoardDetailResponseVo;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentRequestVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.util.file.FileInfo;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.devtools.v85.layertree.model.StickyPositionConstraint;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public int insertSuggestionBoard(BoardCreateRequestVo boardCreateRequestVo) {
        int boardResult = -1;
        boardResult = boardSuggestionRepository.insertSuggestionBoard(boardCreateRequestVo);
        return  boardResult;
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertSuggestionBoard(BoardCreateRequestVo boardCreateRequestVo,List<FileInfo> fileList) {
        int boardResult = -1;
        int boardImageListResult = -1;
        boardResult = boardSuggestionRepository.insertSuggestionBoard(boardCreateRequestVo);
        int board_id = boardCreateRequestVo.getId();
        Map<String, Object> map = new HashMap<>();
        map.put("id", board_id);
        map.put("fileList", fileList);
        try {
            boardImageListResult = boardSuggestionRepository.insertSuggestionBoardImages(map);
            if(boardResult>0 && boardImageListResult>0){
                return boardImageListResult;
            }else {
                return -1;
            }
        } catch (Exception e) {
            throw e;
        }
    }



    /**
     * suggestion 글 상세보기
     **/
    public List<BoardDetailResponseVo> showDetailSuggestionBoard(int id) {
        List<BoardDetailResponseVo> boardVos = null;
        boardVos = boardSuggestionRepository.showDetailImageBoard(id);
        if(boardVos.size() == 0){
            boardVos = boardSuggestionRepository.showDetailBoard(id);
        }
        return boardVos;
    }

    /**
     * suggestion 조회수
     **/
    public int updateHit(int id) {
        int result = -1;
        result = boardSuggestionRepository.updateHit(id);
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
    public List<BoardVo> selectBoard(int b_id) {
        List<BoardVo> boardVos = null;
        boardVos = boardSuggestionRepository.selectImageBoard(b_id);
        if(boardVos.size() == 0){
            boardVos = boardSuggestionRepository.selectBoard(b_id);
        }
        return boardVos;
    }

    /**
     * suggestion 글 수정 폼 제출
     **/
    @Transactional(rollbackFor = Exception.class)
    public int updateSuggestionBoard(BoardCreateRequestVo boardCreateRequestVo) {
        int result = -1;
        int board_id = boardCreateRequestVo.getId();
        try {
            result = boardSuggestionRepository.updateBoard(boardCreateRequestVo);
            boardSuggestionRepository.deleteBoardImage(board_id);
            return result;
        }catch (Exception e){
            throw e;
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public int updateSuggestionBoard(BoardCreateRequestVo boardCreateRequestVo, List<FileInfo> fileList) {
        System.out.println("넘어온값"+boardCreateRequestVo);
        int boardResult = -1;
        int boardImageListResult = -1;
        int board_id = boardCreateRequestVo.getId();
        try {
            boardResult = boardSuggestionRepository.updateBoard(boardCreateRequestVo);
            boardSuggestionRepository.deleteBoardImage(board_id);
            Map<String, Object> map = new HashMap<>();
            map.put("id", board_id);
            map.put("fileList", fileList);
            boardImageListResult = boardSuggestionRepository.insertSuggestionBoardImages(map);
            if(boardResult>0 && boardImageListResult>0){
                return boardImageListResult;
            }else {
                return -1;
            }
        } catch (Exception e) {
            throw e;
        }
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

    public List<String> selectBoardImages(List<Integer> idList){
    List<String> imageList = null;
    imageList = boardSuggestionRepository.selectBoardImages(idList);
    return imageList;
    }

    public int deleteHistoryBoard(List<Integer> idList){
        int result = -1;
        result = boardSuggestionRepository.deleteHistoryBoard(idList);
        return result;
    }

    public int deleteHistoryComment(List<Integer> idList){
        int result = -1;
        result = boardSuggestionRepository.deleteHistoryComment(idList);
        return result;
    }

    public int deleteHistoryLike(List<Integer> idList){
        int result = -1;
        result = boardSuggestionRepository.deleteHistoryLike(idList);
        return result;
    }

    public void deleteLikes(List<Integer> boardidList){
       boardSuggestionRepository.deleteLikes(boardidList);
    }

}

