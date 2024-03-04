package com.org.makgol.boards.controller;

import com.org.makgol.boards.service.BoardVentService;
import com.org.makgol.boards.vo.BoardCreateRequestVo;
import com.org.makgol.boards.vo.BoardDetailResponseVo;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.boards.vo.PageVo;
import com.org.makgol.comment.vo.CommentRequestVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.users.vo.UsersResponseVo;
import com.org.makgol.util.cookie.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




@Controller
@RequiredArgsConstructor
@RequestMapping("/board/vent")
public class BoardVentController {
	private final BoardVentService boardService;
	private final CookieUtil	cookieUtil;

	/**
	 * vent 게시판 게시글리스트
	 * @return vent.jsp로 이동
	 */
	@GetMapping({ "/", "" })
	public String showmain() {
		String nextPage = "jsp/board/vent/vent";
		return nextPage;
	}

	/**
	 * 모든 후기 게시글 목록을 보여주는 메서드입니다.
	 *
	 * @param pageGroup 페이지 그룹을 나타내는 문자열
	 * @param pageNum   페이지 번호를 나타내는 문자열
	 * @param model     Model 객체
	 * @return "jsp/board/vent/all_vent_list" 뷰를 반환하고, 모델에 후기 게시글 목록과 페이징 정보를 추가합니다.
	 */
	@GetMapping("/showAllList")
	public String showAllList(@RequestParam("pageGroup") String pageGroup,
							  @RequestParam("pageNum") String pageNum, Model model) {
		System.out.println("확인");
		List<BoardDetailResponseVo> reviewListAll = new ArrayList<>();
		int pGroup = Integer.parseInt(pageGroup);
		int pNum = Integer.parseInt(pageNum);
		int amount = 10;

		System.out.println(reviewListAll);
		System.out.println(pNum + "pNum");
		System.out.println(amount + "amount");

		reviewListAll = boardService.boardVentAll((pNum-1)*amount , amount);
		int totArticles = boardService.boardVentAll();
		PageVo pageVo = new PageVo(totArticles, pNum, pGroup);

		if (reviewListAll != null && totArticles!=0) {
			model.addAttribute("reviewListAll", reviewListAll);
			model.addAttribute("pageVo", pageVo);
		}
		System.out.println(reviewListAll);
		return "jsp/board/vent/all_vent_list";
	}


	/**
	 * vent 글 쓰기 버튼
	 * @return create_board_form.jsp로 이동
	 */
	@GetMapping("/create")
	public String create(Model model, HttpServletRequest request) {

		String nextPage = "jsp/board/vent/create_board_form";

		Map<String, List<String>> map = cookieUtil.getCookie(request);
		List<String> nameList = map.get("name");
		List<String> valueList = map.get("value");

		for(int i = 0; i <nameList.size(); i ++){
			if("name".equals(nameList.get(i))){
				model.addAttribute("name", valueList.get(i));
			} else if("id".equals(nameList.get(i))) {
				model.addAttribute("user_id", valueList.get(i));
			}
		}

		return nextPage;
	}

	/**
	 * vent 글 쓰기 폼 제출
	 * @return 글쓰기 성공 여부 성공 시 : board/create_board_ok.jsp
	 *                       실패 시 : board/create_board_ng.jsp
	 */
	@PostMapping("/createConfirm")
	public String createConfirm(@ModelAttribute BoardCreateRequestVo boardCreateRequestVo) {
		String nextPage = "jsp/board/vent/create_board_ok";

		int result = boardService.createBoardConfirm(boardCreateRequestVo);

		if (result < 1) {
			nextPage = "jsp/board/vent/create_board_ng";
		}

		return nextPage;
	}


