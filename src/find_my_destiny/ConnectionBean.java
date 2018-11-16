package find_my_destiny;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public final class ConnectionBean {
	private Connection Conn;

	@Inject
        private User user;
	@Inject
	private Package tourPackage;
	@Inject
	private Login login;
	
	private static final String Database_ServerName = "jdbc:mysql://localhost:3306/find_my_destiny";
	private static final String Database_User = "root";
	private static final String Database_Password = "root";
	
	public boolean open()
	{
		try
		{	
			Class.forName("com.mysql.cj.jdbc.Driver");
			Conn = DriverManager.getConnection(Database_ServerName+
                                               "?user="+Database_User+"&password="+Database_Password+"&useTimezone=true&serverTimezone=UTC");
		}
		catch(Exception e)
		{
			System.out.println("Error: connection not opened");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean close()
	{
		try
		{
			Conn.close();
		}
		catch(Exception e)
		{
			return false; 
		}
		System.out.println("Connection closed successfully");
		return true;
	}
	
	public void insertUserIntoDatabase()
	{
		try
		{
			java.sql.Connection Conn = this.getConnection();
			Statement statement = Conn.createStatement();
            
			String name = user.getName();
			String username = user.getUsername();
			String cpf = user.getCpf();
			String address = user.getAddress();
			String password = user.getPassword();
			String telephone = user.getTelephone();
			String email = user.getEmail();
			statement.executeUpdate("INSERT INTO user (fullName, email, username, cpf, address, telephone, password) VALUES('"+name+"','"+email+"','"+username+"', '"+cpf+"','"+address+"','"+telephone+"','"+password+"');");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
    public void loginUser()
    {
        try
        {
            java.sql.Connection Conn = this.getConnection();
			Statement statement = Conn.createStatement();
            
            String username = user.getUsername();
            String password = user.getPassword();
            
            String sql = "SELECT username, id from user where username like '"+
                username+"' and password like '"+password+"'";
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while(resultSet.next())
            {
            	login.setLoginStatus(true);
            	user.setId(resultSet.getInt("id"));
            	user.setUsername(resultSet.getString("username"));
            	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            	externalContext.redirect("hub.xhtml");
            }
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
	public void PrintConnection()
	{
		System.out.println("Connection info:");
	}
	
	public void signOut()
	{
		// TODO: sign out code, like clear logged flag and session indicators of login
		
		try
		{
			login.setLoginStatus(false);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("home.xhtml");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void createPackage()
	{
		this.open();
		try
		{
			java.sql.Connection Conn = this.getConnection();
			Statement statement = Conn.createStatement();
			
			String packageName = tourPackage.getName();
			String SQL = "INSERT INTO tourism_package (package_name, user_id) VALUES('"+packageName+"', "+user.getId()+")";
			statement.executeUpdate(SQL);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		this.close();
	}
	
	public void searchPackages()
	{
		this.open();
		try
		{
			java.sql.Connection Conn = this.getConnection();
			Statement statement = Conn.createStatement();
			String sql = "SELECT package_name from tourism_package where user_id = "+login.getUser().getId();
			ResultSet resultSet = statement.executeQuery(sql);
			String packagesFromUserHtml = ""; 
			
			while(resultSet.next())
			{
				packagesFromUserHtml += "<li>"+resultSet.getString("package_name")+"</li>";
			}
			System.out.println(packagesFromUserHtml);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		this.close();
	}
	
	public Connection getConnection() {return Conn;}
}