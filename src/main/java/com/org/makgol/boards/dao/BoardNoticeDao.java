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

    /**
     * 공지사항 게시글 전체 리스트를 조회하는 메서드입니다.
     *
     * @return 공지사항 게시글 목록을 담은 List 객체를 반환하며, 목록이 없을 경우 null을 반환합니다.
     * @throws DataAccessException 데이터베이스 액세스 예외가 발생할 경우 처리합니다.
     */
    public List<BoardVo> selectNotice() throws DataAccessException {
        List<BoardVo> boards = null;
        boards = boardNoticeRepository.selectNotice();
        return boards.size() > 0 ? boards : null;
    }

    /**
     * 공지사항 게시글을 검색하는 메서드입니다.
     *
     * @param searchWord 검색어를 나타내는 문자열
     * @return 검색된 공지사항 게시글 목록을 담은 List 객체를 반환합니다.
     */
    public List<BoardVo> selectSearchNotice(String searchWord) {
        return boardNoticeRepository.selectSearchNotice(searchWord);
    }

    /**
     * 공지사항에 새로운 게시글을 추가하는 메서드입니다.
     *
     * @param boardVo 추가할 게시글 정보를 담은 BoardVo 객체
     * @return 게시글 추가 결과를 나타내는 정수를 반환합니다.
     * @throws DataAccessException 데이터베이스 액세스 예외가 발생할 경우 처리합니다.
     */
    public int insertNotice(BoardVo boardVo) throws DataAccessException {
        int result = -1;
        result= boardNoticeRepository.insertNotice(boardVo);
        return result;
    }

    /**
     * 공지사항 게시글의 상세 내용을 조회하는 메서드입니다.
     *
     * @param b_id 조회할 게시글의 식별자
     * @return 조회된 공지사항 게시글의 정보를 담은 BoardVo 객체를 반환하며, 목록이 없을 경우 null을 반환합니다.
     */
    public BoardVo selectDetailNotice(int b_id) {
        List<BoardVo> boardVo = null;
        boardNoticeRepository.updateNoticeHit(b_id);
        boardVo = boardNoticeRepository.selectDetailNotice(b_id);
        return boardVo.size() > 0 ? boardVo.get(0) : null;
    }

    /**
     * 공지사항 게시글 수정 페이지에서 필요한 정보를 조회하는 메서드입니다.
     *
     * @param b_id 수정할 게시글의 식별자
     * @return 수정 페이지에 표시할 공지사항 게시글 정보를 담은 BoardVo 객체를 반환하며, 목록이 없을 경우 null을 반환합니다.
     */
    public BoardVo selectModNotice(int b_id) {
        List<BoardVo> boards = null;
        boards = boardNoticeRepository.selectModNotice(b_id);
        return boards.size() > 0 ? boards.get(0) : null;
    }

    /**
     * 공지사항 게시글을 수정하는 메서드입니다.
     *
     * @param boardVo 수정할 게시글 정보를 담은 BoardVo 객체
     * @return 게시글 수정 결과를 나타내는 정수를 반환합니다.
     * @throws DataAccessException 데이터베이스 액세스 예외가 발생할 경우 처리합니다.
     */
    public int updateNotice(BoardVo boardVo) throws DataAccessException {
        int result=-1;
        result= boardNoticeRepository.updateNotice(boardVo);
        return result;
    }

    /**
     * 공지사항 게시글을 삭제하는 메서드입니다.
     *
     * @param b_id 삭제할 게시글의 식별자
     * @return 게시글 삭제 결과를 나타내는 정수를 반환합니다.
     * @throws DataAccessException 데이터베이스 액세스 예외가 발생할 경우 처리합니다.
     */
    public int deleteNotice(int b_id) throws DataAccessException {
        int result=-1;
        result= boardNoticeRepository.deleteNotice(b_id);
        return result;
    }
}
