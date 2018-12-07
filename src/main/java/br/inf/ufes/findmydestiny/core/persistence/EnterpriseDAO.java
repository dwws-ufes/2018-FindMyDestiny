package br.inf.ufes.findmydestiny.core.persistence;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import br.inf.ufes.findmydestiny.core.domain.Enterprise;

@Stateless
public class EnterpriseDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName="FindMyDestiny")
	private EntityManager em;

public Enterprise checkEnterprise(String nomeEnterprise, String senha) {

      try {
            Enterprise enterprise = (Enterprise) em
                       .createQuery(
                                   "SELECT u from Enterprise u where u.enterpriseName = :name and u.password = :senha")
                       .setParameter("name", nomeEnterprise)
                       .setParameter("senha", senha).getSingleResult();

            return enterprise;
      } catch (NoResultException e) {
            return null;
      }
}

public boolean inserirEnterprise(Enterprise enterprise) {
      try {
            em.persist(enterprise);        
            return true;
      } catch (Exception e) {
            e.printStackTrace();
            return false;
      }
}
 
public boolean deletarEnterprise(Enterprise enterprise) {
      try {
            em.remove(enterprise);
            return true;
      } catch (Exception e) {
            e.printStackTrace();
            return false;
      }
}	
		
	
	
}
