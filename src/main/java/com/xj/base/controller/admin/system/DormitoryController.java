package com.xj.base.controller.admin.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xj.base.common.JsonResult;
import com.xj.base.controller.BaseController;
import com.xj.base.entity.Dormitory;
import com.xj.base.entity.Dormitory;
import com.xj.base.entity.Student;
import com.xj.base.service.IDormitoryService;
import com.xj.base.service.specification.SimpleSpecificationBuilder;
import com.xj.base.service.specification.SpecificationOperator.Operator;

@Controller
@RequestMapping("/admin/dorm")
public class DormitoryController extends BaseController{
	@Autowired
	private IDormitoryService dormitoryService;
	
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		
		return "admin/dorm/index";
	}

	

	@RequestMapping(value = { "/list" })
	@ResponseBody
	public Page<Dormitory> list() {
		SimpleSpecificationBuilder<Dormitory> builder = new SimpleSpecificationBuilder<Dormitory>();
		SimpleSpecificationBuilder<Student> builder1 = null;
		String searchText = request.getParameter("searchText");
		if(StringUtils.isNotBlank(searchText)){
			builder.addOr("name", Operator.likeAll.name(), searchText);
		}
		Page<Dormitory> page = dormitoryService.findAll(builder.generateSpecification(), getPageRequest());
		
		return page;
	}
	

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		Map<String, String> map2 = new HashMap<String, String>();
		List<Dormitory> list = dormitoryService.findAll();
		map.put("map2", map2);
		return "admin/dorm/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id,ModelMap map) {
		Dormitory dormitory = dormitoryService.find(id);
		map.put("dormitory", dormitory);
		return "admin/dorm/form";
	}

	@RequestMapping(value= {"/edit"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(Dormitory dormitory,ModelMap map){
		try {
			dormitoryService.saveOrUpdate(dormitory);
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id,ModelMap map) {
		try {
			dormitoryService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	
	
	
	
	
}
