package com.xj.base.controller.admin.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.xj.base.entity.Course;
import com.xj.base.entity.Student;
import com.xj.base.entity.User;
import com.xj.base.entity.Dormitory;
import com.xj.base.entity.Role;
import com.xj.base.service.ICourseService;
import com.xj.base.service.IDormitoryService;
import com.xj.base.service.IStudentService;
import com.xj.base.service.specification.SimpleSpecificationBuilder;
import com.xj.base.service.specification.SpecificationOperator.Operator;


@Controller
@RequestMapping("/admin/student")
public class StudentController extends BaseController {
	
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IDormitoryService dormitoryService;
	@Autowired
	private ICourseService courseService;
	

	
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "admin/student/index";
	}

	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public Page<Student> list() {
		SimpleSpecificationBuilder<Student> builder = new SimpleSpecificationBuilder<Student>();
		String searchText = request.getParameter("searchText");
		if(StringUtils.isNotBlank(searchText)){
			builder.addOr("name", Operator.likeAll.name(), searchText);
			builder.addOr("address", Operator.likeAll.name(), searchText);
		
		}
		Page<Student> page  = studentService.findAll(builder.generateSpecification(), getPageRequest());
		for (Student student : page) {
			String name = studentService.findDormitoryNameById(student.getId());
			student.setDormName(name);
//			student.setDormitory(null);
//			student.setCourses(null);
		} 

		return page;
	
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		Map<String, String> map2 = new HashMap<String, String>();
		List<Course> list = courseService.findAll();
		
//		List<Dormitory> dormitorys = dormitoryService.findAll();
//		map.put("dormitorys", dormitorys);
		map.put("add", "add");
		return "admin/student/form";
	}


	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id,ModelMap map) {
		Student student = studentService.find(id);
		map.put("student", student);
		
		List<Dormitory> dormitorys = dormitoryService.findAll();
		map.put("dormitorys", dormitorys);
		
		
		
		return "admin/student/form";
	}
	
	
	@RequestMapping(value= {"/edit"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(Student student,ModelMap map, Integer dormid){
		try {
			if (null != dormid) {
				Dormitory dormitory = dormitoryService.find(dormid);
				student.setDormitory(dormitory);
			}
			studentService.saveOrUpdate(student);
		//	studentService.update(student);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
	return JsonResult.success();
	}
	

	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id,ModelMap map) {
		try {
			
		
			studentService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	
	
	@RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
	public String grant(@PathVariable Integer id, ModelMap map) {
		Student student = studentService.find(id);
		map.put("student", student);
		
		Set<Course> set = student.getCourses();
		List<Integer> courseIds = new ArrayList<Integer>();
		for (Course course : set) {
			courseIds.add(course.getId());
		}
		map.put("courseIds", courseIds);
		
		List<Course> courses = courseService.findAll();
		map.put("courses", courses);
		return "admin/student/grant";
	}
	
	@ResponseBody
	@RequestMapping(value = "/grant/{id}", method = RequestMethod.POST)
	public JsonResult grant(@PathVariable Integer id,String[] roleIds, ModelMap map) {
		try {
			studentService.grant(id,roleIds);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	

}
