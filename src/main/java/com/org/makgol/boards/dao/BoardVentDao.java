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



    /*
    게시판 페이징
     */
    public List<BoardDetailResponseVo> boardVentAll(int pNum, int scale) {
        System.out.println(pNum);
        System.out.println(scale);
        Map<String, Integer> map = new HashMap<>();
        map.put("pNum", pNum);
        map.put("scale", scale);
        return boardventRepository.boardVentAll(map);
    }

    public int boardVentAll() {
        return boardventRepository.boardVentCount();
    }

}