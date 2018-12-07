package br.inf.ufes.findmydestiny.core.persistence;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.inf.ufes.findmydestiny.core.domain.*;
import br.inf.ufes.findmydestiny.core.domain.Package;

@ApplicationScoped
public class UserDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="FindMyDestiny")
	private EntityManager em;
	
	

	public User checkUser(String nomeUser, String senha) {
	
	      try {
	            User user = (User) em
	                       .createQuery(
	                                   "SELECT u from User u where u.username = :name and u.password = :senha")
	                       .setParameter("name", nomeUser)
	                       .setParameter("senha", senha).getSingleResult();
	
	            return user;
	      } catch (NoResultException e) {
	            return null;
	      }
	}
	
	public User searchById(Long id) {
		try {
			User user = (User) em
                    .createQuery(
                                "SELECT u from User u where u.userId = :id2 ")
                    .setParameter("id2", id).getSingleResult();                    
         return user;
		} catch(NoResultException e) {
			return null;
		}
	}
	
	public boolean inserirUser(User user) {
	      try {
	            em.persist(user); 
	            return true;
	      } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	      }
	}
	 
	public boolean deletarUser(User user) {
	      try {
	            em.remove(user);
	            return true;
	      } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	      }
	}	
	
	


	
}