package com.org.makgol.boards.dao;

import com.org.makgol.boards.repository.BoardVentRepository;
import com.org.makgol.boards.vo.BoardCreateRequestVo;
import com.org.makgol.boards.vo.BoardDetailResponseVo;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentRequestVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.util.file.FileInfo;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BoardVentDao {


    private final BoardVentRepository boardventRepository;
    private final SqlSessionTemplate sql;
    private SqlSessionTemplate sqlSession;


    /**
     * 게시판 페이징을 위한 모든 후기 게시글 목록을 조회하는 메서드입니다.
     *
     * @param pNum  현재 페이지 번호를 나타내는 정수
     * @param scale 한 페이지에 표시될 게시글 수를 나타내는 정수
     * @return 페이징된 후기 게시글 목록을 담은 List 객체를 반환합니다.
     */
    public List<BoardDetailResponseVo> boardVentAll(int pNum, int scale) {
        System.out.println(pNum);
        System.out.println(scale);
        Map<String, Integer> map = new HashMap<>();
        map.put("pNum", pNum);
        map.put("scale", scale);
        return boardventRepository.boardVentAll(map);
    }

    /**
     * 게시판 페이징을 위한 모든 후기 게시글의 총 수를 조회하는 메서드입니다.
     *
     * @return 모든 후기 게시글의 총 수를 나타내는 정수를 반환합니다.
     */
    public int boardVentAll() {
        return boardventRepository.boardVentCount();
    }

}