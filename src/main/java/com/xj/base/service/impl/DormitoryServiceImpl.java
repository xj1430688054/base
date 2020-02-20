package com.xj.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xj.base.dao.IDormitoryDao;
import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Dormitory;
import com.xj.base.entity.Dormitory;
import com.xj.base.entity.Role;
import com.xj.base.service.IDormitoryService;
import com.xj.base.service.IRoleService;
import com.xj.base.service.support.impl.BaseServiceImpl;

@Service
public class DormitoryServiceImpl   extends BaseServiceImpl<Dormitory, Integer> implements IDormitoryService {
	
	@Autowired
	private IDormitoryDao iPositionDao;

	@Override
	public IBaseDao<Dormitory, Integer> getBaseDao() {
		// TODO Auto-generated method stub
		return this.iPositionDao;
	}

	@Override
	public void saveOrUpdate(Dormitory dormitory) {
		if(dormitory.getId() != null){
			Dormitory dbdormitory = find(dormitory.getId());
			dbdormitory.setBuilding(dormitory.getBuilding());
			dbdormitory.setDoorplate(dormitory.getDoorplate());
			update(dbdormitory);
		}else{
			
			
			save(dormitory);
		}
		
	}
	

}
