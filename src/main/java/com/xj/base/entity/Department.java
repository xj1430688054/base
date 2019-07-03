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
@Table(name = "tb_department")
public class Department extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    private Integer parentid;

    private String deppath;

    private String enabled;

    private String isparent;
    
    @Transient
    private String isparentname;
    
    @Column(name = "create_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    @Column(name = "update_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    
    @Transient
    private Integer empnum;
    
    @Transient
    private Integer number;
    
    
	public synchronized String getIsparentname() {
		return isparentname;
	}

	public synchronized void setIsparentname(String isparentname) {
		this.isparentname = isparentname;
	}

	public  Integer getId() {
		return id;
	}

	public  void setId(Integer id) {
		this.id = id;
	}

	public  String getName() {
		return name;
	}

	public  void setName(String name) {
		this.name = name;
	}

	public  Integer getParentid() {
		return parentid;
	}

	public  void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public  String getDeppath() {
		return deppath;
	}

	public  void setDeppath(String deppath) {
		this.deppath = deppath;
	}

	public  String getEnabled() {
		return enabled;
	}

	public  void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public  String getIsparent() {
		return isparent;
	}

	public  void setIsparent(String isparent) {
		this.isparent = isparent;
	}

	public  Date getCreateTime() {
		return createTime;
	}

	public  void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public  Date getUpdateTime() {
		return updateTime;
	}

	public  void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public  Integer getEmpnum() {
		return empnum;
	}

	public  void setEmpnum(Integer empnum) {
		this.empnum = empnum;
	}

	public  Integer getNumber() {
		return number;
	}

	public  void setNumber(Integer number) {
		this.number = number;
	}
    
    

}