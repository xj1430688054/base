package com.xj.base.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Course;

@Repository
public interface ICourseDao extends IBaseDao<Course, Integer>{

	Course findByName(String name);
	
	@Query(nativeQuery = true,value = "select name from tb_course where id = ?1")
	String findNameById(Integer id);
	

	
}
