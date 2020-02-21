package com.xj.base.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Score;

@Repository
public interface IScoreDao  extends IBaseDao<Score, Integer>{

	@Query(nativeQuery = true,value = "select name from tb_student where id = ?1")
	String findStunameById(Integer sid);

	@Query(nativeQuery = true,value = "select name from tb_course where id = ?1")
	String findCourseNameByid(Integer cid);

}
