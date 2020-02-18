package com.xj.base.controller.admin.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xj.base.controller.BaseController;

@Controller
@RequestMapping("/admin/teacher")
public class TeacherController extends BaseController{
	
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "admin/teacher/index";
	}

}
