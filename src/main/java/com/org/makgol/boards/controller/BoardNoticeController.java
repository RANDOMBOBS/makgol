package com.org.makgol.boards.controller;


import com.org.makgol.boards.service.BoardNoticeService;
import com.org.makgol.boards.vo.BoardLikeVo;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.users.vo.UsersRequestVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
    public String noticeCreateForm(Model model, HttpSession session) {
        String nextPage = "jsp/board/notice/notice_create_form";
        UsersRequestVo loginedUsersRequestVo = (UsersRequestVo) session.getAttribute("loginedUsersRequestVo");
        int userId = loginedUsersRequestVo.getId();
        String grade = loginedUsersRequestVo.getGrade();
        model.addAttribute("user_id", userId);
        model.addAttribute("grade", grade);
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

    @GetMapping("/modifyNotice")
    /***
     * 게시글 수정 페이지
     * @param b_id
     * @param model
     * @return
     */
    public String modifyNotice(@RequestParam("b_id") int b_id, Model model) {
        String nextPage = "jsp/board/notice/notice_modify_form";
        BoardVo boardVo = boardNoticeService.modifyNotice(b_id);
        model.addAttribute("boardVo", boardVo);
        return nextPage;
    }

    @PostMapping("/modifyNoticeConfirm")
    /***
     * 게시글 수정버튼
     * @param boardVo
     * @return
     */
    public String modifyNoticeConfirm(BoardVo boardVo) {
        String nextPage = "jsp/board/notice/notice_modify_ok";
        int result = boardNoticeService.modifyNoticeConfirm(boardVo);
        if (result <= 0) {
            nextPage = "jsp/board/notice/notice_modify_ng";
        }
        return nextPage;
    }

    @GetMapping("/deleteNotice")
    /***
     * 게시글 삭제
     * @param b_id
     * @return
     */
    public String deleteNotice(@RequestParam("b_id") int b_id) {
        String nextPage = "jsp/board/notice/notice_delete_ok";
        int result = boardNoticeService.deleteNotice(b_id);
        if (result <= 0) {
            nextPage = "jsp/board/notice/notice_delete_ng";
        }
        return nextPage;
    }

    @ResponseBody
    @PostMapping("/noticeLikeCount")
    public Map<String,Integer> noticeLikeCount(@RequestBody BoardLikeVo boardLikeVo){
        Map<String,Integer> map = new HashMap<>();
        int count = boardNoticeService.noticeLikeCount(boardLikeVo);
        map.put("likeCnt",count);
        return map;
    }


    @ResponseBody
    @PostMapping("/noticeLikeStatus")
    public Map<String,Integer> noticeLikeStatus (@RequestBody BoardLikeVo boardLikeVo){
        Map<String,Integer> map = new HashMap<>();
        int status = boardNoticeService.noticeLikeStatus(boardLikeVo);
        map.put("status", status);
        return map;
    }

    @ResponseBody
    @PostMapping("/noticeLikeInsert")
    public Map<String,Integer> noticeLikeInsert (@RequestBody BoardLikeVo boardLikeVo){
        Map<String,Integer> map = new HashMap<>();
        int count = 0;
        int result = boardNoticeService.noticeLikeInsert(boardLikeVo);
        if(result>0){
            count = boardNoticeService.noticeLikeCount(boardLikeVo);
        }
        map.put("likeCnt", count);
        return map;
    }

    @ResponseBody
    @PostMapping("/noticeLikeDelete")
    public Map<String,Integer> noticeLikeDelete (@RequestBody BoardLikeVo boardLikeVo){
        Map<String,Integer> map = new HashMap<>();
        int count = 0;
        int result = boardNoticeService.noticeLikeDelete(boardLikeVo);
        if(result>0){
            count = boardNoticeService.noticeLikeCount(boardLikeVo);
        }
        map.put("likeCnt", count);
        return map;
    }
}

