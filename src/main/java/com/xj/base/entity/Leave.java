package com.xj.base.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import com.xj.base.entity.support.BaseEntity;

@Entity
@Table(name = "tb_leave")
public class Leave extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@Column(nullable = true, name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	/** 申请用户 ，HttpServletRequest 中的request 对象 getSession.getAttribute("users")*/
	private String eid;
	
	/** 请假类型 */
	private Integer leaveid;
	
	/** 请假的类型，如事假期，病假，丧家等等   此字段不在数据库表中*/
	@Transient
	private String leaveName;
	
	@Transient
	private String eName;
	
	@Transient
	private String pName;
	
	/** 请假理由*/
	private String reqmessage;
	
	/** 请假开始时间*/
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	/** 请假结束时间*/
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date stopTime;
	
	/** 请假时长*/
	private String duration;
	
	/** 申请时间*/
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	/** 状态0表示未审批，1表示审批同意 2表示不同意 */
	private String status;
	
	/** 审批人id*/
	private String pid;
	
	/** 审批备注*/
	private String remark; 
	
	/** 审批时间*/
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	

	public synchronized String getDuration() {
		return duration;
	}

	public synchronized void setDuration(String duration) {
		this.duration = duration;
	}

	public synchronized Integer getId() {
		return id;
	}
	
	public synchronized String geteName() {
		return eName;
	}

	public synchronized void seteName(String eName) {
		this.eName = eName;
	}

	public synchronized String getpName() {
		return pName;
	}

	public synchronized void setpName(String pName) {
		this.pName = pName;
	}

	public synchronized void setId(Integer id) {
		this.id = id;
	}

	public synchronized String getEid() {
		return eid;
	}

	public synchronized void setEid(String eid) {
		this.eid = eid;
	}

	public synchronized Integer getLeaveid() {
		return leaveid;
	}

	public synchronized void setLeaveid(Integer leaveid) {
		this.leaveid = leaveid;
	}

	public synchronized String getLeaveName() {
		return leaveName;
	}

	public synchronized void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public synchronized String getReqmessage() {
		return reqmessage;
	}

	public synchronized void setReqmessage(String reqmessage) {
		this.reqmessage = reqmessage;
	}

	public synchronized Date getStartTime() {
		return startTime;
	}

	public synchronized void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public synchronized Date getStopTime() {
		return stopTime;
	}

	public synchronized void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public synchronized Date getCreateTime() {
		return createTime;
	}

	public synchronized void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public synchronized String getStatus() {
		return status;
	}

	public synchronized void setStatus(String status) {
		this.status = status;
	}

	public synchronized String getPid() {
		return pid;
	}

	public synchronized void setPid(String pid) {
		this.pid = pid;
	}

	public synchronized String getRemark() {
		return remark;
	}

	public synchronized void setRemark(String remark) {
		this.remark = remark;
	}

	public synchronized Date getUpdateTime() {
		return updateTime;
	}

	public synchronized void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	
	

}
