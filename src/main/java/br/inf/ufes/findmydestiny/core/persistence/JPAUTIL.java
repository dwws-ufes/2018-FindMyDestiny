package br.inf.ufes.findmydestiny.core.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAUTIL {
	
	
	private EntityManagerFactory factory = null;
	
	public JPAUTIL() {
		if(factory == null) factory = Persistence.createEntityManagerFactory("FindMyDestiny");
	}
	
	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
