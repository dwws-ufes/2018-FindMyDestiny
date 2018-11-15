package find_my_destiny;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tourism_package")
public class TourismPackage {

	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Id
	@Column(name="id", nullable=false, unique=true)
	private int packageId;

	@Column(name="package_name", nullable=false, unique=false)
	private String packageName;

	@OneToOne
	// TODO: if sytems accepts multiple user per package in the future, 'unique' value must turn to be true
	private FindMyDestinyUser user;
	
	@ManyToMany(targetEntity=Destination.class)
	private List<Destination> destinations;
}
