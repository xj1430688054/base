package com.xj.base.service;

import java.util.Date;

import com.xj.base.entity.Department;
import com.xj.base.entity.User;
import com.xj.base.service.support.IBaseService;

/**
 * <p>
 * 部门服务类
 * </p>
 *
 * @author xj
 * @since 2019-03-15
 */
public interface IDepartmentService extends IBaseService<Department, Integer>{
	
	/**
	 * 根据部门名字查找部门
	 * @param username
	 * @return
	 */
	User findByUserName(String name);

	void saveOrUpdate(Department department);

	String findNameById(Integer id);
	
	String findDeppathById(Integer id);
	
	Integer findMaxId();



}
