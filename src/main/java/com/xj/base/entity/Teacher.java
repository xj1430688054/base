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

import lombok.Data;

@Data
@Entity
@Table(name = "tb_teacher")
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	private String name;

	private int sex;

	@JSONField(format = "yyyy-MM-dd")
	private Date birthday;


	private Integer nation;

	private String nativeplace;

	private String email;

	private String phone;

	private String address;

    @JSONField(serialize = false)
	@OneToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "tb_teacher_course", joinColumns = { @JoinColumn(name = "tid") }, inverseJoinColumns = { @JoinColumn(name = "cid") })
    private java.util.Set<Course> courses;
}
