package com.xj.base.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.xj.base.entity.support.BaseEntity;


@Entity
@Table(name = "tb_salary")
public class Salary extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
    private Integer id;
	
    private String bonus;
    
    private String lunchsalary;
    
    private String trafficsalary;
    
    private String basicsalary;
    
    private String allsalary;
    
    private String pensionbase;
    
    private String pensionper;
    
    @JSONField(format = "yyyy-MM-dd")
    private Date createdate;
    
    private String medicalbase;
    
    private String medicalper;
    
    private String accumulationfundbase;
    
    private String accumulationfundper;
    
    private String name;

	public synchronized Integer getId() {
		return id;
	}

	public synchronized void setId(Integer id) {
		this.id = id;
	}

	public synchronized String getBonus() {
		return bonus;
	}

	public synchronized void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public synchronized String getLunchsalary() {
		return lunchsalary;
	}

	public synchronized void setLunchsalary(String lunchsalary) {
		this.lunchsalary = lunchsalary;
	}

	public synchronized String getTrafficsalary() {
		return trafficsalary;
	}

	public synchronized void setTrafficsalary(String trafficsalary) {
		this.trafficsalary = trafficsalary;
	}

	public synchronized String getBasicsalary() {
		return basicsalary;
	}

	public synchronized void setBasicsalary(String basicsalary) {
		this.basicsalary = basicsalary;
	}

	public synchronized String getAllsalary() {
		return allsalary;
	}

	public synchronized void setAllsalary(String allsalary) {
		this.allsalary = allsalary;
	}

	public synchronized String getPensionbase() {
		return pensionbase;
	}

	public synchronized void setPensionbase(String pensionbase) {
		this.pensionbase = pensionbase;
	}

	public synchronized String getPensionper() {
		return pensionper;
	}

	public synchronized void setPensionper(String pensionper) {
		this.pensionper = pensionper;
	}

	public synchronized Date getCreatedate() {
		return createdate;
	}

	public synchronized void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public synchronized String getMedicalbase() {
		return medicalbase;
	}

	public synchronized void setMedicalbase(String medicalbase) {
		this.medicalbase = medicalbase;
	}

	public synchronized String getMedicalper() {
		return medicalper;
	}

	public synchronized void setMedicalper(String medicalper) {
		this.medicalper = medicalper;
	}

	public synchronized String getAccumulationfundbase() {
		return accumulationfundbase;
	}

	public synchronized void setAccumulationfundbase(String accumulationfundbase) {
		this.accumulationfundbase = accumulationfundbase;
	}

	public synchronized String getAccumulationfundper() {
		return accumulationfundper;
	}

	public synchronized void setAccumulationfundper(String accumulationfundper) {
		this.accumulationfundper = accumulationfundper;
	}

	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}
    
    
    
}