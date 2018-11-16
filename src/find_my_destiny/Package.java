package find_my_destiny;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Singleton;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
@Singleton
public class Package {

	private String name;
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
}
