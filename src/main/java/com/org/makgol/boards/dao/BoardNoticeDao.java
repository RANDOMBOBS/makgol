package com.org.makgol.boards.dao;


import com.org.makgol.boards.repository.BoardNoticeRepository;
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
    // Notice insert -> mybatis ( insertNotice )
    public int insertNotice(BoardVo boardVo) throws DataAccessException {
        int result = -1;
        result= boardNoticeRepository.insertNotice(boardVo);
        return result;
    } // end

    // 게시글 내용 수정 페이지
    // Notice detail page -> mybatis ( selectModNotice )
    public BoardVo selectDetailNotice(int b_id) throws DataAccessException {
        List<BoardVo> boards = null;
        boards = boardNoticeRepository.selectDetailNotice(b_id);
        return boards.size() > 0 ? boards.get(0) : null;
    } // end
}
