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

	@RequestMapping(value = "/userManagement", method = { RequestMethod.GET, RequestMethod.POST })
	public String userManagement(){
		return "jsp/admin/user_management";
	}

	@RequestMapping(value = "/userList", method = { RequestMethod.GET, RequestMethod.POST })
	public String userList(Model model){
		String nextPage = "jsp/admin/user_list";
		List<UsersResponseVo> userVos = adminService.getUserList();
		if(userVos != null) {
			model.addAttribute("userVos", userVos);
		}
		return nextPage;

	}

	@ResponseBody
	@RequestMapping(value = "/modifyGrade", method = { RequestMethod.GET, RequestMethod.POST })
	public int modifyGrade(@RequestBody UsersRequestVo userVo) {
		int result = adminService.modGrade(userVo);
		return result;
	}


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
