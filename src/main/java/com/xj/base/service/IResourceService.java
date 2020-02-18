package com.xj.base.service;

import java.util.List;

import com.xj.base.entity.Resource;
import com.xj.base.service.support.IBaseService;
import com.xj.base.vo.ZtreeView;

/**
 * <p>
 * 资源服务类
 * </p>
 *
 * @author xujian
 * @since 2020-02-28
 */
public interface IResourceService extends IBaseService<Resource, Integer> {

	/**
	 * 获取角色的权限树
	 * @param roleId
	 * @return
	 */
	List<ZtreeView> tree(int roleId);

	/**
	 * 修改或者新增资源
	 * @param resource
	 */
	void saveOrUpdate(Resource resource);

}
