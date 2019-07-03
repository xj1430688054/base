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
@Table(name = "tb_notice")
public class Notice extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一标识
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 公告内容
	 */
	@Column(length = 2000)
	private String content;

	/**
	 * 添加时间
	 */
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 发布人主键
	 */
	private Integer uid;
	
	/**
	 * 发布人姓名
	 */
	@Transient
	private String authorName;
	
	/**
	 * 
	 */
	@Transient
	private String typeName;

	/**
	 * 暂未使用
	 */
	@Column(name = "EXT1")
	private String ext1;
	
	
	private Integer tid;
	
	public synchronized void setUid(Integer uid) {
		this.uid = uid;
	}

	public synchronized String getTypeName() {
		return typeName;
	}

	public synchronized void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public synchronized Integer getTid() {
		return tid;
	}

	public synchronized void setTid(Integer tid) {
		this.tid = tid;
	}

	public synchronized Date getCreateTime() {
		return createTime;
	}

	public synchronized void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public synchronized Integer getId() {
		return id;
	}

	public synchronized void setId(Integer id) {
		this.id = id;
	}

	public synchronized String getTitle() {
		return title;
	}

	public synchronized void setTitle(String title) {
		this.title = title;
	}

	public synchronized String getContent() {
		return content;
	}

	public synchronized void setContent(String content) {
		this.content = content;
	}


	public synchronized Integer getUid() {
		return uid;
	}

	public synchronized void setEid(Integer uid) {
		this.uid = uid;
	}

	public synchronized String getAuthorName() {
		return authorName;
	}

	public synchronized void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public synchronized String getExt1() {
		return ext1;
	}

	public synchronized void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	
	

	

	

}