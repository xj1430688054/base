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

import lombok.Data;

@Data
@Entity
@Table(name = "tb_dormitory")
public class Dormitory extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id" , nullable = false)
    private Integer id;


    private String building;
    
    private String doorplate;
    
    @Transient
    private Integer dormid;
}