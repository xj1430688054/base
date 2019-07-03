package com.xj.base.controller.admin.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xj.base.common.JsonResult;
import com.xj.base.controller.BaseController;
import com.xj.base.entity.Position;
import com.xj.base.service.IPositionService;

@Controller
@RequestMapping("/admin/posi")
public class PositionController extends BaseController{
	@Autowired
	private IPositionService positionService;
	
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		
		return "admin/posi/index";
	}

	@RequestMapping("find")
	@ResponseBody
	public JsonResult findByPrimary(Integer id,ModelMap map){
		id = 30;
		Position position = positionService.find(id);
		map.put("positon", position);
		return JsonResult.success();
		
	}
}
