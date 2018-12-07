package br.inf.ufes.findmydestiny.core.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

//@Entity
//public class Destination {
//	
//	@Id
//	@GeneratedValue(strategy= GenerationType.AUTO) 
//	private String id;
//	
//	@ManyToMany(targetEntity=TourismPackage.class)
//	private List<TourismPackage> tourPackage;
//	
//	@ManyToMany(targetEntity=User.class)
//	private List<User> user;
//}