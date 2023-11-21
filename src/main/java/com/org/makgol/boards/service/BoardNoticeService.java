package com.org.makgol.boards.service;

import com.org.makgol.boards.dao.BoardNoticeDao;
import com.org.makgol.boards.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardNoticeService {


    @Autowired
    BoardNoticeDao boardNoticeDao;


    // 게시글 전체 리스트
    // Notice allList
    public List<BoardVo> notice() {
        return boardNoticeDao.selectNotice();
    }

    // 게시글 특정단어 검색
    // Notice search % ? %
    public List<BoardVo> searchNotice(String searchWord) {
        return boardNoticeDao.selectSearchNotice(searchWord);
    }

    // 게시글 글쓰기
    // Notice insert
    public int noticeAddList(BoardVo boardVo) {
        return boardNoticeDao.insertNotice(boardVo);
    }

    // 게시글 정보 페이지
    // Notice detailPage
    public BoardVo detailNotice(int b_id) {
        return boardNoticeDao.selectDetailNotice(b_id);
    }

    // 게시글 수정 페이지
    // Notice modifyPage
    public BoardVo modifyNotice(int b_id) {
        return boardNoticeDao.selectModNotice(b_id);
    }

    // 게시글 수정 완료
    // Notice modify update
    public int modifyNoticeConfirm(BoardVo boardVo) {
        return boardNoticeDao.updateNotice(boardVo);
    }

    // 게시글 삭제
    // Notice delete
    public int deleteNotice(int b_id) {
        return boardNoticeDao.deleteNotice(b_id);
    }
}
