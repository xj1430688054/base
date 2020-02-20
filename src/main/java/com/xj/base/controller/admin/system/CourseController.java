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
import com.xj.base.entity.Course;
import com.xj.base.entity.Student;
import com.xj.base.entity.Dormitory;
import com.xj.base.service.ICourseService;
import com.xj.base.service.IStudentService;
import com.xj.base.service.specification.SimpleSpecificationBuilder;
import com.xj.base.service.specification.SpecificationOperator.Operator;

@Controller
@RequestMapping("/admin/course")
public class CourseController extends BaseController{
	@Autowired
	private IStudentService employeeService;
	@Autowired
	private ICourseService courseService;

	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "admin/course/index";
	}

	@RequestMapping(value = { "/list" })
	@ResponseBody
	public Page<Course> list() {
		SimpleSpecificationBuilder<Course> builder = new SimpleSpecificationBuilder<Course>();
		SimpleSpecificationBuilder<Student> builder1 = null;
		String searchText = request.getParameter("searchText");
		if(StringUtils.isNotBlank(searchText)){
			builder.addOr("name", Operator.likeAll.name(), searchText);
		}
		Page<Course> page = courseService.findAll(builder.generateSpecification(), getPageRequest());
		for (Course course : page) {
			String teacherName = courseService.findTeacheById(course.getId());
			course.setTeachername(teacherName);
//			course.setTeacher(teacher);
		}
		
		
		return page;
	}
	

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		Map<String, String> map2 = new HashMap<String, String>();
		List<Course> list = courseService.findAll();
		return "admin/course/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id,ModelMap map) {
		Course course = courseService.find(id);
		map.put("course", course);
		return "admin/course/form";
	}

	@RequestMapping(value= {"/edit"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(Course course,ModelMap map){
		try {
			courseService.saveOrUpdate(course);
			
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
			courseService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	
	
	


}


