package com.xj.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.stat.TableStat.Name;
import com.xj.base.dao.ISalaryDao;
import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Salary;
import com.xj.base.service.ISalaryService;
import com.xj.base.service.support.impl.BaseServiceImpl;

@Service
public class SalaryServiceImpl extends BaseServiceImpl<Salary, Integer> implements ISalaryService{
	@Autowired
	private ISalaryDao salaryDao;
	
	@Override
	public IBaseDao<Salary, Integer> getBaseDao() {
		// TODO Auto-generated method stub
		return salaryDao;
	}

	@Override
	public void saveOrUpdate(Salary salary) {
		// TODO Auto-generated method stub
		if(salary.getId() != null){
			update(salary);
		}else{
			save(salary);
		}
		
	}

	@Override
	public Salary findById(Integer id) {
		// TODO Auto-generated method stub
		Salary salary = salaryDao.findById(id);
		return salary;
	}

}
