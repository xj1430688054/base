package com.xj.base.service;

import com.xj.base.entity.User;
import com.xj.base.service.support.IBaseService;

/**
 * <p>
 * 用户服务类
 * </p>
 *
 * @author xujian
 * @since 2020-02-28
 */
public interface IUserService extends IBaseService<User, Integer> {

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	User findByUserName(String username);

	/**
	 * 增加或者修改用户
	 * @param user
	 */
	void saveOrUpdate(User user);

	/**
	 * 给用户分配角色
	 * @param id 用户ID
	 * @param roleIds 角色Ids
	 */
	void grant(Integer id, String[] roleIds);

	String findRoleNameById(Integer id);


}
