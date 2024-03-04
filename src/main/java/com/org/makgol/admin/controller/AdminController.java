package com.org.makgol.admin.controller;

import java.util.List;
import java.util.Map;

import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.users.vo.UsersResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.makgol.admin.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;

	/**
	 * 사용자 관리 페이지를 처리하는 메서드입니다.
	 *
	 * @return "jsp/admin/user_management" 뷰를 반환합니다.
	 */
	@RequestMapping(value = "/userManagement", method = { RequestMethod.GET, RequestMethod.POST })
	public String userManagement(){
		return "jsp/admin/user_management";
	}

	/**
	 * 사용자 목록을 처리하는 메서드입니다.
	 *
	 * @param model Model 객체
	 * @return "jsp/admin/user_list" 뷰를 반환합니다.
	 */
	@RequestMapping(value = "/userList", method = { RequestMethod.GET, RequestMethod.POST })
	public String userList(Model model){
		String nextPage = "jsp/admin/user_list";
		List<UsersResponseVo> userVos = adminService.getUserList();
		if(userVos != null) {
			model.addAttribute("userVos", userVos);
		}
		return nextPage;
	}

	/**
	 * 사용자 등급을 수정하는 메서드입니다.
	 *
	 * @param userVo 사용자 요청 정보를 담은 객체
	 * @return 수정 결과를 나타내는 정수를 반환합니다.
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyGrade", method = { RequestMethod.GET, RequestMethod.POST })
	public int modifyGrade(@RequestBody UsersRequestVo userVo) {
		int result = adminService.modGrade(userVo);
		return result;
	}

	/**
	 * 검색을 처리하는 메서드입니다.
	 *
	 * @param map   검색 매개변수를 담은 Map 객체
	 * @param model Model 객체
	 * @return "jsp/admin/search_user_list" 뷰를 반환합니다.
	 */
	@RequestMapping(value = "/search", method = {RequestMethod.POST})
	public String search(@RequestBody Map<String, String> map, Model model){
		String nextPage = "jsp/admin/search_user_list";
		List<UsersResponseVo> userVos = adminService.getSearchUser(map);
		if(userVos != null){
			model.addAttribute("userVos", userVos);
			model.addAttribute("value", map.get("searchValue"));
		}
		return nextPage;
	}

}
