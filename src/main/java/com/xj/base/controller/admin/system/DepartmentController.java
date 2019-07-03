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
import com.xj.base.entity.Department;
import com.xj.base.entity.Employee;
import com.xj.base.entity.Position;
import com.xj.base.service.IDepartmentService;
import com.xj.base.service.IEmployeeService;
import com.xj.base.service.specification.SimpleSpecificationBuilder;
import com.xj.base.service.specification.SpecificationOperator.Operator;

@Controller
@RequestMapping("/admin/dept")
public class DepartmentController extends BaseController{
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IDepartmentService departmentService;

	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "admin/dept/index";
	}

	@RequestMapping(value = { "/list" })
	@ResponseBody
	public Page<Department> list() {
		SimpleSpecificationBuilder<Department> builder = new SimpleSpecificationBuilder<Department>();
		SimpleSpecificationBuilder<Employee> builder1 = null;
		String searchText = request.getParameter("searchText");
		if(StringUtils.isNotBlank(searchText)){
			builder.addOr("name", Operator.likeAll.name(), searchText);
			builder.addOr("id", Operator.likeAll.name(), searchText);
		}
		Page<Department> page = departmentService.findAll(builder.generateSpecification(), getPageRequest());
		
		
		for (Department department : page) {
			builder1 = new SimpleSpecificationBuilder<Employee>();
			String name = departmentService.findNameById(department.getParentid());
			builder1.add("departmentid", Operator.eq.name(), department.getId());
			int number = (int)employeeService.count(builder1.generateSpecification());
			department.setNumber(number);
			if(department.getParentid() != -1){
				department.setIsparentname(name);
			}else{
				department.setIsparentname("当前已经是最上级部门了");
			}
		}
		return page;
	}
	

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		Map<String, String> map2 = new HashMap<String, String>();
		List<Department> list = departmentService.findAll();
		for (Department department1 : list) {
			if(department1.getParentid() == -1){
				map2.put(String.valueOf(department1.getParentid()),"无上级部门");
			}else{
				map2.put(String.valueOf(department1.getParentid()),departmentService.findNameById(department1.getParentid()));
			}
		} 
		map.put("map2", map2);
		return "admin/dept/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id,ModelMap map) {
		Department department = departmentService.find(id);
		map.put("department", department);
		return "admin/dept/form";
	}

	@RequestMapping(value= {"/edit"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(Department department,ModelMap map){
		try {
			departmentService.saveOrUpdate(department);
			
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
			departmentService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	/*	@RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
	public String grant(@PathVariable Integer id, ModelMap map) {
		Department department = dpartmentService.find(id);
		map.put("department", department);

		Set<Role> set = department.getRoles();
		List<Integer> roleIds = new ArrayList<Integer>();
		for (Role role : set) {
			roleIds.add(role.getId());
		}
		map.put("roleIds", roleIds);

		List<Role> roles = roleService.findAll();
		map.put("roles", roles);
		return "admin/department/grant";
	}*/

}


