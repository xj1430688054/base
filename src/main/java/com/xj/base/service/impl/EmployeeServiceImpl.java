package com.xj.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xj.base.common.utils.MD5Utils;
import com.xj.base.dao.IEmployeeDao;
import com.xj.base.dao.IUserDao;
import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Employee;
import com.xj.base.entity.User;
import com.xj.base.service.IEmployeeService;
import com.xj.base.service.IUserService;
import com.xj.base.service.support.impl.BaseServiceImpl;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Integer> implements IEmployeeService{
	
	@Autowired
	private IEmployeeDao iEmployeeDao;
	
	@Autowired
	private IUserService userService;

	@Override
	public Employee findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBaseDao<Employee, Integer> getBaseDao() {
		// TODO Auto-generated method stub
		return this.iEmployeeDao;
	}

	@Override
	public String saveOrUpdate(Employee employee) {
		if(employee.getId() != null){
			Employee dbemployee = find(employee.getId());
			dbemployee.setName(employee.getName());
			dbemployee.setPosid(employee.getPosid());
			dbemployee.setSex(employee.getSex());
			dbemployee.setDepartmentid(employee.getDepartmentid());
			dbemployee.setAddress(employee.getAddress());
			dbemployee.setPhone(employee.getPhone());
			dbemployee.setTiptopdegree(employee.getTiptopdegree());
			update(dbemployee);
			return "";
		}else{
			String a[]={"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
			String b = "";
			for(int i = 0; i <= 6; i++){
				String s = a[(int)(Math.random()*(a.length-1))];
				if (i == 0) {
					while(true){
						if(!"0".equals(s))break;
					}
				}
				b += s;
			}
			User user = new User();
			user.setAddress(employee.getAddress());
			user.setUserName(b);
			user.setNickName(employee.getName());
			user.setTelephone(employee.getPhone());
			user.setBirthday(employee.getBirthday());
			user.setDeleteStatus(0);
			user.setSex(employee.getSex());
			user.setLocked(0);
			user.setCreateTime(new Date());
			user.setDescription("拉拉");
			//默认密码为111111
			user.setPassword(MD5Utils.md5("111111"));
			user.setUpdateTime(new Date());
			employee.setIdcard(b);
			employee.setBegindate(new Date());
			user.setEmail(employee.getEmail());
			userService.save(user);
			save(employee);
			return b;
		}
		
	}

	@Override
	public int findCountByDeptId(Integer id) {
		// TODO Auto-generated method stub
		int a = iEmployeeDao.findCountByDeptId(id);
		return a;
	}





	



	

}
