package com.org.makgol.admin.controller;

import java.util.List;

import com.org.makgol.users.vo.UsersRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		System.out.println("유저리스트 컨트롤러");
		String nextPage = "jsp/admin/user_list";
		List<UsersRequestVo> userVos = adminService.getUserList();
		System.out.println("유저정보?"+userVos);
		if(userVos != null) {
			model.addAttribute("userVos", userVos);
		}
		return nextPage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/modifyGrade", method = { RequestMethod.GET, RequestMethod.POST })
		public int modifyGrade(@RequestBody UsersRequestVo usersRequestVo) {
		int result = adminService.modGrade(usersRequestVo);
		return result;
	}
	
}
