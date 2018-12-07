package br.inf.ufes.findmydestiny.core.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.inf.ufes.findmydestiny.core.domain.User;
import br.inf.ufes.findmydestiny.core.application.*;

@Named @SessionScoped
public class UserController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UserMB usermb;

	//private User user = usermb.getUser();
	private User user = new User();

	
	public String createUser() {
		if(usermb.insertDAO(user)) {
			this.clearFields();
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			return externalContext.getRequestContextPath() + "/login.xhtml";
		}else {
			FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível cadastrar o usuário!",
                                "Verifique se todos os campos estão preenchidos"));
         return null;
		}
		
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public UserController() {
		
	}
	
	public void clearFields() {
		user.setAddress(null);
		user.setCpf(null);
		user.setEmail(null);
		user.setName(null);		
		user.setUsername(null);
		user.setTelephone(null);		
		user.setPassword(null);
		user.setCellphone(null);
		
	}	
}
