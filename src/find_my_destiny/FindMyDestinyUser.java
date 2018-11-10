package find_my_destiny;
  
import java.util.Date;
  
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
  
@Entity
public class FindMyDestinyUser {
       
      @Id
      @Column(name="id", nullable=false, unique=true)
      private int id;
      
      @Column(name="fullName", nullable=false, unique=true)
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
       
    
 }