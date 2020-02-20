package com.xj.base.controller.admin.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.xj.base.entity.Course;
import com.xj.base.entity.Dormitory;
import com.xj.base.entity.Teacher;
import com.xj.base.service.ICourseService;
import com.xj.base.service.IDormitoryService;
import com.xj.base.service.ITeacherService;
import com.xj.base.service.specification.SimpleSpecificationBuilder;
import com.xj.base.service.specification.SpecificationOperator.Operator;

@Controller
@RequestMapping("/admin/teacher")
public class TeacherController extends BaseController{
	
	@Autowired
	private ITeacherService teacherService;
	@Autowired
	private IDormitoryService dormitoryService;
	@Autowired
	private ICourseService courseService;
	
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "admin/teacher/index";
	}
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public Page<Teacher> list() {
		SimpleSpecificationBuilder<Teacher> builder = new SimpleSpecificationBuilder<Teacher>();
		String searchText = request.getParameter("searchText");
		if(StringUtils.isNotBlank(searchText)){
			builder.addOr("name", Operator.likeAll.name(), searchText);
			builder.addOr("address", Operator.likeAll.name(), searchText);
		
		}
		Page<Teacher> page  = teacherService.findAll(builder.generateSpecification(), getPageRequest());
//		for (Teacher teacher : page) {
//			String name = teacherService.findDormitoryNameById(teacher.getId());
//			teacher.setDormName(name);
////			teacher.setDormitory(null);
////			teacher.setCourses(null);
//		} 

		return page;
	
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		Map<String, String> map2 = new java.util.HashMap<>();
		List<Dormitory> list = dormitoryService.findAll();
		
		
		Map<String, String> map3 = new java.util.HashMap<>();
		List<Course> list1 = courseService.findAll();
		for (Course dosition : list1) {
			map3.put(String.valueOf(dosition.getId()),dosition.getName());
		}
		map.put("map3", map3);
		return "admin/teacher/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id,ModelMap map) {
		Teacher teacher = teacherService.find(id);
		map.put("teacher", teacher);
		
		List<Dormitory> dormitorys = dormitoryService.findAll();
		map.put("dormitorys", dormitorys);
		
		
		
		return "admin/teacher/form";
	}
	
	
	@RequestMapping(value= {"/edit"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(Teacher teacher,ModelMap map){
		String number = "";
		Integer id = teacher.getId();
		try {
//			Dormitory dormitory = dormitoryService.find(dormid);
//			teacher.setDormitory(dormitory);
			 number = teacherService.saveOrUpdate(teacher);
		//	teacherService.update(teacher);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
	return JsonResult.success();
//		if(id == null){
//			return JsonResult.success("新增员工的工号是： " + number + "默认密码是111111");
//		}
//		else{
//			return JsonResult.success();
//		}
	}
	

	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id,ModelMap map) {
		try {
			
		
			teacherService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	
	
	@RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
	public String grant(@PathVariable Integer id, ModelMap map) {
		Teacher teacher = teacherService.find(id);
		map.put("teacher", teacher);
		
		Set<Course> set = teacher.getCourses();
		
		//解决java.lang.StackOverflowError: null
		
		List<Integer> courseIds = new ArrayList<Integer>();
		for (Course course : set) {
			courseIds.add(course.getId());
		}
		map.put("courseIds", courseIds);
		
		List<Integer> coursid = courseService.findTeacherAll(id);
		
		List<Course> courses = courseService.findList(coursid);
		
		map.put("courses", courses);
		return "admin/teacher/grant";
	}
	
	@ResponseBody
	@RequestMapping(value = "/grant/{id}", method = RequestMethod.POST)
	public JsonResult grant(@PathVariable Integer id,String[] roleIds, ModelMap map) {
		try {
			teacherService.grant(id,roleIds);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

}
