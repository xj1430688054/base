package com.xj.base.service;

import com.xj.base.entity.Notice;
import com.xj.base.service.support.IBaseService;

/**
 * <p>
 * 公告服务类
 * </p>
 *
 * @author xj
 * @since 2020-02-28
 */
public interface INoticeService extends IBaseService<Notice, Integer>{

	void saveOrUpdate(Notice notice);

}
