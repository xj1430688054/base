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
import com.xj.base.dao.ICourseDao;
import com.xj.base.dao.IDormitoryDao;
import com.xj.base.dao.IStudentDao;
import com.xj.base.dao.IUserDao;
import com.xj.base.entity.Course;
import com.xj.base.entity.Dormitory;
import com.xj.base.entity.Student;
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


	@Autowired
	private IUserDao userDao;
	
	@RequestMapping(value = { "/admin/login" }, method = RequestMethod.GET)
	public String login() {
		return "admin/login";
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
