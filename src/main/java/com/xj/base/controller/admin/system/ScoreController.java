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
import com.xj.base.entity.Score;
import com.xj.base.entity.Student;
import com.xj.base.service.ICourseService;
import com.xj.base.service.IScoreService;
import com.xj.base.service.IStudentService;
import com.xj.base.service.specification.SimpleSpecificationBuilder;
import com.xj.base.service.specification.SpecificationOperator.Operator;

@Controller
@RequestMapping("/admin/score")
public class ScoreController extends BaseController{

	@Autowired
	private ICourseService courseService;
	@Autowired
	private IScoreService scoreService;

	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "admin/score/index";
	}

	@RequestMapping(value = { "/list" })
	@ResponseBody
	public Page<Score> list() {
		SimpleSpecificationBuilder<Score> builder = new SimpleSpecificationBuilder<Score>();
		SimpleSpecificationBuilder<Student> builder1 = null;
		String searchText = request.getParameter("searchText");
		if(StringUtils.isNotBlank(searchText)){
			builder.addOr("sid", Operator.likeAll.name(), searchText);
		}
		Page<Score> page = scoreService.findAll(builder.generateSpecification(), getPageRequest());
		for (Score score : page) {
			String stuname = scoreService.findStunameById(score.getSid());
			score.setStuname(stuname);
			String coname = scoreService.findCourseNameByid(score.getCid());
			score.setConame(coname);
		}
		
		
		return page;
	}
	

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		Map<String, String> map2 = new HashMap<String, String>();
		List<Score> list = scoreService.findAll();
		List<Course> courses = courseService.findAll();
		map.put("courses", courses);
		return "admin/score/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id,ModelMap map) {
		Score score = scoreService.find(id);
		map.put("score", score);
//		List<Course> courses = courseService.findAll();
//		map.put("courses", courses);
		return "admin/score/form";
	}

	@RequestMapping(value= {"/edit"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(Score score,ModelMap map){
		try {
			scoreService.saveOrUpdate(score);
			
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
			scoreService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	
	
}
