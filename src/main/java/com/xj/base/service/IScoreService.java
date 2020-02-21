package com.xj.base.service;

import com.xj.base.entity.Score;
import com.xj.base.service.support.IBaseService;

public interface IScoreService extends IBaseService<Score, Integer>{

	void saveOrUpdate(Score score);

	String findStunameById(Integer sid);

	String findCourseNameByid(Integer cid);

}
