package com.org.makgol.boards.controller;


import com.org.makgol.boards.service.BoardNoticeService;
import com.org.makgol.boards.vo.BoardLikeVo;
import com.org.makgol.boards.vo.BoardVo;

import com.org.makgol.users.vo.UsersResponseVo;
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


    /**
     * 공지사항 페이지로 이동하는 메서드입니다.
     *
     * @param model Model 객체
     * @return "jsp/board/notice/notice" 뷰를 반환합니다.
     */
    @GetMapping("/notice")
    public String notice(Model model){
        return "jsp/board/notice/notice";
    }

    /**
     * 모든 공지사항 목록을 가져오는 메서드입니다.
     *
     * @param model Model 객체
     * @return "jsp/board/notice/notice_all_list" 뷰를 반환하고, 모델에 공지사항 목록을 추가합니다.
     */
    @GetMapping("/noticeAllList")
    public String noticeAllList(Model model) {
        String nextPage = "jsp/board/notice/notice_all_list";
        List<BoardVo> boardVo = boardNoticeService.notice();
        model.addAttribute("boardVo", boardVo);
        return nextPage;
    }

    /**
     * 공지사항을 검색하는 메서드입니다.
     *
     * @param map   검색 매개변수를 담은 Map 객체
     * @param model Model 객체
     * @return "jsp/board/notice/notice_all_searchlist" 뷰를 반환하고, 모델에 검색 결과 및 검색어를 추가합니다.
     */
    @RequestMapping(value = "/searchNoticeList", method = { RequestMethod.GET, RequestMethod.POST })
    public String search(@RequestBody Map<String, String> map, Model model) {
        String nextPage = "jsp/board/notice/notice_all_searchlist";
        String searchWord = (String) map.get("searchWord");
        List<BoardVo> boardVo = boardNoticeService.searchNotice(searchWord);
        model.addAttribute("boardVo", boardVo);
        model.addAttribute("searchWord", searchWord);
        return nextPage;
    }

    /**
     * 공지사항 작성 폼으로 이동하는 메서드입니다.
     *
     * @return "jsp/board/notice/notice_create_form" 뷰를 반환합니다.
     */
    @GetMapping("/noticeCreateForm")
    public String noticeCreateForm() {
        return "jsp/board/notice/notice_create_form";
    }

    /**
     * 공지사항을 추가하는 메서드입니다.
     *
     * @param boardVo 추가할 공지사항 정보를 담은 객체
     * @return 처리 결과에 따라 "jsp/board/notice/notice_register_ok" 또는 "jsp/board/notice/notice_register_ng" 뷰를 반환합니다.
     */
    @PostMapping("/noticeAddList")
    public String noticeAddList(BoardVo boardVo) {
        String nextPage = "jsp/board/notice/notice_register_ok";
        int result = boardNoticeService.noticeAddList(boardVo);
        if (result <= 0) {
            nextPage = "jsp/board/notice/notice_register_ng";
        }
        return nextPage;
    }

    /**
     * 특정 공지사항의 상세 정보를 조회하는 메서드입니다.
     *
     * @param b_id   조회할 공지사항의 식별자
     * @param model  Model 객체
     * @param session HttpSession 객체
     * @return "jsp/board/notice/notice_detail" 뷰를 반환하고, 모델에 조회된 공지사항 정보를 추가합니다.
     */
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
}

