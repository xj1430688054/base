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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xj.base.entity.support.BaseEntity;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_teacher")
public class Teacher extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	private String name;

	private int sex;

	@JSONField(format = "yyyy-MM-dd")
	private Date birthday;


	private String nation;

	private String nativeplace;

	private String email;

	private String phone;

	private String address;

	@JsonIgnoreProperties
    @JsonIgnore
    @JSONField(serialize = false)
	@OneToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "tb_teacher_course", joinColumns = { @JoinColumn(name = "tid") }, inverseJoinColumns = { @JoinColumn(name = "cid") })
    private java.util.Set<Course> courses;
	

	
}