package com.org.makgol.boards.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.org.makgol.boards.vo.BoardCreateRequestVo;
import com.org.makgol.boards.vo.BoardDetailResponseVo;
import com.org.makgol.comment.vo.CommentRequestVo;
import com.org.makgol.users.vo.UsersRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.org.makgol.boards.service.BoardSuggestionService;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentResponseVo;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/suggestion")
public class BoardSuggestionController {
	private final BoardSuggestionService boardService;

	/**
	 * suggestion 게시판 게시글리스트
	 * 
	 * @return suggestion.jsp로 이동
	 */
	@GetMapping({ "/", "" })
	public String showmain() {
		String nextPage = "jsp/board/suggestion/suggestion";
		return nextPage;
	}

	@GetMapping("/showAllList")
	public String showAllList(Model model) {
		List<BoardVo> boardVos = boardService.getSuggestionBoard();

		if (boardVos != null) {
			model.addAttribute("boardVos", boardVos);
		}
		return "jsp/board/suggestion/all_suggestion_list";
	}

	/**
	 * suggestion 글 쓰기 버튼
	 * 
	 * @param model   : 다음 화면으로 name 값을 전달
	 * @param session :
	 * 
	 * @return create_board_form.jsp로 이동
	 */
	@GetMapping("/create")
	public String create(Model model, HttpSession session) {
		String nextPage = "jsp/board/suggestion/create_board_form";
		UsersRequestVo loginedUsersRequestVo = (UsersRequestVo) session.getAttribute("loginedUsersRequestVo");
		String userName = loginedUsersRequestVo.getName();
		int userId = loginedUsersRequestVo.getId();
		if (loginedUsersRequestVo != null) {
			model.addAttribute("name", userName);
			model.addAttribute("user_id", userId);
		}
		return nextPage;
	}

	/**
	 * suggestion 글 쓰기 폼 제출
	 * 
	 *
	 * @return 글쓰기 성공 여부 성공 시 : board/create_board_ok.jsp 실패 시 :
	 *         board/create_board_ng.jsp
	 */
	@PostMapping("/createConfirm")
	public String createConfirm(@ModelAttribute BoardCreateRequestVo boardCreateRequestVo) {
	    String nextPage = "jsp/board/suggestion/create_board_ok";
	    int result = boardService.createBoardConfirm(boardCreateRequestVo);
	    if (result < 1) {
	        nextPage = "jsp/board/suggestion/create_board_ng";
	    }
	    return nextPage;
	}


	/**
	 * suggestion 글 상세보기 버튼
	 * 
	 * @param model : 다음 화면으로 값(boardVo: 선택한 b_id가 포함된 레코드 값)을 전달
	 * 
	 * @return suggestion_board_detail.jsp로 이동
	 */
	@RequestMapping(value = "/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String detail(@RequestParam("b_id") int id, Model model, HttpSession httpSession) {
		String nextPage = "jsp/board/suggestion/suggestion_board_detail";
		BoardDetailResponseVo boardVo = boardService.readSuggestionBoard(id);
		boardService.addHit(id);
		model.addAttribute("boardVo", boardVo);
		return nextPage;
	}

	/**
	 * suggestion 댓글 INSERT
	 * 
	 * @param commentRequestVo : 댓글 폼에서 가져온 정보(board_id, nickname, content)
	 * 
	 * @return result값(INSERT 쿼리문 성공여부)를 가지고 suggestion_board_detail.jsp로 이동
	 */
	@ResponseBody
	@PostMapping("/commentCreate")
	public int createComment(@RequestBody CommentRequestVo commentRequestVo) {
		int result = boardService.addComment(commentRequestVo);
		return result;
	}

	/**
	 * suggestion 댓글 SELECT
	 * 
	 * @param board_id : 게시판 번호
	 * @param model    : 다음 화면으로 값(commentVo: 선택한 b_id에 적힌 댓글 목록 데이터)을 전달
	 * 
	 * @return board_comment_list.jsp로 이동
	 */
	@RequestMapping(value = "/commentList/{board_id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String commentList(@PathVariable("board_id") int board_id, Model model) {
		List<CommentResponseVo> commentResponseVos = boardService.getCommentList(board_id);
		model.addAttribute("commentVos", commentResponseVos);
		return "jsp/board/suggestion/board_comment_list";
	}

	/**
	 * suggestion 댓글 수정 폼 제출
	 * 
	 * @param commentResponseVo : 수정폼에서 가져온 데이터(nickname, contents, id)
	 * @return result값(UPDATE 쿼리문 성공여부)를 가지고 board_comment_list.jsp로 이동
	 */
	@ResponseBody
	@RequestMapping(value = "/commentModifyConfirm", method = { RequestMethod.GET, RequestMethod.POST })
	public int commentModifyConfirm(@RequestBody CommentResponseVo commentResponseVo) {
		int result = boardService.modifyCommentConfirm(commentResponseVo);
		return result;
	}

