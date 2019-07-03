package com.xj.base.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Department;

@Repository
public interface IDepartmentDao extends IBaseDao<Department, Integer>{

	Department findByName(String name);
	
	@Query(nativeQuery = true,value = "select name from tb_department where id = ?1")
	String findNameById(Integer id);
	@Query(nativeQuery = true,value = "select deppath from tb_department where id = ?1")
	String findDeppathById(Integer id);
	
	@Query(nativeQuery = true,value = "select max(id) from base.tb_department")
	Integer findMaxId();
	

	
}
