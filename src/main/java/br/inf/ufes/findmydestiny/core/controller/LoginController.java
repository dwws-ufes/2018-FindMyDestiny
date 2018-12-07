package br.inf.ufes.findmydestiny.core.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.inf.ufes.findmydestiny.core.application.LoginMB;
import br.inf.ufes.findmydestiny.core.domain.TourismPackage;
import br.inf.ufes.findmydestiny.core.domain.User;
import br.inf.ufes.findmydestiny.core.persistence.TourismPackageDAO;
import br.inf.ufes.findmydestiny.filter.*;

@Named @SessionScoped 
public class LoginController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	@Inject
	@Inject
	private LoginMB loginmb;
	
	@Inject
	private TourismPackageDAO tpDAO;
	
	private String username;
	private String password;
	private boolean isEnterprise = false;
	private String tourPackagename;
	private Date startDate;
	private Date endDate;
	
	
	






	public LoginController( ) {}
	


	public void init(String page)
	{
		String viewId = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath(); 
		if(!loginmb.getLoginStatus() && viewId.equals("/hub.xhtml"))
		{
			try
			{
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("home.xhtml");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		if (viewId.equals("/hub.xhtml"))
		{
			loginmb.updateUserPackagesList();
		}
	}	
	
	public String login() {		
		String result = loginmb.envia(username, password,isEnterprise);
		//if(loginmb.getLoginStatus())
			username = loginmb.getUser().getUsername();
		return result;
	}
	
	public String logoff() {
		username = null;
		//return loginmb.logOff();
		
		FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        
        return fc.getExternalContext().getRequestContextPath() +  "/home.xhtml?faces-redirect=true";
	}
	
	
	public String newPackage() {
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		String sd = df.format(this.startDate);
		String ed = df.format(endDate);
		String result = loginmb.createTourismPackage(tourPackagename,sd,ed);		
		return result;
	}
	
	public void deletePackage(int id) {
		loginmb.delPackage(id);
		
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent component = (UIComponent)viewRoot.findComponent("form_"+id);
        //component.setParent(null);
        component.getParent().setRendered(false);
        component.getParent().clearInitialState();
        
        loginmb.updateUserPackagesList();
	}
	
	public String goHome() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		return externalContext.getRequestContextPath() + "/home.xhtml";
	}
	
	public String goHub() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		return externalContext.getRequestContextPath() + "/hub.xhtml";
	}
	
	public void addPlace() {
		try
		{
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("add_place.xhtml");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public boolean checkLoggedIn() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		//String checksession = FacesContext.getCurrentInstance().getExternalContext().getSessionId(false);
		User u = (User)session.getAttribute("usuario");
		if(loginmb.getLoginStatus()) {
			System.out.println("true----------");
			return true;
		}else {
			System.out.println("false----------");
			return false;
		}
//		return loginmb.getLoginStatus();
	}

	public List<TourismPackage> getTps(){
		return  tpDAO.searchPackages(loginmb.getUser().getUserId());
		
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
	public boolean isEnterprise() {
		return isEnterprise;
	}

	public void setEnterprise(boolean isEnterprise) {
		this.isEnterprise = isEnterprise;
	}
	

	public String getTourPackagename() {
		return tourPackagename;
	}

	public void setTourPackagename(String tourPackagename) {
		this.tourPackagename = tourPackagename;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
