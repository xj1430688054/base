package com.xj.base.controller.admin.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xj.base.common.JsonResult;
import com.xj.base.controller.BaseController;
import com.xj.base.entity.Dormitory;
import com.xj.base.service.IDormitoryService;

@Controller
@RequestMapping("/admin/posi")
public class DormitoryController extends BaseController{
	@Autowired
	private IDormitoryService dormitoryService;
	
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		
		return "admin/posi/index";
	}

	@RequestMapping("find")
	@ResponseBody
	public JsonResult findByPrimary(Integer id,ModelMap map){
		id = 30;
		Dormitory dormitory = dormitoryService.find(id);
		map.put("positon", dormitory);
		return JsonResult.success();
		
	}
}
