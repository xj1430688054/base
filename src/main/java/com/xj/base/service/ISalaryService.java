package com.xj.base.service;

import com.xj.base.entity.Salary;
import com.xj.base.service.support.IBaseService;

public interface ISalaryService extends IBaseService<Salary, Integer>{

	void saveOrUpdate(Salary salary);
	
	Salary findById(Integer id);
}
