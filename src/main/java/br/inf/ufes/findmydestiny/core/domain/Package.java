package br.inf.ufes.findmydestiny.core.domain;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.inject.Singleton;

/*@SuppressWarnings("deprecation")
@ManagedBean*/
@ApplicationScoped
public class Package implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
}
