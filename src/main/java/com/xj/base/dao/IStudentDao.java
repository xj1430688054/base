package com.xj.base.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Student;

@Repository
public interface IStudentDao extends IBaseDao<Student, Integer>{
	
	Student findByName(String name);
	
//	@Query(nativeQuery = true,value = "select a.name from tb_course a where id = (select b.did from tb_student_dormitory b where b.sid = ?1)")
//	String findCourseNameById(Integer id);
	
	@Query(nativeQuery = true,value = "select count(*) from tb_employee where courseid = ?1")
	int findCountByDeptId(@Param("id")Integer id);
	@Query(nativeQuery = true,value = "select concat(a.building,a.doorplate) from tb_dormitory a where id = (select b.did from tb_student_dormitory b where b.sid = ?1)")
	String findDormitoryNameById(Integer id);

	
	@Query(nativeQuery = true,value = "select last_insert_id();")
	Integer selectId();

}
