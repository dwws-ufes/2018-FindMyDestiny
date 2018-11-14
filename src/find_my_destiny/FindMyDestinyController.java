package find_my_destiny;

import java.sql.Statement;
import java.util.Date;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

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
	
    private String htmlListOfPlaces = "";
    
    @Inject
	private ConnectionBean Connection; 
	
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
    
	public ConnectionBean CreateConnection()
	{
		Connection = new ConnectionBean();
		return Connection;
	}
	
	public void login()
	{
		Connection.open();	
		
		// TODO: login code here (maybe JAAS?)
		
		Connection.close();
	}
	
	public void createUser()
	{
		Connection.open();
		
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
	
	public Date getTime() {return new Date();}
}
