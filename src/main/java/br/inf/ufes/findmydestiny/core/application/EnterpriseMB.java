package br.inf.ufes.findmydestiny.core.application;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.inf.ufes.findmydestiny.core.domain.Enterprise;
import br.inf.ufes.findmydestiny.core.persistence.EnterpriseDAO;

@Stateless @PermitAll
public class EnterpriseMB {

	@Inject
    private EnterpriseDAO enterpriseDAO;
    private Enterprise enterprise = new Enterprise();
     
    public String envia() {
           
          enterprise = enterpriseDAO.checkEnterprise(enterprise.getEnterpriseName(), enterprise.getPassword());
          if (enterprise == null) {
                enterprise = new Enterprise();
                FacesContext.getCurrentInstance().addMessage(
                           null,
                           new FacesMessage(FacesMessage.SEVERITY_ERROR, "Empresa não encontrado!",
                                       "Erro no Login!"));
                return null;
          } else {
                return "/index";
          }
           
           
    }
    
    public boolean insertDAO(Enterprise u) {
    	return enterpriseDAO.inserirEnterprise(u);
    	
    }

    public Enterprise getEnterprise() {
          return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
          this.enterprise = enterprise;
    }	
	
	
	
	
}
