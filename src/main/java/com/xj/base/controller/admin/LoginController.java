package com.xj.base.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xj.base.controller.BaseController;
import com.xj.base.dao.IDepartmentDao;
import com.xj.base.dao.IEmployeeDao;
import com.xj.base.dao.IPositionDao;
import com.xj.base.dao.ISalaryDao;
import com.xj.base.dao.IUserDao;
import com.xj.base.entity.Department;
import com.xj.base.entity.Employee;
import com.xj.base.entity.Position;
import com.xj.base.entity.Salary;
import com.xj.base.entity.User;
import com.xj.base.service.specification.SimpleSpecificationBuilder;
import com.xj.base.service.specification.SpecificationOperator.Operator;

@Controller
public class LoginController extends BaseController {
	public static Map<Integer, String> map2 = new HashMap<>();
	public static Map<Integer, String> map3 = new HashMap<>();
	public static Map<Integer, Integer> map4 = new HashMap<>();
	public static Map<Integer, String> map5 = new HashMap<>();
	public static Map<String, Integer> map6 = new HashMap<>();
	public static Map<Integer, String> map7 = new HashMap<>();
	
	public static synchronized Map<String, Integer> getMap6() {
		return map6;
	}


	public static synchronized void setMap6(Map<String, Integer> map6) {
		LoginController.map6 = map6;
	}


	public static synchronized Map<Integer, String> getMap7() {
		return map7;
	}


	public static synchronized void setMap7(Map<Integer, String> map7) {
		LoginController.map7 = map7;
	}

	Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	private IEmployeeDao employeeDao;
	@Autowired
	private IPositionDao positionDao;
	@Autowired
	private IDepartmentDao departmentDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private ISalaryDao salaryDao;
	
	@RequestMapping(value = { "/admin/login" }, method = RequestMethod.GET)
	public String login() {
		init();
		return "admin/login";
	}
	
	
	public static synchronized Map<Integer, String> getMap5() {
		return map5;
	}


	public static synchronized void setMap5(Map<Integer, String> map5) {
		LoginController.map5 = map5;
	}


	public static Map<Integer, String> getMap2() {
		return map2;
	}

	public static void setMap2(Map<Integer, String> map2) {
		LoginController.map2 = map2;
	}

	public static Map<Integer, String> getMap3() {
		return map3;
	}

	public static void setMap3(Map<Integer, String> map3) {
		LoginController.map3 = map3;
	}

	public static Map<Integer, Integer> getMap4() {
		return map4;
	}

	public static void setMap4(Map<Integer, Integer> map4) {
		LoginController.map4 = map4;
	}

	private void init() {
		try {
			Date startTime = new Date();
			logger.info("开始缓存职位以及部门信息，开始时间： " + startTime);
			List<Position> list = positionDao.findAll();
			for (Position position : list) {
				map2.put(position.getId(),position.getName());
			}
			
			List<Department> list1 = departmentDao.findAll();
			for (Department dosition : list1) {
				map3.put(dosition.getId(),dosition.getName());
			}
			Date stopTime = new Date();
			logger.info("缓存职位以及部门信息，结束时间： " + stopTime);
			logger.info("");
			
			startTime = new Date();
			logger.info("开始缓存部门人数以及上级部门名字信息: 开始时间：" + startTime);
			for (Department department : list1) {
				SimpleSpecificationBuilder<Employee> builder = new SimpleSpecificationBuilder<Employee>();
				builder.add("departmentid", Operator.eq.name(), department.getId());
//				int oi = (int)employeeDao.count(builder.generateSpecification());
				int co = employeeDao.findCountByDeptId(department.getId());
				String name = departmentDao.findNameById(department.getParentid());
				map4.put(department.getId(), co);
				if(department.getParentid() == -1){
					map5.put(department.getParentid(), "无上级部门了");
				}else{
					map5.put(department.getParentid(), name);
				}
			}
			logger.info("缓存职位以及以及上级部门名字信息，结束时间： " + new Date());
			
			List<Salary> list2 = salaryDao.findAll();
			for (Salary salary : list2) {
				map7.put(salary.getId(), salary.getName());
			}
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = { "/admin/login" }, method = RequestMethod.POST)
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password,ModelMap model
			) {
		try {
			 Subject subject = SecurityUtils.getSubject();
			 UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
			User users = userDao.findByUserName(username);
			request.getSession().setAttribute("users", users);
			return redirect("/admin/index");
		} catch (AuthenticationException e) {
			model.put("message", e.getMessage());
		}
		return "admin/login";
	}
	
	@RequestMapping(value = { "/admin/logout" }, method = RequestMethod.GET)
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return redirect("admin/login");
	}
	
}
