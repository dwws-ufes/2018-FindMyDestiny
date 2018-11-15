package find_my_destiny;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class User {
	
	private String name;
	private String username;
	private String email;
	private String cpf;
	private String address;
	private String telephone;
	private String password;
	
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}
    public String getCpf(){return cpf;}
    public void setCpf(String cpf){this.cpf = cpf;}
    public String getAddress(){return address;}
    public void setAddress(String address){this.address = address;}
    public String getTelephone(){return telephone;}
    public void setTelephone(String telephone){this.telephone = telephone;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
}
