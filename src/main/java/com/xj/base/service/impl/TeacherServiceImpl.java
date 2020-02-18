package com.xj.base.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.xj.base.dao.ITeacherDao;
import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Course;
import com.xj.base.entity.Student;
import com.xj.base.entity.Teacher;
import com.xj.base.service.ICourseService;
import com.xj.base.service.ITeacherService;
import com.xj.base.service.support.impl.BaseServiceImpl;


@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher, Integer> implements ITeacherService{
	
	@Autowired
	private ITeacherDao teacherDao;
	
	@Autowired
	private ICourseService courseService;

	@Override
	public IBaseDao<Teacher, Integer> getBaseDao() {
		// TODO Auto-generated method stub
		return teacherDao;
	}

	@Override
	public String saveOrUpdate(Teacher teacher) {
		if(teacher.getId() != null){
			Teacher stu = find(teacher.getId());
			stu.setName(teacher.getName());
			stu.setSex(teacher.getSex());
			stu.setAddress(teacher.getAddress());
			stu.setPhone(teacher.getPhone());
			stu.setEmail(teacher.getEmail());
			update(stu);
			return "";
		}else{

			save(teacher);
		}
		return "";
	}

	@Override
	public void grant(Integer id, String[] roleIds) {
		Teacher teacher = find(id);
			Assert.notNull(teacher, "教师不存在");
			Course course;
			Set<Course> courses = new HashSet<Course>();
			if(roleIds != null){
				for (int i = 0; i < roleIds.length; i++) {
					Integer rid = Integer.parseInt(roleIds[i]);
					course = courseService.find(rid);
					courses.add(course);
				}
			}
			teacher.setCourses(courses);
			update(teacher);
		
	}

}
