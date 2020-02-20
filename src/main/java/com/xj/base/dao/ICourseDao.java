package com.xj.base.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Course;
import com.xj.base.entity.Teacher;

@Repository
public interface ICourseDao extends IBaseDao<Course, Integer>{

	Course findByName(String name);
	
	@Query(nativeQuery = true,value = "select name from tb_course where id = ?1")
	String findNameById(Integer id);

	@Query(nativeQuery = true,value = "select a.name from tb_teacher a where id = (select b.tid from tb_teacher_course b where b.cid = ?1)")
	String findTeacheById(Integer id);
	
	
	@Query(nativeQuery = true,value = "select a.id from tb_course a  where a.id not in (select distinct b.cid from studentbase.tb_teacher_course b ) union select c.id from tb_course c where c.id  in (select  d.cid from tb_teacher_course d where d.tid = ?1)")
	List<Integer> findTeacherAll(Integer id);
	

	
}