	/**
	 * vent 글 상세보기 버튼
	 * @param model : 다음 화면으로 값(boardVo: 선택한 id가 포함된 레코드 값)을 전달
	 * @return vent_board_detail.jsp로 이동
	 */
	@RequestMapping(value = "/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String detail(@RequestParam("id") int id, Model model, HttpSession httpSession) {
		String nextPage = "jsp/board/vent/vent_board_detail";
		System.out.println("id = " + id);
		BoardDetailResponseVo boardVo = boardService.readVentBoard(id);
		boardService.addHit(id);


		model.addAttribute("boardVo", boardVo);
		return nextPage;
	}

	/**
	 * vent 댓글 INSERT
	 * @param commentRequestVo : 댓글 폼에서 가져온 정보(board_id, nickname, content)
	 * @return result값(INSERT 쿼리문 성공여부)를 가지고 vent_board_detail.jsp로 이동
	 */
	@ResponseBody
	@PostMapping("/commentCreate")
	public int createComment(@RequestBody CommentRequestVo commentRequestVo) {
		int result = boardService.addComment(commentRequestVo);
		return result;
	}

	/**
	 * vent 댓글 SELECT
	 *
	 * @param b_id : 게시판 번호
	 * @param model    : 다음 화면으로 값(commentVo: 선택한 b_id에 적힌 댓글 목록 데이터)을 전달
	 * @return board_comment_list.jsp로 이동
	 */
	@RequestMapping(value = "/commentList/{b_id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String commentList(@PathVariable("b_id") int b_id, Model model) {
		List<CommentResponseVo> commentResponseVos = boardService.getCommentList(b_id);
		model.addAttribute("commentVos", commentResponseVos);
		return "jsp/board/vent/board_comment_list";
	}

	/**
	 * vent 댓글 수정 폼 제출
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
	 * vent 댓글 DELETE
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
	 * vent 글 수정 버튼
	 *
	 * @param b_id  : 게시글 번호
	 * @param model : 다음 화면으로 값(boardVo : 수정폼에 입력한 값)을 전달해주는 객체
	 * @return modify_board_form.jsp로 이동
	 */
	@GetMapping("/modify")
	public String modify(@RequestParam("b_id") int b_id, @RequestParam("name") String name, Model model) {
		String nextPage = "jsp/board/vent/modify_board_form";
		BoardVo boardVo = boardService.modifyBoard(b_id, name);
		model.addAttribute("boardVo", boardVo);
		return nextPage;
	}

	/**
	 * vent 글 수정 폼 제출
	 *
	 *
	 * @return 수정 성공 여부 성공 시 : modify_board_ok.jsp 실패 시 : modify_board_ng.jsp
	 */
	@PostMapping("/modifyConfirm")
	public String modifyConfirm(@ModelAttribute BoardCreateRequestVo boardCreateRequestVo, @RequestParam("oldImages") String oldImages, @RequestParam("id") int board_id) {
		boardCreateRequestVo.setId(board_id);
		System.out.println("아이디는?"+board_id+"보드는?"+boardCreateRequestVo);
		String nextPage = "jsp/board/vent/modify_board_ok";
		int result = boardService.modifyBoardConfirm(boardCreateRequestVo, oldImages);
		if (result < 1) {
			nextPage = "jsp/board/vent/modify_board_ng";
		}
		return nextPage;
	}

	/**
	 * vent 글 DELETE
	 *
	 * @param b_id : 게시글 번호
	 * @return 삭제 성공 여부 성공 시 : delete_board_ok.jsp 실패 시 : delete_board_ng.jsp
	 */
	@GetMapping("/delete")
	public String delete(@RequestParam("b_id") int b_id, @RequestParam("images") String images) {
		String nextPage = "jsp/board/vent/delete_board_ok";
		int result = boardService.deleteBoard(b_id,images);
		if (result < 1) {
			nextPage = "jsp/board/vent/delete_board_ng";
		}
		return nextPage;
	}

