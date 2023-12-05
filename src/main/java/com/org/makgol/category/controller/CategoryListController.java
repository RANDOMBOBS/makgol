package com.org.makgol.category.controller;

import java.util.List;

import com.org.makgol.category.vo.CategoryRequestVo;
import com.org.makgol.util.file.FileInfo;
import com.org.makgol.util.file.FileUpload;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.org.makgol.category.service.CategoryListService;
import com.org.makgol.category.vo.CategoryListVo;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/category")
public class CategoryListController {

	@Autowired
	CategoryListService categoryListService;

	@Autowired
	FileUpload fileUp;

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
	public String categoryList(Model model) {
		String nextPage = "jsp/category/category_list"; // ajax
		List<CategoryListVo> categoryVo = categoryListService.categoryList();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categoryKor", method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryKor(Model model) {
		String nextPage = "jsp/category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categoryKor();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categoryWest", method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryWest(Model model) {
		String nextPage = "jsp/category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categoryWest();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categoryChi", method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryChi(Model model) {
		String nextPage = "jsp/category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categoryChi();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categorySnack", method = { RequestMethod.GET, RequestMethod.POST })
	public String categorySnack(Model model) {
		String nextPage = "jsp/category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categorySnack();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categoryJpn", method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryJpn(Model model) {
		String nextPage = "jsp/category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categoryJpn();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categoryCafe", method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryCafe(Model model) {
		String nextPage = "jsp/category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categoryCafe();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}


	@PostMapping("/cateFile")
	public String cateFile (@ModelAttribute CategoryRequestVo categoryRequestVo, RedirectAttributes redirectAttributes)  {
		categoryListService.updateCateFile(categoryRequestVo);
		String message = "이미지가 등록되었습니다.";
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/category/categoryMain";
    }
}
