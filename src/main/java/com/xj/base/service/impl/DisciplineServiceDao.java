package com.xj.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xj.base.dao.DisciplineDao;
import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Discipline;
import com.xj.base.service.DisciplineService;
import com.xj.base.service.support.impl.BaseServiceImpl;


@Service
public class DisciplineServiceDao extends BaseServiceImpl<Discipline, Integer> implements DisciplineService {
	
	
	@Autowired
	private DisciplineDao disciplineDao;

	@Override
	public IBaseDao<Discipline, Integer> getBaseDao() {
		// TODO Auto-generated method stub
		return disciplineDao;
	}

	@Override
	public String findNameById(Integer id) {
		// TODO Auto-generated method stub
		return disciplineDao.findNameById(id);
	}

//	@Override
//	public void saves(Discipline d) {
//		// TODO Auto-generated method stub
//		disciplineDao.save(d.getId());
//		
//	}

}
