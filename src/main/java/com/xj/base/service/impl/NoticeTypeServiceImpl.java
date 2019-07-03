package com.xj.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xj.base.dao.INoticeTypeDao;
import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.NoticeType;
import com.xj.base.service.INoticeTypeSerivce;
import com.xj.base.service.support.impl.BaseServiceImpl;

@Service
public class NoticeTypeServiceImpl extends BaseServiceImpl<NoticeType, Integer> implements  INoticeTypeSerivce{
	
	@Autowired
	private INoticeTypeDao noticetypeDap;

	@Override
	public IBaseDao<NoticeType, Integer> getBaseDao() {
		// TODO Auto-generated method stub
		return this.noticetypeDap;
	}

}
