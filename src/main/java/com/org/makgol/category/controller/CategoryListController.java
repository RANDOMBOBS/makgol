package com.org.makgol.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.makgol.category.service.CategoryListService;
import com.org.makgol.category.vo.CategoryListVo;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/category")

public class CategoryListController {

	@Autowired
	CategoryListService categoryListService;

	@GetMapping("/rouletteResult")
	public String rouletteResult(@RequestParam("category") String category, Model model) {
		String nextPage = "category/category"; 
		model.addAttribute("category", category );
		return nextPage;
	}

	

	@RequestMapping(value = "/categoryMain", method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryMain() {
		return "category/category";
	}

	@RequestMapping(value = { "/categoryList" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryList(Model model) {
		String nextPage = "category/category_list"; // ajax
		List<CategoryListVo> categoryVo = categoryListService.categoryList();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categoryKor", method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryKor(Model model) {
		String nextPage = "category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categoryKor();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categoryWest", method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryWest(Model model) {
		String nextPage = "category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categoryWest();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categoryChi", method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryChi(Model model) {
		String nextPage = "category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categoryChi();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categorySnack", method = { RequestMethod.GET, RequestMethod.POST })
	public String categorySnack(Model model) {
		String nextPage = "category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categorySnack();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categoryJpn", method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryJpn(Model model) {
		String nextPage = "category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categoryJpn();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categoryCafe", method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryCafe(Model model) {
		String nextPage = "category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categoryCafe();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}
}
