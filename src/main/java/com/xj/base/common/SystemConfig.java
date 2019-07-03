package com.xj.base.common;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.xj.base.dao.IDepartmentDao;
import com.xj.base.dao.IEmployeeDao;
import com.xj.base.dao.IPositionDao;
import com.xj.base.entity.Department;
import com.xj.base.entity.Position;

/**
 * 此类初始化数据并加入到集合中
 *
 * @author xujian
 * @since 2019-03-15
 */
public class SystemConfig {
	private static Map<Integer, String> map2 = new HashMap<Integer, String>();
	private static Map<Integer, String> map3 = new HashMap<Integer, String>();
	
	@Autowired
	private IEmployeeDao employeeDao;
	@Autowired
	private IPositionDao positionDao;
	@Autowired
	private IDepartmentDao dpartmentDao;
	
	private Logger logger = Logger.getLogger(SystemConfig.class);
	public SystemConfig(){
		init();
	}

	private void init() {
		try {
			Date startTime = new Date();
			logger.info("开始缓存职位以及部门信息，开始时间： " + startTime);
			List<Position> list = positionDao.findAll();
			for (Position position : list) {
				map2.put(position.getId(),position.getName());
			}
			
			List<Department> list1 = dpartmentDao.findAll();
			for (Department dosition : list1) {
				map3.put(dosition.getId(),dosition.getName());
			}
			Date stopTime = new Date();
			logger.info("缓存职位以及部门信息，结束时间： " + stopTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
