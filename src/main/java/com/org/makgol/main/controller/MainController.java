package com.org.makgol.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.category.vo.CategoryListVo;
import com.org.makgol.main.service.MainService;

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
	@RequestMapping(value = "/todayMenuList", method = { RequestMethod.GET, RequestMethod.POST })
	public String todayMenuList(Model model) {
		String nextPage = "jsp/main/todayMenu";
		List<CategoryListVo> categoryVo = mainService.todayMenuList();
		model.addAttribute("categoryVo",categoryVo);
		return nextPage;
	}

	// Top5 메뉴 
	@RequestMapping(value = "/topMenuList", method = { RequestMethod.GET, RequestMethod.POST })
	public String topMenuList(Model model) {
		String nextPage = "jsp/main/topMenu";
		List<CategoryListVo> categoryVo = mainService.topMenuList();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}


}
