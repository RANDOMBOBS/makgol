package com.org.makgol.boards.repository;

import com.org.makgol.boards.vo.BoardLikeVo;
import com.org.makgol.boards.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardNoticeRepository {


    /**
     * 공지사항 게시글 목록을 조회하는 메서드입니다.
     *
     * @return 공지사항 게시글 목록을 담은 List 객체를 반환합니다.
     */
    List<BoardVo> selectNotice();

    /**
     * 공지사항 게시글을 검색하는 메서드입니다.
     *
     * @param searchWord 검색어를 나타내는 문자열
     * @return 검색된 공지사항 게시글 목록을 담은 List 객체를 반환합니다.
     */
    List<BoardVo> selectSearchNotice(String searchWord);

    /**
     * 새로운 공지사항 게시글을 추가하는 메서드입니다.
     *
     * @param boardVo 추가할 공지사항 게시글 정보를 담은 BoardVo 객체
     * @return 게시글 추가 결과를 나타내는 정수를 반환합니다.
     */
    int insertNotice(BoardVo boardVo);

    /**
     * 공지사항 게시글의 상세 내용을 조회하는 메서드입니다.
     *
     * @param b_id 조회할 게시글의 식별자
     * @return 조회된 공지사항 게시글의 정보를 담은 List 객체를 반환합니다.
     */
    List<BoardVo> selectDetailNotice(int b_id);

    /**
     * 공지사항 게시글을 수정하기 위한 정보를 조회하는 메서드입니다.
     *
     * @param b_id 수정할 게시글의 식별자
     * @return 수정 페이지에 표시할 공지사항 게시글 정보를 담은 List 객체를 반환합니다.
     */
    List<BoardVo> selectModNotice(int b_id);

    /**
     * 공지사항 게시글을 수정하는 메서드입니다.
     *
     * @param boardVo 수정할 공지사항 게시글 정보를 담은 BoardVo 객체
     * @return 게시글 수정 결과를 나타내는 정수를 반환합니다.
     */
    int updateNotice(BoardVo boardVo);

    /**
     * 공지사항 게시글 조회수를 증가시키는 메서드입니다.
     *
     * @param b_id 조회수를 증가시킬 게시글의 식별자
     */
    void updateNoticeHit(int b_id);

    /**
     * 공지사항 게시글을 삭제하는 메서드입니다.
     *
     * @param b_id 삭제할 게시글의 식별자
     * @return 게시글 삭제 결과를 나타내는 정수를 반환합니다.
     */
    int deleteNotice(int b_id);

}
