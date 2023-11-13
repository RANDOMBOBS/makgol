package com.org.makgol.boards.controller;


import com.org.makgol.boards.service.BoardNoticeService;
import com.org.makgol.boards.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardNoticeController {

    @Autowired
    BoardNoticeService boardNoticeService;


    @GetMapping("/notice")
    public String notice(){
        return "jsp/board/notice/notice";
    }

    @GetMapping("/noticeAllList")
    public String noticeAllList(Model model) {
        String nextPage = "jsp/board/notice/notice_all_list";
        List<BoardVo> boardVo = boardNoticeService.notice();
        model.addAttribute("boardVo", boardVo);
        return nextPage;
    }


    @RequestMapping(value = "/searchNoticeList", method = { RequestMethod.GET, RequestMethod.POST })
    public String search(@RequestBody Map<String, String> map, Model model) {
        String nextPage = "jsp/board/notice/notice_all_searchlist";
        String searchWord = (String) map.get("searchWord");
        List<BoardVo> boardVo = boardNoticeService.searchNotice(searchWord);
        model.addAttribute("boardVo", boardVo);
        return nextPage;
    }

    @GetMapping("/noticeCreateForm")
    public String noticeCreateForm(@RequestParam("name") String name, Model model, HttpSession session) {
        String nextPage = "jsp/board/notice/notice_create_form";
        model.addAttribute("name", name);
        return nextPage;
    }

    @PostMapping("/noticeAddList")
    public String noticeAddList(BoardVo boardVo) {
        String nextPage = "jsp/board/notice/notice_register_ok";
        int result = boardNoticeService.noticeAddList(boardVo);
        if (result <= 0) {
            nextPage = "jsp/board/notice/notice_register_ng";
        }
        return nextPage;
    }

    @GetMapping("/detailNotice")
    public String detailNotice(@RequestParam("b_id") int b_id, Model model, HttpSession session) {
        String nextPage = "jsp/board/notice/notice_detail";
        BoardVo boardVo = boardNoticeService.detailNotice(b_id);
        model.addAttribute("boardVo", boardVo);
        return nextPage;
    }
}

