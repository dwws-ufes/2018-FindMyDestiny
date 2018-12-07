package br.inf.ufes.findmydestiny.filter;


import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Singleton;
import javax.servlet.http.HttpSession;
  

public class SessionContext {
     
    private static SessionContext instance;
     
    public static SessionContext getInstance(){
       if (instance == null){
           instance = new SessionContext();
       }
        
       return instance;
    }
     
    private SessionContext(){
        
    }
     
    private ExternalContext currentExternalContext(){
       if (FacesContext.getCurrentInstance() == null){
           throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
       }else{
           return FacesContext.getCurrentInstance().getExternalContext();
       }
    }
     
     
    public void encerrarSessao(){
       currentExternalContext().invalidateSession();
    }
     
    public Object getAttribute(String nome){
       return currentExternalContext().getSessionMap().get(nome);
    }
     
    public void setAttribute(String nome, Object valor){
       currentExternalContext().getSessionMap().put(nome, valor);
    }
     
}





















/*package br.inf.ufes.findmydestiny.phaselistener;

import java.io.Serializable;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;

import br.inf.ufes.findmydestiny.core.domain.Enterprise;
import br.inf.ufes.findmydestiny.core.domain.User;

//@SessionScoped @PermitAll
@WebListener
public class SessionMB implements PhaseListener {

	  *//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;
	private FacesContext facesContext;

	    @Override
	    public void afterPhase(PhaseEvent event) {
	    	System.out.println("passou aqui\n\n\n");
	        facesContext = event.getFacesContext();
	        String viewId = facesContext.getViewRoot().getViewId();

	        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
	        boolean paginaLogin = viewId.lastIndexOf("login") > -1;

	        if (existeUsuarioLogado() && paginaLogin) {
	            nh.handleNavigation(facesContext, null, "/index?faces-redirect=true");
	        } else if (!existeUsuarioLogado() && !paginaLogin) {
	            nh.handleNavigation(facesContext, null, "/login?faces-redirect=true");
	        }
	    }

	    public boolean existeUsuarioLogado() {
	        return (((User) getAtributoSessao("usuario")) != null);
	    }

	    public Object getAtributoSessao(String attributeName) {
	        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	        if (session != null) {
	            return session.getAttribute(attributeName);
	        }
	        return null;
	    }

	    @Override
	    public void beforePhase(PhaseEvent event) {
	        
	    }

	    @Override
	    public PhaseId getPhaseId() {
	        return PhaseId.RESTORE_VIEW;
	    }

}*/
