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
    
    private String name;
    private String username;
    private String cpf;
    private String password;
    private String email;
    private String address;
    private String telephone;
	
    // TODO: we should add security acess to user data here
    public String getName() {return name;}
	public void setName(String name) {this.name = name;}
    public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
    public String getCpf() {return cpf;}
	public void setCpf(String cpf) {this.cpf = cpf;}
    public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}
    public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
    public String getTelephone() {return telephone;}
	public void setTelephone(String telephone) {this.telephone = telephone;}
    public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
    
	public ConnectionBean CreateConnection()
	{
		return Connection;
	}
	
	public void login()
	{
		Connection.open();	
		
		// TODO: login code here (maybe JAAS?) and go to hub.xhtml
		
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
	
	public Date getTime() {return new Date();}
}
