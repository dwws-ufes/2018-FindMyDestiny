package br.inf.ufes.findmydestiny.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Enterprise implements Serializable{

	public long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseFullname() {
		return enterpriseFullname;
	}

	public void setEnterpriseFullname(String enterpriseFullname) {
		this.enterpriseFullname = enterpriseFullname;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMainTel() {
		return mainTel;
	}

	public void setMainTel(String mainTel) {
		this.mainTel = mainTel;
	}

	public String getSecondaryTel() {
		return secondaryTel;
	}

	public void setSecondaryTel(String secondaryTel) {
		this.secondaryTel = secondaryTel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="id",nullable=false, unique=true)
	@Id
	@GeneratedValue
	private long enterpriseId;
	
	@Column(name = "enterpriseFullname",nullable=false, unique=false, length=80)
	private String enterpriseFullname;
	
	@Column(name = "managerName",nullable=false, unique=false, length=80)
	private String managerName;
	
	@Column(name = "enterpriseName",nullable=false, unique=true, length=80)
	private String enterpriseName;
	
	@Column(name = "cnpj",nullable=false, unique=false, length=30)
	private String cnpj;
	
	@Column(name = "email",nullable=false, unique=true, length=80)
	private String email;
	
	@Column(name = "address",nullable=false, unique=false, length=80)
	private String address;
	
	@Column(name = "mainTel",nullable=false, unique=false, length=15)
	private String mainTel;
	
	@Column(name = "secondaryTel",nullable=false, unique=false, length=15)
	private String secondaryTel;
	
	@Column(name = "password",nullable=false, unique=false, length=80)
	private String password;
	
	
}
