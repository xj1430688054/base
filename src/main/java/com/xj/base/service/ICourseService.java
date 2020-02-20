package com.xj.base.service;

import java.util.Date;
import java.util.List;

import com.xj.base.entity.Teacher;
import com.xj.base.entity.Course;
import com.xj.base.entity.User;
import com.xj.base.service.support.IBaseService;

/**
 * <p>
 * 部门服务类
 * </p>
 *
 * @author xj
 * @since 2020-02-28
 */
public interface ICourseService extends IBaseService<Course, Integer>{
	
	/**
	 * 根据部门名字查找部门
	 * @param username
	 * @return
	 */
	User findByUserName(String name);

	void saveOrUpdate(Course course);

	String findNameById(Integer id);
	
	String findDeppathById(Integer id);
	
	Integer findMaxId();

	String findTeacheById(Integer id);

	List<Integer> findTeacherAll(Integer id);



}
