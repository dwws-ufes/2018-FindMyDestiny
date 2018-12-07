package br.inf.ufes.findmydestiny.core.application;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.inf.ufes.findmydestiny.core.domain.*;
import br.inf.ufes.findmydestiny.core.persistence.*;
import java.io.Serializable;

@ApplicationScoped @PermitAll
public class UserMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
    private UserDAO userDAO;
	
    private User user = new User();
     

    @Transactional
    public boolean insertDAO(User u) {
    	return userDAO.inserirUser(u);
    	
    }

    public User getUser() {
          return user;
    }

    public void setUser(User user) {
          this.user = user;
    }	
	
	
}