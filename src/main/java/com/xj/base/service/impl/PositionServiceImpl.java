package com.xj.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xj.base.dao.IPositionDao;
import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Position;
import com.xj.base.entity.Role;
import com.xj.base.service.IPositionService;
import com.xj.base.service.IRoleService;
import com.xj.base.service.support.impl.BaseServiceImpl;

@Service
public class PositionServiceImpl   extends BaseServiceImpl<Position, Integer> implements IPositionService {
	
	@Autowired
	private IPositionDao iPositionDao;

	@Override
	public IBaseDao<Position, Integer> getBaseDao() {
		// TODO Auto-generated method stub
		return this.iPositionDao;
	}

}
