package com.xj.base.service;

import com.xj.base.entity.Dormitory;
import com.xj.base.entity.User;
import com.xj.base.service.support.IBaseService;

/**
 * <p>
 * 职位服务类
 * </p>
 *
 * @author xj
 * @since 2020-02-28
 */
public interface IDormitoryService extends IBaseService<Dormitory, Integer>{

	void saveOrUpdate(Dormitory dormitory);

	
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
//	Position findById(Integer id);

}
