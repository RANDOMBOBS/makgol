package com.org.makgol.boards.controller;

import com.org.makgol.boards.service.BoardTestService;
import com.org.makgol.boards.service.BoardVentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/board/test")
public class BoardTestController {

    @Autowired
    private final BoardTestService boardTestService;


    public BoardTestController(BoardTestService boardTestService) {
        this.boardTestService = boardTestService;
    }

    /**
     * vent 게시판 게시글리스트
     * @return vent.jsp로 이동
     */
    @GetMapping({ "/", "" })
    public String showmain() {
        String nextPage = "jsp/board/vent/vent";
        return nextPage;
    }
}