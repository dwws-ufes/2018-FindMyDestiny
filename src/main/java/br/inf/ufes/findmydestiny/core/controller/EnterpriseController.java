package br.inf.ufes.findmydestiny.core.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.inf.ufes.findmydestiny.core.application.EnterpriseMB;
import br.inf.ufes.findmydestiny.core.domain.Enterprise;

@Named @RequestScoped
public class EnterpriseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EnterpriseMB enterprisemb;

	//private Enterprise enterprise = enterprisemb.getEnterprise();
	private Enterprise enterprise = new Enterprise();

	
	public void createEnterprise() {		
		enterprisemb.insertDAO(enterprise);	
		this.clearFields();
	}
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	
	public EnterpriseController() {
		
	}	
	
	public void clearFields() {
		enterprise.setAddress(null);
		enterprise.setCnpj(null);
		enterprise.setEmail(null);
		enterprise.setEnterpriseFullname(null);		
		enterprise.setEnterpriseName(null);
		enterprise.setMainTel(null);
		enterprise.setManagerName(null);
		enterprise.setPassword(null);
		enterprise.setSecondaryTel(null);
		
	}

}
