package com.xj.base.service;

import com.xj.base.entity.Discipline;
import com.xj.base.service.support.IBaseService;


public interface DisciplineService extends IBaseService<Discipline, Integer>{

	String findNameById(Integer id);

//	 void saves(Discipline d);

}
