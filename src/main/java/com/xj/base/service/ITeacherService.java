package com.xj.base.service;

import com.xj.base.entity.Teacher;
import com.xj.base.service.support.IBaseService;

public interface ITeacherService extends IBaseService<Teacher, Integer>{

	String saveOrUpdate(Teacher teacher);

	void grant(Integer id, String[] roleIds);

}
