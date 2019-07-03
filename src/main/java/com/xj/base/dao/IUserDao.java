package com.xj.base.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.User;

@Repository
public interface IUserDao extends IBaseDao<User, Integer> {

	User findByUserName(String username);
	
	@Query(nativeQuery = true,value = "select a.name from tb_role a where id = (select b.role_id from tb_user_role b where b.user_id = ?1)")
	String findRoleNameById(Integer id);

}
