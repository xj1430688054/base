package com.xj.base.controller.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xj.base.controller.BaseController;
import com.xj.base.entity.User;
import com.xj.base.service.IUserService;

@Controller
public class IndexController extends BaseController{
	
	@Autowired
	private IUserService userService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value={"/","/index"})
	public String index(){
		List<User> users = userService.findAll();
		logger.debug(users.toString());
		return "admin/login";
	}
}
