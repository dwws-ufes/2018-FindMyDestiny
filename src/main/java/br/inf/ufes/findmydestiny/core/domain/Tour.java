package br.inf.ufes.findmydestiny.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tour_places")
public class Tour {
	@Column(name="USER_ID", nullable=false, unique=false)
	private String userId;
	
	@Id @GeneratedValue
	@Column(name="TOUR_ID", nullable=false, unique=true)
	private String tourId;
	
	@Column(name="TOUR_NAME", nullable=false, unique=false)
	private String tourName;
	
	@Column(name="PLACE_ID", nullable=false, unique=false)
	private String placeId;
	
	@Column(name="PLACE_NAME", nullable=false, unique=false)
	private String placeName;
}