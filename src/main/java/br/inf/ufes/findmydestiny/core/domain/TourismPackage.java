package br.inf.ufes.findmydestiny.core.domain;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tourism_package")
public class TourismPackage {
	


	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Id
	@Column(name="id", nullable=false, unique=true)
	private int packageId;

	@Column(name="package_name", nullable=false, unique=false)
	private String packageName;

	//@OneToOne
	// TODO: if sytems accepts multiple user per package in the future, 'unique' value must turn to be true
	//private User user;
	
	//@OneToOne
	@Column(name="user_id",nullable=false,unique=false)
	private Long userid;
	
	
	@Column(name="start_date",nullable=false,unique=false)
	private String startDate;
	

	
	@Column(name="end_date",nullable=false,unique=false)
	private String endDate;
	
	@Column(name="price",nullable=true,unique=false)
	private float price = 0;
	
	@Column(name="confirm",nullable=false,unique=false)
	private boolean confirmed = false;
	
	
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

//	@ManyToMany(targetEntity=Destination.class)
//	private List<Destination> destinations;
//	
	
	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
/*	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/

//	public List<Destination> getDestinations() {
//		return destinations;
//	}
//
//	public void setDestinations(List<Destination> destinations) {
//		this.destinations = destinations;
//	}	
}