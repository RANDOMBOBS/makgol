package com.org.makgol.category.controller;

import java.util.List;

import com.org.makgol.category.vo.CategoryRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.org.makgol.category.service.CategoryListService;
import com.org.makgol.category.vo.CategoryListVo;
import com.org.makgol.users.vo.UsersResponseVo;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/category")
public class CategoryListController {

	@Autowired
	CategoryListService categoryListService;

	@GetMapping("/rouletteResult")
	public String rouletteResult(@RequestParam("category") String category, Model model) {
		String nextPage = "jsp/category/category";
		model.addAttribute("category", category );
		return nextPage;
	}

	@RequestMapping(value = "/categoryMain", method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryMain() {
		return "jsp/category/category";
	}

	@RequestMapping(value = { "/categoryList" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryList(Model model,HttpSession session) {
		List<CategoryListVo> categoryVo = categoryListService.categoryList();
		UsersResponseVo loginedUserVo = (UsersResponseVo) session.getAttribute("loginedUserVo");
		model.addAttribute("loginedUserVo", loginedUserVo);
		model.addAttribute("categoryVo", categoryVo);
		return "jsp/category/category_list";
	}

	@RequestMapping(value = "/{categoryType}", method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryMenu(Model model ,@PathVariable String categoryType) {
		List<CategoryListVo> categoryVo;
		if ("kor".equals(categoryType)){
			categoryVo = categoryListService.categoryKor(); // 한식
		} else if ("west".equals(categoryType)) {
			categoryVo = categoryListService.categoryWest(); // 양식
		} else if ("chi".equals(categoryType)) {
			categoryVo = categoryListService.categoryChi(); // 중식
		} else if ("snack".equals(categoryType)) {
			categoryVo = categoryListService.categorySnack(); // 분식
		} else if ("jpn".equals(categoryType)) {
			categoryVo = categoryListService.categoryJpn(); // 일식
		} else if ("cafe".equals(categoryType)) {
			categoryVo = categoryListService.categoryCafe(); // 카페
		} else {
			return "error";
		}
		model.addAttribute("categoryVo", categoryVo);
		return "jsp/category/category_list";
	}

	@PostMapping("/cateFile")
	public String cateFile (@ModelAttribute CategoryRequestVo categoryRequestVo, RedirectAttributes redirectAttributes)  {
		categoryListService.updateCateFile(categoryRequestVo);
		String message = "이미지가 등록되었습니다.";
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/category/categoryMain";
    }
}