	/**
	 * suggestion 댓글 DELETE
	 * 
	 * @param id : 댓글 번호
	 * @return result값(DELETE 쿼리문 성공여부)를 가지고 board_comment_list.jsp로 이동
	 */
	@ResponseBody
	@RequestMapping(value = "/commentDelete/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public int deleteComment(@PathVariable("id") int id) {
		int result = boardService.delComment(id);
		return result;
	}

	/**
	 * suggestion 글 수정 버튼
	 * 
	 * @param b_id  : 게시글 번호
	 * @param model : 다음 화면으로 값(boardVo : 수정폼에 입력한 값)을 전달해주는 객체
	 * @return modify_board_form.jsp로 이동
	 */
	@GetMapping("/modify")
	public String modify(@RequestParam("b_id") int b_id, @RequestParam("name") String name, Model model, @RequestParam("images") String images) {
		String nextPage = "jsp/board/suggestion/modify_board_form";
		BoardVo boardVo = boardService.modifyBoard(b_id, name);
		model.addAttribute("boardVo", boardVo);
		return nextPage;
	}

	/**
	 * suggestion 글 수정 폼 제출
	 * 
	 *
	 * @return 수정 성공 여부 성공 시 : modify_board_ok.jsp 실패 시 : modify_board_ng.jsp
	 */
	@PostMapping("/modifyConfirm")
	public String modifyConfirm(@ModelAttribute BoardCreateRequestVo boardCreateRequestVo, @RequestParam("oldImages") String oldImages, @RequestParam("id") int board_id) {
		boardCreateRequestVo.setId(board_id);
		String nextPage = "jsp/board/suggestion/modify_board_ok";

		int result = boardService.modifyBoardConfirm(boardCreateRequestVo, oldImages);
		if (result < 1) {
			nextPage = "jsp/board/suggestion/modify_board_ng";
		}
		return nextPage;
	}

	/**
	 * suggestion 글 DELETE
	 * 
	 * @param b_id : 게시글 번호
	 * @return 삭제 성공 여부 성공 시 : delete_board_ok.jsp 실패 시 : delete_board_ng.jsp
	 */
	@GetMapping("/delete")
	public String delete(@RequestParam("b_id") int b_id, @RequestParam("images") String images) {
		String nextPage = "jsp/board/suggestion/delete_board_ok";
		int result = boardService.deleteBoard(b_id,images);
		if (result < 1) {
			nextPage = "jsp/board/suggestion/delete_board_ng";
		}
		return nextPage;
	}

	/** suggestion 글 검색 **/
	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(@RequestBody Map<String, String> map, Model model) {
		String nextPage = "jsp/board/suggestion/search_suggestion_list";
		String searchOption = (String) map.get("searchOption");
		String searchWord = (String) map.get("searchWord");
		List<BoardVo> boardVos = boardService.searchBoard(searchOption, searchWord);
		if (boardVos != null) {
			model.addAttribute("boardVos", boardVos);
		}
		return nextPage;
	}

	
	@ResponseBody
	@RequestMapping(value="/userLikeStatus", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Integer> userLikeStatus(@RequestBody BoardVo boardVo) {
		Map<String, Integer> map = new HashMap<>();
		int status = boardService.userLikeStatus(boardVo);
		map.put("status", status);
		return map;	}
	
	
	
	/** suggestion 글 좋아요 **/
	@ResponseBody
	@RequestMapping(value = "/likeBoard", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Integer> likeBoard(@RequestBody BoardVo boardVo) {
		Map<String, Integer> map = new HashMap<>();
		int b_id = boardVo.getB_id();
		int result = boardService.addLikeBoard(boardVo);
		int totalLike = 0;
		if(result > 0) {
			totalLike = boardService.countLike(b_id);
			map.put("totalLike", totalLike);
			map.put("b_id", b_id);
			boardService.addBoardSympathy(map);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/unlikeBoard", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Integer> unlikeBoard(@RequestBody BoardVo boardVo) {
		Map<String, Integer> map = new HashMap<>();
		int b_id = boardVo.getB_id();
		int result = boardService.removeLikeBoard(boardVo);
		int totalLike = 0;
		if(result > 0) {
			totalLike = boardService.countLike(b_id);
			map.put("totalLike", totalLike);
			map.put("b_id", b_id);
			boardService.addBoardSympathy(map);
		}
		return map;
	}

	@ResponseBody
	@PostMapping("/deleteMyBoard/{ids}")
	public Map<String, Integer> deleteMyBoard(@PathVariable("ids") String ids){
		int result = boardService.deleteMyBoard(ids);
		Map<String, Integer> map = new HashMap<>();
		map.put("result",result);
		return map;
	}

	@ResponseBody
	@PostMapping("/deleteMyComment/{comids}")
	public Map<String, Integer> deleteMyComment(@PathVariable("comids") String comids){
		int result = boardService.deleteMyComment(comids);
		Map<String, Integer> map = new HashMap<>();
		map.put("result",result);
		return map;
	}

	@ResponseBody
	@PostMapping("/deleteMyLike")
	public Map<String, Integer> deleteMyLike(@RequestBody Map<String, String> data) {
		String boardids = data.get("boardids");
		String likeids = data.get("likeids");


		int result = boardService.deleteMyLike(likeids, boardids);


		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		return map;
	}

}
