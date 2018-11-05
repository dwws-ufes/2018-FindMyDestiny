package find_my_destiny;

import find_my_destiny.FindMyDestinyConnection;
import java.util.Date;
import javax.enterprise.inject.Model;

@Model
public class FindMyDestinyController {
	private String visitor = "visitor";
	private FindMyDestinyConnection Connection = null; 
	
	public String getVisitor() {return visitor;}
	
	public void setVisitor(String visitor) {this.visitor= visitor;}
	
	public FindMyDestinyConnection CreateConnection()
	{
		Connection = new FindMyDestinyConnection();
		return Connection;
	}
	
	public void registerNewUser(String name, String username)
	{
		
	}
	
	public void OpenConnection()
	{
		if (Connection == null)
		{
			Connection = new FindMyDestinyConnection();
			System.out.println("Created new connection.");
		}
		Connection.OpenConnection();
	}
	
	public void CloseConnection()
	{
		if (FindMyDestinyConnection.close())
		{
			System.out.println("Connection closed successfully.");
		}
		else
		{
			System.out.println("Attempt to close connection, but connection does not exists.");
		}
	}
	
	public Date getTime() {return new Date();}
}
