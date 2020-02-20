package com.xj.base.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Discipline;

@Repository
public interface DisciplineDao extends IBaseDao<Discipline, Integer>{

	
	@Query(nativeQuery = true,value = "select a.name from tb_student a where id = ?1")
	String findNameById(Integer id);

	
//	@Modifying
//	@Query(nativeQuery = true,value = "insert into tb_student_discipline (did, sid) values (?1, ?1)")
//	void save(Integer id);

}
