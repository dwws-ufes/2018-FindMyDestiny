package find_my_destiny;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Singleton;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
@Singleton
public class Login {
	private boolean loginStatus;
	private Date loginTime;
	
	@Inject
	private User user;
	
	public User getUser() {return user;}
	public void setUser(User user) {this.user = user;}
	
	public boolean getLoginStatus() {return loginStatus;}
	public void setLoginStatus(boolean loginStatus) {this.loginStatus = loginStatus;}
	
	public Date getLoginTime() {return loginTime;}
	public void setLoginTime(Date loginTime) {this.loginTime = loginTime;}
}
