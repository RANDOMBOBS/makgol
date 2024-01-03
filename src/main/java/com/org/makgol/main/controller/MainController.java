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
	 * 음식점의 모든 카테고리 가져오기
	 * @param model : 돌림판 페이지로 카테고리 목록을 넘김
	 * @return random_wheel.jsp
	 */
	@RequestMapping(value = "/allCategory", method = { RequestMethod.GET, RequestMethod.POST })
	public String allCategory(Model model) {
		List<CategoryListVo> categorys = mainService.getAllCategory();
		model.addAttribute("categorys", categorys);
		return "jsp/main/random_wheel";
	}
	
	
	/**
	 * 메뉴추천 결과
	 * @param menu
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/resultMenu/{menu}", method = { RequestMethod.GET, RequestMethod.POST })
	public String resultMenu(@PathVariable("menu") String menu, Model model) {
		model.addAttribute("menu", menu);
		return "jsp/main/roulette_result";
		
	}
	// 오늘의 추천 메뉴
	@RequestMapping(value = "/todayMenuList/", method = { RequestMethod.GET, RequestMethod.POST})
	public String todayMenuList(@RequestBody UserXy userXy, Model model) {
		String nextPage = "jsp/main/todayMenu";
		List<CategoryListVo> categoryVo = mainService.todayMenuList(userXy);
		model.addAttribute("categoryVo",categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/menusCategory/{category}", method = { RequestMethod.GET, RequestMethod.POST })
	public String menusCategory(@PathVariable("category") String category, Model model) {
		String nextPage = "jsp/main/todayMenu";
		List<CategoryListVo> categoryVo = mainService.todayMenuList(category);
		model.addAttribute("categoryVo",categoryVo);
		return nextPage;
	}

	// Top5 메뉴 
	@RequestMapping(value = "/topMenuList", method = { RequestMethod.GET, RequestMethod.POST })
	public String topMenuList(Model model, @RequestBody UserXy userXy) {
		String nextPage = "jsp/main/topMenu";
		List<CategoryListVo> categoryVo = mainService.topMenuList(userXy);
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}


}

