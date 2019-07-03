package com.xj.base.dao;

import org.springframework.stereotype.Repository;

import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Position;
import com.xj.base.entity.Resource;

@Repository
public interface IPositionDao extends IBaseDao<Position, Integer>{

}
