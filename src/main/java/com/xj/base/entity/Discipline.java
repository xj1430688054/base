package com.xj.base.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import com.xj.base.entity.support.BaseEntity;

import lombok.Data;

/**
 * 违纪
 * @author xujian
 * @since 
 */
@Data
@Entity
@Table(name = "tb_discipline")
public class Discipline extends BaseEntity{
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "id", nullable = false)
//	private Integer id;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 学生id
	 */
	@Id
	private Integer id;
	
//	@OneToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
//	@JoinTable(name = "tb_student_discipline", joinColumns = { @JoinColumn(name = "sid") }, inverseJoinColumns = { @JoinColumn(name = "did") })
//	private Student student;
	
	private Integer semester1 = 0;
	
	private Integer semester2 = 0;
	
	private Integer semester3 = 0;
	
	private Integer semester4 = 0;
	
	private Integer semester5 = 0;
	
	private Integer semester6 = 0;
	
	@Transient
	private String stuname;
	
	
	@SuppressWarnings("unused")
	private Integer sum;
	
	public Integer getSum() {
		
		return semester1 + semester2 +  semester3 + semester4 + semester5 + semester6;
	}
	
	public void setSum() {
		
		this.sum =  semester1 + semester2 +  semester3 + semester4 + semester5 + semester6;
	}

}
