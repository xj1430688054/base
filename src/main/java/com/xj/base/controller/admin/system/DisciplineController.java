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
import com.xj.base.entity.Discipline;
import com.xj.base.entity.Discipline;
import com.xj.base.entity.Student;
import com.xj.base.service.DisciplineService;
import com.xj.base.service.IStudentService;
import com.xj.base.service.specification.SimpleSpecificationBuilder;
import com.xj.base.service.specification.SpecificationOperator.Operator;

@Controller
@RequestMapping("/admin/disc")
public class DisciplineController extends BaseController{
	
	@Autowired
	private DisciplineService disciplineService;
	
	@Autowired
	private IStudentService stu;
	
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		
		return "admin/disc/index";
	}

	@RequestMapping(value = { "/list" })
	@ResponseBody
	public Page<Discipline> list() {
		SimpleSpecificationBuilder<Discipline> builder = new SimpleSpecificationBuilder<Discipline>();
		SimpleSpecificationBuilder<Student> builder1 = null;
		String searchText = request.getParameter("searchText");
		if(StringUtils.isNotBlank(searchText)){
			builder.addOr("name", Operator.likeAll.name(), searchText);
		}
		Page<Discipline> page = disciplineService.findAll(builder.generateSpecification(), getPageRequest());
		for (Discipline discipline : page) {
			String name = disciplineService.findNameById(discipline.getId());
			discipline.setStuname(name);
			discipline.setSum();
		}
		
		
		return page;
	}
	


	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id,ModelMap map) {
		Discipline discipline = disciplineService.find(id);
		map.put("discipline", discipline);
		return "admin/disc/form";
	}

	@RequestMapping(value= {"/edit"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(Discipline discipline,ModelMap map){
		try {
			disciplineService.update(discipline);
			
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
			disciplineService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
}

