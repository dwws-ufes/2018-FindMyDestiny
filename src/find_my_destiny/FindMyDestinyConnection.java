package find_my_destiny;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.enterprise.inject.Model;

@Model
public class FindMyDestinyConnection {
	private String ConnectionName;
	private Connection Conn;
	private static final String Driver_Name = "com.mysql.jdbc.Driver";
	private static final String Database_Address = "jdbc:mysql://localhost:3306/FindMyDestinyDB";
	private static final String Database_User = "root";
	private static final String Database_Password = "root";
	
	public FindMyDestinyConnection(String Name)
	{
		ConnectionName = Name;		
	}
	
	public String ConnectToMySQLDatabase()
	{
		try
		{
			Class.forName(Driver_Name);
			Conn = DriverManager.getConnection(Database_Address, Database_User, Database_Password);
		}
		catch(Exception e)
		{
			System.out.println("Error: connction not opened");
		}
		
		return "Ok";
	}
	
	public void CloseConnection()
	{
		try
		{
			Conn.close();
		}
		catch(Exception e)
		{
			System.out.println("Error: connection can not closed successfully");
		}
	}
	
	public void PrintConnection()
	{
		System.out.println("Connection info:");
		System.out.println("Name: " + ConnectionName);
	}
}
