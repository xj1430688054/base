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
import com.xj.base.controller.admin.LoginController;
import com.xj.base.entity.Department;
import com.xj.base.entity.Employee;
import com.xj.base.entity.Position;
import com.xj.base.entity.Salary;
import com.xj.base.service.IDepartmentService;
import com.xj.base.service.IEmployeeService;
import com.xj.base.service.IPositionService;
import com.xj.base.service.ISalaryService;
import com.xj.base.service.specification.SimpleSpecificationBuilder;
import com.xj.base.service.specification.SpecificationOperator.Operator;

@Controller
@RequestMapping("/admin/empsal")
public class EmpSalaryController extends BaseController{
	
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IPositionService positionService;
	@Autowired
	private IDepartmentService dpartmentService;
	@Autowired
	private ISalaryService salaryService;

	
	
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		ModelMap map = new ModelMap();
		Map<Integer, String> map2 = new java.util.HashMap<>();
		List<Position> list = positionService.findAll();
		for (Position position : list) {
			map2.put(position.getId(),position.getName());
		}
		map.put("map2", map2);
		return "admin/empsal/index";
	}
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public Page<Employee> list() {
		SimpleSpecificationBuilder<Employee> builder = new SimpleSpecificationBuilder<Employee>();
		String searchText = request.getParameter("searchText");
		Page<Employee> page = null;
		
		 if(StringUtils.isNotBlank(searchText)){
			builder.addOr("name", Operator.likeAll.name(), searchText);
			builder.addOr("idcard", Operator.likeAll.name(), searchText);
		}
			 page = employeeService.findAll(builder.generateSpecification(), getPageRequest());
			 
		
			 /** 把相应的id与另外的表的Name所对应 */ 
		if (page != null) {
			for (Employee employee : page) {
				if (employee.getPosid() != null) {
					int posiId = employee.getPosid();
					employee.setPosina(LoginController.getMap2().get(posiId));
				}
				if(employee.getDepartmentid() != null){
					int dpartmentId = employee.getDepartmentid();
					employee.setDeptname(LoginController.getMap3().get(dpartmentId));
				}
				if(employee.getSid() != null){
					employee.setSalName(LoginController.getMap7().get(employee.getSid()));
				}else{
					employee.setSalName("暂未设置");
				}
			} 
		}
		return page;
	}
	
	
	@RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
	public String add(@PathVariable Integer id ,ModelMap map) {
		Employee emp = employeeService.find(id);
		Salary salary = salaryService.findById(emp.getSid());
		map.put("salary", salary);
		return "admin/empsal/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id,ModelMap map) {
		Employee employee = employeeService.find(id);
		map.put("employee", employee);
		Map<Integer, String> map1 = LoginController.getMap7();
		Map<String, String> map2 = new HashMap<>();
		for(Map.Entry<Integer, String> entry : map1.entrySet()){
			map2.put(String.valueOf(entry.getKey()), entry.getValue());
		}
		map.put("map2", map2);
		return "admin/empsal/form";
	}
	
	@RequestMapping(value= {"/edit"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(Employee employee,ModelMap map){
		try {
//			employeeService.saveSid(employee);
			Employee em = employeeService.find(employee.getId());
			em.setSid(employee.getSid());
			employeeService.update(em);
		//	employeeService.update(employee);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id,ModelMap map) {
		try {
			employeeService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	

}
