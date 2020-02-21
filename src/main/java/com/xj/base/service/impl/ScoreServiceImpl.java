package com.xj.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xj.base.dao.IScoreDao;
import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Score;
import com.xj.base.service.IScoreService;
import com.xj.base.service.support.impl.BaseServiceImpl;

@Service
public class ScoreServiceImpl extends BaseServiceImpl<Score, Integer> implements IScoreService{
	
	@Autowired
	private IScoreDao scoreDao;

	@Override
	public IBaseDao<Score, Integer> getBaseDao() {
		// TODO Auto-generated method stub
		return scoreDao;
	}

	@Override
	public void saveOrUpdate(Score score) {
		if(score.getId() != null){
			Score dbscore = find(score.getId());
			dbscore.setScore(score.getScore());
			update(dbscore);
		}else{
			
//			score.setEnabled("1");
			save(score);
		}
		
	}

	@Override
	public String findStunameById(Integer sid) {
		// TODO Auto-generated method stub
		return scoreDao.findStunameById(sid);
	}

	@Override
	public String findCourseNameByid(Integer cid) {
		// TODO Auto-generated method stub
		return scoreDao.findCourseNameByid(cid);
	}

	

}
