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
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xj.base.entity.support.BaseEntity;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_course")
public class Course extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
    private Integer id;

    private String name;

//    private Integer tid;


    private String enabled;
    
//    @JsonIgnoreProperties
//    @JsonIgnore
//    @JSONField(serialize = false)
//   	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
//   	@JoinTable(name = "tb_teacher_course", joinColumns = { @JoinColumn(name = "cid") }, inverseJoinColumns = { @JoinColumn(name = "tid") })
//    private Teacher teacher;
    
    @Transient
    private String teachername;
    
    
//    public void setTeacher(Teacher teacher) {
//        this.teacher = teacher;
//        this.teachername = teacher.getName();
//        
//    }
//    
    

  
    
    
	
    
    

}