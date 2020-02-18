package com.xj.base.service;

import com.xj.base.entity.Student;
import com.xj.base.entity.User;
import com.xj.base.service.support.IBaseService;

/**
 * <p>
 * 人员服务类
 * </p>
 *
 * @author xj
 * @since 2020-02-28
 */
public interface IStudentService extends IBaseService<Student, Integer>{

	/**
	 * 根据用户名查找用户
	 * @param name 名字
	 * @return
	 */
	Student findByName(String name);
	/**
	 * 增加或者修改用户
	 * @param user
	 */
	String saveOrUpdate(Student student);
	
	/**
	 * 根据部门id查找数量
	 */
	int findCountByDeptId(Integer id);
	void grant(Integer id, String[] roleIds);
	String findDormitoryNameById(Integer id);
	
}
