package find_my_destiny;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public final class ConnectionBean {
	private static Connection Conn;
	
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
	
	public void PrintConnection()
	{
		System.out.println("Connection info:");
	}
	
	public Connection getConnection() {return Conn;}
}