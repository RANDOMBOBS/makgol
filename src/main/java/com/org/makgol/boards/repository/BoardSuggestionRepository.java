package com.org.makgol.boards.repository;

import com.org.makgol.boards.vo.BoardCreateRequestVo;
import com.org.makgol.boards.vo.BoardDetailResponseVo;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentRequestVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.util.file.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardSuggestionRepository {

    /**
     * 모든 건의사항 게시글을 조회하는 메서드입니다.
     *
     * @return 모든 건의사항 게시글 목록을 담은 List 객체를 반환합니다.
     */
    List<BoardVo> selectAllSuggestionBoard(@Param("offset") int offset, @Param("pageSize") int pageSize);

    /**
     * 건의사항 게시글을 추가하는 메서드입니다.
     *
     * @param boardCreateRequestVo 건의사항 게시글의 생성 정보를 담은 BoardCreateRequestVo 객체
     * @return 게시글 추가 결과를 나타내는 정수를 반환합니다.
     */
    int insertSuggestionBoard(BoardCreateRequestVo boardCreateRequestVo);

    /**
     * 건의사항 게시글에 첨부된 이미지를 추가하는 메서드입니다.
     *
     * @param map 이미지 추가 정보를 담은 Map 객체
     * @return 이미지 추가 결과를 나타내는 정수를 반환합니다.
     */
    int insertSuggestionBoardImages(Map<String, Object> map);

    /**
     * 건의사항 게시글의 상세 이미지를 조회하는 메서드입니다.
     *
     * @param id 조회할 게시글의 식별자
     * @return 게시글의 상세 이미지 목록을 담은 List 객체를 반환합니다.
     */
    List<BoardDetailResponseVo> showDetailImageBoard(int id);

    /**
     * 건의사항 게시글의 상세 정보를 조회하는 메서드입니다.
     *
     * @param id 조회할 게시글의 식별자
     * @return 게시글의 상세 정보를 담은 List 객체를 반환합니다.
     */
    List<BoardDetailResponseVo> showDetailBoard(int id);

    /**
     * 건의사항 게시글의 조회수를 증가시키는 메서드입니다.
     *
     * @param id 조회수를 증가시킬 게시글의 식별자
     * @return 조회수 증가 결과를 나타내는 정수를 반환합니다.
     */
    int updateHit(int id);

    /**
     * 건의사항 게시글에 댓글을 추가하는 메서드입니다.
     *
     * @param commentRequestVo 댓글 추가 정보를 담은 CommentRequestVo 객체
     * @return 댓글 추가 결과를 나타내는 정수를 반환합니다.
     */
    int insertComment(CommentRequestVo commentRequestVo);

    /**
     * 건의사항 게시글의 댓글 목록을 조회하는 메서드입니다.
     *
     * @param board_id 조회할 게시글의 식별자
     * @return 댓글 목록을 담은 List 객체를 반환합니다.
     */
    List<CommentResponseVo> selectCommentList(int board_id);

    /**
     * 건의사항 게시글의 댓글을 수정하는 메서드입니다.
     *
     * @param commentResponseVo 수정할 댓글 정보를 담은 CommentResponseVo 객체
     * @return 댓글 수정 결과를 나타내는 정수를 반환합니다.
     */
    int updateComment(CommentResponseVo commentResponseVo);

    /**
     * 건의사항 게시글의 댓글을 삭제하는 메서드입니다.
     *
     * @param id 삭제할 댓글의 식별자
     * @return 댓글 삭제 결과를 나타내는 정수를 반환합니다.
     */
    int deleteComment(int id);

    /**
     * 주어진 파일명 목록에 해당하는 파일 정보를 조회하는 메서드입니다.
     *
     * @param existingFile 조회할 파일명 목록을 담은 List 객체
     * @return 조회된 파일 정보 목록을 담은 List 객체를 반환합니다.
     */
    List<FileInfo> selectExistingFile(List<String> existingFile);

    /**
     * 주어진 게시글 식별자에 해당하는 이미지 목록을 조회하는 메서드입니다.
     *
     * @param b_id 조회할 게시글의 식별자
     * @return 조회된 이미지 목록을 담은 List 객체를 반환합니다.
     */
    List<BoardVo> selectImageBoard(int b_id);

    /**
     * 주어진 게시글 식별자에 해당하는 게시글 목록을 조회하는 메서드입니다.
     *
     * @param b_id 조회할 게시글의 식별자
     * @return 조회된 게시글 목록을 담은 List 객체를 반환합니다.
     */
    List<BoardVo> selectBoard(int b_id);

    /**
     * 건의사항 게시글을 수정하는 메서드입니다.
     *
     * @param boardCreateRequestVo 수정할 건의사항 게시글 정보를 담은 BoardCreateRequestVo 객체
     * @return 게시글 수정 결과를 나타내는 정수를 반환합니다.
     */
    int updateBoard(BoardCreateRequestVo boardCreateRequestVo);

    /**
     * 주어진 게시글 식별자에 해당하는 이미지를 삭제하는 메서드입니다.
     *
     * @param board_id 삭제할 게시글의 식별자
     */
    void deleteBoardImage(int board_id);

    /**
     * 건의사항 게시글을 삭제하는 메서드입니다.
     *
     * @param b_id 삭제할 건의사항 게시글의 식별자
     * @return 게시글 삭제 결과를 나타내는 정수를 반환합니다.
     */
    int deleteBoard(int b_id);

    /**
     * 주어진 검색 조건에 해당하는 건의사항 게시글을 조회하는 메서드입니다.
     *
     * @param map 검색 조건을 담은 Map 객체
     * @return 검색된 건의사항 게시글 목록을 담은 List 객체를 반환합니다.
     */
    List<BoardVo> selectSearchBoard(Map<String, String> map);

    /**
     * 주어진 게시글에 대한 사용자의 좋아요 상태를 조회하는 메서드입니다.
     *
     * @param boardVo 좋아요 상태를 조회할 게시글 정보를 담은 BoardVo 객체
     * @return 사용자의 좋아요 상태를 나타내는 정수를 반환합니다.
     */
    /**
     * 주어진 게시글에 대한 사용자의 좋아요 상태를 조회하는 메서드입니다.
     *
     * @param boardVo 좋아요 상태를 조회할 게시글 정보를 담은 BoardVo 객체
     * @return 사용자의 좋아요 상태를 나타내는 정수를 반환합니다.
     */
    int selectUserLikeStatus(BoardVo boardVo);

    /**
     * 주어진 게시글에 대한 사용자의 좋아요를 추가하는 메서드입니다.
     *
     * @param boardVo 좋아요를 추가할 게시글 정보를 담은 BoardVo 객체
     * @return 좋아요 추가 결과를 나타내는 정수를 반환합니다.
     */
    int insertBoardLike(BoardVo boardVo);

    /**
     * 주어진 게시글에 대한 사용자의 좋아요를 삭제하는 메서드입니다.
     *
     * @param boardVo 좋아요를 삭제할 게시글 정보를 담은 BoardVo 객체
     * @return 좋아요 삭제 결과를 나타내는 정수를 반환합니다.
     */
    int deleteBoardLike(BoardVo boardVo);

    /**
     * 주어진 게시글에 대한 좋아요 수를 조회하는 메서드입니다.
     *
     * @param b_id 좋아요 수를 조회할 게시글의 식별자
     * @return 조회된 좋아요 수를 나타내는 정수를 반환합니다.
     */
    int selectLikeCount(int b_id);

    /**
     * 주어진 게시글에 대한 게시글 좋아요 수를 업데이트하는 메서드입니다.
     *
     * @param map 게시글 식별자와 업데이트할 좋아요 수를 담은 Map 객체
     */
    void updateBoardSympathy(Map<String, Integer> map);

    /**
     * 주어진 게시글 식별자 목록에 해당하는 게시글을 삭제하는 메서드입니다.
     *
     * @param idList 삭제할 게시글 식별자 목록을 담은 List 객체
     * @return 게시글 삭제 결과를 나타내는 정수를 반환합니다.
     */
    int deleteHistoryBoard(List<Integer> idList);

    /**
     * 주어진 게시글 식별자 목록에 해당하는 이미지 파일명을 조회하는 메서드입니다.
     *
     * @param idList 조회할 게시글 식별자 목록을 담은 List 객체
     * @return 조회된 이미지 파일명 목록을 담은 List 객체를 반환합니다.
     */
    List<String> selectBoardImages(List<Integer> idList);

    /**
     * 주어진 댓글 식별자 목록에 해당하는 댓글을 삭제하는 메서드입니다.
     *
     * @param idList 삭제할 댓글 식별자 목록을 담은 List 객체
     * @return 댓글 삭제 결과를 나타내는 정수를 반환합니다.
     */
    int deleteHistoryComment(List<Integer> idList);

    /**
     * 주어진 좋아요 식별자 목록에 해당하는 좋아요를 삭제하는 메서드입니다.
     *
     * @param idList 삭제할 좋아요 식별자 목록을 담은 List 객체
     */
    int deleteHistoryLike(List<Integer> idList);

    /**
     * 주어진 게시글 식별자 목록에 해당하는 좋아요를 삭제하는 메서드입니다.
     *
     * @param boardidList 삭제할 게시글 식별자 목록을 담은 List 객체
     */
    void deleteLikes(List<Integer> boardidList);


    List<BoardDetailResponseVo> boardSuggestionAll(Map<String, Integer> map);

    int boardSuggestionCount();
}