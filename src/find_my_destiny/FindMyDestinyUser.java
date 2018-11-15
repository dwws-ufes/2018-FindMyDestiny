package find_my_destiny;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
@PersistenceUnit(unitName="FindMyDestiny")
@Table(name="user")
public class FindMyDestinyUser {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
        @Column(name="id", nullable=false, unique=true)
        private int id;
    
    @Column(name="fullName", nullable=false, unique=false)
        private String name;
    
    @Column(name="userName", nullable=false, unique=true)
        private String username;
    
    @Column(name="password", nullable=false, unique=false)
        private String password;
    
    @Column(name="cpf", nullable=false,unique=true)
        private String cpf;
    
    @Column(name="address", nullable=false, unique=true)
        private String address;
    
    @Column(name="email", nullable=false, unique=true)
        private String email;
    
    @Column(name="telephone", nullable=false, unique=true)
        private String telephone;
    
    @Column(name="lastAccess", unique=true)
        @Temporal(TemporalType.DATE)
        private Date lastAccess;
    
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