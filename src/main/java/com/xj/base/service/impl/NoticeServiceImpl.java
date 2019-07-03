package com.xj.base.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xj.base.dao.INoticeDao;
import com.xj.base.dao.support.IBaseDao;
import com.xj.base.entity.Notice;
import com.xj.base.entity.User;
import com.xj.base.service.INoticeService;
import com.xj.base.service.support.impl.BaseServiceImpl;

@Service
public class NoticeServiceImpl extends BaseServiceImpl<Notice, Integer> implements INoticeService{
	@Autowired
	private INoticeDao noticeDao;
	@Autowired
	private HttpServletRequest request;

	@Override
	public IBaseDao<Notice, Integer> getBaseDao() {
		// TODO Auto-generated method stub
		return this.noticeDao;
	}

	@Override
	public void saveOrUpdate(Notice notice) {
		// TODO Auto-generated method stub
		if(notice.getId() != null){
			//do nothing
		}else{
			User user = (User)request.getSession().getAttribute("users");
			String userName = user.getUserName();
			System.out.println(userName);
			notice.setCreateTime(new Date());
			notice.setUid(user.getId());
			save(notice);
		}
		
	}

}
