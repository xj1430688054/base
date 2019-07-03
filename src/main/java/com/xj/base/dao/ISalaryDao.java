package com.xj.base.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Salary;

@Repository
public interface ISalaryDao extends IBaseDao<Salary, Integer>{
	
//	@Query(nativeQuery = true,value = "select name from tb_salary where id = ?1")
	
	Salary findById(Integer id);

}
