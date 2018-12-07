package br.inf.ufes.findmydestiny.core.application;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.inject.Inject;
import javax.persistence.Tuple;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.primefaces.component.commandbutton.CommandButton;

import br.inf.ufes.findmydestiny.core.domain.*;
import br.inf.ufes.findmydestiny.core.domain.Package;
import br.inf.ufes.findmydestiny.core.persistence.EnterpriseDAO;
import br.inf.ufes.findmydestiny.core.persistence.TourismPackageDAO;
import br.inf.ufes.findmydestiny.core.persistence.UserDAO;

@SuppressWarnings("deprecation")
@ApplicationScoped @PermitAll 
public class LoginMB  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean loginStatus = false;
	private Date loginTime;
	private User user;
	private Enterprise enterprise;
	private String packagesFromUserHtml;
	
	@Inject
	private Package tourPackage;
	@Inject
	private UserDAO userDAO;
	@Inject
	private EnterpriseDAO enterpriseDAO;
	@Inject
	private TourismPackageDAO tpDAO;
	
	
    public String envia(String username, String password, boolean isEnterprise) {
    	
    	if (isEnterprise) {
    		enterprise = enterpriseDAO.checkEnterprise(username,password);
    		if (enterprise != null) {
    			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                if (session != null) {
                    session.setAttribute("enterprise", enterprise);
                }
    			loginStatus = true;
    			
        		return "/hub_enterprise.xhtml?faces-redirect=true";
        	}              
              FacesContext.getCurrentInstance().addMessage(
                         null,
                         new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
                                     "Erro no Login!"));
              return null;
    	}else {
    		user = userDAO.checkUser(username,password);
    		if (user == null) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
                                    "Erro no Login!"));
             return null;
    		}else {
    			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                if (session != null) {
                    session.setAttribute("usuario", user);
                }
                loginStatus = true;
                return "/hub.xhtml?faces-redirect=true";
    		}
    	}
        
   
    }
    
    @Transactional
    public String createTourismPackage(String tourPackagename, String startDate, String endDate) {    	
    	String tp = tourPackagename;
    	tpDAO.insertPackage(tp, user.getUserId(),startDate,endDate);
    	return updateUserPackagesList();
    }
    @Transactional
    public String updateUserPackagesList()
	{	
    	try {
    		
    		List<TourismPackage> tplist = tpDAO.searchPackages(user.getUserId());
    		
    		
    		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
            HtmlPanelGroup component = (HtmlPanelGroup)viewRoot.findComponent("tours");
            component.setLayout("block");
            
            System.out.println(component.isRendered());
            
            packagesFromUserHtml = "<ul>"; 
    		
            if(tplist.size() > 0) {
	    		for(TourismPackage t: tplist) {
	    			
	    			// NOTE: Right now we are passing the id of package as the id of form
	                //String packageName = t.get(0).toString();	                
	                //int id = (Integer)t.get(1);
	    			String packageName = t.getPackageName();
	    			int id = (Integer)t.getPackageId();
	                
	                if (component.findComponent("form_"+id) == null)
	                {
	                    HtmlPanelGroup panelGroup = new HtmlPanelGroup();
	                    panelGroup.setLayout("block");
	                    panelGroup.setStyleClass("add_to_list");
	                    
	                    HtmlForm form = new HtmlForm();
	                    form.setId("form_"+id);
	                    
	                    HtmlOutputText outputText = new HtmlOutputText();
	                    outputText.setValue(packageName);
	                    outputText.setStyle("size:16px");
	                    outputText.setEscape(false);
	                    
	                    HtmlPanelGroup buttonGroup = new HtmlPanelGroup();
	                    buttonGroup.setLayout("block");
	                    
	                    MethodBinding methodBinding = FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{loginController.deletePackage("+id+")}", 
	                                                                                                                         new Class[] {int.class});
	                    
	                   
	                    /*
	                    ExpressionFactory expressionFactory = ExpressionFactory.newInstance();
	                    MethodExpression methodExpression = expressionFactory.createMethodExpression(FacesContext.getCurrentInstance().getELContext(), 
	                                                                                                 "#{connectionBean.deletePackage("+id+")}",
	                                                                                                 String.class,
	                                                                                                 new Class<?>[] { int.class });
	                    */
	                    
	                    CommandButton deleteButton = new CommandButton();
	                    deleteButton.setStyleClass("btn button_delete");
	                    deleteButton.setValue("deletar");
	                    deleteButton.setType("submit");
	                    deleteButton.setIcon("fa fa-minus");
	                    deleteButton.setStyle("padding:5px 10px; margin: 0px 10px 0px 50px;");
	                    deleteButton.setActionListener(methodBinding);
	                    deleteButton.setUpdate("tours");
	                    
	                    CommandButton expandButton = new CommandButton();
	                    expandButton.setStyleClass("btn button_expand");
	                    expandButton.setValue("Detalhes");
	                    expandButton.setType("submit");
	                    expandButton.setIcon("fa fa-expand");
	                    expandButton.setStyle("padding:5px 10px; margin: 0px 10px 0px 0px;");
	                    
	                    CommandButton sendButton = new CommandButton();
	                    sendButton.setStyleClass("btn button_send");
	                    sendButton.setValue("Enviar");
	                    sendButton.setType("submit");
	                    sendButton.setIcon("fa fa-send");
	                    sendButton.setStyle("padding:5px 10px; margin: 0px 10px 0px 0px;");
	                    
	                    component.getChildren().add(panelGroup);
	                    panelGroup.getChildren().add(form);
	                    form.getChildren().add(outputText);
	                    form.getChildren().add(buttonGroup);
	                    buttonGroup.getChildren().add(deleteButton);
	                    buttonGroup.getChildren().add(expandButton);
	                    buttonGroup.getChildren().add(sendButton);
	                }
	    		}
            }
    		 packagesFromUserHtml += "</ul>";
    	}catch (IndexOutOfBoundsException e) {
    		System.out.println("deu ruim");
    		return null;
    	}
		
		 
		 FacesContext context = FacesContext.getCurrentInstance();
	        
		 return context.getApplication().evaluateExpressionGet(context, packagesFromUserHtml, String.class);
	}
	
    
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean getLoginStatus() {return loginStatus;}
	public void setLoginStatus(boolean loginStatus) {this.loginStatus = loginStatus;}
	
	public Date getLoginTime() {return loginTime;}
	public void setLoginTime(Date loginTime) {this.loginTime = loginTime;}
	
	public String logOff() {
		loginStatus = false;
		FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        try {
        	fc.getExternalContext().redirect("home.xhtml");
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        return "/home.xhtml?faces-redirect=true";
	}
	

	
	public void delPackage(int id) {
		tpDAO.deletePackage(id);
	}
	
}
