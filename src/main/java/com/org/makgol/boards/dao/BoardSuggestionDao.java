package com.org.makgol.boards.dao;

import com.org.makgol.boards.repository.BoardSuggestionRepository;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BoardSuggestionDao {


    private final BoardSuggestionRepository boardSuggestionRepository;
    JdbcTemplate jdbcTemplate;
    private SqlSession sqlSession;

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
        result = sqlSession.insert("mapper.boardSuggestion.insertSuggestionBoard", boardVo);
        return result;

    }

    /**
     * suggestion 글 상세보기
     **/
    public BoardVo showDetailSuggestionBoard(int b_id) {
        List<BoardVo> boardVo = null;
        boardVo = sqlSession.selectList("mapper.boardSuggestion.showDetailSuggestionBoard", b_id);
        return boardVo.size() > 0 ? boardVo.get(0) : null;
    }

    /**
     * suggestion 조회수
     **/
    public int updateHit(int b_id) {
        int result = -1;
        result = sqlSession.update("mapper.boardSuggestion.updateHit", b_id);
        return result;
    }

    /**
     * suggestion 댓글 INSERT
     **/
    public int insertComment(CommentVo commentVo) {
        int result = -1;
        result = sqlSession.insert("mapper.boardSuggestion.insertComment", commentVo);
        return result;
    }

    /**
     * suggestion 댓글 SELECT
     **/
    public List<CommentVo> selectCommentList(int board_id) {
        List<CommentVo> CommentVos = null;
        CommentVos = sqlSession.selectList("mapper.boardSuggestion.selectCommentList", board_id);
        return CommentVos.size() > 0 ? CommentVos : null;
    }

    /**
     * suggestion 댓글 수정 폼 제출
     **/
    public int updateComment(CommentVo commentVo) {
        int result = -1;
        result = sqlSession.update("mapper.boardSuggestion.updateComment", commentVo);
        return result;
    }

    /**
     * suggestion 댓글 DELETE
     **/
    public int deleteComment(int id) {
        int result = -1;
        result = sqlSession.delete("mapper.boardSuggestion.deleteComment", id);
        return result;
    }

    /**
     * suggestion 글 수정버튼
     **/
    public BoardVo selectBoard(int b_id) {
        List<BoardVo> boardVo = null;
        boardVo = sqlSession.selectList("mapper.boardSuggestion.selectBoard", b_id);
        return boardVo.size() > 0 ? boardVo.get(0) : null;
    }

    /**
     * suggestion 글 수정 폼 제출
     **/
//	실패
    public int updateBoard(BoardVo boardVo) {
        String sql = "UPDATE boards SET title=?, contents=?, attachment=? WHERE id=? ";
        int result = -1;

        try {
            result = jdbcTemplate.update(sql, boardVo.getTitle(), boardVo.getContents(), boardVo.getAttachment(),
                    boardVo.getB_id());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * suggestion 글 DELETE
     **/
    public int deleteBoard(int b_id) {
        int result = -1;
        result = sqlSession.delete("mapper.boardSuggestion.deleteBoard", b_id);
//		글 삭제할때 폴더에 있는 사진도 지워지게 하기!(폴더 경로 잡히면 진행하기)
        return result;
    }

    /**
     * suggestion 글 검색
     **/
    public List<BoardVo> selectSearchBoard(String searchOption, String searchWord) {
        List<BoardVo> boardVos = new ArrayList<BoardVo>();
        Map<String, String> map = new HashMap<>();
        map.put("searchOption", searchOption);
        map.put("searchWord", searchWord);
        boardVos = sqlSession.selectList("mapper.boardSuggestion.selectSearchBoard", map);
        return boardVos.size() > 0 ? boardVos : null;
    }

    public int selectuserLikeStatus(BoardVo boardVo) {
        int status = sqlSession.selectOne("mapper.boardSuggestion.selectuserLikeStatus", boardVo);
        return status;
    }

    public int insertBoardLike(BoardVo boardVo) {
        int result = -1;
        result = sqlSession.insert("mapper.boardSuggestion.insertBoardLike", boardVo);
        return result;
    }

    public int deleteBoardLike(BoardVo boardVo) {
        int result = -1;
        result = sqlSession.delete("mapper.boardSuggestion.deleteBoardLike", boardVo);
        return result;
    }

    public int selectCountLike(int b_id) {
        int totalLike = sqlSession.selectOne("mapper.boardSuggestion.selectLikeCount", b_id);
        return totalLike;
    }

    public void updateBoardSympathy(Map<String, Integer> map) {
        sqlSession.update("mapper.boardSuggestion.updateBoardSympathy", map);
    }
}

