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
@Table(name = "tb_employeeLog")
public class EmployeeLog extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    private int sex;
    
    @JSONField(format = "yyyy-MM-dd")
    private Date birthday;

    private String idcard;

    private String wedlock;

    private Integer nationid;

    private String nativeplace;

    private Integer politicid;

    private String email;

    private String phone;

    private String address;

    private Integer departmentid;

    private Integer joblevelid;

    private Integer posid;

    private String engageform;

    private String tiptopdegree;

    private String specialty;

    private String school;

    private Date begindate;

    private String workstate;

    private String workid;

    private Double contractterm;
    
    private Integer sid;
    
    @Transient
    private String salName;

	@JSONField(format = "yyyy-MM-dd")
    private Date conversiontime;
    
    @JSONField(format = "yyyy-MM-dd")
    private Date notworkdate;
    
    @JSONField(format = "yyyy-MM-dd")
    private Date begincontract;

    @JSONField(format = "yyyy-MM-dd")
    private Date endcontract;

    public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	private Integer workage;
	
	
	
	@Transient
	private String posina;
	
    public String getPosina() {
		return posina;
	}
    

	public synchronized Integer getSid() {
		return sid;
	}

	public synchronized void setSid(Integer sid) {
		this.sid = sid;
	}

	public void setPosina(String posina) {
		this.posina = posina;
	}

	@Transient
    private String deptname;

    public synchronized String getSalName() {
		return salName;
	}

	public synchronized void setSalName(String salName) {
		this.salName = salName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getWedlock() {
        return wedlock;
    }

    public void setWedlock(String wedlock) {
        this.wedlock = wedlock;
    }

    public Integer getNationid() {
        return nationid;
    }

    public void setNationid(Integer nationid) {
        this.nationid = nationid;
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    public Integer getPoliticid() {
        return politicid;
    }

    public void setPoliticid(Integer politicid) {
        this.politicid = politicid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getJoblevelid() {
        return joblevelid;
    }



	public void setJoblevelid(Integer joblevelid) {
        this.joblevelid = joblevelid;
    }




	public String getEngageform() {
        return engageform;
    }

    public Integer getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}

	public Integer getPosid() {
		return posid;
	}

	public void setPosid(Integer posid) {
		this.posid = posid;
	}

	public void setEngageform(String engageform) {
        this.engageform = engageform;
    }

    public String getTiptopdegree() {
        return tiptopdegree;
    }

    public void setTiptopdegree(String tiptopdegree) {
        this.tiptopdegree = tiptopdegree;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Date getBegindate() {
        return begindate;
    }

    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    public String getWorkstate() {
        return workstate;
    }

    public void setWorkstate(String workstate) {
        this.workstate = workstate;
    }

    public String getWorkid() {
        return workid;
    }

    public void setWorkid(String workid) {
        this.workid = workid;
    }

    public Double getContractterm() {
        return contractterm;
    }

    public void setContractterm(Double contractterm) {
        this.contractterm = contractterm;
    }

    public Date getConversiontime() {
        return conversiontime;
    }

    public void setConversiontime(Date conversiontime) {
        this.conversiontime = conversiontime;
    }

    public Date getNotworkdate() {
        return notworkdate;
    }

    public void setNotworkdate(Date notworkdate) {
        this.notworkdate = notworkdate;
    }

    public Date getBegincontract() {
        return begincontract;
    }

    public void setBegincontract(Date begincontract) {
        this.begincontract = begincontract;
    }

    public Date getEndcontract() {
        return endcontract;
    }

    public void setEndcontract(Date endcontract) {
        this.endcontract = endcontract;
    }

    public Integer getWorkage() {
        return workage;
    }

    public void setWorkage(Integer workage) {
        this.workage = workage;
    }
}