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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import com.xj.base.entity.support.BaseEntity;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_student")
public class Student extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "id", nullable = false)
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

    
    @JSONField(serialize = false)
	@ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "tb_student_courses", joinColumns = { @JoinColumn(name = "sid") }, inverseJoinColumns = { @JoinColumn(name = "cid") })
    private java.util.Set<Course> courses;
    
    
    @JSONField(serialize = false)
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "tb_student_dormitory", joinColumns = { @JoinColumn(name = "sid") }, inverseJoinColumns = { @JoinColumn(name = "did") })
    private Dormitory dormitory;
//    private java.util.Set<Dormitory> dormitory;
//    private Integer cid;
    
    private Integer did;
    
    
    @Transient
    private String dormName;


	

    
    

    
	
	
    


}