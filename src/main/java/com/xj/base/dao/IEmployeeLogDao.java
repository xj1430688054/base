package com.xj.base.dao;

import org.springframework.stereotype.Repository;

import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.EmployeeLog;

@Repository
public interface IEmployeeLogDao extends IBaseDao<EmployeeLog, Integer>{
	
}

