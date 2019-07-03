package com.xj.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xj.base.dao.IEmployeeLogDao;
import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.EmployeeLog;
import com.xj.base.service.IEmployeeLogService;
import com.xj.base.service.support.impl.BaseServiceImpl;

@Service
public class EmployeeLogServiceImpl extends BaseServiceImpl<EmployeeLog, Integer> implements IEmployeeLogService{
	@Autowired
	private IEmployeeLogDao employeeLogDao;
	
	@Override
	public IBaseDao<EmployeeLog, Integer> getBaseDao() {
		// TODO Auto-generated method stub
		return employeeLogDao;
	}

}
