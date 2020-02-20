package com.xj.base.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.xj.base.common.utils.MD5Utils;
import com.xj.base.dao.ICourseDao;
import com.xj.base.dao.IStudentDao;
import com.xj.base.dao.IUserDao;
import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Course;
import com.xj.base.entity.Discipline;
import com.xj.base.entity.Role;
import com.xj.base.entity.Student;
import com.xj.base.entity.User;
import com.xj.base.service.DisciplineService;
import com.xj.base.service.ICourseService;
import com.xj.base.service.IStudentService;
import com.xj.base.service.IUserService;
import com.xj.base.service.support.impl.BaseServiceImpl;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student, Integer> implements IStudentService{
	
	@Autowired
	private IStudentDao iStudentDao;
	
	@Autowired
	private IUserService userService;
	@Autowired
	private ICourseService courseService;
	@Autowired
	private DisciplineService disciplineService;

	@Override
	public Student findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBaseDao<Student, Integer> getBaseDao() {
		// TODO Auto-generated method stub
		return this.iStudentDao;
	}
	
	

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		super.delete(id);
		disciplineService.delete(id);
	}

	@Override
	public String saveOrUpdate(Student student) {
		if(student.getId() != null){
			Student stu = find(student.getId());
			stu.setName(student.getName());
			stu.setSex(student.getSex());
			stu.setAddress(student.getAddress());
			stu.setPhone(student.getPhone());
			stu.setEmail(student.getEmail());
			stu.setDormitory(student.getDormitory());
			update(stu);
			return "";
		}else{
			Integer ss = (new Random()).nextInt(100000);
			student.setId(ss);
			save(student);
//			Integer i= iStudentDao.selectId() + 1;
			Discipline  d = new Discipline();
			d.setId(ss);
			disciplineService.save(d);
		}
		return "";
		
	}

	@Override
	public int findCountByDeptId(Integer id) {
		// TODO Auto-generated method stub
		int a = iStudentDao.findCountByDeptId(id);
		return a;
	}

	@Override
	public void grant(Integer id, String[] roleIds) {
		 Student students = find(id);
		Assert.notNull(students, "学生不存在");
		Course course;
		Set<Course> courses = new HashSet<Course>();
		if(roleIds != null){
			for (int i = 0; i < roleIds.length; i++) {
				Integer rid = Integer.parseInt(roleIds[i]);
				course = courseService.find(rid);
				courses.add(course);
			}
		}
		students.setCourses(courses);
		update(students);
		
	}

	@Override
	public String findDormitoryNameById(Integer id) {
		// TODO Auto-generated method stub
		return iStudentDao.findDormitoryNameById(id);
	}






	



	

}
