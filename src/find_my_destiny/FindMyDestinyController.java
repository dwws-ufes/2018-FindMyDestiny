package find_my_destiny;

import java.util.Date;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class FindMyDestinyController {
    @Inject
        private ConnectionBean Connection;
    @Inject
        private User user;
    
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
	}
	
	private String packageName;
	private boolean loginAuthorized;
	
	public String getPackageName() {return packageName;}
	public void setPackageName(String packageName) {this.packageName = packageName;}
	
	public void setLoginAuthorized(boolean loginAuthorized)
	{
		this.loginAuthorized = loginAuthorized;
	}
	
	public Date getTime() {return new Date();}
}