	/**
	 * 후기 게시글을 검색하는 메서드입니다.
	 *
	 * @param map   검색 매개변수를 담은 Map 객체
	 * @param model Model 객체
	 * @return "jsp/board/vent/search_vent_list" 뷰를 반환하고, 모델에 검색된 후기 게시글 목록을 추가합니다.
	 */
	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(@RequestBody Map<String, String> map, Model model) {

		System.out.println("search");
		int pGroup = Integer.parseInt(map.get("pageGroup"));
		int pNum = Integer.parseInt(map.get("pageNum"));
		String searchOption = map.get("searchOption");
		String searchWord = map.get("searchWord");
		int amount = 10;

		List<BoardDetailResponseVo> reviewListAll = boardService.searchBoard((pNum-1)*amount , amount, searchOption, searchWord);
		int totArticles = boardService.boardVentAll(searchOption, searchWord);
		System.out.println(totArticles);
		PageVo pageVo = new PageVo(totArticles, pNum, pGroup);

		if (reviewListAll != null && totArticles!=0) {
			model.addAttribute("reviewListAll", reviewListAll);
			model.addAttribute("pageVo", pageVo);
		}
		System.out.println(reviewListAll);

		String nextPage = "jsp/board/vent/all_vent_list";

		return nextPage;
	}


	/**
	 * 사용자의 게시글 좋아요 상태를 확인하는 메서드입니다.
	 *
	 * @param boardVo BoardVo 객체
	 * @return 사용자의 게시글 좋아요 상태를 나타내는 정수를 포함한 Map 객체를 반환합니다.
	 */
	@ResponseBody
	@RequestMapping(value="/userLikeStatus", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Integer> userLikeStatus(@RequestBody BoardVo boardVo) {
		Map<String, Integer> map = new HashMap<>();
		int status = boardService.userLikeStatus(boardVo);
		map.put("status", status);
		return map;
	}

	/**
	 * 게시글에 좋아요를 추가하는 메서드입니다.
	 *
	 * @param boardVo BoardVo 객체
	 * @return 좋아요 추가 결과와 게시글의 총 좋아요 수를 나타내는 Map 객체를 반환합니다.
	 */
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

	/**
	 * 게시글 좋아요를 취소하는 메서드입니다.
	 *
	 * @param boardVo BoardVo 객체
	 * @return 좋아요 취소 결과와 게시글의 총 좋아요 수를 나타내는 Map 객체를 반환합니다.
	 */
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

	/**
	 * 사용자의 게시글을 삭제하는 메서드입니다.
	 *
	 * @param ids 삭제할 게시글의 식별자를 담은 문자열
	 * @return 삭제 결과를 나타내는 정수를 포함한 Map 객체를 반환합니다.
	 */
	@ResponseBody
	@PostMapping("/deleteMyBoard/{ids}")
	public Map<String, Integer> deleteMyBoard(@PathVariable("ids") String ids){
		System.out.println("보드ids = " + ids);
		int result = boardService.deleteMyBoard(ids);
		Map<String, Integer> map = new HashMap<>();
		map.put("result",result);
		return map;
	}

	/**
	 * 사용자의 댓글을 삭제하는 메서드입니다.
	 *
	 * @param comids 삭제할 댓글의 식별자를 담은 문자열
	 * @return 삭제 결과를 나타내는 정수를 포함한 Map 객체를 반환합니다.
	 */
	@ResponseBody
	@PostMapping("/deleteMyComment/{comids}")
	public Map<String, Integer> deleteMyComment(@PathVariable("comids") String comids){
		System.out.println("댓글comids = " + comids);
		int result = boardService.deleteMyComment(comids);
		Map<String, Integer> map = new HashMap<>();
		map.put("result",result);
		return map;
	}

	/**
	 * 사용자의 좋아요를 삭제하는 메서드입니다.
	 *
	 * @param data 삭제할 좋아요 정보를 담은 Map 객체
	 * @return 삭제 결과를 나타내는 정수를 포함한 Map 객체를 반환합니다.
	 */
	@ResponseBody
	@PostMapping("/deleteMyLike")
	public Map<String, Integer> deleteMyLike(@RequestBody Map<String, String> data) {
		int result = boardService.deleteMyLike(data);
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		return map;
	}

}