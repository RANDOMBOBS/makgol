package com.org.makgol.boards.dao;


import com.org.makgol.boards.repository.BoardNoticeRepository;
import com.org.makgol.boards.vo.BoardLikeVo;
import com.org.makgol.boards.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardNoticeDao {

    private final BoardNoticeRepository boardNoticeRepository;

    // 게시글 전체 리스트
    // Notice select (all list)
    public List<BoardVo> selectNotice() throws DataAccessException {
        List<BoardVo> boards = null;
        boards = boardNoticeRepository.selectNotice();
        return boards.size() > 0 ? boards : null;
    } // end

    // 게시글 검색 리스트
    // Notice select(search list)
    public List<BoardVo> selectSearchNotice(String searchWord) {
        return boardNoticeRepository.selectSearchNotice(searchWord);
    } // end

    // 글쓰기버튼하여 게시글 추가
    // Notice insert
    public int insertNotice(BoardVo boardVo) throws DataAccessException {
        int result = -1;
        result= boardNoticeRepository.insertNotice(boardVo);
        return result;
    } // end

    // 게시글 내용 수정 페이지
    // Notice detail page
    public BoardVo selectDetailNotice(int b_id) {
        List<BoardVo> boardVo = null;
        boardNoticeRepository.updateNoticeHit(b_id);
        boardVo = boardNoticeRepository.selectDetailNotice(b_id);
        return boardVo.size() > 0 ? boardVo.get(0) : null;
    } // end

    // 게시글 내용 수정 페이지
    // Notice detail page
    public BoardVo selectModNotice(int b_id) {
        List<BoardVo> boards = null;
        boards = boardNoticeRepository.selectModNotice(b_id);
        return boards.size() > 0 ? boards.get(0) : null;
    } // end

    // 게시글 수정
    // Notice update
    public int updateNotice(BoardVo boardVo) throws DataAccessException {
        int result=-1;
        result= boardNoticeRepository.updateNotice(boardVo);
        return result;
    }

    // 게시글 삭제
    // Notice delete
    public int deleteNotice(int b_id) throws DataAccessException {
        int result=-1;
        result= boardNoticeRepository.deleteNotice(b_id);
        return result;
    } // end





}
