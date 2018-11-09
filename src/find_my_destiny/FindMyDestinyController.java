package find_my_destiny;

import find_my_destiny.FindMyDestinyConnection;
import find_my_destiny.FindMyDestinySearch;
import find_my_destiny.FindMyDestinyI18n;

import java.sql.Statement;
import java.util.Date;
import javax.enterprise.inject.Model;

@Model
public class FindMyDestinyController {
	private String visitor = "visitor";
	private String name = null;
	private String username = null;
    private String email = null;
	private String cpf = null;
	private String address = null;
	private String password = null;
	private String telephone = null;
	
	private FindMyDestinyConnection Connection = null; 
	
	public String getVisitor() {return visitor;}
	public String getName() {return name;}
	public String getUsername() {return username;}
	public String getCpf() {return cpf;}
	public String getAddress() {return address;}
	public String getPassword() {return password;}
	public String getTelephone() {return telephone;}
    public String getEmail() {return email;}
	
	public void setVisitor(String visitor) {this.visitor= visitor;}
	public void setName(String name) {this.name= name;}
	public void setUsername(String username) {this.username= username;}
	public void setCpf(String cpf) {this.cpf= cpf;}
	public void setAddress(String address) {this.address= address;}
	public void setPassword(String password) {this.password= password;}
	public void setTelephone(String telephone) {this.telephone= telephone;}
    public void setEmail(String email) {this.email= email;}
    
	public FindMyDestinyConnection CreateConnection()
	{
		Connection = new FindMyDestinyConnection();
		return Connection;
	}
	
	public void registerNewUser()
	{
		OpenConnection();
		
		try
		{
			java.sql.Connection Conn = Connection.getConnection();
			Statement statement = Conn.createStatement();
            
			statement.executeUpdate("INSERT INTO user (name, email, username, cpf, address, telephone, password) VALUES('"+name+"','"+email+"','"+username+"', '"+cpf+"','"+address+"','"+telephone+"','"+password+"');");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		CloseConnection();
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
