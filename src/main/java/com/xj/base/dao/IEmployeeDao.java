package com.xj.base.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Employee;

@Repository
public interface IEmployeeDao extends IBaseDao<Employee, Integer>{
	
	Employee findByName(String name);
	
	@Query(nativeQuery = true,value = "select count(*) from tb_employee where departmentid = ?1")
	int findCountByDeptId(@Param("id")Integer id);

}
