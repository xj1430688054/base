package com.xj.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xj.base.dao.ILeaveTypeDao;
import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.LeaveType;
import com.xj.base.service.ILeaveTypeService;
import com.xj.base.service.support.impl.BaseServiceImpl;

@Service
public class LeaveTypeServiceImpl extends BaseServiceImpl<LeaveType, Integer> implements ILeaveTypeService{
	
	@Autowired
	private ILeaveTypeDao leaveTypeDao;
	@Override
	public IBaseDao<LeaveType, Integer> getBaseDao() {
		// TODO Auto-generated method stub
		return leaveTypeDao;
	}

}
