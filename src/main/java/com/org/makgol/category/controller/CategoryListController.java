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

	/**
	 * URL 경로 "/rouletteResult"에 대한 GET 요청을 처리하는 메서드입니다.
	 * 룰렛 결과 페이지로 이동하며, 카테고리 정보를 모델에 추가합니다.
	 *
	 * @param category  룰렛 결과에 사용할 카테고리 정보
	 * @param model     Spring의 Model 객체로, 뷰에 데이터를 전달하는 데 사용됩니다.
	 * @return          다음 이동할 뷰 페이지의 경로를 반환합니다.
	 */
	@GetMapping("/rouletteResult")
	public String rouletteResult(@RequestParam("category") String category, Model model) {
		String nextPage = "jsp/category/category";
		model.addAttribute("category", category);
		return nextPage;
	}

	/**
	 * URL 경로 "/categoryMain"에 대한 GET 및 POST 요청을 처리하는 메서드입니다.
	 * 카테고리 메인 페이지로 이동합니다.
	 *
	 * @return  다음 이동할 뷰 페이지의 경로를 반환합니다.
	 */
	@RequestMapping(value = "/categoryMain", method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryMain() {
		return "jsp/category/category";
	}

	/**
	 * URL 경로 "/categoryList"에 대한 GET 및 POST 요청을 처리하는 메서드입니다.
	 * 카테고리 목록을 조회하고, 해당 목록을 모델에 추가하여 뷰 페이지로 전달합니다.
	 *
	 * @param model    Spring의 Model 객체로, 뷰에 데이터를 전달하는 데 사용됩니다.
	 * @param session  HttpSession 객체로, 현재 세션의 정보를 관리하는 데 사용됩니다.
	 * @return         다음 이동할 뷰 페이지의 경로를 반환합니다.
	 */
	@RequestMapping(value = { "/categoryList" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryList(Model model, HttpSession session) {
		List<CategoryListVo> categoryVo = categoryListService.categoryList();
		UsersResponseVo loginedUserVo = (UsersResponseVo) session.getAttribute("loginedUserVo");
		model.addAttribute("loginedUserVo", loginedUserVo);
		model.addAttribute("categoryVo", categoryVo);
		return "jsp/category/category_list";
	}

	/**
	 * URL 경로 "/{categoryType}"에 대한 GET 및 POST 요청을 처리하는 메서드입니다.
	 * 주어진 카테고리 유형에 따라 해당하는 카테고리 목록을 조회하고, 모델에 추가하여 뷰 페이지로 전달합니다.
	 *
	 * @param model         Spring의 Model 객체로, 뷰에 데이터를 전달하는 데 사용됩니다.
	 * @param categoryType  카테고리 유형에 대한 경로 변수
	 * @return              다음 이동할 뷰 페이지의 경로를 반환합니다.
	 */
	@RequestMapping(value = "/{categoryType}", method = { RequestMethod.GET, RequestMethod.POST })
	public String categoryMenu(Model model, @PathVariable String categoryType) {
		List<CategoryListVo> categoryVo;
		switch (categoryType) {
			case "kor":
				categoryVo = categoryListService.categoryKor(); // 한식
				break;
			case "west":
				categoryVo = categoryListService.categoryWest(); // 양식
				break;
			case "chi":
				categoryVo = categoryListService.categoryChi(); // 중식
				break;
			case "snack":
				categoryVo = categoryListService.categorySnack(); // 분식
				break;
			case "jpn":
				categoryVo = categoryListService.categoryJpn(); // 일식
				break;
			case "cafe":
				categoryVo = categoryListService.categoryCafe(); // 카페
				break;
			default:
				return "error";
		}
		model.addAttribute("categoryVo", categoryVo);
		return "jsp/category/category_list";
	}

	/**
	 * URL 경로 "/cateFile"에 대한 POST 요청을 처리하는 메서드입니다.
	 * 카테고리 파일을 업데이트하고, 리다이렉트 속성을 통해 메시지를 전달합니다.
	 *
	 * @param categoryRequestVo        카테고리 파일 업데이트를 위한 데이터를 포함하는 객체
	 * @param redirectAttributes      리다이렉트 속성으로, 리다이렉트된 페이지에 데이터를 전달하는 데 사용됩니다.
	 * @return                        다음 이동할 뷰 페이지의 경로를 반환합니다.
	 */
	@PostMapping("/cateFile")
	public String cateFile(@ModelAttribute CategoryRequestVo categoryRequestVo, RedirectAttributes redirectAttributes) {
		categoryListService.updateCateFile(categoryRequestVo);
		String message = "이미지가 등록되었습니다.";
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/category/categoryMain";
	}
}

