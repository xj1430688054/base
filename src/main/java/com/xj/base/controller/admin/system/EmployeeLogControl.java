package com.xj.base.controller.admin.system;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xj.base.controller.BaseController;
import com.xj.base.controller.admin.LoginController;
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
@RequestMapping("/admin/log")
public class EmployeeLogControl extends BaseController{
	@Autowired
	private IEmployeeLogService employeeLogService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IPositionService positionService;
	@Autowired
	private IDepartmentService dpartmentService;
	
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
		return "admin/log/index";
	}
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public Page<EmployeeLog> list() {
		SimpleSpecificationBuilder<EmployeeLog> builder = new SimpleSpecificationBuilder<EmployeeLog>();
		String searchText = request.getParameter("searchText");
		Page<EmployeeLog> page = null;
		if(departmentid != null && departmentid != 0){
			builder.add("departmentid", Operator.likeAll.name(), departmentid);
			int size = Integer.parseInt(request.getParameter("pageSize"));
			PageRequest pageRequest = new PageRequest(0, size, null);
			 page = employeeLogService.findAll(builder.generateSpecification(), pageRequest);
			 
			departmentid = null;
		}
		else if(StringUtils.isNotBlank(searchText)){
			builder.addOr("name", Operator.likeAll.name(), searchText);
			builder.addOr("address", Operator.likeAll.name(), searchText);
			builder.addOr("workid", Operator.likeAll.name(), searchText);
			builder.addOr("departmentid", Operator.likeAll.name(), searchText);
			builder.addOr("posid", Operator.likeAll.name(), searchText);
		
		 page = employeeLogService.findAll(builder.generateSpecification(), getPageRequest());
		 
		 /** 把相应的id与另外的表的Name所对应 */ 
		}else {
			 page = employeeLogService.findAll(builder.generateSpecification(), getPageRequest());
		}
		if (page != null) {
			for (EmployeeLog employeeLog : page) {
				if (employeeLog.getPosid() != null) {
					int posiId = employeeLog.getPosid();
					employeeLog.setPosina(LoginController.getMap2().get(posiId));
				}
				if(employeeLog.getDepartmentid() != null){
					int dpartmentId = employeeLog.getDepartmentid();
					employeeLog.setDeptname(LoginController.getMap3().get(dpartmentId));
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
}
