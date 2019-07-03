package com.xj.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xj.base.dao.ILeaveDao;
import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Leave;
import com.xj.base.service.ILeaveService;
import com.xj.base.service.support.impl.BaseServiceImpl;

@Service
public class LeaveServiceImpl extends BaseServiceImpl<Leave, Integer> implements ILeaveService{
	
	@Autowired
	private ILeaveDao leaveDao;
	
	@Override
	public IBaseDao<Leave, Integer> getBaseDao() {
		// TODO Auto-generated method stub
		return leaveDao;
	}

}
