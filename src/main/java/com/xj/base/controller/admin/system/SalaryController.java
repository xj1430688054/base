package com.xj.base.controller.admin.system;

import java.util.List;

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
import com.xj.base.entity.Salary;
import com.xj.base.service.ISalaryService;
import com.xj.base.service.specification.SimpleSpecificationBuilder;
import com.xj.base.service.specification.SpecificationOperator.Operator;

@Controller
@RequestMapping("/admin/salary")
public class SalaryController extends BaseController{
	@Autowired
	private ISalaryService salaryService;
	
	@RequestMapping("/index")
	public String index() {
		return "admin/salary/index";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Page<Salary> list() {
		SimpleSpecificationBuilder<Salary> builder = new SimpleSpecificationBuilder<Salary>();
		String searchText = request.getParameter("searchText");
		if(StringUtils.isNotBlank(searchText)){
			builder.add("name", Operator.likeAll.name(), searchText);
		}
		Page<Salary> page = salaryService.findAll(builder.generateSpecification(),getPageRequest());
		return page;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		List<Salary> list = salaryService.findAll();
		map.put("list", list);
		return "admin/salary/form";
	}
	

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id,ModelMap map) {
		Salary salary = salaryService.find(id);
		map.put("salary", salary);
		
		List<Salary> list = salaryService.findAll();
		map.put("list", list);
		return "admin/salary/form";
	}
	
	@RequestMapping(value= {"/edit"}, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(Salary salary,ModelMap map){
		try {
			Integer.parseInt(salary.getAccumulationfundbase());
			Integer.parseInt(salary.getBasicsalary());
			Integer.parseInt(salary.getBonus());
			Integer.parseInt(salary.getLunchsalary());
			Integer.parseInt(salary.getMedicalbase());
			Integer.parseInt(salary.getPensionbase());
			Integer.parseInt(salary.getTrafficsalary());
			Double.parseDouble(salary.getAccumulationfundper());
			Double.parseDouble(salary.getPensionper());
			Double.parseDouble(salary.getMedicalper());
			
			salaryService.saveOrUpdate(salary);
		} catch (Exception e) {
			return JsonResult.failure("请输入正确的格式");
		}
		return JsonResult.success();
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id,ModelMap map) {
		try {
			salaryService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
}
