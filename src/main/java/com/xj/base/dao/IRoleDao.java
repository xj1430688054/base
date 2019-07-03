package com.xj.base.dao;

import org.springframework.stereotype.Repository;

import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Role;

@Repository
public interface IRoleDao extends IBaseDao<Role, Integer> {

}
