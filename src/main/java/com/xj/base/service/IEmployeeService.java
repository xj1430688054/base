package com.xj.base.service;

import com.xj.base.entity.Employee;
import com.xj.base.entity.User;
import com.xj.base.service.support.IBaseService;

/**
 * <p>
 * 人员服务类
 * </p>
 *
 * @author xj
 * @since 2019-03-15
 */
public interface IEmployeeService extends IBaseService<Employee, Integer>{

	/**
	 * 根据用户名查找用户
	 * @param name 名字
	 * @return
	 */
	Employee findByName(String name);
	/**
	 * 增加或者修改用户
	 * @param user
	 */
	String saveOrUpdate(Employee employee);
	
	/**
	 * 根据部门id查找数量
	 */
	int findCountByDeptId(Integer id);
	
}
