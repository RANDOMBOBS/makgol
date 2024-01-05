package com.org.makgol.main.controller;

import java.util.List;

import com.org.makgol.comment.vo.CommentRequestVo;
import com.org.makgol.users.vo.UserXy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.category.vo.CategoryListVo;
import com.org.makgol.main.service.MainService;

import javax.servlet.RequestDispatcher;

@Slf4j
@Controller
@RequestMapping("/main")
public class MainController {
	@Autowired
	MainService mainService;

	
	@GetMapping({ "/", "" })
	public String showList(Model model) {
		return "main/main";
	}


	/**
	 * 모든 음식점 카테고리를 가져와서 돌림판 페이지에 전달합니다.
	 *
	 * @param model 모델 객체로 돌림판 페이지에 카테고리 목록을 전달합니다.
	 * @return 돌림판 페이지(random_wheel.jsp)
	 */
	@RequestMapping(value = "/allCategory", method = { RequestMethod.GET, RequestMethod.POST })
	public String allCategory(Model model) {
		List<CategoryListVo> categorys = mainService.getAllCategory();
		model.addAttribute("categorys", categorys);
		return "jsp/main/random_wheel";
	}

	/**
	 * 메뉴 추천 결과를 표시하는 페이지로 이동합니다.
	 *
	 * @param menu  추천된 메뉴의 이름
	 * @param model 모델 객체로 메뉴 이름을 전달합니다.
	 * @return 추천 결과 페이지(roulette_result.jsp)
	 */
	@RequestMapping(value = "/resultMenu/{menu}", method = { RequestMethod.GET, RequestMethod.POST })
	public String resultMenu(@PathVariable("menu") String menu, Model model) {
		model.addAttribute("menu", menu);
		return "jsp/main/roulette_result";
	}

	/**
	 * 오늘의 추천 메뉴를 표시하는 페이지로 이동합니다.
	 *
	 * @param userXy 사용자의 위치 정보를 담은 객체
	 * @param model  모델 객체로 카테고리 목록을 전달합니다.
	 * @return 오늘의 추천 메뉴 페이지(todayMenu.jsp)
	 */
	@RequestMapping(value = "/todayMenuList/", method = { RequestMethod.GET, RequestMethod.POST})
	public String todayMenuList(@RequestBody UserXy userXy, Model model) {
		String nextPage = "jsp/main/todayMenu";
		List<CategoryListVo> categoryVo = mainService.todayMenuList(userXy);
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	/**
	 * 특정 카테고리의 오늘의 추천 메뉴를 표시하는 페이지로 이동합니다.
	 *
	 * @param category 특정 카테고리의 이름
	 * @param model    모델 객체로 카테고리 목록을 전달합니다.
	 * @return 오늘의 추천 메뉴 페이지(todayMenu.jsp)
	 */
	@RequestMapping(value = "/menusCategory/{category}", method = { RequestMethod.GET, RequestMethod.POST })
	public String menusCategory(@PathVariable("category") String category, Model model) {
		String nextPage = "jsp/main/todayMenu";
		List<CategoryListVo> categoryVo = mainService.todayMenuList(category);
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	/**
	 * Top5 메뉴를 표시하는 페이지로 이동합니다.
	 *
	 * @param model   모델 객체로 카테고리 목록을 전달합니다.
	 * @param userXy  사용자의 위치 정보를 담은 객체
	 * @return Top5 메뉴 페이지(topMenu.jsp)
	 */
	@RequestMapping(value = "/topMenuList", method = { RequestMethod.GET, RequestMethod.POST })
	public String topMenuList(Model model, @RequestBody UserXy userXy) {
		String nextPage = "jsp/main/topMenu";
		List<CategoryListVo> categoryVo = mainService.topMenuList(userXy);
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}


}

