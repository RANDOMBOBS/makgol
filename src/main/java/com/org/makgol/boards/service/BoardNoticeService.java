package com.org.makgol.boards.service;

import com.org.makgol.boards.dao.BoardNoticeDao;
import com.org.makgol.boards.vo.BoardLikeVo;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.util.file.FileInfo;
import com.org.makgol.util.file.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class BoardNoticeService {


    @Autowired
    BoardNoticeDao boardNoticeDao;


    /**
     * 게시판의 모든 공지사항 목록을 조회하는 메서드입니다.
     *
     * @return 조회된 모든 공지사항 목록을 담은 List 객체를 반환합니다.
     */
    public List<BoardVo> notice() {
        return boardNoticeDao.selectNotice();
    }

    /**
     * 주어진 검색어에 해당하는 공지사항 목록을 조회하는 메서드입니다.
     *
     * @param searchWord 검색어
     * @return 검색어에 해당하는 공지사항 목록을 담은 List 객체를 반환합니다.
     */
    public List<BoardVo> searchNotice(String searchWord) {
        return boardNoticeDao.selectSearchNotice(searchWord);
    }

    /**
     * 새로운 공지사항을 등록하는 메서드입니다.
     *
     * @param boardVo 등록할 공지사항 정보를 담은 BoardVo 객체
     * @return 등록 결과를 나타내는 정수를 반환합니다.
     */
    public int noticeAddList(BoardVo boardVo) {
        return boardNoticeDao.insertNotice(boardVo);
    }

    /**
     * 주어진 게시글 식별자에 해당하는 공지사항의 상세 정보를 조회하는 메서드입니다.
     *
     * @param b_id 조회할 공지사항의 게시글 식별자
     * @return 조회된 공지사항의 상세 정보를 담은 BoardVo 객체를 반환합니다.
     */
    public BoardVo detailNotice(int b_id) {
        return boardNoticeDao.selectDetailNotice(b_id);
    }

    /**
     * 주어진 게시글 식별자에 해당하는 공지사항의 수정 페이지 정보를 조회하는 메서드입니다.
     *
     * @param b_id 수정 페이지를 조회할 공지사항의 게시글 식별자
     * @return 조회된 공지사항의 수정 페이지 정보를 담은 BoardVo 객체를 반환합니다.
     */
    public BoardVo modifyNotice(int b_id) {
        return boardNoticeDao.selectModNotice(b_id);
    }

    /**
     * 주어진 정보로 공지사항을 수정하는 메서드입니다.
     *
     * @param boardVo 수정할 공지사항 정보를 담은 BoardVo 객체
     * @return 수정 결과를 나타내는 정수를 반환합니다.
     */
    public int modifyNoticeConfirm(BoardVo boardVo) {
        return boardNoticeDao.updateNotice(boardVo);
    }

    /**
     * 주어진 게시글 식별자에 해당하는 공지사항을 삭제하는 메서드입니다.
     *
     * @param b_id 삭제할 공지사항의 게시글 식별자
     * @return 삭제 결과를 나타내는 정수를 반환합니다.
     */
    public int deleteNotice(int b_id) {
        return boardNoticeDao.deleteNotice(b_id);
    }


}
