package find_my_destiny;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.enterprise.inject.Model;
import javax.inject.Singleton;

import com.mysql.cj.jdbc.MysqlDataSource;

@Singleton
public final class FindMyDestinyConnection {
	private static Connection Conn = null;
	private static final String Database_ServerName = "jdbc:mysql://localhost:3306/FindMyDestinyDB";
	private static final String Database_User = "root";
	private static final String Database_Password = "root";
	
	public FindMyDestinyConnection()
	{
		
	}
	
	public boolean OpenConnection()
	{
		try
		{	
			Conn = DriverManager.getConnection(Database_ServerName + 
					"?user="+Database_User+
					"&password="+Database_Password);
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
}
