package find_my_destiny;

import java.util.Date;
import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Model
public class FindMyDestinyController {
    @Inject
        private ConnectionBean Connection;
    @Inject
        private User user;
    @Inject
    private Package tourPackage;
    @Inject
    private Login login;
    
    public User getUser() {return user;}
    	
	public ConnectionBean CreateConnection()
	{
		return Connection;
	}
	
	public void login()
	{
		Connection.open();	
		// TODO: login code here (maybe JAAS?) and go to hub.xhtml
        Connection.loginUser();
		Connection.close();
	}
	
	public void createUser()
	{
		Connection.open();
		Connection.insertUserIntoDatabase();
		Connection.close();
	}
	
	public void updateUser()
	{
		Connection.open();
		
		// TODO: user update information code here!
		
		Connection.close();
	}
	
	public void readUser()
	{
		Connection.open();
		
		// TODO: user read information code here!
		
		Connection.close();
	}
	
	public void deleteUser()
	{
		Connection.open();
		
		// TODO: user deletion code here!
		
		Connection.close();
	}
	
	public void signOut()
	{
		Connection.signOut();
	}
	
	public void newPackage()
	{
		Connection.createPackage();
		updatePackagesList();
	}
	
	public void updatePackagesList()
	{
		Connection.searchPackages();
	}
	
	public void init(String page)
	{
		String viewId = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath(); 
		if(!login.getLoginStatus() && viewId.equals("/hub.xhtml"))
		{
			try
			{
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("home.xhtml");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public Package getTourPackage() {return tourPackage;}
	public void setTourPackage(Package tourPackage) {this.tourPackage= tourPackage;}
	
	public Date getTime() {return new Date();}
}
