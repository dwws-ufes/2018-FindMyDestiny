package br.inf.ufes.findmydestiny.core.view;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class FindMyDestinyI18n implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Language = "pt_BR";
	private ResourceBundle resourceBundle;
	private Locale locale;
	
	public FindMyDestinyI18n() {} 
	
	public String getI18nMessage(String message)
	{
		if (resourceBundle == null)
		{
			if (Language.equals("pt_BR"))
			{
				locale = new Locale("pt", "BR");
			}
			else if (Language.equals("en_US"))
			{
				locale = new Locale("en", "US");
			}
			else
			{
				// Standard locale pt_BR
				locale = new Locale("pt", "BR");
			}
			resourceBundle = ResourceBundle.getBundle("messages", locale);
		}
		
		return resourceBundle.getString(message);
	}
}
