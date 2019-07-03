package com.xj.base.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xj.base.common.utils.MD5Utils;
import com.xj.base.dao.IDepartmentDao;
import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Department;
import com.xj.base.entity.User;
import com.xj.base.service.IDepartmentService;
import com.xj.base.service.support.impl.BaseServiceImpl;


@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department, Integer>implements IDepartmentService{
	
	@Autowired
	private IDepartmentDao iDeprecatedDao;

	@Override
	public User findByUserName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBaseDao<Department, Integer> getBaseDao() {
		// TODO Auto-generated method stub
		return this.iDeprecatedDao;
	}

	@Override
	public void saveOrUpdate(Department department) {
		if(department.getId() != null){
			Department dbdepartment = find(department.getId());
			dbdepartment.setName(department.getName());
			dbdepartment.setUpdateTime(new Date());
			update(dbdepartment);
		}else{
			Integer i = iDeprecatedDao.findMaxId() + 1;
			String name = iDeprecatedDao.findDeppathById(department.getParentid());
			
			department.setCreateTime(new Date());
			department.setUpdateTime(new Date());
			department.setId(i); 
			department.setDeppath(name + "." + i);
			department.setEnabled("1");
			department.setIsparent("0");
			save(department);
		}
		
	}

	@Override
	public String findNameById(Integer id) {
		// TODO Auto-generated method stub
		String name = iDeprecatedDao.findNameById(id);
		return name;
	}

	@Override
	public String findDeppathById(Integer id) {
		// TODO Auto-generated method stub
		String name = iDeprecatedDao.findDeppathById(id);
		return name;
	}

	@Override
	public Integer findMaxId() {
		// TODO Auto-generated method stub
		Integer i = iDeprecatedDao.findMaxId();
		return i;
	}



	

	

}
