package com.xj.base.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xj.base.dao.ICourseDao;
import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Course;
import com.xj.base.entity.Teacher;
import com.xj.base.entity.User;
import com.xj.base.service.ICourseService;
import com.xj.base.service.support.impl.BaseServiceImpl;


@Service
public class CourseServiceImpl extends BaseServiceImpl<Course, Integer>implements ICourseService{
	
	@Autowired
	private ICourseDao courseDao;

	@Override
	public User findByUserName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBaseDao<Course, Integer> getBaseDao() {
		// TODO Auto-generated method stub
		return this.courseDao;
	}

	@Override
	public void saveOrUpdate(Course course) {
		if(course.getId() != null){
			Course dbcourse = find(course.getId());
			dbcourse.setName(course.getName());
			update(dbcourse);
		}else{
			
			course.setEnabled("1");
			save(course);
		}
		
	}

	@Override
	public String findNameById(Integer id) {
		// TODO Auto-generated method stub
		String name = courseDao.findNameById(id);
		return name;
	}

	@Override
	public String findDeppathById(Integer id) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public Integer findMaxId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String findTeacheById(Integer id) {
		// TODO Auto-generated method stub
		return courseDao.findTeacheById(id);
	}

	@Override
	public List<Integer> findTeacherAll(Integer id) {
		// TODO Auto-generated method stub
		return courseDao.findTeacherAll(id);
	}



	

	

}
