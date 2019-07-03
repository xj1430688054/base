package com.xj.base.controller.admin.system;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.xj.base.entity.EmployeeLog;
import com.xj.base.entity.Position;
import com.xj.base.service.IDepartmentService;
import com.xj.base.service.IEmployeeLogService;
import com.xj.base.service.IEmployeeService;
import com.xj.base.service.IPositionService;
import com.xj.base.service.specification.SimpleSpecificationBuilder;
import com.xj.base.service.specification.SpecificationOperator.Operator;


@Controller
@RequestMapping("/admin/employee")
public class EmloyeeController extends BaseController {
	
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IPositionService positionService;
	@Autowired
	private IDepartmentService dpartmentService;
	@Autowired
	private IEmployeeLogService employeeLogService;
	
	private static Integer departmentid;
	
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		ModelMap map = new ModelMap();
		Map<Integer, String> map2 = new java.util.HashMap<>();
		List<Position> list = positionService.findAll();
		for (Position position : list) {
			map2.put(position.getId(),position.getName());
		}
		map.put("map2", map2);
		return "admin/employee/index";
	}
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public Page<Employee> list() {
		SimpleSpecificationBuilder<Employee> builder = new SimpleSpecificationBuilder<Employee>();
		String searchText = request.getParameter("searchText");
		Page<Employee> page = null;
		if(departmentid != null && departmentid != 0){
			builder.add("departmentid", Operator.likeAll.name(), departmentid);
			int size = Integer.parseInt(request.getParameter("pageSize"));
			PageRequest pageRequest = new PageRequest(0, size, null);
			 page = employeeService.findAll(builder.generateSpecification(), pageRequest);
			 
			departmentid = null;
		}
		else if(StringUtils.isNotBlank(searchText)){
			builder.addOr("name", Operator.likeAll.name(), searchText);
			builder.addOr("address", Operator.likeAll.name(), searchText);
			builder.addOr("workid", Operator.likeAll.name(), searchText);
			builder.addOr("departmentid", Operator.likeAll.name(), searchText);
			builder.addOr("posid", Operator.likeAll.name(), searchText);
		
		 page = employeeService.findAll(builder.generateSpecification(), getPageRequest());
		 
		 /** 把相应的id与另外的表的Name所对应 */ 
		}else {
			 page = employeeService.findAll(builder.generateSpecification(), getPageRequest());
		}
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
			} 
		}
		
		return page;
	}
	
	@RequestMapping(value = "/con")
	@ResponseBody
	public void findByDept(Integer department){
		departmentid = department;
	} 
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		Map<String, String> map2 = new java.util.HashMap<>();
		List<Position> list = positionService.findAll();
		for (Position position : list) {
			map2.put(String.valueOf(position.getId()),position.getName());
		} 
		map.put("map2", map2);
		
		Map<String, String> map3 = new java.util.HashMap<>();
		List<Department> list1 = dpartmentService.findAll();
		for (Department dosition : list1) {
			map3.put(String.valueOf(dosition.getId()),dosition.getName());
		}
		map.put("map3", map3);
		return "admin/employee/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id,ModelMap map) {
		Employee employee = employeeService.find(id);
		map.put("employee", employee);
		
		Map<String, String> map2 = new java.util.HashMap<>();
		List<Position> list = positionService.findAll();
		for (Position position : list) {
			map2.put(String.valueOf(position.getId()),position.getName());
		} 
		map.put("map2", map2);
		
		Map<String, String> map3 = new java.util.HashMap<>();
		List<Department> list1 = dpartmentService.findAll();
		for (Department dosition : list1) {
			map3.put(String.valueOf(dosition.getId()),dosition.getName());
		}
		map.put("map3", map3);
		
		return "admin/employee/form";
	}
	@RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
	public String modify(@PathVariable Integer id,ModelMap map) {
		Employee employee = employeeService.find(id);
		map.put("employee", employee);
		
		Map<String, String> map2 = new java.util.HashMap<>();
		List<Position> list = positionService.findAll();
		for (Position position : list) {
			map2.put(String.valueOf(position.getId()),position.getName());
		} 
		map.put("map2", map2);
		
		Map<String, String> map3 = new java.util.HashMap<>();
		List<Department> list1 = dpartmentService.findAll();
		for (Department dosition : list1) {
			map3.put(String.valueOf(dosition.getId()),dosition.getName());
		}
		map.put("map3", map3);
		
		return "admin/employee/modify";
	}
	
	@RequestMapping(value= {"/edit"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(Employee employee,ModelMap map){
		String number = "";
		Integer id = employee.getId();
		try {
			 number = employeeService.saveOrUpdate(employee);
		//	employeeService.update(employee);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		if(id == null){
			return JsonResult.success("新增员工的工号是： " + number + "默认密码是111111");
		}
		else{
			return JsonResult.success();
		}
	}
	
	@RequestMapping(value= {"/modify"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult modify(Employee employee,ModelMap map){
		String id = "";
		try {
			 id = employeeService.saveOrUpdate(employee);
		//	employeeService.update(employee);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success("修改成功");
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id,ModelMap map) {
		try {
			
			Employee employee = employeeService.find(id);
			EmployeeLog employeeLog = new EmployeeLog();
			employeeLog.setAddress(employee.getAddress());
			employeeLog.setBegincontract(employee.getBegincontract());
			employeeLog.setBegindate(employee.getBegindate());
			employeeLog.setBirthday(employee.getBirthday());
			employeeLog.setContractterm(employee.getContractterm());
			employeeLog.setConversiontime(employee.getConversiontime());
			employeeLog.setDepartmentid(employee.getDepartmentid());
			employeeLog.setEmail(employee.getEmail());
			employeeLog.setEndcontract(employee.getEndcontract());
			employeeLog.setEngageform(employee.getEngageform());
			employeeLog.setId(id);
			employeeLog.setIdcard(employee.getIdcard());
			employeeLog.setJoblevelid(employee.getJoblevelid());
			employeeLog.setName(employee.getName());
			employeeLog.setNationid(employee.getNationid());
			employeeLog.setNativeplace(employee.getNativeplace());
			employeeLog.setNotworkdate(employee.getNotworkdate());
			employeeLog.setPhone(employee.getPhone());
			employeeLog.setPoliticid(employee.getPoliticid());
			employeeLog.setPosid(employee.getPosid());
			employeeLog.setPosina(employee.getPosina());
			employeeLog.setSalName(employee.getSalName());
			employeeLog.setSchool(employee.getSchool());
			employeeLog.setSex(employee.getSex());
			employeeLog.setSid(employee.getSid());
			employeeLog.setSpecialty(employee.getSpecialty());
			employeeLog.setTiptopdegree(employee.getTiptopdegree());
			employeeLog.setWedlock(employee.getWedlock());
			employeeLog.setWorkage(employee.getWorkage());
			employeeLog.setWorkid(employee.getWorkid());
			employeeLog.setWorkstate(employee.getWorkstate());
			employeeLogService.save(employeeLog);
			employeeService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	

}
