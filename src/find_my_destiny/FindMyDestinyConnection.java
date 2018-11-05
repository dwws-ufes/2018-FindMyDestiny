package find_my_destiny;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Singleton;

@ManagedBean
@SessionScoped
public final class FindMyDestinyConnection {
	private static Connection Conn = null;
	private static final String Database_ServerName = "jdbc:mysql://localhost/find_my_destiny";
	private static final String Database_User = "root";
	private static final String Database_Password = "root";
	
	public FindMyDestinyConnection()
	{
		
	}
	
	public boolean OpenConnection()
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
	
	public static boolean close()
	{
		try
		{
			Conn.close();
		}
		catch(Exception e)
		{
			return false; 
		}
		return true;
	}
	
	public void PrintConnection()
	{
		System.out.println("Connection info:");
	}
	
	public Connection getConnection() {return Conn;}
}