package br.inf.ufes.findmydestiny.core.domain;



import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	  public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}	
	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}	
	
	
	@Column(name="id",nullable=false, unique=true)
	@Id
	@GeneratedValue
	private long userId;
	  


	@Column(name="fullName", nullable=false, unique=false, length=80)
	  private String name;
	   
	  @Column(name="userName", nullable=false, unique=true, length=80)
	  private String username;
	   
	  @Column(name="password", nullable=false, length=80)
	  private String password;
	  
	  @Column(name="cpf", nullable=false,unique=true, length=11)
	  private String cpf;
	  
	  @Column(name="address", nullable=true, unique=false, length=80)
	  private String address;
	  
	  @Column(name="email", nullable=false, unique=true, length=80)
	  private String email;
	  
	  @Column(name="telephone", nullable=false, unique=false, length=13)
	  private String telephone;
	
	  @Column(name="cellphone", nullable=false, unique=false, length=13)
	  private String cellphone;

	
}

